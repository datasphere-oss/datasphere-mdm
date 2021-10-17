

package org.datasphere.mdm.data.dto;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.datasphere.mdm.system.type.pipeline.batch.BatchedPipelineOutput;
import org.datasphere.mdm.system.type.pipeline.fragment.FragmentId;
import org.datasphere.mdm.system.type.pipeline.fragment.OutputFragment;

/**
 * The all-in-one bulk ops result transfer object.
 * @author Dmitry Kopin on 14.02.2019.
 */
public class RecordsBulkResultDTO extends AbstractBulkResultDTO implements BatchedPipelineOutput, OutputFragment<RecordsBulkResultDTO> {
    /**
     * The id.
     */
    public static final FragmentId<RecordsBulkResultDTO> ID = new FragmentId<>("RECORDS_BULK_RESULT");
    /**
     * Upserted info.
     */
    private List<UpsertRecordDTO> upsertRecords;
    /**
     * Deleted info.
     */
    private List<DeleteRecordDTO> deleteRecords;
    /**
     * Merge info.
     */
    private List<MergeRecordsDTO> mergeRecords;
    /**
     * Restore info.
     */
    private List<RestoreRecordDTO> restoreRecords;
    /**
     * {@inheritDoc}
     */
    @Override
    public FragmentId<RecordsBulkResultDTO> fragmentId() {
        return ID;
    }
    /**
     * Gets upserted.
     * @return upserted
     */
    public List<UpsertRecordDTO> getUpsertRecords() {
        return Objects.isNull(upsertRecords) ? Collections.emptyList() : upsertRecords;
    }
    /**
     * Set upserted.
     * @param records
     */
    public void setUpsertRecords(List<UpsertRecordDTO> records) {
        this.upsertRecords = records;
    }
    /**
     * @return the deleteRecords
     */
    public List<DeleteRecordDTO> getDeleteRecords() {
        return Objects.isNull(deleteRecords) ? Collections.emptyList() : deleteRecords;
    }
    /**
     * @param deleteRecords the deleteRecords to set
     */
    public void setDeleteRecords(List<DeleteRecordDTO> deleteRecords) {
        this.deleteRecords = deleteRecords;
    }
    /**
     * @return the mergeRecords
     */
    public List<MergeRecordsDTO> getMergeRecords() {
        return Objects.isNull(mergeRecords) ? Collections.emptyList() : mergeRecords;
    }
    /**
     * @param mergeRecords the mergeRecords to set
     */
    public void setMergeRecords(List<MergeRecordsDTO> mergeRecords) {
        this.mergeRecords = mergeRecords;
    }
    /**
     * @return the restoreRecords
     */
    public List<RestoreRecordDTO> getRestoreRecords() {
        return Objects.isNull(restoreRecords) ? Collections.emptyList() : restoreRecords;
    }
    /**
     * @param restoreRecords the restoreRecords to set
     */
    public void setRestoreRecords(List<RestoreRecordDTO> restoreRecords) {
        this.restoreRecords = restoreRecords;
    }
}
