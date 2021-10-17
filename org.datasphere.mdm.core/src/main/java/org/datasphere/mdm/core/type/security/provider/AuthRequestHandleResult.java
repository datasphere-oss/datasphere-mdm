

package org.datasphere.mdm.core.type.security.provider;

public class AuthRequestHandleResult implements RequestHandleResult {

    private static final AuthRequestHandleResult INSTANCE = new AuthRequestHandleResult();

    protected AuthRequestHandleResult() {
    }

    public static AuthRequestHandleResult get() {
        return INSTANCE;
    }
}
