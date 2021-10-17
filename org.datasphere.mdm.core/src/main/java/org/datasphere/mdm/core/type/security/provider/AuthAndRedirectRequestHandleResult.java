
package org.datasphere.mdm.core.type.security.provider;

import java.util.List;
import java.util.Map;

public final class AuthAndRedirectRequestHandleResult extends AuthRequestHandleResult {

    private final RedirectRequestHandleResult redirectTokenCheckResult;

    private AuthAndRedirectRequestHandleResult(
            final RedirectRequestHandleResult redirectTokenCheckResult
    ) {
        this.redirectTokenCheckResult = redirectTokenCheckResult;
    }

    public static AuthAndRedirectRequestHandleResult get(
            final String location
    ) {
        return new AuthAndRedirectRequestHandleResult(RedirectRequestHandleResult.get(location));
    }

    public static AuthAndRedirectRequestHandleResult get(
            final String location,
            final Map<String, List<String>> headers
    ) {
        return new AuthAndRedirectRequestHandleResult(RedirectRequestHandleResult.get(location, headers));
    }

    public RedirectRequestHandleResult getRedirectTokenCheckResult() {
        return redirectTokenCheckResult;
    }
}
