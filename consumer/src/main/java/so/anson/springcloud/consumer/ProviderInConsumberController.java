package so.anson.springcloud.consumer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: AnsonChan
 * Date: 06/03/2017
 */
@RestController
public class ProviderInConsumberController {
    @RequestMapping(value = "/minus", method = RequestMethod.GET)
    public Integer minus(@RequestParam Integer a, @RequestParam Integer b) {
        return a - b;
    }
}
