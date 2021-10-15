

package org.datasphere.mdm.core.configuration;

/**
 * TODO : 5.2 class, needs to be refactored and moved
 *
 * @author maria.chistyakova
 * @since 30.10.2019
 */
public class UserMessageConstants {

    private UserMessageConstants() {
        super();
    }

    public static final String JOB_REINDEX_META_SUCCESS = "app.user.events.reindex.meta.jobs.success";
    public static final String JOB_REINDEX_META_FAIL = "app.user.events.reindex.meta.jobs.fail";
    public static final String DATA_IMPORT_METADATA_FAILED = "app.user.events.import.metadata.failed";
    public static final String DATA_IMPORT_METADATA_SUCCESS = "app.user.events.import.metadata.success";
    public static final String DATA_IMPORT_UNSUCCESS = "app.user.events.import.data.unsuccess";
}
