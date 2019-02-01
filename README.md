#### Maven模块描述

| 编号 | 名称 | 描述 |
| --- | --- | --- |
| 01 | cloud-common | 系统工具类、服务接口、实体类封装 |
| 02 | cloud-config-server | 配置中心 |
| 03 | cloud-dal | mybatisMapper，sql |
| 04 | cloud-eureka | eureka注册中心 |
| 05 | cloud-eureka-ha | 注册中心集群 |
| 06 | cloud-gateway-zuul | zuul网关 |
| 07 | cloud-hystrix-dashboard | hystrix可视化 |
| 08 | cloud-hystrix-turbine | hystrix多模块监控 |
| 09 | cloud-order-service | 订单服务 |
| 10 | cloud-sidecar | 多语言支持，springCloud包裹其他语言的接口，如php等 |
| 11 | cloud-user-service | 用户服务 |

#### 启动步骤
- 启动eureka注册中心
- 启动配置中心
- 启动user服务
- 启动order服务