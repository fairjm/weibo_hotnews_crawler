# 微博热门新闻爬取

------

爬出的内容主要是新浪的热门话题页(http://d.weibo.com/100803)  
爬取第一页的内容 拿到新闻标题和类别 并读取页面 爬取阅读数 讨论数和关注数 

crawler为爬虫小程序 使用java编写

在运行前 需要编辑 `crawler/conf/application.conf` 中的相关属性
> * sub是页面cookie 请自行用浏览器获取这个cookie值 原本的可能已经失效
> * db相关的是数据库配置 具体的数据库在`crawler/sql`中 请自行导入

运行`App.java` 启动程序

------
