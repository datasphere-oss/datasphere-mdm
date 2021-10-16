

/**
 *
 */
package org.datasphere.mdm.core.po.lob;

/**
 * @author Mikhail Mikhailov
 * Character data.
 */
public class CharacterLargeObjectPO extends LargeObjectPO {
    /**
     * Table name.
     */
    public static final String TABLE_NAME = "character_data";
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBinary() {
        return false;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCharacter() {
        return true;
    }
}
