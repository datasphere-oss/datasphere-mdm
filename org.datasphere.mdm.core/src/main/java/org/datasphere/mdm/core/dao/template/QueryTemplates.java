

package org.datasphere.mdm.core.dao.template;

import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Mikhail Mikhailov
 * The templates holder.
 */
public class QueryTemplates {
    /**
     * The queries.
     */
    private Map<? extends QueryTemplateDescriptor, QueryTemplate> queries;
    /**
     * Constructor.
     */
    public QueryTemplates(Map<? extends QueryTemplateDescriptor, QueryTemplate> queries) {
        super();
        this.queries = queries;
    }

    public String getQuery(QueryTemplateDescriptor d) {
        // NPE or wrong, i. e. distributed query may fail the request here
        QueryTemplate qt = queries.get(d);
        return qt.toSourceQuery();
    }

    public String getQuery(QueryTemplateDescriptor d, int shard) {
        // NPE or wrong, not distributed query may fail the request here
        QueryTemplate qt = queries.get(d);
        return qt.toQuery(shard);
    }

    public void init(int shards) {
        for (Entry<? extends QueryTemplateDescriptor, QueryTemplate> entry : queries.entrySet()) {
            if (entry.getKey().isDistributed()) {
                entry.getValue().init(shards);
            }
        }
    }
}
