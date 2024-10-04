package ch.heigvd.amt.messaging;

import ch.heigvd.amt.services.ProbeService;
import jakarta.jms.ConnectionFactory;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ProbeProducer {
    @Inject
    ProbeService probeService;

    @Inject
    ConnectionFactory connectionFactory;

    @Scheduled(every= "1s")
    public void checkProbes() {
        try (var context = connectionFactory.createContext()) {
            var queue = context.createQueue("probes");
            var producer = context.createProducer();
            for (var probe : probeService.listProbes()) {
                producer.send(queue, probe.getUrl());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
