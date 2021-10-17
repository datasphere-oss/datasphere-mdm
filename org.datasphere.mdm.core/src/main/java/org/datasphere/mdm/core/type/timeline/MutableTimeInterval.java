

package org.datasphere.mdm.core.type.timeline;

import org.datasphere.mdm.core.type.calculables.Calculable;
import org.datasphere.mdm.core.type.calculables.ModificationBox;

/**
 * @author Mikhail Mikhailov
 * Marker interface indicating mutability of period content.
 * Mutator methods are defined by {@link ModificationBox}.
 * Common methods are defined by {@link TimeInterval}.
 */
public interface MutableTimeInterval<C extends Calculable> extends TimeInterval<C>, ModificationBox<C> {}
