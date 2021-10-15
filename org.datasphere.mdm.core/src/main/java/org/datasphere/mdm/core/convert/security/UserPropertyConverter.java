

package org.datasphere.mdm.core.convert.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.datasphere.mdm.core.dto.UserPropertyDTO;
import org.datasphere.mdm.core.po.security.UserPropertyPO;
import org.datasphere.mdm.core.po.security.UserPropertyValuePO;
import org.springframework.util.CollectionUtils;

/**
 * @author Mikhail Mikhailov on Sep 24, 2019
 */
public class UserPropertyConverter {

    /**
     * Constructor.
     */
    private UserPropertyConverter() {
        super();
    }

    /**
     * Convert property po to dto.
     *
     * @param propertyPO the property PO
     * @return the user property DTO
     */
    public static UserPropertyDTO convert(UserPropertyPO propertyPO) {

        if (propertyPO == null) {
            return null;
        }

        UserPropertyDTO dto = new UserPropertyDTO();

        dto.setId(propertyPO.getId());
        dto.setRequired(propertyPO.isRequired());
        dto.setName(propertyPO.getName());
        dto.setDisplayName(propertyPO.getDisplayName());

        return dto;
    }

    /**
     * Convert properties po to dto.
     *
     * @param propertyPOs the property P os
     * @return the list
     */
    public static List<UserPropertyDTO> convertPropertyPOs(List<UserPropertyPO> propertyPOs) {
        if (propertyPOs == null) {
            return new ArrayList<>();
        }
        final List<UserPropertyDTO> target = new ArrayList<>();
        propertyPOs.forEach(s -> target.add(convert(s)));
        return target;
    }

    /**
     * Convert property dto to po.
     *
     * @param propertyDTO the property DTO
     * @return the user property PO
     */
    public static UserPropertyPO convert(UserPropertyDTO propertyDTO) {
        if (propertyDTO == null) {
            return null;
        }

        UserPropertyPO po = new UserPropertyPO();
        po.setId(propertyDTO.getId());
        po.setRequired(propertyDTO.isRequired());
        po.setName(StringUtils.trim(propertyDTO.getName()));
        po.setDisplayName(StringUtils.trim(propertyDTO.getDisplayName()));

        return po;
    }

    /**
     * Convert property value po to dto.
     *
     * @param valuePO the value PO
     * @return the user property DTO
     */
    public static UserPropertyDTO convert(UserPropertyValuePO valuePO) {

        if (valuePO == null) {
            return null;
        }

        UserPropertyDTO dto = new UserPropertyDTO();
        if (valuePO.getProperty() != null) {
            dto.setId(valuePO.getProperty().getId());
            dto.setName(valuePO.getProperty().getName());
            dto.setDisplayName(valuePO.getProperty().getDisplayName());
        }

        dto.setValue(valuePO.getValue());
        return dto;
    }

    /**
     * Convert property values po to dto.
     *
     * @param valuePOs the value P os
     * @return the list
     */
    public static List<UserPropertyDTO> convertValuePOs(List<UserPropertyValuePO> valuePOs) {

        if (CollectionUtils.isEmpty(valuePOs)) {
            return Collections.emptyList();
        }

        return valuePOs.stream()
                .map(UserPropertyConverter::convert)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Convert property value dto to po.
     *
     * @param valueDto the value dto
     * @return the user property value PO
     */
    public static UserPropertyValuePO convertValueDTO(final UserPropertyDTO valueDto) {

        if (valueDto == null) {
            return null;
        }

        final UserPropertyPO propertyPO = new UserPropertyPO();
        propertyPO.setName(valueDto.getName());
        propertyPO.setDisplayName(valueDto.getDisplayName());
        propertyPO.setId(valueDto.getId());

        final UserPropertyValuePO valuePO = new UserPropertyValuePO();
        valuePO.setProperty(propertyPO);
        valuePO.setValue(valueDto.getValue());

        return valuePO;
    }

    /**
     * Convert property values dto to po.
     *
     * @param valueDtos the value dtos
     * @return the list
     */
    public static List<UserPropertyValuePO> convertPropertyDTOs(final List<UserPropertyDTO> valueDtos) {

        if (CollectionUtils.isEmpty(valueDtos)) {
            return Collections.emptyList();
        }

        return valueDtos.stream()
                .map(UserPropertyConverter::convertValueDTO)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
