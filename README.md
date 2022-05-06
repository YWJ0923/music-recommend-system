# 音乐推荐系统

前端React+Antd，后端SpringBoot，数据库Mysql+Redis

启动方式：
1. 导入sql文件
2. 进入server/front文件夹，启动后端
3. 进入client文件夹，npm start，启动前端

**数据库，Redis的用户名、密码、ip地址、端口号自行修改**


admin文件夹和server/back文件夹直接无视，本来还想做一个可视化后台管理，懒得做了

cookie也没有用，我充了个vip，本来是为了能听付费歌曲，因为放歌是从酷狗获取链接。但是发现cookie里有类似于时间戳的字段，需要一直修改cookie才行，就放弃了。目前付费歌曲只能听1分钟，其余都可以正常听。