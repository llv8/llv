README.md文件如何换行？直接在要换行的语句最后打上2个空格。    


//如果target文件夹已经提交，可以使用以下命令删除。然后再提交和push到remote  
find . -type d -name target|xargs rm -rf    

//如果.project文件已经提交，可以使用以下命令删除暂存区文件，然后再push到remote  
find . -name .project | xargs git rm --cached  
find . -name .classpath | xargs git rm --cached  
find . -name .settings | xargs git rm -r --cached    