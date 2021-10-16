

package org.datasphere.mdm.core.po.security;

/**
 * @author Denis Kostovarov
 */
public abstract class BaseTokenPO extends BaseSecurityPO {
	/** The id. */
	private Integer id;
	/** The token. */
	private String token;
	/** The user. */
	private BaseUserPO user;

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
	 * @param id
	 *            the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public String getToken() {
		return this.token;
	}

	/**
	 * Sets the token.
	 *
	 * @param token
	 *            the new token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public BaseUserPO getUser() {
		return this.user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user
	 *            the new user
	 */
	public void setUser(BaseUserPO user) {
		this.user = user;
	}

	/**
	 * The Class Fields.
	 */
	public static class Fields extends BaseSecurityPO.Fields {

		/**
		 * Instantiates a new fields.
		 */
		protected Fields() {
			super();
		}

		/** The Constant TOKEN. */
		public static final String TOKEN = "TOKEN";
	}
}
