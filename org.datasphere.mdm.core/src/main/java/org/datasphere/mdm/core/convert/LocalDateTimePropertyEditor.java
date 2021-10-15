
package org.datasphere.mdm.core.convert;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;

import org.datasphere.mdm.system.util.ConvertUtils;

/**
 * @author Mikhail Mikhailov on Jul 10, 2021
 */
public class LocalDateTimePropertyEditor extends PropertyEditorSupport {
    /**
     * Constructor.
     */
    public LocalDateTimePropertyEditor() {
        super();
    }
    /**
     * Constructor.
     * @param source
     */
    public LocalDateTimePropertyEditor(Object source) {
        super(source);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getAsText() {
        return ConvertUtils.localDateTime2String((LocalDateTime) super.getValue());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        super.setValue(ConvertUtils.string2LocalDateTime(text));
    }
}
