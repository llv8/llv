#执行agent的有两种方式，但必须是以jar包执行agent。
#1.MANIFEST.MF
Manifest-Version: 1.0
Premain-Class: com.ddou.example.javaagent.AopAgentExample

#2. java -javaagent:{path}/aopagent.jar -cp ./ ...... 



