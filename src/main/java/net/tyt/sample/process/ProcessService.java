package net.tyt.sample.process;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 *
 * @author 69TytarIA
 */
@Service
public class ProcessService {
    public Mono<ProcessResult> execute(String command, String arguments) {
        return Mono.fromCallable(new ProcessExecutor(command,arguments));
    }
}
