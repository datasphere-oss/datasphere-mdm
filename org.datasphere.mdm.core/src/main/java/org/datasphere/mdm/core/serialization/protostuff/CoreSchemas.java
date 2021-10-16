

package org.datasphere.mdm.core.serialization.protostuff;

import org.datasphere.mdm.core.type.data.ArrayAttribute;
import org.datasphere.mdm.core.type.data.CodeAttribute;
import org.datasphere.mdm.core.type.data.ComplexAttribute;
import org.datasphere.mdm.core.type.data.DataRecord;
import org.datasphere.mdm.core.type.data.SimpleAttribute;
import org.datasphere.mdm.core.type.formless.BundlesArray;
import org.datasphere.mdm.core.type.formless.DataBundle;

import io.protostuff.Schema;

/**
 * @author Mikhail Mikhailov
 * Just a static schemas holder.
 */
public final class CoreSchemas {
    /**
     * Data bundles array.
     */
    public static final Schema<BundlesArray> BUNDLES_ARRAY_SCHEMA = new BundlesArraySchema();
    /**
     * Record + variables bundle schema.
     */
    public static final Schema<DataBundle> DATA_BUNDLE_SCHEMA = new DataBundleSchema();
    /**
     * Data record schema.
     */
    public static final Schema<DataRecord> DATA_RECORD_SCHEMA = new DataRecordSchema();
    /**
     * Simple attribute schema.
     */
    public static final Schema<SimpleAttribute<?>> SIMPLE_ATTRIBUTE_SCHEMA = new SimpleAttributeSchema();
    /**
     * Code attribute schema.
     */
    public static final Schema<CodeAttribute<?>> CODE_ATTRIBUTE_SCHEMA = new CodeAttributeSchema();
    /**
     * Array attribute schema.
     */
    public static final Schema<ArrayAttribute<?>> ARRAY_ATTRIBUTE_SCHEMA = new ArrayAttributeSchema();
    /**
     * Complex attribute schema.
     */
    public static final Schema<ComplexAttribute> COMPLEX_ATTRIBUTE_SCHEMA = new ComplexAttributeSchema();
    /**
     * Constructor.
     */
    private CoreSchemas() {
        super();
    }
}
