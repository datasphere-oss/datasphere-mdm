package org.datasphere.mdm.data.configuration;

import org.datasphere.mdm.data.module.DataModule;
import org.datasphere.mdm.data.type.messaging.DataTypes;
import org.datasphere.mdm.system.type.messaging.DomainType;
import org.datasphere.mdm.system.util.ResourceUtils;

/**
 * Data messaging domain(s).
 * @author Mikhail Mikhailov on Jul 15, 2020
 */
public class DataMessagingDomain {
    /**
     * Constructor.
     */
    private DataMessagingDomain() {
        super();
    }
    /**
     * Domain name.
     */
    public static final String NAME = "data";
    /**
     * The sole data domain.
     */
    public static final DomainType DOMAIN =
            DomainType.ofLocalized(NAME, ResourceUtils.asString("classpath:/routes/data.xml"))
                .withDescription(DataModule.MODULE_ID + ".messaging.domain.description")
                .withMessageTypes(DataTypes.values());
}
