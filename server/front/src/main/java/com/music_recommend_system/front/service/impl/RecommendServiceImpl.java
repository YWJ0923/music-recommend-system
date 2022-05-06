package com.music_recommend_system.front.service.impl;

import com.music_recommend_system.front.VO.MusicVO;
import com.music_recommend_system.front.dao.*;
import com.music_recommend_system.front.entity.Recommended;
import com.music_recommend_system.front.entity.Score;
import com.music_recommend_system.front.entity.UserTag;
import com.music_recommend_system.front.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Service
public class RecommendServiceImpl implements RecommendService {
    @Autowired
    UserTagMapper userTagMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RecommendedMapper recommendedMapper;
    @Autowired
    MusicMapper musicMapper;
    @Autowired
    ScoreMapper scoreMapper;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<MusicVO> listRecommendMusics(Integer userId) {
        List<MusicVO> musicVOList = (List<MusicVO>) redisTemplate.opsForValue().get("recommend:" + userId);
        if (musicVOList == null) {
            int userCount = userMapper.countUser();
            if (userCount > 100) {
                List<Score> scoreList = scoreMapper.listAllScores();
                musicVOList = collaborativeFiltering(scoreList, userId);
            } else {
                List<UserTag> tagList = userTagMapper.listUserTags(userId);
                if (tagList.size() == 0) {
                    musicVOList = listHotMusics(userId);
                } else {
                    musicVOList = listMusicsByTags(userId, tagList);
                }
            }
            redisTemplate.opsForValue().set("recommend:" + userId, musicVOList, 1, TimeUnit.DAYS);
            recommendedMapper.insertRecommendedMusics(musicVOList, userId);
        }
        return musicVOList;
    }

    /**
     * 用户没有选择标签，则推荐10首热门歌曲
     * @param userId
     * @return
     */
    private List<MusicVO> listHotMusics(Integer userId) {
        List<Recommended> recommendList = recommendedMapper.listUserRecommendedMusics(userId);
        Set<String> musicIdSet = new HashSet<>();
        for (Recommended recommended : recommendList) {
            musicIdSet.add(recommended.getMusicId());
        }
        List<MusicVO> musicVOList = new ArrayList<>();
        while (musicVOList.size() < 10) {
            List<MusicVO> tmp = musicMapper.getTopList100().stream().filter(musicVO -> !musicIdSet.contains(musicVO.getMusicId())).collect(Collectors.toList());
            musicVOList.addAll(tmp);
        }
        List<MusicVO> ans = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ans.add(musicVOList.get(i));
        }
        return ans;
    }

    private List<MusicVO> listMusicsByTags(Integer userId, List<UserTag> tagList) {
        return null;
    }

    private List<MusicVO> collaborativeFiltering(List<Score> list, Integer userId) {
        Map<Integer, List<Score>> userMap = list.stream().collect(Collectors.groupingBy(Score::getUserId));
        double p = 0.0;
        Integer neighborId = null;
        for (Integer id : userMap.keySet()) {
            if (id.intValue() != userId.intValue()) {
                double tmp = pearson(userMap.get(id), userMap.get(userId));
                if (tmp > p) {
                    p = tmp;
                    neighborId = id;
                }
            }
        }
        return scoreMapper.listMusicsByUserId(neighborId);
    }

    /**
     * 计算两个序列间的皮尔逊相关系数
     * @param list1
     * @param list2
     * @return
     */
    private double pearson(List<Score> list1, List<Score> list2) {
        // 用户x，y各自对这些音乐的平均得分
        double average1, average2;
        double count1 = 0, count2 = 0;
        Set<String> set = new HashSet<>();
        Map<String, Score> map1 = new HashMap<>();
        Map<String, Score> map2 = new HashMap<>();
        for (Score score : list1) {
            count1 += score.getScore();
            set.add(score.getMusicId());
            map1.put(score.getMusicId(), score);
        }
        for (Score score : list2) {
            count2 += score.getScore();
            set.add(score.getMusicId());
            map2.put(score.getMusicId(), score);
        }
        average1 = count1 / set.size();
        average2 = count2 / set.size();
        double sum1 = 0, sum2 = 0, sum3 = 0;
        for (String s : set) {
            sum1 += (map1.getOrDefault(s, new Score()).getScore() - average1) * (map2.getOrDefault(s, new Score()).getScore() - average2);
            sum2 += Math.pow(map1.getOrDefault(s, new Score()).getScore() - average1, 2);
            sum3 += Math.pow(map2.getOrDefault(s, new Score()).getScore() - average2, 2);
        }
        return Math.abs(sum1 / (Math.sqrt(sum2) * Math.sqrt(sum3)));
    }
}
