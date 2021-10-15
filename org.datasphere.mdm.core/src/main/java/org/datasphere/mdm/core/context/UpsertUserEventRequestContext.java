

/**
 *
 */
package org.datasphere.mdm.core.context;

import org.datasphere.mdm.system.context.CommonRequestContext;

/**
 * @author Mikhail Mikhailov
 * User event.
 */
public class UpsertUserEventRequestContext extends CommonRequestContext {
    /**
     * SVUID.
     */
    private static final long serialVersionUID = -4820373900025554339L;
    /**
     * Type.
     */
    private final String type;
    /**
     * Content.
     */
    private final String content;

    /**
     * Content details.
     */
    private final String details;
    /**
     * User login.
     */
    private final String login;
    /**
     * User id.
     */
    private final Integer userId;
    /**
     * Constructor.
     */
    private UpsertUserEventRequestContext(UpsertUserEventRequestContextBuilder b) {
        super(b);
        this.content = b.content;
        this.details = b.details;
        this.login = b.login;
        this.type = b.type;
        this.userId = b.userId;
    }
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }
    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }
    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }
    /**
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
    }
    /**
     * @return the details
     */
    public String getDetails() {
        return details;
    }
    /**
     *
     * @return builder
     */
    public static UpsertUserEventRequestContextBuilder builder(){
        return new UpsertUserEventRequestContextBuilder();
    }
    /**
     * @author Mikhail Mikhailov
     * Builder class.
     */
    public static class UpsertUserEventRequestContextBuilder extends CommonRequestContextBuilder<UpsertUserEventRequestContextBuilder> {
        /**
         * Type.
         */
        private String type;
        /**
         * Content.
         */
        private String content;
        /**
         * Content details
         */
        private String details;
        /**
         * User login.
         */
        private String login;
        /**
         * User id.
         */
        private Integer userId;
        /**
         * Constructor.
         */
        private UpsertUserEventRequestContextBuilder() {
            super();
        }
        /**
         * Sets type.
         * @param type
         * @return
         */
        public UpsertUserEventRequestContextBuilder type(String type) {
            this.type = type;
            return this;
        }
        /**
         * Sets content.
         * @param content
         * @return
         */
        public UpsertUserEventRequestContextBuilder content(String content) {
            this.content = content;
            return this;
        }
        /**
         * Sets details.
         * @param details
         * @return
         */
        public UpsertUserEventRequestContextBuilder details(String details) {
            this.details = details;
            return this;
        }
        /**
         * Sets login.
         * @param login
         * @return
         */
        public UpsertUserEventRequestContextBuilder login(String login) {
            this.login = login;
            return this;
        }
        /**
         * Sets userId.
         * @param userId
         * @return
         */
        public UpsertUserEventRequestContextBuilder userId(Integer userId) {
            this.userId = userId;
            return this;
        }
        /**
         * Build method.
         * @return
         */
        @Override
        public UpsertUserEventRequestContext build() {
            return new UpsertUserEventRequestContext(this);
        }
    }
}
