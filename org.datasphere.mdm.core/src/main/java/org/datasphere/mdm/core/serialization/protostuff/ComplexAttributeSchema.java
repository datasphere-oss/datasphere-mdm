
package org.datasphere.mdm.core.serialization.protostuff;

import java.io.IOException;
import java.util.Objects;

import org.datasphere.mdm.core.serialization.VerifyableComplexAttribute;
import org.datasphere.mdm.core.type.data.ComplexAttribute;
import org.datasphere.mdm.core.type.data.DataRecord;
import org.datasphere.mdm.core.type.data.impl.AbstractAttribute;
import org.datasphere.mdm.system.serialization.protostuff.CommonFieldValues;

import io.protostuff.Input;
import io.protostuff.Output;
import io.protostuff.Schema;

/**
 * @author Mikhail Mikhailov
 * Complex attributes.
 */
public class ComplexAttributeSchema implements Schema<ComplexAttribute> {
    /**
     * {@inheritDoc}
     */
    @Override
    public String getFieldName(int number) {
        return CoreSchemaFields.intToString(number);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int getFieldNumber(String name) {
        return CoreSchemaFields.stringToInt(name);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInitialized(ComplexAttribute message) {

        if (message instanceof VerifyableComplexAttribute) {
            return ((VerifyableComplexAttribute) message).isValid();
        }

        return true;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public ComplexAttribute newMessage() {
        // Should not be called.
        return null;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String messageName() {
        return ComplexAttribute.class.getSimpleName();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String messageFullName() {
        return ComplexAttribute.class.getName();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? super ComplexAttribute> typeClass() {
        return ComplexAttribute.class;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void mergeFrom(Input input, ComplexAttribute message) throws IOException {

        int field;
        while ((field = input.readFieldNumber(this)) != CommonFieldValues.END_OF_RECORD) {

            switch (field) {
            case CoreFieldValues.FIELD_NAME_VAL:
                ((AbstractAttribute) message).setName(input.readString());
                break;
            case CoreFieldValues.DATA_RECORD_VAL:
                message.add(input.mergeObject(null, CoreSchemas.DATA_RECORD_SCHEMA));
                break;
            default:
                break;
            }
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void writeTo(Output output, ComplexAttribute message) throws IOException {

        output.writeString(CoreSchemaFields.FIELD_NAME.getValue(), message.getName(), false);
        if (Objects.isNull(message.isEmpty())) {
            return;
        }

        for (DataRecord child : message) {
            output.writeObject(CoreSchemaFields.DATA_RECORD.getValue(), child, CoreSchemas.DATA_RECORD_SCHEMA, true);
        }
    }
}
