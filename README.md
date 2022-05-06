# 718音乐

前端React+Antd，后端SpringBoot，数据库Mysql+Redis

启动方式：
1. 导入sql文件
2. 进入server/front文件夹，启动后端
3. 进入client文件夹，npm start，启动前端

**数据库，Redis的用户名、密码、ip地址、端口号自行修改**


admin文件夹和server/back文件夹直接无视，本来还想做一个可视化后台管理，懒得做了

cookie也没有用，我充了个vip，本来是为了能听付费歌曲，因为放歌是从酷狗获取链接。但是发现cookie里有类似于时间戳的字段，需要一直修改cookie才行，就放弃了。目前付费歌曲只能听1分钟，其余都可以正常听。

## 推荐算法
### 基于标签推荐
用户注册完会弹出小框，选择喜爱的歌曲类型，之后会根据选择的标签来推荐，如果没选就推荐当前播放量高的歌曲
### 协同过滤算法
当拥有一定的用户基数之后，使用协同过滤算法。用户播放过音乐后会对它有个评分，收藏的评分最高，否则按用户听的时长占比打分。推荐时，把所有用户的评分记录拉出来，计算当前用户与其他用户的皮尔逊系数，从而得到与当前用户最相似的用户。然后将那个用户评分高的歌推荐给当前用户。

## 项目截图
![](https://gitee.com/ywj0923/music-recommend-system/raw/master/images/718%E6%88%AA%E5%9B%BE1.png)
![](https://gitee.com/ywj0923/music-recommend-system/raw/master/images/718%E6%88%AA%E5%9B%BE2.png)
![](https://gitee.com/ywj0923/music-recommend-system/raw/master/images/718%E6%88%AA%E5%9B%BE3.png)
