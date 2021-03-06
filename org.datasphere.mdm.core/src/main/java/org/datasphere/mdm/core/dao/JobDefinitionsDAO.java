
package org.datasphere.mdm.core.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.datasphere.mdm.core.po.job.JobDefinitionPO;
import org.datasphere.mdm.system.dao.BaseDAO;

/**
 * @author Mikhail Mikhailov on Jun 24, 2021
 */
public interface JobDefinitionsDAO extends BaseDAO {
    /**
     * Loads all definitions, stored on the system.
     * @param fields parameters map
     * @param sortBy the ordering field
     * @param sortOrder the order
     * @param limit the limit
     * @param offset the offset
     * @return all definitions, stored on the system
     */
    List<JobDefinitionPO> load(Map<String, Object> fields, String sortBy, String sortOrder, Integer limit, Long offset);
    /**
     * Counts definitions applying filter.
     * @param fields filter
     * @return count
     */
    int count(Map<String, Object> fields);
    /**
     * Load by ids.
     * @param ids the ids
     * @return collection of definitions.
     */
    List<JobDefinitionPO> load(Collection<Long> ids);
    /**
     * Loads all defined tags.
     * @return rags without duplicates
     */
    List<String> loadAllTags();
    /**
     * Inserts a job def into DB.
     * @param po the JDPO
     * @return generated id
     */
    long insert(JobDefinitionPO po);
    /**
     * Updates a job def in DB.
     * @param po the JDPO
     */
    void update(JobDefinitionPO po);
    /**
     * Enables or disables a JD with the given id.
     * @param id the id
     * @param state the state
     */
    void enable(Long id, boolean state);
    /**
     * Put definitions to error state.
     * @param id the ids
     * @param state the state
     */
    void mark(Long id, boolean state);
    /**
     * Remove a definition by id.
     * @param id the id
     */
    void remove(Long id);
}
