

package org.datasphere.mdm.core.type.security.provider;

public final class NotAuthRequestHandleResult implements RequestHandleResult {
    private static final NotAuthRequestHandleResult INSTANCE = new NotAuthRequestHandleResult();
    private NotAuthRequestHandleResult() {}

    public static NotAuthRequestHandleResult get() {
        return INSTANCE;
    }
}
