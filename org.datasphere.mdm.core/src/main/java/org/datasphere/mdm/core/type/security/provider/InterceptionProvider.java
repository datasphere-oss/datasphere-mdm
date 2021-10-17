

package org.datasphere.mdm.core.type.security.provider;

import javax.servlet.http.HttpServletRequest;

public interface InterceptionProvider {

    default RequestHandleResult handleRequest(final HttpServletRequest request) {
        return NotAuthRequestHandleResult.get();
    }
}
