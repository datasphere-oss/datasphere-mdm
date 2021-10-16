
package org.datasphere.mdm.core.type.model.instance;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.datasphere.mdm.core.type.model.CustomPropertyElement;
import org.datasphere.mdm.core.type.model.ModelInstance;
import org.datasphere.mdm.core.type.model.ModelSource;
import org.datasphere.mdm.core.type.model.VersionedElement;
import org.datasphere.mdm.core.type.model.source.AbstractModelSource;
import org.datasphere.mdm.core.type.model.source.CustomProperty;

/**
 * @author Mikhail Mikhailov on Oct 7, 2020
 * Version info.
 */
public abstract class AbstractModelInstanceImpl<X extends ModelSource>
    extends AbstractNamedDisplayableCustomPropertiesImpl
    implements ModelInstance<X>, VersionedElement {
    /**
     * Current version.
     */
    private final int version;
    /**
     * Create date.
     */
    private final OffsetDateTime createDate;
    /**
     * Created by.
     */
    private final String createdBy;
    /**
     * The storage id.
     */
    private final String storageId;
    /**
     * Constructor.
     * @param m the object
     */
    protected AbstractModelInstanceImpl(AbstractModelSource<?> m) {
        super(m.getName(), m.getDisplayName(), m.getDescription(), m.getCustomProperties());
        this.version = m.getVersion();
        this.createDate = m.getCreateDate();
        this.createdBy = m.getCreatedBy();
        this.storageId = m.getStorageId();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int getVersion() {
        return version;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public OffsetDateTime getCreateDate() {
        return createDate;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getCreatedBy() {
        return createdBy;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getStorageId() {
        return storageId;
    }
    /**
     * Restores CP source view.
     * @return CP source view
     */
    protected Collection<CustomProperty> getSourceCustomProperties() {

        Collection<CustomPropertyElement> cpe = getCustomProperties();
        if (CollectionUtils.isEmpty(cpe)) {
            return Collections.emptyList();
        }

        return getCustomProperties().stream()
                .map(pe -> new CustomProperty()
                        .withName(pe.getName())
                        .withValue(pe.getValue()))
                .collect(Collectors.toList());
    }
}
