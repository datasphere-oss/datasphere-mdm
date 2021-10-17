package org.datasphere.mdm.data.context;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.datasphere.mdm.data.type.data.OriginRelation;
import org.datasphere.mdm.core.context.ValidityRangeContext;
import org.datasphere.mdm.system.type.pipeline.fragment.FragmentId;
import org.datasphere.mdm.system.type.pipeline.fragment.InputFragment;

/**
 * @author Mikhail Mikhailov on May 2, 2020
 */
public class RestoreRelationsRequestContext extends AbstractRelationsFromToRequestContext<RestoreFromRelationRequestContext, RestoreToRelationRequestContext>
        implements
            InputFragment<RestoreRelationsRequestContext>,
            BatchAwareContext,
            ValidityRangeContext,
            ReadWriteDataContext<OriginRelation> {
    /**
     * GSVUID.
     */
    private static final long serialVersionUID = -2770004457875707763L;
    /**
     * This context fragment id.
     */
    public static final FragmentId<RestoreRelationsRequestContext> FRAGMENT_ID
        = new FragmentId<>("RESTORE_RELATIONS_REQUEST");
    /**
     * Selected 'FROM' relations names, to perform the operation on.
     */
    private final transient Set<String> fromRelationNames;
    /**
     * Selected 'TO' relations names, to perform the operation on.
     */
    private final transient Set<String> toRelationNames;
    /**
     * Last update date to use (optional).
     */
    private final Date lastUpdate;
    /**
     * For a particular date (as of).
     */
    private final Date forDate;
    /**
     * Set range from.
     */
    private Date validFrom;
    /**
     * Set range to.
     */
    private Date validTo;

    /**
     * Constructor.
     * @param b the builder
     */
    private RestoreRelationsRequestContext(RestoreRelationsRequestContextBuilder b) {
        super(b);
        this.fromRelationNames = b.fromRelationNames;
        this.toRelationNames = b.toRelationNames;
        this.forDate = b.forDate;
        this.lastUpdate = b.lastUpdate;
        this.validFrom = b.validFrom;
        this.validTo = b.validTo;

        flags.set(DataContextFlags.FLAG_BATCH_OPERATION, b.batchOperation);
        flags.set(DataContextFlags.FLAG_IS_PERIOD_RESTORE, b.restorePeriod);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public FragmentId<RestoreRelationsRequestContext> fragmentId() {
        return FRAGMENT_ID;
    }
    /**
     * @return the fromRelationNames
     */
    public Set<String> getFromRelationNames() {
        return Objects.isNull(fromRelationNames) ? Collections.emptySet() : fromRelationNames;
    }
    /**
     * @return the toRelationNames
     */
    public Set<String> getToRelationNames() {
        return Objects.isNull(toRelationNames) ? Collections.emptySet() : toRelationNames;
    }
    /**
     * @return the forDate
     */
    public Date getForDate() {
        return forDate;
    }

    /**
     * @return the lastUpdate
     */
    public Date getLastUpdate() {
        return lastUpdate;
    }
    /**
     * @return define that is period restore request.
     */
    public boolean isPeriodRestore() {
        return flags.get(DataContextFlags.FLAG_IS_PERIOD_RESTORE);
    }
    /**
     * @return the validFrom
     */
    @Override
    public Date getValidFrom() {
        return validFrom;
    }

    /**
     * @return the validTo
     */
    @Override
    public Date getValidTo() {
        return validTo;
    }
    /**
     * The builder.
     * @return builder
     */
    public static RestoreRelationsRequestContextBuilder builder() {
        return new RestoreRelationsRequestContextBuilder();
    }
    /**
     * Builder class.
     * @author Mikhail Mikhailov on May 3, 2020
     */
    public static class RestoreRelationsRequestContextBuilder
        extends AbstractRelationsFromToRequestContextBuilder
            <RestoreFromRelationRequestContext, RestoreToRelationRequestContext, RestoreRelationsRequestContextBuilder> {
        /**
         * Selected 'FROM' relations names, to perform the operation on.
         */
        private Set<String> fromRelationNames;
        /**
         * Selected 'TO' relations names, to perform the operation on.
         */
        private Set<String> toRelationNames;
        /**
         * This context is participating in a batch operation. Collect artifacts instead of upserting immediately.
         */
        private boolean batchOperation;
        /**
         * The TL point.
         */
        private Date forDate;
        /**
         * Last update date to use (optional).
         */
        private Date lastUpdate;
        /**
         * Set range from.
         */
        private Date validFrom;
        /**
         * Set range to.
         */
        private Date validTo;
        /**
         * define that is restore period request.
         */
        private boolean restorePeriod;
        /**
         * Constructor.
         */
        private RestoreRelationsRequestContextBuilder() {
            super();
        }
        /**
         * Selected 'FROM' relations names, to perform the operation on.
         * @param names the relation names
         * @return self
         */
        public RestoreRelationsRequestContextBuilder fromRelationNames(Collection<String> names) {
            if (CollectionUtils.isNotEmpty(names)) {
                fromRelationNames(names.toArray(String[]::new));
            }
            return self();
        }
        /**
         * Selected 'FROM' relations names, to perform the operation on.
         * @param names the relation names
         * @return self
         */
        public RestoreRelationsRequestContextBuilder fromRelationNames(String... names) {
            if (ArrayUtils.isNotEmpty(names)) {
                for (String n : names) {
                    if (Objects.isNull(fromRelationNames)) {
                        fromRelationNames = new HashSet<>(names.length);
                    }

                    if (StringUtils.isBlank(n)) {
                        continue;
                    }

                    fromRelationNames.add(n);
                }
            }
            return self();
        }
        /**
         * Selected 'TO' relations names, to perform the operation on.
         * @param names the relation names
         * @return self
         */
        public RestoreRelationsRequestContextBuilder toRelationNames(Collection<String> names) {
            if (CollectionUtils.isNotEmpty(names)) {
                toRelationNames(names.toArray(String[]::new));
            }
            return self();
        }
        /**
         * Selected 'TO' relations names, to perform the operation on.
         * @param names the relation names
         * @return self
         */
        public RestoreRelationsRequestContextBuilder toRelationNames(String... names) {
            if (ArrayUtils.isNotEmpty(names)) {
                for (String n : names) {
                    if (Objects.isNull(toRelationNames)) {
                        toRelationNames = new HashSet<>(names.length);
                    }

                    if (StringUtils.isBlank(n)) {
                        continue;
                    }

                    toRelationNames.add(n);
                }
            }
            return self();
        }
        /**
         * @param batchOperation the flag
         * @return self
         */
        public RestoreRelationsRequestContextBuilder batchOperation(boolean batchOperation) {
            this.batchOperation = batchOperation;
            return self();
        }
        /**
         * @param lastUpdate the last update to set
         */
        public RestoreRelationsRequestContextBuilder lastUpdate(Date lastUpdate) {
            this.lastUpdate = lastUpdate;
            return self();
        }
        /**
         * @param forDate the forDate to set
         */
        public RestoreRelationsRequestContextBuilder forDate(Date forDate) {
            this.forDate = forDate;
            return self();
        }
        /**
         * @param validFrom the range from to set
         */
        public RestoreRelationsRequestContextBuilder validFrom(Date validFrom) {
            this.validFrom = validFrom;
            return self();
        }
        /**
         * @param validTo the range to to set
         */
        public RestoreRelationsRequestContextBuilder validTo(Date validTo) {
            this.validTo = validTo;
            return self();
        }
        /**
         * define that that is a period restore request.
         * @param periodRestore
         * @return self
         */
        public RestoreRelationsRequestContextBuilder periodRestore(boolean periodRestore) {
            this.restorePeriod = periodRestore;
            return self();
        }
        /**
         * {@inheritDoc}
         */
        @Override
        public RestoreRelationsRequestContext build() {
            return new RestoreRelationsRequestContext(this);
        }
    }
}
