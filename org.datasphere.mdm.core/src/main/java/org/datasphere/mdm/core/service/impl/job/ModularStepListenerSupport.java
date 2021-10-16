
package org.datasphere.mdm.core.service.impl.job;

/**
 * @author Mikhail Mikhailov on Jul 12, 2021
 */
public class ModularStepListenerSupport extends ModularJobListenerSupport {
    /**
     * The step name. Null will cause bad things.
     */
    protected final String stepName;
    /**
     * Constructor.
     */
    public ModularStepListenerSupport(String jobName, String stepName) {
        super(jobName);
        this.stepName = stepName;
    }
    /**
     * @return the stepName
     */
    protected String getStepName() {
        return stepName;
    }
}
