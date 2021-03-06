

package org.datasphere.mdm.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.datasphere.mdm.core.service.SecurityService;
import org.datasphere.mdm.core.type.security.Right;
import org.datasphere.mdm.core.type.security.SecurityToken;
import org.datasphere.mdm.core.type.security.impl.BearerToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * The Class BearerTokenAuthenticationProvider.
 */
@Component
public class BearerTokenAuthenticationProvider implements AuthenticationProvider {

   /** The security service. */
    @Autowired
    private SecurityService securityService;
    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.authentication.AuthenticationProvider#
     * authenticate(org.springframework.security.core.Authentication)
     */
    @Override
    public Authentication authenticate(Authentication authentication) {

        BearerToken bearerToken = (BearerToken) authentication;
        // use the token to try to authenticate
        if (authenticateByToken(bearerToken.getCurrentToken(), bearerToken.prolongTTL())) {
            SecurityToken tokenObject = securityService.getTokenObjectByToken(bearerToken.getCurrentToken());
            return new BearerToken(tokenObject, loadGrantedAuthorities(tokenObject));
        } else {
            throw new BadCredentialsException("Security token not valid.");
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.security.authentication.AuthenticationProvider#supports
     * (java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(BearerToken.class);
    }

    /**
     * Authenticate by token.
     *
     * @param token
     *            the token
     * @param prolongTTL whether to prolong token TTL or not
     * @return true, if successful
     * @throws AuthenticationException
     *             the authentication exception
     */
    private boolean authenticateByToken(String token, boolean prolongTTL) {
        try {
            return securityService.validateAndProlongToken(token, prolongTTL);
        } catch (Exception e) {
            throw new BadCredentialsException("Caught exception while validating token.", e);
        }
    }

    /**
     * Load granted authorities.
     *
     * @param token
     *            the token
     * @return the list
     */
    private List<GrantedAuthority> loadGrantedAuthorities(final SecurityToken token) {

        final List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (final Right right : token.getRightsMap().values()) {
            final GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(right.getSecuredResource().getName());
            grantedAuthorities.add(grantedAuthority);
        }

        return grantedAuthorities;
    }
}
