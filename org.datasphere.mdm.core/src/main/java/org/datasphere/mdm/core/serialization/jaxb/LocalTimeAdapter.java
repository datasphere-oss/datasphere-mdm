

package org.datasphere.mdm.core.serialization.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @author Mikhail Mikhailov
 * Local time adapter.
 */
public class LocalTimeAdapter extends XmlAdapter<String, LocalTime> {

    /**
     * Constructor.
     */
    public LocalTimeAdapter() {
        super();
    }

    /* (non-Javadoc)
     * @see javax.xml.bind.annotation.adapters.XmlAdapter#unmarshal(java.lang.Object)
     */
    @Override
    public LocalTime unmarshal(String v) throws Exception {

        if (Objects.isNull(v)) {
            return null;
        }

        // UN-3876, try time first, then DT, if result is null
        LocalTime result = LocalTime.parse(v, DateTimeFormatter.ISO_TIME);
        if (Objects.isNull(result)) {
            result = LocalTime.parse(v, DateTimeFormatter.ISO_DATE_TIME);
        }

        return result;
    }

    /* (non-Javadoc)
     * @see javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.Object)
     */
    @Override
    public String marshal(LocalTime v) throws Exception {

        if (Objects.isNull(v)) {
            return null;
        }

        return DateTimeFormatter.ISO_LOCAL_TIME.format(v);
    }

}
