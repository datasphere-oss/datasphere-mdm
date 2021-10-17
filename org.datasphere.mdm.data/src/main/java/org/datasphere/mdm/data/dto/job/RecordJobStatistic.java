
package org.datasphere.mdm.data.dto.job;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

/**
 * @author maria.chistyakova
 * @since 13.04.2020
 */
public class RecordJobStatistic {
    private long failed;
    private long skipped;
    private long updated;
    private long deleted;

    private Map<String, RecordJobStatistic> any;

    public long getSkipped() {
        return skipped;
    }

    public void setSkipped(long skipped) {
        this.skipped = skipped;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public long getDeleted() {
        return deleted;
    }

    public void setDeleted(long deleted) {
        this.deleted = deleted;
    }

    public long getFailed() {
        return failed;
    }

    public void setFailed(long failed) {
        this.failed = failed;
    }

    public Map<String, RecordJobStatistic> getAny() {
        return any == null ? Collections.emptyMap() : any;
    }

    public void addAny(String k, RecordJobStatistic v) {
        if(this.any == null){
            this.any = new HashMap<>();
        }
        if (StringUtils.isNoneEmpty(k) && Objects.nonNull(v)) {
            this.any.put(k, v);
        }
    }

}
