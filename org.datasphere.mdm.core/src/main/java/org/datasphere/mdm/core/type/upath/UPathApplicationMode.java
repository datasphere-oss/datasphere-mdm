

package org.datasphere.mdm.core.type.upath;

/**
 * @author Mikhail Mikhailov
 *
 */
public enum UPathApplicationMode {
    /**
     * Apply to all (collect all hits).
     */
    MODE_ALL,
    /**
     * Apply to all (collect all hits) and collect also incomplete paths.
     */
    MODE_ALL_WITH_INCOMPLETE,
    /**
     * Apply once (collect / set to first and exit).
     */
    MODE_ONCE
}
