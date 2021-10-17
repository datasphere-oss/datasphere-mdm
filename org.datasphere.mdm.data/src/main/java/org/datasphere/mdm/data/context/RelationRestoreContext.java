package org.datasphere.mdm.data.context;

import java.util.Date;

import javax.annotation.Nullable;

import org.datasphere.mdm.data.type.data.OriginRelation;
import org.datasphere.mdm.core.context.DataRecordContext;
import org.datasphere.mdm.core.context.MutableValidityRangeContext;
import org.datasphere.mdm.system.context.DraftAwareContext;
import org.datasphere.mdm.system.context.DraftIdResettingContext;
import org.datasphere.mdm.system.context.SetupAwareContext;

/**
 * @author Mikhail Mikhailov on Jun 9, 2020
 */
public interface RelationRestoreContext
    extends
        RelationIdentityContext,
        ReadWriteTimelineContext<OriginRelation>,
        ReadWriteDataContext<OriginRelation>,
        ContainmentRelationContext<RestoreRecordRequestContext>,
        MutableValidityRangeContext,
        OperationTypeContext,
        SetupAwareContext,
        AccessRightContext,
        BatchAwareContext,
        DraftAwareContext,
        DraftIdResettingContext,
        DataRecordContext {
    /**
     * Internal use.
     */
    public enum RestoreRelationHint {
        HINT_PUBLISHING
    }
    /**
     * @return define that is period restore request.
     */
    default boolean isPeriodRestore() {
        return getFlag(DataContextFlags.FLAG_IS_PERIOD_RESTORE);
    }
    /**
     * @return the forDate
     */
    Date getForDate();
    /**
     * @return the lastUpdate
     */
    Date getLastUpdate();
    /**
     * Gets content by hint value.
     * @param <X> expected return type
     * @param h the hint
     * @return value or null
     */
    @Nullable
    <X> X getHint(RestoreRelationHint h);
}
