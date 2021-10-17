
package org.datasphere.mdm.data.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Alexey Tsarapkin
 * User favorite etalons Dao
 */
public interface FavoriteEtalonsDAO {

    String CREATED_BY = "created_by";
    String ENTITY_NAME = "entity_name";
    String ETALON_ID = "etalon_id";

    /**
     * add favorite etalons by entity
     *
     * @param favorites
     */
    void add(Map<String, List<UUID>> favorites);

    /**
     * add favorite etalons by entity
     *
     * @param userLogin
     * @param entityName
     * @param favorite
     */
    void add(String userLogin, String entityName, UUID favorite);

    /**
     * remove UUID from all users
     * @param favorite
     */
    void removeUUID(UUID favorite);

    /**
     * exclude favorite by etalon uuid
     *
     * @param favorite
     */
    void exclude(UUID favorite);

    /**
     * exclude favorite by etalon uuid
     *
     * @param favorites
     */
    void exclude(List<UUID> favorites);

    /**
     * exclude all favorites by entity name from current user
     *
     * @param entityName
     */
    void excludeByEntity(String entityName);

    /**
     * remove all favorites by entity name from all users
     *
     * @param entityName
     */
    void removeAllByEntity(String entityName);

    /**
     * check etalon is favorite
     *
     * @param uuid
     * @return
     */
    boolean find(UUID uuid);

    /**
     * get all user favorites
     *
     * @return
     */
    List<UUID> getEtalons();

    /**
     * get all user favorites by entity
     *
     * @param entityName
     * @return
     */
    List<UUID> getEtalons(String entityName);

    /**
     * get all favorites by entity
     *
     * @param entityName
     * @return
     */
    List<UUID> getAllEtalons(String entityName);

    /**
     * get all user favorites
     *
     * @return
     */
    Map<String, List<UUID>> getEtalonsMap();

    /**
     * get all user favorites by entity
     *
     * @param entityName
     * @return
     */
    Map<String, List<UUID>> getEtalonsMap(String entityName);

    /**
     * get all favorites
     *
     * @param handler
     * @param fetchSize
     */
    void getAllFavorites(FavoriteEtalonsRowHandler handler, int fetchSize);

    /**
     * All favorite entity names
     */
    Collection<String> getFavoriteEntityNames();
}
