package so.anson.springcloud.proxy;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by IntelliJ IDEA.
 * User: AnsonChan
 * Date: 06/03/2017
 */
@EnableZuulProxy
@SpringBootApplication
public class ProxyApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ProxyApplication.class).web(true).run(args);
    }
}
