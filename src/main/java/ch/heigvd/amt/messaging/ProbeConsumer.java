package ch.heigvd.amt.messaging;

import ch.heigvd.amt.services.ProbeService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.TextMessage;

@ApplicationScoped
public class ProbeConsumer {
    @Inject
    ProbeService probeService;

    @Inject
    ConnectionFactory connectionFactory;

    JMSContext context;

    void OnStart() {
        context = connectionFactory.createContext();
        var queue = context.createQueue("probes");
        var consumer = context.createConsumer(queue);
        consumer.setMessageListener(message -> {
            if (message instanceof TextMessage textMessage) {
                try {
                    var url = textMessage.getText();
                    probeService.executeProbe(url);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
