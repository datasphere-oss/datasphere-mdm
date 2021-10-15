
package org.datasphere.mdm.core.convert;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;

import org.datasphere.mdm.system.util.ConvertUtils;

/**
 * @author Mikhail Mikhailov on Jul 10, 2021
 */
public class LocalDatePropertyEditor extends PropertyEditorSupport {
    /**
     * Constructor.
     */
    public LocalDatePropertyEditor() {
        super();
    }
    /**
     * Constructor.
     * @param source
     */
    public LocalDatePropertyEditor(Object source) {
        super(source);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getAsText() {
        return ConvertUtils.localDate2String((LocalDate) super.getValue());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        super.setValue(ConvertUtils.string2LocalDate(text));
    }
}
