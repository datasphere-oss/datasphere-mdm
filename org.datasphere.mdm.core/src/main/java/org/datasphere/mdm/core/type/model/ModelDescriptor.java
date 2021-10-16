
package org.datasphere.mdm.core.type.model;

import java.util.Objects;

/**
 * @author Mikhail Mikhailov on Oct 8, 2020
 * The model descriptor.
 */
public final class ModelDescriptor<I extends ModelInstance<?>> {
    /**
     * ID/name.
     */
    private final String typeId;
    /**
     * Instance implementing class.
     */
    private final Class<I> instance;
    /**
     * Constructor.
     * @param typeId the model type name/ID
     */
    public ModelDescriptor(String typeId, Class<I> instance) {
        super();
        Objects.requireNonNull(typeId, "Model type id must not be null.");
        this.typeId = typeId;
        this.instance = instance;
    }
    /**
     * @return the name
     */
    public String getModelTypeId() {
        return typeId;
    }
    /**
     * @return the instance
     */
    public Class<I> getInstance() {
        return instance;
    }
    /**
     * Shortcut.
     * @param <I> instance type
     * @param name the model type id
     * @param klass the class
     * @return descriptor
     */
    public static<I extends ModelInstance<?>> ModelDescriptor<I> of(String name, Class<I> klass) {
        return new ModelDescriptor<>(name, klass);
    }
}
