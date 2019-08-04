## spring-cloud-sample

#### 项目说明
SpringCloud示例：整合Eureka、Zuul、Feign、Ribbon、Hystrix等

#### Maven模块描述

| 端口 | 名称 | 描述 |
| --- | --- | --- |
| ----- | cloud-common | 系统工具类、服务接口、实体类封装 |
| 8070  | cloud-config-server | 配置中心 |
| ----- | cloud-dal | mybatisMapper，sql |
| 8761  | cloud-eureka | eureka注册中心 |
| 8763  | cloud-eureka-ha | eureka注册中心集群 |
| 8060,8720  | cloud-gateway | spring-cloud-gateway网关，注册中心为nacos，整合sentinel |
| 8040  | cloud-gateway-zuul | zuul网关 |
| 8030  | cloud-hystrix-dashboard | hystrix可视化 |
| 8031  | cloud-hystrix-turbine | hystrix多模块监控 |
| 8050  | cloud-sidecar | 多语言支持，springCloud包裹其他语言的接口，如php等 |
| 10090 | cloud-user-service | 用户服务，注册中心为eureka |
| 10090 | cloud-user-service-consul | 用户服务，注册中心为consul |
| 10090 | cloud-user-service-nacos | 用户服务，注册中心为nacos |
| 10090 | cloud-user-service-zk | 用户服务，注册中心为zookeeper |
| 10091 | cloud-order-service | 订单服务，注册中心为eureka |
| 10091 | cloud-order-service-consul | 订单服务，注册中心为consul |
| 10091,8719 | cloud-order-service-nacos | 订单服务，注册中心为nacos，整合sentinel |
| 10091 | cloud-order-service-zk | 订单服务，注册中心为zookeeper |
| 9411  | cloud-zipkin | 链路跟踪 |
| 10092  | cloud-rocketmq-producer | 消息生产者，整合RocketMQ |
| 10093  | cloud-rocketmq-consumer | 消息消费者，整合RocketMQ |

#### 启动步骤
- 启动eureka注册中心
- 启动配置中心
- 启动user服务
- 启动order服务