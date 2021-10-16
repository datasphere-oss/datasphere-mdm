

package org.datasphere.mdm.core.po;

/**
 * @author Mikhail Mikhailov
 * Basic distributed stuff.
 */
public abstract class AbstractDistributedPO implements ObjectPO {
    /**
     * Shard.
     */
    public static final String FIELD_SHARD = "shard";
    /**
     * The shard number.
     */
    protected int shard;
    /**
     * Constructor.
     */
    protected AbstractDistributedPO() {
        super();
    }
    /**
     * @return the shard
     */
    public int getShard() {
        return shard;
    }
    /**
     * @param shard the shard to set
     */
    public void setShard(int shard) {
        this.shard = shard;
    }
}
