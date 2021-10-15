package net.tyt.sample.process;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 *
 * @author 69TytarIA
 */
@Component
public class ProcessHandler {
    private final ProcessService processService;
    
    public ProcessHandler(ProcessService processService) {
        this.processService = processService;
    }
    
    public Mono<ServerResponse> execute(ServerRequest request) {
        String cmd = request.queryParam("cmd").orElseThrow();
        String args = request.queryParam("args").orElse("");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromProducer(processService.execute(cmd,args), ProcessResult.class));
    }
}
