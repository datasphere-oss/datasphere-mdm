

package org.datasphere.mdm.core.dao;

import java.util.List;
import java.util.Map;

import org.datasphere.mdm.core.po.security.LabelAttributeValuePO;
import org.datasphere.mdm.core.type.security.SecurityLabel;

public interface SecurityLabelDao {
    void saveLabelsForObject(int objectId, List<SecurityLabel> securityLabels);

    List<LabelAttributeValuePO> findLabelsAttributesValuesForObject(int objectId);

    /**
     * Clean users' labels values where label don't assigned to role
     *
     * @param roleName role name
     */
    void cleanUsersLabels(String roleName);

    Map<Integer, List<LabelAttributeValuePO>> fetchObjectsSecurityLabelsValues();
}
