# hanlp-ext-crf

当前支持分词类型：

- hanlp : 标准分词

- hanlp-crf: crf分词

## 编译、安装、测试

文件路径按着自己的安装路径设置

1. 编译、打包插件

```
gradle -p es-plugin jar buildPluginZip
```
    
2. 使用命令安装插件

```
ES_HOME/bin/elasticsearch-plugin install file:///home/hanlp-ext-crf/es-plugin/build/distributions/hanlp-ext-crf-5.4.3.zip
```
    
3. 修改 `ES_HOME/config` 目录下的 `jvm.options` 文件添加一行（读取hanlp.properties配置文件需要）
    
```
-Djava.security.policy=file:///你的ES目录/plugins/hanlp-ext-crf/plugin-security.policy
```
    
4. 最后修改ES/bin/elasticsearch.in.sh文件将 ES_CLASSPATH修改为
    
```
ES_CLASSPATH="$ES_HOME/lib/elasticsearch-5.4.3.jar:$ES_HOME/lib/*:$ES_HOME/plugins/hanlp-ext-crf/"
```

5.用户词典热更新，只需替换/dictionary/custom/目录下的字典即可热更新
   
最后运行elasticsearch即可

