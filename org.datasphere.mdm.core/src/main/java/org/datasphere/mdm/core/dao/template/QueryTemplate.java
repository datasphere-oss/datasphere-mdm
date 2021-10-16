

package org.datasphere.mdm.core.dao.template;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Mikhail Mikhailov
 * Distributed query generator.
 */
public class QueryTemplate {
    /**
     * Partition mark string.
     */
    public static final String PARTITION_MARK = "${#}";
    /**
     * The template query source.
     */
    private final String source;
    /**
     * "Narrowed" queries, created from the template above.
     */
    private String[] narrowed;
    /**
     * Constructor.
     */
    public QueryTemplate(String source) {
        super();
        this.source = source;
        this.narrowed = new String[0];
    }
    /**
     * (Re-) initializes templated queries.
     * @param shards the number of shards to initialize
     */
    public void init(int shards) {
        narrowed = new String[shards];
        for (int i = 0; i < narrowed.length; i++) {
            narrowed[i] = StringUtils.replace(source, PARTITION_MARK, Integer.toString(i));
        }
    }
    /**
     * Simple here.
     * @param shard the shard number.
     * @return query string
     */
    public String toQuery(int shard) {
        return narrowed[shard];
    }
    /**
     * Not distributed. Return source as is.
     * @return source query
     */
    public String toSourceQuery() {
        return source;
    }
}
