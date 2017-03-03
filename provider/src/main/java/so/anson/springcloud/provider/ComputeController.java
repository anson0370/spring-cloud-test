package so.anson.springcloud.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RestController;
import so.anson.springcloud.provider.service.ComputeService;

/**
 * Created by IntelliJ IDEA.
 * User: AnsonChan
 * Date: 01/03/2017
 */
@RestController
public class ComputeController implements ComputeService {
    @Autowired
    private DiscoveryClient client;

    public Integer add(Integer a, Integer b) {
        Integer result = a + b;
        ServiceInstance instance = client.getLocalServiceInstance();
        System.out.println("/add invoked, service: " + instance);
        System.out.println("result: " + result);
        return result;
    }
}
