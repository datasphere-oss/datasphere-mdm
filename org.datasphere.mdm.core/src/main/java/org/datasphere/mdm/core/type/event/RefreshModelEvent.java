

package org.datasphere.mdm.core.type.event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.datasphere.mdm.system.type.event.AbstractMulticastEvent;

/**
 * This event is sent when other nodes have to notified that they have to reload model.
 * @author Mikhail Mikhailov on Oct 28, 2019
 */
public class RefreshModelEvent extends AbstractMulticastEvent {
    /**
     * GSVUID.
     */
    private static final long serialVersionUID = 807674465478762451L;
    /**
     * This type name.
     */
    private static final String TYPE_NAME = "MODEL_REFRESH_EVENT";
    /**
     * The tuples to refresh.
     */
    private final ArrayList<RefreshModelTuple> tuples;
    /**
     * Constructor.
     * @param typeName
     * @param id
     */
    public RefreshModelEvent(Collection<RefreshModelTuple> payload) {
        super(TYPE_NAME);
        tuples = new ArrayList<>(payload);
    }
    /**
     * Gets the payload.
     * @return payload
     */
    public List<RefreshModelTuple> getPayload() {
        return Objects.nonNull(tuples) ? tuples : Collections.emptyList();
    }
    /**
     * @author Mikhail Mikhailov on Oct 29, 2020
     */
    public static final class RefreshModelTuple implements Serializable {
        /**
         * GSVUID.
         */
        private static final long serialVersionUID = 418430367514674068L;
        /**
         * The model type id.
         */
        private final String typeId;
        /**
         * The storage id.
         */
        private final String storageId;
        /**
         * The model instance id.
         */
        private final String instanceId;
        /**
         * Constructor.
         * @param typeId the model type id
         * @param storageId the storage id
         * @param instanceId the model instance id
         */
        public RefreshModelTuple(String typeId, String storageId, String instanceId) {
            super();
            this.typeId = typeId;
            this.storageId = storageId;
            this.instanceId = instanceId;
        }
        /**
         * @return the typeId
         */
        public String getTypeId() {
            return typeId;
        }
        /**
         * @return the storageId
         */
        public String getStorageId() {
            return storageId;
        }
        /**
         * @return the instanceId
         */
        public String getInstanceId() {
            return instanceId;
        }
    }
}
