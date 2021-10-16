

package org.datasphere.mdm.core.dao.vendor;

/**
 * Pg data types.
 * @author Mikhail Mikhailov
 */
public enum VendorDataType {

    BOOLEAN(16),
    BYTEA(17),
    CHAR(18),
    INT8(20),
    INT2(21),
    INT4(23),
    TEXT(25),
    JSONB(114),
    REAL(700), // Float, 4 bytes
    DOUBLE(701),
    INET4(869),
    INET6(869),
    // CIDR(650),
    // UNKNOWN(705),
    // CASH(790),
    // MONEY(791),
    // MAC_ADDRESS(829),
    DATE(1082),
    TIMESTAMP(1114),
    UUID(2950)
    // POINT,
    // BOX,
    // LINE,
    // LINESEGMENT,
    // CIRCLE,
    // PATH,
    // POLYGON,
    // HSTORE,
    // NUMERIC
    ;
    /**
     * Type Oid,
     */
    private final int id;
    /**
     * Constructor.
     * @param id type id
     */
    private VendorDataType(int id) {
        this.id = id;
    }
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
}
