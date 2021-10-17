
package org.datasphere.mdm.data.context;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import org.datasphere.mdm.core.context.ValidityRangeContext;
import org.datasphere.mdm.system.type.pipeline.fragment.FragmentId;
import org.datasphere.mdm.system.type.pipeline.fragment.InputFragment;

/**
 * @author Mikhail Mikhailov
 * Upsert relations request context.
 */
public class UpsertRelationsRequestContext
    extends AbstractRelationsFromRequestContext<UpsertRelationRequestContext>
    implements ValidityRangeContext, InputFragment<UpsertRelationsRequestContext> {
    /**
     * This fragment ID.
     */
    public static final FragmentId<UpsertRelationsRequestContext> FRAGMENT_ID
        = new FragmentId<>("UPSERT_RELATIONS_REQUEST");
    /**
     * Generated SVUID.
     */
    private static final long serialVersionUID = 5342638028065367745L;
     /**
     * Last update date to use (optional).
     */
    private final Date lastUpdate;
    /**
     * Skip cleanse functions.
     */
    private final boolean skipCleanse;
    /**
     * Bypass extension points during upsert.
     */
    private final boolean bypassExtensionPoints;
    /**
     * Set range from.
     */
    private final Date validFrom;
    /**
     * Set range to.
     */
    private final Date validTo;
    /**
     * Override on the supplied validFrom and validTo.
     */
    private final boolean override;
    /**
     * Constructor.
     * @param b the builder
     */
    protected UpsertRelationsRequestContext(UpsertRelationsRequestContextBuilder b) {
        super(b);
        this.lastUpdate = b.lastUpdate;
        this.skipCleanse = b.skipCleanse;
        this.bypassExtensionPoints = b.bypassExtensionPoints;
        this.validFrom = b.validFrom;
        this.validTo = b.validTo;
        this.override = b.override;
    }

    @Override
    public void setOperationId(String operationId) {
        super.setOperationId(operationId);
        if (Objects.nonNull(relationsFrom)) {
            relationsFrom.values().stream()
                    .flatMap(Collection::stream)
                    .forEach(ctx -> ctx.setOperationId(operationId));
        }
    }

    /**
     * @return the lastUpdate
     */
    public Date getLastUpdate() {
        return lastUpdate;
    }

    /**
     * @return the skipCleanse
     */
    public boolean isSkipCleanse() {
        return skipCleanse;
    }

    /**
     * @return the bypassExtensionPoints
     */
    public boolean isBypassExtensionPoints() {
        return bypassExtensionPoints;
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
     * @return the override
     */
    public boolean isOverride() {
        return override;
    }

    @Override
    public FragmentId<UpsertRelationsRequestContext> fragmentId() {
        return FRAGMENT_ID;
    }

    /**
     * Gets new builder.
     * @return builder
     */
    public static UpsertRelationsRequestContextBuilder builder() {
        return new UpsertRelationsRequestContextBuilder();
    }

    /**
     * @author Mikhail Mikhailov
     * Context builder class.
     */
    public static class UpsertRelationsRequestContextBuilder
        extends AbstractRelationsFromRequestContextBuilder<UpsertRelationRequestContext, UpsertRelationsRequestContextBuilder> {
        /**
         * Last update date to use (optional).
         */
        private Date lastUpdate;
        /**
         * Skip cleanse functions.
         */
        private boolean skipCleanse;
        /**
         * Bypass extension points during upsert.
         */
        private boolean bypassExtensionPoints;
        /**
         * Set range from.
         */
        private Date validFrom;
        /**
         * Set range to.
         */
        private Date validTo;
        /**
         * Override on the supplied validFrom and validTo.
         */
        private boolean override;
        /**
         * Constructor.
         */
        protected UpsertRelationsRequestContextBuilder() {
            super();
        }

        /**
         * @param lastUpdate the last update to set
         */
        public UpsertRelationsRequestContextBuilder lastUpdate(Date lastUpdate) {
            this.lastUpdate = lastUpdate;
            return this;
        }

        /**
         * @param skipCleanse skip cleanse or not
         */
        public UpsertRelationsRequestContextBuilder skipCleanse(boolean skipCleanse) {
            this.skipCleanse = skipCleanse;
            return this;
        }

        /**
         * @param bypassExtensionPoints bypass extension points or not
         */
        public UpsertRelationsRequestContextBuilder bypassExtensionPoints(boolean bypassExtensionPoints) {
            this.bypassExtensionPoints = bypassExtensionPoints;
            return this;
        }

        /**
         * @param validFrom the range from to set
         */
        public UpsertRelationsRequestContextBuilder validFrom(Date validFrom) {
            this.validFrom = validFrom;
            return this;
        }

        /**
         * @param validTo the range to to set
         */
        public UpsertRelationsRequestContextBuilder validTo(Date validTo) {
            this.validTo = validTo;
            return this;
        }

        /**
         * Override all missing relations on the supplied period.
         * @param override the override to set
         * @return self
         */
        public UpsertRelationsRequestContextBuilder override(boolean override) {
            this.override = override;
            return this;
        }

        /**
         * Builds a context.
         * @return a new context
         */
        @Override
        public UpsertRelationsRequestContext build() {
            return new UpsertRelationsRequestContext(this);
        }
    }
}
