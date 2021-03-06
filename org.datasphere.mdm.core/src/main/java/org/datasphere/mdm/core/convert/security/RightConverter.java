

package org.datasphere.mdm.core.convert.security;

import org.apache.commons.collections4.CollectionUtils;
import org.datasphere.mdm.core.dto.RightDTO;
import org.datasphere.mdm.core.dto.SecuredResourceDTO;
import org.datasphere.mdm.core.po.security.ResourcePO;
import org.datasphere.mdm.core.po.security.ResourceRightPO;
import org.datasphere.mdm.core.po.security.RightPO;
import org.datasphere.mdm.core.type.security.Right;
import org.datasphere.mdm.core.type.security.SecuredResourceType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * todo: JavaDoc
 *
 * @author maria.chistyakova
 * @since 31.05.2019
 */
public class RightConverter {


    /**
     * Create.
     */
    public static final String CREATE_LABEL = "CREATE";
    /**
     * Update.
     */
    public static final String UPDATE_LABEL = "UPDATE";
    /**
     * Delete.
     */
    public static final String DELETE_LABEL = "DELETE";
    /**
     * Read.
     */
    public static final String READ_LABEL = "READ";
    /**
     * Convert rights.
     *
     * @param source
     *            the source
     * @return the list
     */
    public static List<Right> convertRightsPoToDto(List<ResourceRightPO> source) {

        if (CollectionUtils.isEmpty(source)) {
            return Collections.emptyList();
        }

        List<Right> target = new ArrayList<>();
        Map<ResourcePO, List<RightPO>> map = new HashMap<>();
        for (ResourceRightPO rr : source) {
            if (map.containsKey(rr.getResource())) {
                map.get(rr.getResource()).add(rr.getRight());
            } else {
                List<RightPO> list = new ArrayList<>();
                list.add(rr.getRight());
                map.put(rr.getResource(), list);
            }
        }

        Set<ResourcePO> pos = map.keySet();
        for (ResourcePO po : pos) {
            RightDTO dto = new RightDTO();
            SecuredResourceDTO ssd = new SecuredResourceDTO();
            ssd.setName(po.getName());
            ssd.setDisplayName(po.getDisplayName());
            ssd.setType(SecuredResourceType.valueOf(po.getRType()));
            ssd.setCategory(po.getCategory());
            dto.setSecuredResource(ssd);
            List<RightPO> list = map.get(po);
            for (RightPO rightPO : list) {
                if (CREATE_LABEL.equals(rightPO.getName())) {
                    dto.setCreate(true);
                } else if (READ_LABEL.equals(rightPO.getName())) {
                    dto.setRead(true);
                } else if (DELETE_LABEL.equals(rightPO.getName())) {
                    dto.setDelete(true);
                } else if (UPDATE_LABEL.equals(rightPO.getName())) {
                    dto.setUpdate(true);
                }
            }
            target.add(dto);
        }

        return target;
    }



}
