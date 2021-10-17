

package org.datasphere.mdm.data.context;

import java.util.Objects;

/**
 * @author Mikhail Mikhailov
 * A utility class to be implemented by listeners.
 */
public interface RecordIdentityContextSupport {
    /**
     * Selects name either from context or from keys.
     * @param ctx context
     * @return name or null
     */
    default String selectEntityName(RecordIdentityContext ctx) {

        if (Objects.nonNull(ctx)) {

            if (ctx.getEntityName() != null) {
                return ctx.getEntityName();
            } else {
                if (ctx.keys() != null) {

                    if (ctx.keys().getEntityName() != null) {
                        return ctx.keys().getEntityName();
                    }

                    return ctx.keys().getOriginKey() != null
                            ? ctx.keys().getOriginKey().getEntityName()
                            : null;
                }
            }
        }

        return null;
    }
}
