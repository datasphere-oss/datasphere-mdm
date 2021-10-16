

package org.datasphere.mdm.core.serialization.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @author Mikhail Mikhailov
 * Local date adapter.
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    /**
     * Constructor.
     */
    public LocalDateAdapter() {
        super();
    }

    /* (non-Javadoc)
     * @see javax.xml.bind.annotation.adapters.XmlAdapter#unmarshal(java.lang.Object)
     */
    @Override
    public LocalDate unmarshal(String v) throws Exception {

        if (Objects.isNull(v)) {
            return null;
        }

        // UN-3876, try date first, then DT, if result is null
        LocalDate result = LocalDate.parse(v, DateTimeFormatter.ISO_DATE);
        if (Objects.isNull(result)) {
            result = LocalDate.parse(v, DateTimeFormatter.ISO_DATE_TIME);
        }

        return result;
    }

    /* (non-Javadoc)
     * @see javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.Object)
     */
    @Override
    public String marshal(LocalDate v) throws Exception {

        if (Objects.isNull(v)) {
            return null;
        }

        return DateTimeFormatter.ISO_LOCAL_DATE.format(v);
    }

}
