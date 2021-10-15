

/**
 *
 */
package org.datasphere.mdm.core.context;

import org.datasphere.mdm.system.context.BooleanFlagsContext;
import org.datasphere.mdm.system.context.StorageCapableContext;
import org.datasphere.mdm.system.context.StorageId;

/**
 * @author Mikhail Mikhailov
 * Is this context capable for notifications?
 * TODO: Check that this interface is still needed. Appears to be pretty useless and even harmful.
 */
public interface NotificationSendableContext extends BooleanFlagsContext, StorageCapableContext {
    /**
     * The notification id SID.
     */
    StorageId SID_NOTIFICATION_ID = new StorageId("NOTIFICATION_ID");
    /**
     * The notification destination SID.
     */
    StorageId SID_NOTIFICATION_DEST = new StorageId("NOTIFICATION_DEST");
    /**
     * Whether notification should be send or not.
     * @return true, if the context should be sent, false otherwise
     */
    default boolean sendNotification() {
        return getFlag(CoreContextFlags.FLAG_SEND_NOTIFICATION);
    }
    /**
     * Send notification using the infos below.
     * @param notificationDestination the destination
     * @param notificationId the id
     */
    default void sendNotification(String notificationDestination, String notificationId) {
        setFlag(CoreContextFlags.FLAG_SEND_NOTIFICATION);
        notificationDestination(notificationDestination);
        notificationId(notificationId);
    }
    /**
     * Tells that a notification should be skipped for this context.
     */
    default void skipNotification() {
        clearFlag(CoreContextFlags.FLAG_SEND_NOTIFICATION);
    }
    /**
     * Gets the destination. This may be JMS replyTo destination queue JNDI name.
     * @return destination name
     */
    default String notificationDestination() {
        return getFromStorage(SID_NOTIFICATION_DEST);
    }
    /**
     * Sets the notification destination.
     * @param destination the destination
     */
    default void notificationDestination(String destination) {
        putToStorage(SID_NOTIFICATION_DEST, destination);
    }
    /**
     * Gets the replay ID. This may be JMS correlationId.
     * @return id
     */
    default String notificationId() {
        return getFromStorage(SID_NOTIFICATION_ID);
    }
    /**
     * Sets the notification id.
     * @param id the notification id
     */
    default void notificationId(String id) {
        putToStorage(SID_NOTIFICATION_ID, id);
    }
    /**
     * Repeat the behaviour.
     * @param notificationSendableContext the behaviour to repeat
     */
    default void repeatNotificationBehavior(NotificationSendableContext notificationSendableContext) {
        notificationDestination(notificationSendableContext.notificationDestination());
        notificationId(notificationSendableContext.notificationId());
        if (notificationSendableContext.sendNotification()) {
            setFlag(CoreContextFlags.FLAG_SEND_NOTIFICATION);
        } else {
            clearFlag(CoreContextFlags.FLAG_SEND_NOTIFICATION);
        }
    }
}
