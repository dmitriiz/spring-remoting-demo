package eu.dazzled.dmitriiz.demo;

import eu.dazzled.dmitriiz.demo.service.DemoService;
import eu.dazzled.dmitriiz.demo.service.DemoServiceImpl;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.remoting.service.AmqpInvokerServiceExporter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServiceApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ServiceApplication.class, args);
    }

    @Bean
    AmqpInvokerServiceExporter listener(ConnectionFactory factory) {
        AmqpInvokerServiceExporter listener = new AmqpInvokerServiceExporter();
        listener.setServiceInterface(DemoService.class);
        listener.setService(new DemoServiceImpl());
        listener.setAmqpTemplate(new RabbitTemplate(factory));
        return listener;
    }

    @Bean
    MessageListenerContainer listenerContainer(ConnectionFactory factory, AmqpInvokerServiceExporter listener) {
        SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer(factory);
        listenerContainer.setQueueNames("spring-remoting-demo");
        listenerContainer.setMessageListener(listener);
        return listenerContainer;
    }

    @Bean
    Queue demoQueue() {
        return new Queue("spring-remoting-demo", true);
    }

    @Bean
    Exchange directExchange(Queue queue) {
        DirectExchange exchange = new DirectExchange("spring-remoting-demo");
        BindingBuilder.bind(queue).to(exchange).with("spring-remoting-demo");
        return exchange;
    }

}
