package net.tyt.sample.process;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

/**
 *
 * @author 69TytarIA
 */
public class ProcessExecutor implements Callable<ProcessResult> {

    private static final boolean WINDOWS = System.getProperty("os.name").toLowerCase().startsWith("windows");
    
    private final String command;
    private final String arguments;
    
    public ProcessExecutor(String command, String arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    @Override
    public ProcessResult call() throws Exception {
        ProcessBuilder builder = new ProcessBuilder();
        if (WINDOWS) {
            builder.command("cmd.exe", "/c", command, arguments);
        } else {
            builder.command("sh", "-c", command, arguments);
        }
        builder.directory(new File(System.getProperty("user.home")));
        Process process = builder.start();
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String l = br.readLine();
            while(l != null) {
                sb.append(l);
                l = br.readLine();
            }
        }
        int exitCode = process.waitFor();
        assert exitCode == 0;
        return new ProcessResult(exitCode,sb.toString());
    }
}
