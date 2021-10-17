

/**
 *
 */
package org.datasphere.mdm.data.context;

import java.util.Date;
import java.util.List;

import org.datasphere.mdm.meta.type.RelativeDirection;
import org.datasphere.mdm.system.context.CommonRequestContext;


/**
 * @author Mikhail Mikhailov
 *
 */
public class GetRelationsDigestRequestContext extends CommonRequestContext {
    /**
     * SVUID.
     */
    private static final long serialVersionUID = -5174822108533923773L;
    /**
     * Viewport etalon id.
     */
    private final String etalonId;
    /**
     * Relation name.
     */
    private final String relName;
    /**
     * Direction.
     */
    private final RelativeDirection direction;
    /**
     * Page.
     */
    private final int page;
    /**
     * Count on page.
     */
    private final int count;
    /**
     * Return total count or not.
     */
    private final boolean totalCount;
    /**
     * From date to filter relations versions for.
     */
    private final Date from;
    /**
     * To date to filter relations versions for.
     */
    private final Date to;
    /**
     * Return fields.
     */
    private final List<String> fields;
    /**
     * Constructor.
     */
    protected GetRelationsDigestRequestContext(GetRelationsDigestRequestContextBuilder b) {
        super(b);
        this.etalonId = b.etalonId;
        this.count = b.count;
        this.from = b.from;
        this.to = b.to;
        this.direction = b.direction;
        this.fields = b.fields;
        this.page = b.page;
        this.relName = b.relName;
        this.totalCount = b.totalCount;
    }


    /**
     * @return the etalonId
     */
    public String getEtalonId() {
        return etalonId;
    }


    /**
     * @return the relName
     */
    public String getRelName() {
        return relName;
    }


    /**
     * @return the direction
     */
    public RelativeDirection getDirection() {
        return direction;
    }


    /**
     * @return the page
     */
    public int getPage() {
        return page;
    }


    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }


    /**
     * @return the totalCount
     */
    public boolean isTotalCount() {
        return totalCount;
    }


    /**
     * @return the from
     */
    public Date getFrom() {
        return from;
    }



    /**
     * @return the to
     */
    public Date getTo() {
        return to;
    }


    /**
     * @return the fields
     */
    public List<String> getFields() {
        return fields;
    }

    public static GetRelationsDigestRequestContextBuilder builder() {
        return new GetRelationsDigestRequestContextBuilder();
    }

    /**
     * Get relations digest requestbuilder.
     * @author Mikhail Mikhailov
     */
    public static class GetRelationsDigestRequestContextBuilder extends CommonRequestContextBuilder {
        /**
         * Viewport etalon id.
         */
        private String etalonId;
        /**
         * Relation name.
         */
        private String relName;
        /**
         * Direction.
         */
        private RelativeDirection direction;
        /**
         * Page.
         */
        private int page;
        /**
         * Count on page.
         */
        private int count;
        /**
         * Return total count or not.
         */
        private boolean totalCount;
        /**
         * From date to filter relations versions for.
         */
        private Date from;
        /**
         * To date to filter relations versions for.
         */
        private Date to;
        /**
         * Return fields.
         */
        private List<String> fields;
        /**
         * Constructor.
         */
        protected GetRelationsDigestRequestContextBuilder() {
            super();
        }

        /**
         * Viewport etalon id.
         */
        public GetRelationsDigestRequestContextBuilder etalonId(String etalonId) {
            this.etalonId = etalonId;
            return this;
        }
        /**
         * Relation name.
         */
        public GetRelationsDigestRequestContextBuilder relName(String relName) {
            this.relName = relName;
            return this;
        }
        /**
         * Direction.
         */
        public GetRelationsDigestRequestContextBuilder direction(RelativeDirection direction) {
            this.direction = direction;
            return this;
        }
        /**
         * Page.
         */
        public GetRelationsDigestRequestContextBuilder page(int page) {
            this.page = page;
            return this;
        }
        /**
         * Count on page.
         */
        public GetRelationsDigestRequestContextBuilder count(int count) {
            this.count = count;
            return this;
        }
        /**
         * Return total count or not.
         */
        public GetRelationsDigestRequestContextBuilder totalCount(boolean totalCount) {
            this.totalCount = totalCount;
            return this;
        }
        /**
         * From date to filter relations versions for.
         */
        public GetRelationsDigestRequestContextBuilder from(Date from) {
            this.from = from;
            return this;
        }
        /**
         * To date to filter relations versions for.
         */
        public GetRelationsDigestRequestContextBuilder to(Date to) {
            this.to = to;
            return this;
        }
        /**
         * Return fields.
         */
        public GetRelationsDigestRequestContextBuilder fields(List<String> fields) {
            this.fields = fields;
            return this;
        }
        /**
         * Builder method.
         */
        @Override
        public GetRelationsDigestRequestContext build() {
            return new GetRelationsDigestRequestContext(this);
        }
    }
}
