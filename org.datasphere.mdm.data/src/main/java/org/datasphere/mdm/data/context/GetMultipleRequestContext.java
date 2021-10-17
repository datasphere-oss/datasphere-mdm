

package org.datasphere.mdm.data.context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;
import org.datasphere.mdm.system.context.CommonRequestContext;

/**
 * @author Mikhail Mikhailov
 * Get list of golden records.
 */
public class GetMultipleRequestContext extends CommonRequestContext {
    /**
     * SVUID.
     */
    private static final long serialVersionUID = -4403636926092487309L;
    /**
     * Get contexts
     */
    private final List<GetRequestContext> contexts;
    /**
     * Entity name.
     */
    private final String entityName;
    /**
     * For a particular date (as of).
     */
    private final Date forDate;
    /**
     * lud
     */
    private final Date lastUpdateDate;
    /**
     * userName
     */
    private final String userName;
    /**
     * Constructor.
     */
    protected GetMultipleRequestContext(GetMultipleRequestContextBuilder b) {
        super(b);
        this.entityName = b.entityName;
        this.forDate = b.forDate;
        this.lastUpdateDate = b.lastUpdateDate;
        this.userName = b.userName;
        this.contexts = new ArrayList<>();
        contexts.addAll(b.etalonKeys.stream()
                .map(etalonId -> GetRequestContext.builder()
                        .entityName(b.entityName)
                        .etalonKey(etalonId)
                        .forDate(b.forDate)
                        .forLastUpdate(b.lastUpdateDate)
                        .includeInactive(b.includeInactive)
                        .fetchOrigins(b.fetchOrigins)
                        .fetchOriginsHistory(b.fetchOriginsHistory)
                        .build())
                .collect(Collectors.toList()));

        contexts.addAll(b.etalonsForDates.stream()
                .map(etalon -> GetRequestContext.builder()
                        .entityName(b.entityName)
                        .etalonKey(etalon.getLeft())
                        .forDate(etalon.getRight())
                        .forLastUpdate(b.lastUpdateDate)
                        .includeInactive(b.includeInactive)
                        .loadDataForPeriod(b.loadDataForPeriod)
                        .fetchOrigins(b.fetchOrigins)
                        .fetchOriginsHistory(b.fetchOriginsHistory)
                        .build())
                .collect(Collectors.toList()));
        this.contexts.forEach(ct -> ct.setOperationId(getOperationId()));
    }

    /**
     * @return the etalonKeys
     */
    public List<GetRequestContext> getInnerGetContexts() {
        return contexts;
    }

    /**
     * @return the entityName
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * @return the forDate
     */
    public Date getForDate() {
        return forDate;
    }

    /**
     *
     * @return lud
     */
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     *
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * The context is based on a golden key.
     * @return true if so, false otherwise
     */
    public boolean isValid() {
        return !this.contexts.isEmpty();
    }

    /**
     * Gets builder.
     * @return builder
     */
    public static GetMultipleRequestContextBuilder builder() {
        return new GetMultipleRequestContextBuilder();
    }
    /**
     * @author Mikhail Mikhailov
     * Context builder.
     */
    public static class GetMultipleRequestContextBuilder extends CommonRequestContextBuilder<GetMultipleRequestContextBuilder> {
        /**
         * Golden key.
         */
        private List<String> etalonKeys = Collections.emptyList();
        /**
         * Entity name.
         */
        private String entityName;
        /**
         * For a particular date (as of).
         */
        private Date forDate;
        /**
         * lud
         */
        private Date lastUpdateDate;
        /**
         * Include inactive
         */
        private boolean includeInactive;

        private boolean loadDataForPeriod;

        /**
         * Return fetchOrigins or not.
         */
        private boolean fetchOrigins;
        /**
         * Return fetchOrigins history or not.
         */
        private boolean fetchOriginsHistory;
        /**
         * Golden key.
         */
        private List<Pair<String, Date>> etalonsForDates = Collections.emptyList();
        /**
         * User name.
         */
        private String userName;
        /**
         * Constructor.
         */
        protected GetMultipleRequestContextBuilder() {
            super();
        }

        /**
         * @param goldenKeys the etalonKeys to set
         */
        public GetMultipleRequestContextBuilder etalonKeys(List<String> goldenKeys) {
            this.etalonKeys = goldenKeys;
            return this;
        }

        /**
         * @param entityName the entityName to set
         */
        public GetMultipleRequestContextBuilder entityName(String entityName) {
            this.entityName = entityName;
            return this;
        }

        /**
         * @param forDate the forDate to set
         */
        public GetMultipleRequestContextBuilder forDate(Date forDate) {
            this.forDate = forDate;
            return this;
        }

        /**
         *
         * @param lud lud
         * @return self
         */
        public GetMultipleRequestContextBuilder lastUpdateDate(Date lud){
            this.lastUpdateDate = lud;
            return this;
        }

        /**
         * @param loadDataForPeriod load data for period
         */
        public GetMultipleRequestContextBuilder loadDataForPeriod(boolean loadDataForPeriod) {
            this.loadDataForPeriod = loadDataForPeriod;
            return this;
        }

        /**
         * @param includeInactive
         * @return self
         */
        public GetMultipleRequestContextBuilder includeInactive(boolean includeInactive){
            this.includeInactive = includeInactive;
            return this;
        }

        /**
         * @param etalonsForDates the etalon for dates  to set
         */
        public GetMultipleRequestContextBuilder etalonsForDates(List<Pair<String, Date>> etalonsForDates) {
            this.etalonsForDates = etalonsForDates;
            return this;
        }

        /**
         *
         * @param userName the user name to set
         */
        public GetMultipleRequestContextBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }

        /**
         * @param fetchOrigins the fetchOrigins to set
         */
        public GetMultipleRequestContextBuilder fetchOrigins(boolean fetchOrigins) {
            this.fetchOrigins = fetchOrigins;
            return this;
        }

        /**
         * @param fetchOriginsHistory the fetchOriginsHistory to set
         */
        public GetMultipleRequestContextBuilder fetchOriginsHistory(boolean fetchOriginsHistory) {
            this.fetchOriginsHistory = fetchOriginsHistory;
            return this;
        }
        /**
         * Builds a context.
         * @return a new context
         */
        @Override
        public GetMultipleRequestContext build() {
            return new GetMultipleRequestContext(this);
        }
    }
}
