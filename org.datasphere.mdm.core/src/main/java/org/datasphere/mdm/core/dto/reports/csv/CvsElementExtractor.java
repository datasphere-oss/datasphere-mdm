

package org.datasphere.mdm.core.dto.reports.csv;

import org.datasphere.mdm.core.dto.reports.ElementExtractor;
import org.datasphere.mdm.core.dto.reports.ReportInfoHolder;

public interface CvsElementExtractor<I extends ReportInfoHolder> extends CvsHeader, ElementExtractor<String, I> {
}
