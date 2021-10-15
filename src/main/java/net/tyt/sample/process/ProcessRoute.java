package net.tyt.sample.process;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 *
 * @author 69TytarIA
 */
@Configuration(proxyBeanMethods = false)
public class ProcessRoute {
    @Bean
    public RouterFunction<ServerResponse> route(ProcessHandler handler) {
        return RouterFunctions
                .route(GET("/execute").and(accept(MediaType.APPLICATION_JSON)), handler::execute);
    }
}
