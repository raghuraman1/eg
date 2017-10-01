package eg;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.kubernetes.KubernetesAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import io.fabric8.kubernetes.client.Config;

@SpringBootApplication
@EnableDiscoveryClient
@Import(KubernetesAutoConfiguration.class)
public class EgApplication {
	
	

    public static void main(String[] args) {
        SpringApplication.run(EgApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

          /*  System.out.println("Let's inspect the beans provided by Spring Boot:");

           String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }*/

        };
    }

}