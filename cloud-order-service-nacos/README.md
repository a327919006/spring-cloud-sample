## cloud-order-service-nacos

#### 模块说明
订单服务，注册中心使用Nacos

#### 启动步骤
- 启动Nacos
- 将config/nacos文件夹下配置文件配置到nacos
- 启动Sentinel(可选，启动后可设置spring.cloud.sentinel.enabled=true)
- 启动cloud-user-service-nacos服务
- 启动cloud-order-service-nacos服务