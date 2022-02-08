const {createProxyMiddleware} = require('http-proxy-middleware')

module.exports = function(app) {
	app.use(
		'/kugou',
        createProxyMiddleware({ 
			target: 'https://wwwapi.kugou.com/yy/index.php', //请求转发给谁
			changeOrigin: true,//控制服务器收到的请求头中Host的值
			pathRewrite:{'^/kugou': ''}, //重写请求路径(必须)
			headers: {
				cookie: 'kg_mid=39f0247247ae8d32fa08e059894d0361; kg_dfid=3x8KjG0Z8M2U2M53fL2FxHb9; kg_dfid_collect=d41d8cd98f00b204e9800998ecf8427e; KugooID=1534449652; a_id=1014; KuGoo=KugooID=1534449652&KugooPwd=490DE731557209844A6F65DBD92B3AF1&NickName=%u539f%u5473%u9e21&Pic=http://imge.kugou.com/kugouicon/165/20190817/20190817235035526577.jpg&RegState=1&RegFrom=&t=5807e5ff2d7eaedd7a78dcb0a15c65ff30cbda4365e74b88f88f994e50b4df24&t_ts=1642411414&t_key=&a_id=1014&ct=1640273656&UserName=%u0031%u0033%u0038%u0035%u0037%u0038%u0036%u0030%u0032%u0033%u0034;'
			}
	    })
    );

    app.use(
		'/server',
        createProxyMiddleware({ 
			target: 'http://localhost:8080', //请求转发给谁
			changeOrigin: true,//控制服务器收到的请求头中Host的值
            pathRewrite: {'^/server': ''}
	    })
    );
}