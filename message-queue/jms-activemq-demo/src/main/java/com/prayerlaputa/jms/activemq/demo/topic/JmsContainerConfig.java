package com.prayerlaputa.jms.activemq.demo.topic;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConnectionFactory;

/**
 * @author chenglong.yu
 * created on 2021/1/11
 */
@Configuration
public class JmsContainerConfig {

    @Bean
    public JmsListenerContainerFactory<?> myJmsContainerFactory(@Qualifier("jmsConnectionFactory") ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true);
        return factory;
    }
}
