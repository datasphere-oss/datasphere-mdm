

/**
 * Date: 29.04.2016
 */

package org.datasphere.mdm.core.service.job;

import java.util.List;

/**
 * FIXDOC: add file description.
 *
 * @author amagdenko
 */
public class JobExecutionFilter {
    private List<Long> jobInstanceIds;
    private Long fromIndex;
    private Integer itemsCount;

    public List<Long> getJobInstanceIds() {
        return jobInstanceIds;
    }

    public void setJobInstanceIds(List<Long> jobInstanceIds) {
        this.jobInstanceIds = jobInstanceIds;
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
