

package org.datasphere.mdm.core.dao.template;

import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import org.datasphere.mdm.core.exception.CoreExceptionIds;
import org.datasphere.mdm.system.exception.PlatformFailureException;

/**
 * @author Mikhail Mikhailov
 * The template descriptor.
 */
public interface QueryTemplateDescriptor {
    /**
     * The template's code.
     * @return code
     */
    String getCode();
    /**
     * Tells the caller, whether this decriptor denotes a distributed query or not
     * @return true for distributed queries, false otherwise
     */
    boolean isDistributed();

    static<T extends QueryTemplateDescriptor> QueryTemplates toTemplates(T[] values, Map<T, QueryTemplate> map, Properties p) {

        for (int i = 0; i < values.length; i++) {

            T drq = values[i];
            String source = p.getProperty(drq.getCode());
            if (Objects.isNull(source)) {
                throw new PlatformFailureException(
                        "No record query template found for given descriptor [{}]",
                        CoreExceptionIds.EX_DATA_STORAGE_NO_QUERY_TEMPLATE_FOR_DECSRIPTOR,
                        drq.getCode());
            }

            map.put(drq, new QueryTemplate(source));
        }

        return new QueryTemplates(map);
    }
}
