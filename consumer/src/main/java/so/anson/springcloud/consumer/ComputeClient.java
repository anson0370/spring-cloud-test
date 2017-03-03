package so.anson.springcloud.consumer;

import org.springframework.cloud.netflix.feign.FeignClient;
import so.anson.springcloud.provider.service.ComputeService;

/**
 * Created by IntelliJ IDEA.
 * User: AnsonChan
 * Date: 02/03/2017
 */
@FeignClient(value = "compute-service", fallback = ComputeClientFallback.class)
public interface ComputeClient extends ComputeService {}
