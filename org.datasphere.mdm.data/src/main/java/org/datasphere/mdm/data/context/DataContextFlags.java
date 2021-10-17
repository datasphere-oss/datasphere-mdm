

package org.datasphere.mdm.data.context;

import org.datasphere.mdm.system.context.CommonRequestContext;

/**
 * @author Mikhail Mikhailov
 * Context flags.
 * TODO: BIG Fix needed - review existing flags and remove old/unneeded.
 * It appears like more then a half of the flags are obsolete and are product of migration from 5.x!
 */
public final class DataContextFlags {
    /**
     * Batch upsert indicator.
     */
    public static final int FLAG_BATCH_OPERATION = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    /**
     * Empty storage?
     */
    public static final int FLAG_EMPTY_STORAGE = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    /**
     * Suppress workflow (directed from UI)?
     */
    public static final int FLAG_SUPPRESS_WORKFLOW = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    /**
     * Enrichment hint.
     */
    public static final int FLAG_IS_ENRICHMENT = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    /**
     * Skip DQ hint.
     */
    public static final int FLAG_SKIP_DQ = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    /**
     * Skip DQ hint.
     */
    public static final int FLAG_RECALCULATE_WHOLE_TIMELINE = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    /**
     * Record restore hint.
     */
    public static final int FLAG_IS_RECORD_RESTORE = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    /**
     * Period restore hint.
     */
    public static final int FLAG_IS_PERIOD_RESTORE = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    /**
     * Data restore hint.
     */
    public static final int FLAG_IS_DATA_RESTORE = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    /**
     * Modified hint.
     */
    public static final int FLAG_IS_MODIFIED = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    /**
     * Restore hint.
     */
    public static final int FLAG_MERGE_WITH_PREVIOUS_VERSION = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    /**
     * Restore hint.
     */
    public static final int FLAG_SKIP_CONSISTENCY_CHECKS = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    /**
     * Restore hint.
     */
    public static final int FLAG_SKIP_MATCHING_PREPROCESSING = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();

    public static final int FLAG_SKIP_TIMELINE_CALCULATIONS = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    /**
     * Restore hint.
     */
    public static final int FLAG_SKIP_MATCHING = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    /**
     * Restore hint.
     */
    public static final int FLAG_SKIP_INDEX_DROP = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    /**
     * Restore hint.
     */
    public static final int FLAG_SUPPRESS_AUDIT = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    /**
     * Restore hint.
     */
    public static final int FLAG_IS_APPLY_DRAFT = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();

    public static final int FLAG_FETCH_ORIGINS = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    public static final int FLAG_FETCH_ORIGINS_HISTORY = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    public static final int FLAG_FETCH_SOFT_DELETED = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    public static final int FLAG_FETCH_LARGE_OBJECTS = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    public static final int FLAG_FETCH_TIMELINE_DATA = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    public static final int FLAG_FETCH_KEYS = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    public static final int FLAG_FETCH_DATA_FOR_PERIOD = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    public static final int FLAG_FETCH_TIMELINE_BY_TO_SIDE = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    /**
     * Fetch all relations for the given record etalon id.
     */
    public static final int FLAG_APPLY_TO_ALL = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    public static final int FLAG_INCLUDE_MERGED = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    public static final int FLAG_INCLUDE_INACTIVE = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    public static final int FLAG_INCLUDE_DRAFTS = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    public static final int FLAG_INCLUDE_WINNERS = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    public static final int FLAG_DIFF_TO_DRAFT = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    public static final int FLAG_DIFF_TO_PREVIOUS = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    public static final int FLAG_STRICT_DRAFT = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    public static final int FLAG_REDUCE_REFERENCE_RELATIONS = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    public static final int FLAG_INACTIVATE_CASCADE = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    public static final int FLAG_INACTIVATE_WIPE = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    public static final int FLAG_INACTIVATE_PERIOD = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    public static final int FLAG_INACTIVATE_ORIGIN = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    public static final int FLAG_INACTIVATE_ETALON = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    public static final int FLAG_WORKFLOW_ACTION = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();

    /**
     * Constructor.
     */
    private DataContextFlags() {
        super();
    }
}
