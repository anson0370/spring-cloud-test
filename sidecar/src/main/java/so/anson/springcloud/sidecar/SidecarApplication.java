package so.anson.springcloud.sidecar;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

/**
 * Created by IntelliJ IDEA.
 * User: AnsonChan
 * Date: 06/03/2017
 */
@EnableSidecar
@SpringBootApplication
public class SidecarApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SidecarApplication.class).web(true).run(args);
    }
}
