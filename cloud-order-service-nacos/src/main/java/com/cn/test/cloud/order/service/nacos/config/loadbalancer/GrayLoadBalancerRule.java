package com.cn.test.cloud.order.service.nacos.config.loadbalancer;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.NacosServiceManager;
//import com.alibaba.cloud.nacos.ribbon.ExtendBalancer;
//import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.cn.test.cloud.common.model.Constants;
//import com.netflix.client.config.IClientConfig;
//import com.netflix.loadbalancer.AbstractLoadBalancerRule;
//import com.netflix.loadbalancer.BaseLoadBalancer;
//import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 灰度路由规则
 * @author Chen Nan
 */
//@Slf4j
//@RefreshScope
//public class GrayLoadBalancerRule extends AbstractLoadBalancerRule {
//    @Autowired
//    private NacosDiscoveryProperties nacosDiscoveryProperties;
//    @Autowired
//    private NacosServiceManager nacosServiceManager;
//    @Autowired
//    private GrayProperties grayProperties;
//
//    @Override
//    public void initWithNiwsConfig(IClientConfig iClientConfig) {
//
//    }
//
//    @Override
//    public Server choose(Object key) {
//        try {
//            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//            String version = requestAttributes.getRequest().getHeader(Constants.HEADER_VERSION);
//
//            BaseLoadBalancer baseLoadBalancer = (BaseLoadBalancer) getLoadBalancer();
//            String serviceName = baseLoadBalancer.getName();
//            String clusterName = nacosDiscoveryProperties.getClusterName();
//            String group = nacosDiscoveryProperties.getGroup();
//
//            log.info("version={} serviceName={} clusterName={} group={}", version, serviceName, clusterName, group);
//            log.info("weight={}", grayProperties.getWeight());
//
//            NamingService namingService = nacosServiceManager.getNamingService(nacosDiscoveryProperties.getNacosProperties());
//            List<Instance> instanceList = namingService.selectInstances(serviceName, group, true);
//
//            List<Instance> instancesToChoose = instanceList;
//            if (StringUtils.isNotBlank(clusterName)) {
//                List<Instance> sameClusterInstances = instanceList.stream()
//                        .filter(instance -> Objects.equals(clusterName, instance.getClusterName()))
//                        .collect(Collectors.toList());
//                if (!CollectionUtils.isEmpty(sameClusterInstances)) {
//                    instancesToChoose = sameClusterInstances;
//                } else {
//                    return null;
//                }
//            }
//
//            // 设置实例权重值
//            Map<String, Integer> weightMap = grayProperties.getWeight();
//            instancesToChoose = instancesToChoose.stream().peek(instance -> {
//                String instanceVersion = instance.getMetadata().get(Constants.HEADER_VERSION);
//                Integer weight = weightMap.get(instanceVersion);
//                if (weight != null) {
//                    instance.setWeight(weight);
//                }
//            }).collect(Collectors.toList());
//
//            if (StringUtils.isEmpty(version)) {
//                return chooseInstance(instancesToChoose);
//            }
//
//            List<Instance> versionInstances = instancesToChoose.stream()
//                    .filter(instance -> StringUtils.equalsIgnoreCase(instance.getMetadata().get(Constants.HEADER_VERSION), version))
//                    .peek(instance -> instance.setWeight(1))
//                    .collect(Collectors.toList());
//
//            if (versionInstances.isEmpty()) {
//                return chooseInstance(instancesToChoose);
//            }
//
//            return chooseInstance(versionInstances);
//        } catch (Exception e) {
//            log.error("gray load balance error:", e);
//        }
//        return null;
//    }
//
//    private Server chooseInstance(List<Instance> instanceList) {
//        if (CollectionUtils.isEmpty(instanceList)) {
//            return null;
//        }
//        Instance instance = ExtendBalancer.getHostByRandomWeight2(instanceList);
//        if (instance == null) {
//            return null;
//        }
//        return new NacosServer(instance);
//    }
//}
