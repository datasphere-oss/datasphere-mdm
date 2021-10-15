
package org.datasphere.mdm.core.convert;

import java.beans.PropertyEditorSupport;
import java.time.Instant;

import org.datasphere.mdm.system.util.ConvertUtils;

/**
 * @author Mikhail Mikhailov on Jul 10, 2021
 */
public class InstantPropertyEditor extends PropertyEditorSupport {
    /**
     * Constructor.
     */
    public InstantPropertyEditor() {
        super();
    }
    /**
     * Constructor.
     * @param source
     */
    public InstantPropertyEditor(Object source) {
        super(source);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getAsText() {
        return ConvertUtils.instant2String((Instant) super.getValue());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        super.setValue(ConvertUtils.string2Instant(text));
    }
}
