package ch.heigvd.amt.services;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ProbeServiceTest {

    @Inject
    ProbeService probeService;

    @Test
    @TestTransaction
    void testProbeService() {
        var probeList = probeService.listProbes();
        assertEquals(0, probeList.size());

        var probe = probeService.getOrCreateProbe("http://example.com");
        assertNotNull(probe);
        probeList = probeService.listProbes();
        assertEquals(1, probeList.size());
    }
}