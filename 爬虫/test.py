# @author ywj
# @date 2021/11/13
import json
import time

import re

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions
from selenium.webdriver.chrome.options import Options
from selenium.webdriver import ActionChains

import pymysql

# 初始化driver
options = Options()
options.page_load_stategy = 'eager'
path = 'chromedriver.exe'
driver = webdriver.Chrome(executable_path=path, options=options)
driver.maximize_window()
# 欧美组合
artist_url = 'https://www.kugou.com/yy/singer/index/1-all-10.html'

api_base_url = 'https://wwwapi.kugou.com/yy/index.php?r=play/getdata&mid=1&'
# 连接数据库
# connection = pymysql.connect(host='47.102.130.18', user='root', password='1234', database='music_recommend', charset='utf8', port=3306)
# cursor = connection.cursor()

tag_id = 19
# for i in range(18):
driver.get(artist_url)
# 找到前面有图片的，点击
artist_list = driver.find_elements(by=By.XPATH, value="//ul[@id='list_head']/li/a")
artist_click = artist_list[0]
artist_name = artist_click.get_attribute('title')
artist_click.click()
curr_url = str(driver.current_url)
artist_id = int(re.search(r'(?<=/)([0-9]+)(?=\.html)', curr_url).group(0))
# try:
#     artist_img = str(driver.find_element(by=By.XPATH, value="//div[@class='top']/img").get_attribute('src'))
#     artist_description = str(driver.find_element(by=By.XPATH, value="//div[@class='intro']/p").text)
# except Exception:
#     artist_img = None
#     artist_description = None
# try:
#     # 插入歌手
#     artist_sql = 'insert into artist (artist_id,artist_name,artist_img,artist_description)values(%s,%s,%s,%s)'
#     cursor.execute(query=artist_sql, args=[artist_id, artist_name, artist_img, artist_description])
#     connection.commit()
# except Exception:
#     pass
music_list = driver.find_elements(by=By.XPATH, value="//ul[@id='song_container']/li/a")
for music_click in music_list:
    music_click.click()
    # 切换窗口，等待网址变换
    driver.switch_to.window(driver.window_handles[1])
    WebDriverWait(driver=driver, timeout=2, poll_frequency=0.2).until(expected_conditions.url_contains('hash'))
    time.sleep(1)
    curr_url = driver.current_url
    hash = str(re.search(r'(?<=(hash=))([0-9a-zA-Z]+)(?=&)', curr_url).group(0))
    try:
        # 歌名，歌url
        music_name = str(driver.find_element(by=By.XPATH, value="//span[@class='audioName']").text)
    except Exception:
        driver.close()
        driver.switch_to.window(driver.window_handles[0])
        time.sleep(1)
        continue
    music_url = str(driver.find_element(by=By.XPATH, value="//audio[@id='myAudio']").get_attribute('src'))
    # try:
    #     lyric_list = driver.find_elements(by=By.XPATH, value="//p[@class='ie8FontColor']")
    #     # print(len(lyric_list))
    #     lyric = ''
    #     for lyric_p in lyric_list:
    #         lyric += lyric_p.text
    #         lyric += '\n'
    # except Exception:
    #     lyric = None
    # try:
    #     album_name = str(driver.find_element(by=By.XPATH, value="//p[@class='albumName fl']/a").text)
    # except Exception:
    #     album_name = None
    # print(lyric)
    # 专辑详情链接，点击
    # try:
    album_click = driver.find_element(by=By.XPATH, value="//p[@class='albumName fl']/a")
    album_click.click()
    driver.switch_to.window(driver.window_handles[2])
    time.sleep(1)
    # 专辑id，名字，图片，介绍，发行时间
    curr_url = str(driver.current_url)
    album_id = int(re.search(r'(?<=/)([0-9]+)(?=\.html)', curr_url).group(0))
    try:
        album_img = str(
            driver.find_element(by=By.XPATH, value="//div[@class='pic']/img").get_attribute('src'))
        album_description = \
        str(driver.find_element(by=By.XPATH, value="//div[@class='jspContainer']/div/p").text).split('：')[1]
        album_publish_time = \
        str(driver.find_element(by=By.XPATH, value="//p[@class='detail']").text).split('：')[3]
    except Exception:
        album_img = None
        album_description = None
        album_publish_time = '2021-01-01'
    api_url = api_base_url + '&hash=' + hash + '&album_id=' + str(album_id)
    driver.get(api_url)
    text = json.loads(driver.find_element(by=By.XPATH, value="//body").text)
    lyric = text.get('data').get('lyrics')
    print(lyric)

        # try:
        #     # 插入专辑
        #     album_sql = 'insert into album (album_id,album_name,artist_id,album_img,album_description,album_publish_time)values(%s,%s,%s,%s,%s,%s)'
        #     cursor.execute(query=album_sql,
        #                    args=[album_id, album_name, artist_id, album_img, album_description,
        #                          album_publish_time])
        #     connection.commit()
        # except Exception:
        #     pass
    driver.close()
    # except Exception:
    driver.switch_to.window(driver.window_handles[1])
    # try:
    #     # 插入音乐
    #     music_sql = 'insert into music (music_name,artist_id,album_id,lyric,music_url)values(%s,%s,%s,%s,%s)'
    #     cursor.execute(query=music_sql, args=[music_name, artist_id, album_id, lyric, music_url])
    #     connection.commit()
    # except Exception:
    #     pass
    # try:
    #     # 插入歌标签
    #     music_tag_sql = 'insert into music_tag_tmp (music_name,artist_id,album_id,tag_id)values(%s,%s,%s,%s)'
    #     cursor.execute(query=music_tag_sql, args=[music_name, artist_id, album_id, tag_id])
    #     connection.commit()
    # except Exception:
    #     pass
    driver.close()
    driver.switch_to.window(driver.window_handles[0])



# cursor.close()
# connection.close()