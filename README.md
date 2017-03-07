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

## Case 4 : 使用 zuul 代理请求

* 在 case 1 使用 consul 的基础上（未在 proxy 工程中引入 zk 的依赖，可以自行尝试）
* 运行 `proxy/src/main/java/so.anson.springcloud.proxy.ProxyApplication`
* 验证请求代理成功
  * 在浏览器中打开 `http://localhost:4444/service-a/add?a=1&b=2`
  * 在浏览器中打开 `http://localhost:4444/service-b/minus?a=1&b=2`
  
## Case 5 : 使用 sidecar 引入 node 服务

* 运行 node 应用
  * 进入 `node-service` 目录
  * `npm install`
  * `node index.js`
* 默认使用 consul ，可自行修改 `pom.xml` 和 `application.properties` 来尝试 eureka
* 运行 `sidecar/src/main/java/so.anson.springcloud.sidecar.SidecarApplication`
* 验证注册成功
  * 查看注册中心中的注册端口是否为 5555
  * 在浏览器中打开 `http://localhost:5556/node-sidecar/square?n=10` ，得到输出结果 100
