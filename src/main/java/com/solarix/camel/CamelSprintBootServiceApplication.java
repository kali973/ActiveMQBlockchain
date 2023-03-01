package com.solarix.camel;

import com.solarix.camel.services.ContractService;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.Web3j;

import javax.annotation.PostConstruct;
import javax.jms.*;

@RestController
@SpringBootApplication
public class CamelSprintBootServiceApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(CamelSprintBootServiceApplication.class);

    @Autowired
    Web3j web3j;
    @Autowired
    ContractService service;

    public static void main(String[] args) {
        SpringApplication.run(CamelSprintBootServiceApplication.class, args);
    }

    @PostConstruct
    public void listen() {
        web3j.transactionObservable().subscribe(tx -> {
            if (tx.getTo() != null && tx.getTo().equals(service.getOwnerAccount())) {
                LOGGER.info("New tx: id={}, block={}, from={}, to={}, value={}", tx.getHash(), tx.getBlockHash(), tx.getFrom(), tx.getTo(), tx.getValue().intValue());
                service.processContracts(tx.getValue().longValue());
            } else {
                LOGGER.info("Not matched: id={}, to={}", tx.getHash(), tx.getTo());
            }
        });
    }

    @PostMapping("/solar-panels")
    public String processForm(@RequestBody String formData) {
        // traitement des données de formulaire
        return "Données de formulaire reçues : " + formData;
    }

    @PostMapping("/read-activemq")
    public String readFromActiveMQ() {
        String result = "";
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            javax.jms.Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            javax.jms.Destination destination = session.createQueue("solarix-activemq-queue");
            MessageConsumer consumer = session.createConsumer(destination);

            Message message = consumer.receive();
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                result = "Received message: " + textMessage.getText();
            } else {
                result = "Received message: " + message;
            }

            consumer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return result;
    }

}
