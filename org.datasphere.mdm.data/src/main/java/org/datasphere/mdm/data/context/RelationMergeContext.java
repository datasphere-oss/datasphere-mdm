package org.datasphere.mdm.data.context;

import org.datasphere.mdm.data.type.data.OriginRelation;
import org.datasphere.mdm.data.type.merge.MergeRelationMasterState;
import org.datasphere.mdm.system.context.SetupAwareContext;

/**
 * Common supertype for FROM/TO relations.
 * @author Mikhail Mikhailov on May 10, 2020
 */
public interface RelationMergeContext extends
    ReadWriteTimelineContext<OriginRelation>,
    ReadWriteDataContext<OriginRelation>,
    MergeItemContext<MergeRelationMasterState>,
    RelationIdentityContext,
    SetupAwareContext,
    AccessRightContext,
    BatchAwareContext,
    ExtendedAttributesAwareContext {}
