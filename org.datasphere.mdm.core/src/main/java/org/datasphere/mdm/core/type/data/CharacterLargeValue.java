

package org.datasphere.mdm.core.type.data;

/**
 * @author Mikhail Mikhailov
 * Character large value.
 */
public interface CharacterLargeValue extends LargeValue {
    /**
     * {@inheritDoc}
     */
    @Override
    default ValueType getValueType() {
        return ValueType.CLOB;
    }
}
