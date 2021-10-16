

package org.datasphere.mdm.core.service;

import javax.annotation.Nonnull;

import org.datasphere.mdm.core.type.data.Attribute;
import org.datasphere.mdm.core.type.data.DataRecord;
import org.datasphere.mdm.core.type.upath.UPath;
import org.datasphere.mdm.core.type.upath.UPathApplicationMode;
import org.datasphere.mdm.core.type.upath.UPathExecutionContext;
import org.datasphere.mdm.core.type.upath.UPathResult;

/**
 * @author Mikhail Mikhailov
 * UPath service.
 */
public interface UPathService {
    /**
     * Creates UPath chain.
     * @param path UPath expression, must not be null
     * @return UPath instance
     * @throws UPathException
     */
    UPath upathCreate(@Nonnull String path);
    /**
     * Evaluates the given UPath against the {@linkplain DataRecord}
     * using {@link UPathExecutionContext#FULL_TREE} context and {@link UPathApplicationMode#MODE_ALL}.
     * @param upath the UPath instance
     * @param record the record
     * @return result
     */
    UPathResult upathGet(UPath upath, DataRecord record);
    /**
     * Evaluates the given UPath against the {@linkplain DataRecord}
     * using {@link UPathExecutionContext#FULL_TREE} context and specified application mode.
     * @param upath the UPath instance, must not be null
     * @param record the record, must not be null
     * @param mode the application mode
     * @return result
     * @throws UPathException
     */
    UPathResult upathGet(@Nonnull UPath upath, @Nonnull DataRecord record, UPathApplicationMode mode);
    /**
     * Evaluates the given UPath against the {@linkplain DataRecord}.
     * @param upath the UPath instance, must not be null
     * @param record the record, must not be null
     * @param context the context to apply
     * @param mode the application mode
     * @return result
     * @throws UPathException
     */
    UPathResult upathGet(@Nonnull UPath upath, @Nonnull DataRecord record, UPathExecutionContext context, UPathApplicationMode mode);
    /**
     * Evaluates the given UPath against the {@linkplain DataRecord} and applies target.
     * @param upath the UPath instance, must not be null
     * @param record the record, must not be null
     * @param target target attribute
     * @param context the context
     * @param mode application mode
     * @return true, if target attribute was applied successfullly at least once
     * @throws UPathException
     */
    boolean upathSet(@Nonnull UPath upath, @Nonnull DataRecord record, Attribute target, UPathExecutionContext context, UPathApplicationMode mode);
    /**
     * Evaluates the given UPath against the {@linkplain DataRecord} and applies target.
     * @param upath the UPath instance, must not be null
     * @param record the record, must not be null
     * @param target target attribute
     * @param mode application mode
     * @return true, if target attribute was applied successfullly at least once
     * @throws UPathException
     */
    boolean upathSet(@Nonnull UPath upath, @Nonnull DataRecord record, Attribute target, UPathApplicationMode mode);
    /**
     * Evaluates the given UPath against the {@linkplain DataRecord} and applies target.
     * @param upath the UPath instance, must not be null
     * @param record the record, must not be null
     * @param target target attribute
     * @return true, if target attribute was applied successfullly at least once
     * @throws UPathException
     */
    boolean upathSet(@Nonnull UPath upath, @Nonnull DataRecord record, Attribute target);
    /**
     * Combines previous two.
     * @param path UPath expression, must not be null
     * @param record the record, must not be null
     * @return result
     * @throws UPathException
     */
    UPathResult upathResult(@Nonnull String path, @Nonnull DataRecord record);
}
