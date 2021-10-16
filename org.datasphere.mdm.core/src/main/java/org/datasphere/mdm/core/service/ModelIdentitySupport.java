
package org.datasphere.mdm.core.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.datasphere.mdm.core.context.ModelIdentityContext;
import org.datasphere.mdm.core.type.event.RefreshModelEvent.RefreshModelTuple;
import org.datasphere.mdm.core.util.SecurityUtils;
import org.datasphere.mdm.system.type.pipeline.fragment.InputFragmentContainer;

/**
 * @author Mikhail Mikhailov on Oct 27, 2020
 * Ids collector.
 */
public interface ModelIdentitySupport {
    /**
     * Collects model ids recursively.
     * @param ctx the input
     * @return map
     */
    default Map<String, Map<String, Set<String>>> collectAsMap(ModelIdentityContext ctx) {

        Map<String, Map<String, Set<String>>> result = new LinkedHashMap<>();
        result
            .computeIfAbsent(ctx.getTypeId(), k -> new HashMap<String, Set<String>>())
            .computeIfAbsent(SecurityUtils.getStorageId(ctx), k -> new HashSet<String>())
            .add(ctx.getInstanceId());

        if (ctx instanceof InputFragmentContainer) {
            ((InputFragmentContainer) ctx).recursive(f -> {

                if (f instanceof ModelIdentityContext) {

                    ModelIdentityContext mcc = (ModelIdentityContext) f;
                    result
                        .computeIfAbsent(mcc.getTypeId(), k -> new HashMap<String, Set<String>>())
                        .computeIfAbsent(SecurityUtils.getStorageId(mcc), k -> new HashSet<String>())
                        .add(mcc.getInstanceId());
                }
            });
        }

        return result;
    }

    default Collection<RefreshModelTuple> collectAsTuples(ModelIdentityContext ctx) {

        List<RefreshModelTuple> result = new ArrayList<>();

        result.add(new RefreshModelTuple(ctx.getTypeId(), SecurityUtils.getStorageId(ctx), ctx.getInstanceId()));
        if (ctx instanceof InputFragmentContainer) {
            ((InputFragmentContainer) ctx).recursive(f -> {

                if (f instanceof ModelIdentityContext) {
                    ModelIdentityContext mcc = (ModelIdentityContext) f;
                    result.add(new RefreshModelTuple(mcc.getTypeId(), SecurityUtils.getStorageId(mcc), mcc.getInstanceId()));
                }
            });
        }

        return result;
    }
}
