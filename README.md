# Spring Cloud example

使用 feign 进行类型安全调用，并使用 eureka 、consul 或 zookeeper 作为注册中心 

## Case 1 ：基础使用

* 只当使用 eureka 作为注册中心时，启动 `server/src/main/java/so.anson.springcloud.server/Application`
* 只当使用 consul 作为注册中心时，与 8500 端口启动 consul
  * `brew install consul`
  * `consul agent -dev`
* 只当使用 zk 作为注册中心时，与 2181 端口启动 zk
  * `brew install zookeeper`
  * `zkServer start`
* 编辑 `provider/pom.xml` ，打开某一项的注释并注释其余项（例如使用 eureka ，则注释掉 consule 和 zk 相关依赖）
* 编辑 `provider/src/main/resources/application.properties` ，打开某一项的注释并注释其余项
* 运行 `provider/src/main/java/so.anson.springcloud.provider/ProviderApplication`
* 编辑 `consumer/pom.xml` ，打开某一项的注释并注释其余项（例如使用 eureka ，则注释掉 consule 和 zk 相关依赖）
* 编辑 `consumer/src/main/resources/application.properties` ，打开某一项的注释并注释其余项
* 运行 `consumber/src/main/java/so.anson.springcloud.consumber/ConsumberApplication`
* 验证服务注册成功
  * eureka：在浏览器中打开 `http://localhost:1111` 查看
  * consul：`curl localhost:8500/v1/catalog/service/compute-service`
  * zk：`zkCli` ，`ls /services/compute-service`
* 验证服务调用成功：在浏览器中打开 `http://localhost:3333/add` ，页面输出 30

## Case 2 ：验证 hystrix

* 在 case 1 的基础上不运行 provider 而只运行 `consumber/src/main/java/so.anson.springcloud.consumber/ConsumberApplication`
* 在浏览器中打开 `http://localhost:3333/add` ，页面输出 99

## Case 3 ：验证负载均衡

* 在 case 1 的基础上，在运行一个 provider 后，修改 `provider/src/main/resources/application.properties` 中的 `server.port` 为 2223 并单独运行一份
* 多次验证服务调用成功并观察两个 provider 实例的日志