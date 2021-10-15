

package org.datasphere.mdm.core.convert;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

public class UnidataConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

    @Autowired
    @ConverterQualifier
    public void setConverters(Collection<Converter> converters) {
        super.setConverters(new HashSet<>(converters));
    }
}
