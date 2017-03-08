package so.anson.springcloud.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.discovery.DiscoveryClientRouteLocator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by IntelliJ IDEA.
 * User: AnsonChan
 * Date: 08/03/2017
 */
@Component
public class ProxyIniter {
    @Autowired
    private DiscoveryClientRouteLocator routeLocator;

    @PostConstruct
    public void init() {
        ZuulProperties.ZuulRoute dynamicAddRoute = new ZuulProperties.ZuulRoute();
        dynamicAddRoute.setPath("/new-add-route/**");
        dynamicAddRoute.setServiceId("compute-service");
        routeLocator.addRoute(dynamicAddRoute);
    }
}
