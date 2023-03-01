package com.solarix.camel.routes;

import com.solarix.camel.services.WriteActiveMQ;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import static com.solarix.camel.services.ReadActiveMQ.ReadMessageFromActiveMQ;

@Component
public class ActiveMQMessageProducerRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        WriteActiveMQ.WriteMessageToActiveMQ("Ceci est un message à écrire dans ActiveMQ !");

//        //generates an event/constant message every 60 seconds
//        from("timer:active-mq-timer?period=60000")
//                .transform().constant("Solarix Blockchain Startup")
//                .log("${body}")
//                //send this message to ActiveMQ queue
//                .to("activemq:solarix-activemq-queue");

//        ReadMessageFromActiveMQ();

    }
}