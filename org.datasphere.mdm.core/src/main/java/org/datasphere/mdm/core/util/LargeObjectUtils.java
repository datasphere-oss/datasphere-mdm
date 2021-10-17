

package org.datasphere.mdm.core.util;

import java.io.InputStream;

import javax.ws.rs.core.StreamingOutput;

import org.datasphere.mdm.core.dto.LargeObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Alexander Malyshev
 */
public class LargeObjectUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(LargeObjectUtils.class);
    /**
     * Disabling constructor.
     */
    private LargeObjectUtils() {
        super();
    }
    /**
     * Gets {@link StreamingOutput} for a context.
     *
     * @param result Large object
     * @return streaming output
     */
    public static StreamingOutput createStreamingOutputForLargeObject(final LargeObjectResult result) {
        return output -> {

            try (InputStream is = result.getInputStream()) {

                byte[] buf = new byte[FileUtils.DEFAULT_BUFFER_SIZE];
                int count = -1;
                while ((count = is.read(buf, 0, buf.length)) != -1) {
                    output.write(buf, 0, count);
                }
            } catch (Exception exc) {
                LOGGER.warn("Exception cought while BLOB I/O.", exc);
            }
        };
    }
}
