package eu.dazzled.dmitriiz.demo;

import eu.dazzled.dmitriiz.demo.service.DemoService;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.remoting.client.AmqpProxyFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FrontApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(FrontApplication.class, args);
    }

    @Bean
    AmqpProxyFactoryBean client(ConnectionFactory factory) {
        RabbitTemplate template = new RabbitTemplate(factory);
        template.setReplyTimeout(1000);
        template.setRoutingKey("spring-remoting-demo");
        AmqpProxyFactoryBean client = new AmqpProxyFactoryBean();
        client.setServiceInterface(DemoService.class);
        client.setAmqpTemplate(template);
        return client;
    }

}
