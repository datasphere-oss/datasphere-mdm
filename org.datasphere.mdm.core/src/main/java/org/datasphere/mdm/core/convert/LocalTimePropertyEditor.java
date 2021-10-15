
package org.datasphere.mdm.core.convert;

import java.beans.PropertyEditorSupport;
import java.time.LocalTime;

import org.datasphere.mdm.system.util.ConvertUtils;

/**
 * @author Mikhail Mikhailov on Jul 10, 2021
 */
public class LocalTimePropertyEditor extends PropertyEditorSupport {
    /**
     * Constructor.
     */
    public LocalTimePropertyEditor() {
        super();
    }
    /**
     * Constructor.
     * @param source
     */
    public LocalTimePropertyEditor(Object source) {
        super(source);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getAsText() {
        return ConvertUtils.localTime2String((LocalTime) super.getValue());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        super.setValue(ConvertUtils.string2LocalTime(text));
    }
}
