# @author ywj
# @date 2021/11/13

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

music_url = 'https://www.kugou.com/song/#hash=FA861E2D6243DC0A379E060075785544&album_id=1584398'
# api的url
api_base_url = 'https://wwwapi.kugou.com/yy/index.php?r=play/getdata&mid=1&'
# 连接数据库
connection = pymysql.connect(host='47.102.130.18', user='root', password='1234', database='music_recommend', charset='utf8', port=3306)
cursor = connection.cursor()
# 标签id
tag_id1 = 18
tag_id2 = 63
# tag_id3 = 25
artist_location = 0
artist_type = 1

# 设置i，j，同时设置tag_id, gedan_url

driver.get(music_url)
time.sleep(0.5)
try:
    # 歌名，歌url
    music_name = str(driver.find_element(by=By.XPATH, value="//span[@class='audioName']").text)
    # 歌手名
    artist_name = str(driver.find_element(by=By.XPATH, value="//p[@class='singerName fl']/a[1]").text)
    album_name = str(driver.find_element(by=By.XPATH, value="//p[@class='albumName fl']/a").text)
except Exception:
    exit(-1)
# 获取歌的hash
curr_url = driver.current_url
hash = str(re.search(r'(?<=(hash=))([0-9a-zA-Z]+)', curr_url).group(0))
music_id = hash
# 歌手详情链接，点击
artist_click = driver.find_element(by=By.XPATH, value="//p[@class='singerName fl']/a[1]")
artist_click.click()
driver.switch_to.window(driver.window_handles[1])
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
driver.switch_to.window(driver.window_handles[0])
# 专辑详情链接，点击
try:
    album_click = driver.find_element(by=By.XPATH, value="//p[@class='albumName fl']/a")
    album_click.click()
except Exception:
    exit(-1)
driver.switch_to.window(driver.window_handles[1])
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
lyric = text.get('data').get('lyrics')
time_length = text.get('data').get('timelength')
driver.close()
driver.switch_to.window(driver.window_handles[0])
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
    cursor.execute(query=music_tag_sql, args=[music_id, tag_id1])
    connection.commit()
except Exception:
    pass
try:
    music_tag_sql = 'insert into music_tag (music_id,tag_id)values(%s,%s)'
    cursor.execute(query=music_tag_sql, args=[music_id, tag_id2])
    connection.commit()
except Exception:
    pass
# try:
#     music_tag_sql = 'insert into music_tag (music_id,tag_id)values(%s,%s)'
#     cursor.execute(query=music_tag_sql, args=[music_id, tag_id3])
#     connection.commit()
# except Exception:
#     pass
# 插入歌手\更新歌手
try:
    artist_sql = 'insert into artist (artist_id,artist_name,artist_img,artist_description,artist_type,artist_location)values(%s,%s,%s,%s,%s,%s)'
    cursor.execute(query=artist_sql, args=[artist_id, artist_name, artist_img, artist_description,artist_type,artist_location])
    connection.commit()
except Exception:
    artist_sql = 'update artist set artist_type=%s,artist_location=%s where artist_id=%s'
    cursor.execute(query=artist_sql, args=[artist_type, artist_location, artist_id])
    connection.commit()
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


cursor.close()
connection.close()




