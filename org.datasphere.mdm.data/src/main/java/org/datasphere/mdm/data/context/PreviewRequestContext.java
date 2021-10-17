

/**
 *
 */
package org.datasphere.mdm.data.context;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.datasphere.mdm.data.service.segments.records.merge.RecordPreviewStartExecutor;
import org.datasphere.mdm.data.type.data.OriginRecord;
import org.datasphere.mdm.system.context.SetupAwareContext;
import org.datasphere.mdm.system.type.pipeline.PipelineInput;

/**
 * @author Mikhail Mikhailov
 * Merge request context.
 */
public class PreviewRequestContext
    extends AbstractRecordIdentityContext
    implements
        ReadWriteTimelineContext<OriginRecord>,
        ReadWriteDataContext<OriginRecord>,
        MergeDuplicatesContext<OriginRecord>,
        SetupAwareContext,
        AccessRightContext,
        ExtendedAttributesAwareContext,
        PipelineInput {
    /**
     * Generated SVUID.
     */
    private static final long serialVersionUID = -1140437951331151093L;
    /**
     * Duplicates list.
     */
    private final List<AbstractRecordIdentityContext> duplicates;
    /**
     * In place edit attributes.
     */
    private final Map<String, String> attributes;
    /**
     * For a particular date (as of).
     */
    private final Date forDate;
    /**
     * Has updates (new versions) after this date.
     */
    private final Date updatesAfter;
    /**
     * Last update date to cut off versions.
     */
    private final Date forLastUpdate;
    /**
     * Operation id.
     */
    private final String forOperationId;
    /**
     * Constructor.
     */
    protected PreviewRequestContext(PreviewRequestContextBuilder b) {
        super(b);
        this.duplicates = b.duplicates;
        this.attributes = b.attributes;
        this.forDate = b.forDate;
        this.updatesAfter = b.updatesAfter;
        this.forLastUpdate = b.forLastUpdate;
        this.forOperationId = b.forOperationId;

        flags.set(DataContextFlags.FLAG_INCLUDE_WINNERS, true);
    }
    /**
     * @return the duplicates
     */
    public List<AbstractRecordIdentityContext> getDuplicates() {
        return Objects.isNull(duplicates) ? Collections.emptyList() : duplicates;
    }
    /**
     * @return the attributes
     */
    public Map<String, String> getAttributes() {
        return Objects.isNull(attributes) ? Collections.emptyMap() : attributes;
    }
    /**
     * @return the forDate
     */
    public Date getForDate() {
        return forDate;
    }

    /**
     * @return the updatesAfter
     */
    public Date getUpdatesAfter() {
        return updatesAfter;
    }

    /**
     * @return the lastUpdate
     */
    public Date getForLastUpdate() {
        return forLastUpdate;
    }

    /**
     * @return the forOperationId
     */
    public String getForOperationId() {
        return forOperationId;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getStartTypeId() {
        return RecordPreviewStartExecutor.SEGMENT_ID;
    }
    /**
     * Builder shorthand.
     * @return builder
     */
    public static PreviewRequestContextBuilder builder() {
        return new PreviewRequestContextBuilder();
    }
    /**
     * @author Mikhail Mikhailov
     * Builder for the context.
     */
    public static class PreviewRequestContextBuilder extends AbstractRecordIdentityContextBuilder<PreviewRequestContextBuilder> {
        /**
         * Duplicates list.
         */
        private List<AbstractRecordIdentityContext> duplicates;
        /**
         * In place edit attributes.
         */
        private Map<String, String> attributes;
        /**
         * For a particular date (as of).
         */
        private Date forDate;
        /**
         * Has updates (new versions) after this date.
         */
        private Date updatesAfter;
        /**
         * Last update date to cut off versions.
         */
        private Date forLastUpdate;
        /**
         * Operation id.
         */
        private String forOperationId;

        protected PreviewRequestContextBuilder() {
            super();
        }
        /**
         * Sets the duplicates list.
         * @param duplicates the list to set
         * @return self
         */
        public PreviewRequestContextBuilder duplicates(Collection<? extends AbstractRecordIdentityContext> duplicates) {
            if (CollectionUtils.isNotEmpty(duplicates)) {
                duplicates(duplicates.toArray(AbstractRecordIdentityContext[]::new));
            }
            return self();
        }
        /**
         * Sets the duplicates list.
         * @param duplicates the list to set
         * @return self
         */
        public PreviewRequestContextBuilder duplicates(AbstractRecordIdentityContext... duplicates) {
            if (ArrayUtils.isNotEmpty(duplicates)) {
                for (int i = 0; i < duplicates.length; i++) {
                    duplicate(duplicates[i]);
                }
            }
            return self();
        }
        /**
         * Sets the duplicates list.
         * @param duplicates the list to set
         * @return self
         */
        public PreviewRequestContextBuilder duplicate(AbstractRecordIdentityContext duplicate) {
            if (Objects.nonNull(duplicate)) {
                if (Objects.isNull(this.duplicates)) {
                    this.duplicates = new ArrayList<>();
                }

                this.duplicates.add(duplicate);
            }
            return self();
        }
        /**
         * Sets the duplicates list.
         * @param duplicates the list to set
         * @return self
         */
        public PreviewRequestContextBuilder attributes(Map<String, String> attributes) {
            if (MapUtils.isNotEmpty(attributes)) {
                attributes.forEach(this::attribute);
            }
            return self();
        }
        /**
         * Sets the duplicates list.
         * @param duplicates the list to set
         * @return self
         */
        public PreviewRequestContextBuilder attribute(String key, String value) {
            if (StringUtils.isNotBlank(key)) {
                if (Objects.isNull(this.attributes)) {
                    this.attributes = new HashMap<>();
                }

                this.attributes.put(key, value);
            }
            return self();
        }

        /**
         * @param forDate the forDate to set
         */
        public PreviewRequestContextBuilder forDate(Date forDate) {
            this.forDate = forDate;
            return self();
        }

        /**
         * @param updatesAfter the updatesAfter to set
         */
        public PreviewRequestContextBuilder updatesAfter(Date updatesAfter) {
            this.updatesAfter = updatesAfter;
            return self();
        }

        /**
         * @param forOperationId the forOperationId to set
         */
        public PreviewRequestContextBuilder forOperationId(String forOperationId) {
            this.forOperationId = forOperationId;
            return self();
        }

        /**
         * Sets last update date to the context.
         * @param lastUpdate the date
         * @return self
         */
        public PreviewRequestContextBuilder forLastUpdate(Date lastUpdate) {
            this.forLastUpdate = lastUpdate;
            return self();
        }
        /**
         * The build method.
         * @return new merge context
         */
        @Override
        public PreviewRequestContext build() {
            return new PreviewRequestContext(this);
        }
    }
}
