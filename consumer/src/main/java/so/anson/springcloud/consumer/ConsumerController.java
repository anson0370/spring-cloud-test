package so.anson.springcloud.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by IntelliJ IDEA.
 * User: AnsonChan
 * Date: 02/03/2017
 */
@RestController
public class ConsumerController {
    @Autowired
    private ComputeClient computeClient;
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Integer add() {
        Integer result = computeClient.add(10, 20);
        System.out.println("service call result: " + result);
        return result;
    }

    @RequestMapping(value = "/add2", method = RequestMethod.GET)
    public Integer add2() {
        Integer result = restTemplate.getForEntity("http://compute-service/add?a=1&b=2", Integer.class).getBody();
        System.out.println("service call result: " + result);
        return result;
    }
}
