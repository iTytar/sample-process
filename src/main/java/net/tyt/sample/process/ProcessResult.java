package net.tyt.sample.process;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author 69TytarIA
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessResult {
    private int exitCode;
    private String result;
}
