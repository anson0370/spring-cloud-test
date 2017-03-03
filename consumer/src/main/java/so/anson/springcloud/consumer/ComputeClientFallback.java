package so.anson.springcloud.consumer;

import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: AnsonChan
 * Date: 03/03/2017
 */
@Service
public class ComputeClientFallback implements ComputeClient {
    @Override
    public Integer add(Integer a, Integer b) {
        return -99;
    }
}
