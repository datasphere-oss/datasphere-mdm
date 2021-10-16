
package org.datasphere.mdm.core.service.impl.upath;

import java.util.Objects;
import java.util.function.Predicate;

import org.datasphere.mdm.core.type.data.DataRecord;
import org.datasphere.mdm.core.type.upath.UPathElementType;
import org.datasphere.mdm.core.type.upath.UPathFilterElement;

/**
 * @author Mikhail Mikhailov on Feb 26, 2021
 * Generic predicate filter.
 */
public class PredicateElementImpl extends AbstractElementImpl implements UPathFilterElement {
    /**
     * The presicate.
     */
    private final Predicate<DataRecord> predicate;
    /**
     * Constructor.
     * @param element the source element
     * @param predicate filtering predicate
     */
    public PredicateElementImpl(String element, Predicate<DataRecord> predicate) {
        super(element, UPathElementType.EXPRESSION);
        this.predicate = predicate;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean matches(DataRecord record) {
        return Objects.nonNull(record) && predicate.test(record);
    }
}
