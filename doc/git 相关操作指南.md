## git 相关操作指南

### github 进行fork后如何与原仓库同步

问题场景：

新公司要求所有的代码提交都要先通过自己的库提交到主repo上去，所以先在gitlab网页上fork出一个自己的库，在本地修改完代码后提交到远程自己库上，然后在gitlab网页上发起一个merge request请求，然后等待主repo主人review，同意之后合入。

问题是同时也有其他同学在主repo合入代码，所以我要定期和主repo保持同步。


整体思路如下：

在自己的本地添加主repo为上游代码库，注意只是配置原仓库的路径，并没有真正clone原仓库，

然后将远程主repo同步到自己本地的机器，

然后本地的机器再push到自己的远程的fork库

所有的操作都要在本地命令行完成

1. 首先在终端中配置原仓库的位置。进入项目目录，执行如下命令：查看你的远程仓库的路径。

   ![image-20200425134545532](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200425134545532.png)

2. 添加原仓库地址 

   命令： <u>git remote add upstream https://github.com/</u>

![image-20200425134654468](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200425134654468.png)

3. 再次查看远程仓库目录

   

![image-20200425134754426](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200425134754426.png)

4. 抓取原仓库目录 

   执行命令：<u>git fetch upstream</u>

   

5.  合并到本地master 分支并提交

   执行命令： git merge master，  git push origin master

   ​    

   

   

   

