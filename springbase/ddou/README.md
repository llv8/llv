
问题集：
1. repository的接口如何被注册进bean容器？
使用@EnableJpaRepositories(basePackages = "com.ddou.repository")注解，它会将扫描basepackage里的repository，将这些接口生成代理实现类并注册到bean容器。

2. @Service注解在service的实现类就可以，接口不用使用@Service注解。

3. @MapperScan("com.ddou.mapper")注解configuration类表示:mapper的java接口类要根据@MapperScanr提供的包名进行扫描。
    如果*mapper.xml和*Mapper.java接口类在相同的路径，则xml的路径不用单独配置，否则需要在application.properties里添加mybatis.mapper-locations=classpath:com/ddou/mapper/*.xml




