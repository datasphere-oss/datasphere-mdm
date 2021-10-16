
package org.datasphere.mdm.core.service.impl.upath;

import java.util.Objects;

import org.datasphere.mdm.core.type.data.DataRecord;
import org.datasphere.mdm.core.type.upath.UPathElementType;
import org.datasphere.mdm.core.type.upath.UPathFilterElement;

/**
 * @author Mikhail Mikhailov on Feb 26, 2021
 * Subscript filter.
 */
public class SubscriptElementImpl extends AbstractElementImpl implements UPathFilterElement {
    /**
     * The record's ordinal (subscript).
     */
    private final int ordinal;
    /**
     * Constructor.
     * @param element the source element
     * @param ordinal the record's ordinal
     */
    public SubscriptElementImpl(String element, int ordinal) {
        super(element, UPathElementType.SUBSCRIPT);
        this.ordinal = ordinal;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean matches(DataRecord record) {
        return Objects.nonNull(record) && record.getOrdinal() == ordinal;
    }
}
