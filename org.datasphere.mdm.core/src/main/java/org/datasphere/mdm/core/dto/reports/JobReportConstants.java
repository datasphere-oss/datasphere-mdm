

package org.datasphere.mdm.core.dto.reports;

/**
 * @author Mikhail Mikhailov
 * Various job report constants.
 */
public class JobReportConstants {
    public static final String JOB_NAMED_SUFFIX = ".named";
    /**
     * Started.
     */
    public static final String JOB_STATUS_STARTED = "app.job.status.started";
    /**
     * Starting.
     */
    public static final String JOB_STATUS_STARTING = "app.job.status.starting";
    /**
     * Completed.
     */
    public static final String JOB_STATUS_COMPLETED = "app.job.status.completed";
    /**
     * Stopping.
     */
    public static final String JOB_STATUS_STOPPING = "app.job.status.stopping";
    /**
     * Stopped.
     */
    public static final String JOB_STATUS_STOPPED = "app.job.status.stopped";
    /**
     * Failed.
     */
    public static final String JOB_STATUS_FAILED = "app.job.status.failed";
    /**
     * Unknown.
     */
    public static final String JOB_STATUS_UNKNOWN = "app.job.status.unknown";
    /**
     * Records (first plural).
     */
    public static final String JOB_REPORT_RECORDS_1 = "app.job.report.term.records.1";
    /**
     * Records (second plural).
     */
    public static final String JOB_REPORT_RECORDS_2 = "app.job.report.term.records.2";
    /**
     * Records (singular).
     */
    public static final String JOB_REPORT_RECORD = "app.job.report.term.record";

    public static final String USER_NAME_PARAM = "userName";

    public static final String JOB_REPORT_TYPE = "app.job.report.reportType";

    /**
     * Constructor.
     */
    private JobReportConstants() {
        super();
    }

}
