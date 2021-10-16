
package org.datasphere.mdm.core.type.lob;

/**
 * @author Mikhail Mikhailov on Feb 20, 2021
 * Acceptance state of an LOB object.
 */
public enum LargeObjectAcceptance {
    /**
     * The record is pending for acceptance
     * and may be a subject to be removed by "orphan LOB cleanup" JOB.
     */
    PENDING,
    /**
     * The record is accepted.
     */
    ACCEPTED;
}
