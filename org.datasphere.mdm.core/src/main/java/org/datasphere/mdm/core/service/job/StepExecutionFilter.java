
/**
 * Date: 29.04.2016
 */

package org.datasphere.mdm.core.service.job;

/**
 * FIXDOC: add file description.
 *
 * @author amagdenko
 */
public class StepExecutionFilter {
    private Long jobExecutionId;
    private Long fromIndex;
    private Integer itemsCount;

    public Long getJobExecutionId() {
        return jobExecutionId;
    }

    public void setJobExecutionId(Long jobExecutionId) {
        this.jobExecutionId = jobExecutionId;
    }

    public Long getFromIndex() {
        return fromIndex;
    }

    public void setFromIndex(Long fromInd) {
        this.fromIndex = fromInd;
    }

    public Integer getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(Integer itemCount) {
        this.itemsCount = itemCount;
    }
}
