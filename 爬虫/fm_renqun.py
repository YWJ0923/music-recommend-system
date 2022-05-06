import time
import json
import re

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions
from selenium.webdriver.chrome.options import Options

import pymysql

# 初始化driver
options = Options()
options.page_load_stategy = 'eager'
path = 'chromedriver.exe'
driver = webdriver.Chrome(executable_path=path, options=options)
driver.maximize_window()
# 电台url
fm_url = 'https://www.kugou.com/fmweb/html/index.html'
# api的url
api_base_url = 'https://wwwapi.kugou.com/yy/index.php?r=play/getdata&mid=1&'
driver.get(fm_url)
# 连接数据库
connection = pymysql.connect(host='47.102.130.18', user='root', password='1234', database='music_recommend', charset='utf8', port=3306)
cursor = connection.cursor()
# 找到人群电台，点击
feng_ge_fm = driver.find_element(by=By.XPATH, value="//div[@id='radioList']/dl/dd/a[contains(text(),'人群电台')]")
feng_ge_fm.click()
# 获取所有该类型电台
fm_list = driver.find_elements(by=By.XPATH, value="//dl[@id='rightRadioList']/dd")

for i in range(3, 4):
    fm = fm_list[i]
    # 标签id，名字
    tag_id = int(fm.get_attribute('id'))
    tag_name = str(fm.find_element(by=By.XPATH, value="./a/em").text)
    try:
        # 标签插入数据库
        tag_sql = 'insert into tag (tag_id,tag_name)values(%s,%s)'
        cursor.execute(query=tag_sql, args=[tag_id, tag_name])
        connection.commit()
    except Exception:
        pass
    # 点击
    fm_click = fm.find_element(by=By.XPATH, value="./a")
    fm_click.click()
    # 切换窗口，等待网址变换
    driver.switch_to.window(driver.window_handles[1])
    WebDriverWait(driver=driver, timeout=2, poll_frequency=0.2).until(expected_conditions.url_contains('hash'))
    time.sleep(0.5)
    # 一个电台爬100首歌
    for j in range(100):
        try:
            # 歌名，歌url
            music_name = str(driver.find_element(by=By.XPATH, value="//span[@class='audioName']").text)
            # 歌手名
            artist_name = str(driver.find_element(by=By.XPATH, value="//p[@class='singerName fl']/a[1]").text)
            album_name = str(driver.find_element(by=By.XPATH, value="//p[@class='albumName fl']/a").text)
        except Exception:
            next = driver.find_element(by=By.XPATH, value="//div[@class='controls-left']/a[3]")
            next.click()
            time.sleep(0.5)
            continue
        # music_url = str(driver.find_element(by=By.XPATH, value="//audio[@id='myAudio']").get_attribute('src'))
        # music_url = None
        # 获取歌的hash
        curr_url = driver.current_url
        hash = str(re.search(r'(?<=(hash=))([0-9a-zA-Z]+)', curr_url).group(0))
        music_id = hash
        # 歌手详情链接，点击
        artist_click = driver.find_element(by=By.XPATH, value="//p[@class='singerName fl']/a[1]")
        artist_click.click()
        driver.switch_to.window(driver.window_handles[2])
        time.sleep(1)
        # 歌手id,名字，图片，介绍
        curr_url = str(driver.current_url)
        artist_id = int(re.search(r'(?<=/)([0-9]+)(?=\.html)', curr_url).group(0))
        try:
            artist_img = str(driver.find_element(by=By.XPATH, value="//div[@class='top']/img").get_attribute('src'))
            artist_description = str(driver.find_element(by=By.XPATH, value="//div[@class='intro']/p").text)
        except Exception:
            artist_img = None
            artist_description = None
        driver.close()
        driver.switch_to.window(driver.window_handles[1])
        # 专辑详情链接，点击
        try:
            album_click = driver.find_element(by=By.XPATH, value="//p[@class='albumName fl']/a")
            album_click.click()
        except Exception:
            next = driver.find_element(by=By.XPATH, value="//div[@class='controls-left']/a[3]")
            next.click()
            time.sleep(1)
            continue
        driver.switch_to.window(driver.window_handles[2])
        time.sleep(1)
        # 专辑id，名字，图片，介绍，发行时间
        curr_url = str(driver.current_url)
        album_id = int(re.search(r'(?<=/)([0-9]+)(?=\.html)', curr_url).group(0))
        try:
            album_img = str(
                driver.find_element(by=By.XPATH, value="//div[@class='pic']/img").get_attribute('src'))
            album_description = \
                str(driver.find_element(by=By.XPATH,
                                        value="//div[@class='jspContainer']/div/p").text).split('：')[1]
            album_publish_time = \
                str(driver.find_element(by=By.XPATH, value="//p[@class='detail']").text).split('：')[3]
        except Exception:
            album_img = None
            album_description = None
            album_publish_time = '2021-01-01'
        # 歌词
        api_url = api_base_url + '&hash=' + hash + '&album_id=' + str(album_id)
        driver.get(api_url)
        time.sleep(0.5)
        text = json.loads(driver.find_element(by=By.XPATH, value="//body").text)
        # music_id = text.get('data').get('audio_id')
        lyric = text.get('data').get('lyrics')
        time_length = text.get('data').get('timelength')
        driver.close()
        driver.switch_to.window(driver.window_handles[1])
        # 插入音乐
        try:
            music_sql = 'insert into music (music_id,music_name,artist_id,album_id,lyric,time_length)values(%s,%s,%s,%s,%s,%s)'
            cursor.execute(query=music_sql,
                           args=[music_id, music_name, artist_id, album_id, lyric, time_length])
            connection.commit()
        except Exception:
            pass
        # 插入歌标签
        try:
            music_tag_sql = 'insert into music_tag (music_id,tag_id)values(%s,%s)'
            cursor.execute(query=music_tag_sql, args=[music_id, tag_id])
            connection.commit()
        except Exception:
            pass
        # 插入歌手
        try:
            artist_sql = 'insert into artist (artist_id,artist_name,artist_img,artist_description)values(%s,%s,%s,%s)'
            cursor.execute(query=artist_sql, args=[artist_id, artist_name, artist_img, artist_description])
            connection.commit()
        except Exception:
            pass
        # 插入专辑
        if album_id is not None:
            try:
                album_sql = 'insert into album (album_id,album_name,artist_id,album_img,album_description,album_publish_time)values(%s,%s,%s,%s,%s,%s)'
                cursor.execute(query=album_sql,
                               args=[album_id, album_name, artist_id, album_img, album_description,
                                     album_publish_time])
                connection.commit()
            except Exception:
                pass
        # 下一首
        next = driver.find_element(by=By.XPATH, value="//div[@class='controls-left']/a[3]")
        next.click()
        time.sleep(1)
    driver.close()
    driver.switch_to.window(driver.window_handles[0])


cursor.close()
connection.close()


