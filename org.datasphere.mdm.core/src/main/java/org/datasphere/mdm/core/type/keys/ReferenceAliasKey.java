
package org.datasphere.mdm.core.type.keys;

/**
 * @author Mikhail Mikhailov
 * Key attribute alias key.
 */
public class ReferenceAliasKey {
    /**
     * Value.
     */
    private final String value;
    /**
     * Attribute name.
     */
    private final String entityAttributeName;
    /**
     * Constructor.
     * @param b builder
     */
    private ReferenceAliasKey(ReferenceAliasKeyBuilder b) {
        super();
        this.value = b.value;
        this.entityAttributeName = b.entityAttributeName;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @return the entityAttributeName
     */
    public String getEntityAttributeName() {
        return entityAttributeName;
    }

    @Override
	public String toString() {
		return new StringBuilder()
				.append("entityAttributeName = ")
				.append(entityAttributeName)
				.append("value = ")
				.append(value)
				.toString();
	}

	/**
     * @return new builder
     */
    public static ReferenceAliasKeyBuilder builder() {
        return new ReferenceAliasKeyBuilder();
    }
    /**
     * @author Mikhail Mikhailov
     * Builder.
     */
    public static class ReferenceAliasKeyBuilder {
        /**
         * Value.
         */
        private String value;
        /**
         * Attribute name.
         */
        private String entityAttributeName;

        /**
         * Constructor.
         */
        public ReferenceAliasKeyBuilder() {
            super();
        }

        /**
         * @param value the value to set
         */
        public ReferenceAliasKeyBuilder value(String value) {
            this.value = value;
            return this;
        }

        /**
         * @param entityAttributeName the entityAttributeName to set
         */
        public ReferenceAliasKeyBuilder entityAttributeName(String entityAttributeName) {
            this.entityAttributeName = entityAttributeName;
            return this;
        }

        /**
         * @return new alias key
         */
        public ReferenceAliasKey build() {
            return new ReferenceAliasKey(this);
        }
    }
}
