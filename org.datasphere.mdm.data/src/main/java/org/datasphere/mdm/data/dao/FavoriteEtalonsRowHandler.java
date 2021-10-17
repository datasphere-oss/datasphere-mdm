
package org.datasphere.mdm.data.dao;

import java.util.UUID;

/**
 * @author Alexey Tsarapkin
 */
@FunctionalInterface
public interface FavoriteEtalonsRowHandler {
    void processRow(String createdBy, UUID uuid);
}
