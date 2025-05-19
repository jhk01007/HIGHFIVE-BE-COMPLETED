package advancedweb.project.welfareservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableFeignClients(basePackages = "advancedweb.project.welfareservice.infra.client")
public class WelfareServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WelfareServiceApplication.class, args);
    }

}
