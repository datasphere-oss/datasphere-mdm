

package org.datasphere.mdm.core.service.impl;

import java.util.Map.Entry;

import org.datasphere.mdm.core.type.security.SecurityToken;

import com.hazelcast.core.ReadOnly;
import com.hazelcast.map.EntryProcessor;

/**
 * @author Mikhail Mikhailov
 * Bogus entry processor, dedicated to timestamp renewal after near cache reads.
 */
public class TokenTimestampRefresher implements EntryProcessor<String, SecurityToken, String>, ReadOnly {
    /**
     * SVUID.
     */
    private static final long serialVersionUID = 4779553343762697067L;
    /**
     * {@inheritDoc}
     */
    @Override
    public String process(Entry<String, SecurityToken> entry) {
        // We're done with this
        return entry.getKey();
    }
}
