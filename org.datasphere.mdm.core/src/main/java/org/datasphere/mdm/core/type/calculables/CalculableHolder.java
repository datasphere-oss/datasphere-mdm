

package org.datasphere.mdm.core.type.calculables;

import java.util.Date;

import org.datasphere.mdm.core.type.data.RecordStatus;
import org.datasphere.mdm.core.type.keys.OriginKey;

/**
 * @author Mikhail Mikhailov
 * Holder for calculation objects.
 */
public interface CalculableHolder<T> extends ModificationBoxKey {
    /**
     * @return the relation
     */
    T getValue();
    /**
     * @return the name
     */
    String getTypeName();
    /**
     * @return the sourceSystem
     */
    String getSourceSystem();
    /**
     * @return the external id (if present)
     */
    String getExternalId();
    /**
     * @return the status
     */
    RecordStatus getStatus();

    /**
     * @return the last update date
     */
    Date getLastUpdate();
    /**
     * Gets the revision of the object hold, if applicable.
     * @return revision (&gt; 0), -1 if not applicable or 0 for new objects
     */
    int getRevision();
    /**
     * Validity period from.
     * @return from
     */
    Date getValidFrom();
    /**
     * Validity period to.
     * @return to
     */
    Date getValidTo();
    /**
     * Tells whether this calculable is an enrichment.
     * @return true, if so, false otherwise
     */
    boolean isEnrichment();
    /**
     * Returns true, if this calculable as a modification to timeline (has revision 0).
     * @return true, if this calculable as a modification to timeline (has revision 0), false otherwise
     */
    default boolean isModification() {
        return getRevision() == 0;
    }
    /**
     * Gets the origin key of the value.
     * This might be useful in situation with lowered types visibility.
     * Returns null for attributes.
     * @return origin key
     */
    OriginKey getOriginKey();
}
