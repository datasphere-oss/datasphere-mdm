
package org.datasphere.mdm.core.context;

import java.util.concurrent.Callable;

import javax.annotation.Nullable;

import org.datasphere.mdm.system.context.AfterTransactionAwareContext;

/**
 * @author Mikhail Mikhailov on Oct 8, 2020
 * Model change marker, the model descriptor operates on.
 */
public interface ModelChangeContext extends ModelIdentityContext, AfterTransactionAwareContext {
    /**
     * Wait, until changes applied and the return.
     * @return true, if set to wait.
     */
    boolean waitForFinish();
    /**
     * The version to apply (current + 1 otherwise).
     * Upsert will fail if the version is older then current + 1 and 'force' is false.
     * @return specific version or null, if not set
     */
    @Nullable
    Integer getVersion();
    /**
     * Force upsert, even if supplied version is older then current + 1
     * or concurrent modifications were made during upsert.
     * @return
     */
    boolean force();
    /**
     * Instead of calling refresh immediately, return {@link Callable} implementing refresh operation.
     * This is used to postpone refresh operations while publishing draft objects
     * @return true, if refresh should be postponed, false otherwise
     */
    boolean postponeRefresh();
    /**
     * Model change type.
     */
    enum ModelChangeType {
        /**
         * One element update.
         * Former PARTIAL_UPDATE
         */
        PARTIAL,
        /**
         * Full replacement - model will be recreated.
         * Former FULLY_NEW.
         */
        FULL,
        /**
         * Current model and existed will be merged.
         * Former ADDITION
         */
        MERGE
    }
}
