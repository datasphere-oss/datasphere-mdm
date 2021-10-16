
package org.datasphere.mdm.core.type.keys;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Mikhail Mikhailov
 * LSN - shard number : LSN (local sequence number) as an unattended type.
 */
public class LSN implements Serializable {
    /**
     * SVUID.
     */
    private static final long serialVersionUID = 7881033244701856744L;
    /**
     * The shard number.
     */
    private final int shard;
    /**
     * Local Sequence Number.
     */
    private final long lsn;
    /**
     * Constructor.
     */
    private LSN(int shard, long lsn) {
        super();
        this.shard = shard;
        this.lsn = lsn;
    }
    /**
     * @return the shard
     */
    public int getShard() {
        return shard;
    }
    /**
     * @return the lsn
     */
    public long getLsn() {
        return lsn;
    }
    /**
     * Create instance from numbers.
     * @param shard the shard number
     * @param lsn the lsn
     * @return alias key
     */
    public static LSN of(int shard, long lsn) {
        return new LSN(shard, lsn);
    }
    /**
     * Create instance from string spec.
     * @param spec the string to parse
     * @return alias key
     */
    public static LSN of(String spec) {

        String[] parts = StringUtils.split(spec, ':');
        if (parts == null || parts.length != 2) {
            return null;
        }

        return new LSN(Integer.valueOf(StringUtils.trim(parts[0])), Long.valueOf(StringUtils.trim(parts[1])));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(shard, lsn);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof LSN)) {
            return false;
        }

        LSN other = (LSN) obj;
        return this.shard == other.shard && this.lsn == other.lsn;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new StringBuilder().append("shard = ").append(shard).append(", LSN = ").append(lsn).toString();
    }
}
