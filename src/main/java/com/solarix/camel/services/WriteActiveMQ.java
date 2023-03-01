package com.solarix.camel.services;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class WriteActiveMQ {

    public static void WriteMessageToActiveMQ(String messageContent) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = null; // initialisation de la variable connection à null
        try {
            connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("solarix-activemq-queue");
            MessageProducer producer = session.createProducer(destination);

            TextMessage message = session.createTextMessage(messageContent);
            producer.send(message);

            producer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) { // vérification que la connexion n'est pas nulle avant de la fermer
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
