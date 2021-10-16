

package org.datasphere.mdm.core.po.security;

import java.util.List;

/**
 * @author Denis Kostovarov
 */
public abstract class BaseUserPO extends BaseSecurityPO {
    /**
     * The id.
     */
    private Integer id;
    /**
     * The login.
     */
    private String login;
    /**
     * The S tokens.
     */
    private List<BaseTokenPO> tokens;

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the login.
     *
     * @return the login
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Sets the login.
     *
     * @param login the new login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets the s tokens.
     *
     * @return the s tokens
     */
    public List<BaseTokenPO> getTokens() {
        return this.tokens;
    }

    /**
     * Sets the s tokens.
     *
     * @param tokens the new s tokens
     */
    public void setTokens(List<BaseTokenPO> tokens) {
        this.tokens = tokens;
    }

    /**
     * Adds the s token.
     *
     * @param token the token
     * @return the token po
     */
    public BaseTokenPO addToken(BaseTokenPO token) {
        getTokens().add(token);
        token.setUser(this);

        return token;
    }

    /**
     * Removes the s token.
     *
     * @param token the s token
     * @return the token po
     */
    public TokenPO removeToken(TokenPO token) {
        getTokens().remove(token);
        token.setUser(null);

        return token;
    }

    public static class Fields extends BaseSecurityPO.Fields {

        /**
         * Instantiates a new fields.
         */
        protected Fields() {
            super();
        }


        /** The Constant LOGIN. */
        public static final String LOGIN = "LOGIN";
    }
}
