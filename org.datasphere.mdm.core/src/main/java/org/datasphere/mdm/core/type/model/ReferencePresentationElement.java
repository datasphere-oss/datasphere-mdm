

package org.datasphere.mdm.core.type.model;

import java.util.Collection;

import javax.annotation.Nonnull;

/**
 * @author Mikhail Mikhailov on Oct 16, 2020
 * Lookup, related etc. display info
 */
public interface ReferencePresentationElement {
    /**
     * Display attributes.
     * @return display attributes
     */
    @Nonnull
    Collection<String> getDisplayAttributes();
    /**
     * Search attributes.
     * @return search attributes
     */
    @Nonnull
    Collection<String> getSearchAttributes();
    /**
     * Show attribute names while building presentation or not.
     * @return true, if attribute names should be shown
     */
    boolean showAttributeNames();
}
