package net.tyt.sample.process;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author 69TytarIA
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class ProcessRouterTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testExecute() {
        String command = "echo";
        String aruments = "Hello";
        ProcessResult result = webTestClient
                .get().uri("/execute?cmd="+command+"&args="+aruments)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ProcessResult.class)
                .returnResult().getResponseBody();
        assertNotNull(result);
        assertEquals(0,result.getExitCode());
        assertEquals(aruments,result.getResult());
    }
}
