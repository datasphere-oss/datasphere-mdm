
package org.datasphere.mdm.core.dto;

import java.time.LocalDateTime;

public class PasswordDTO extends BaseSecurityDTO {
    /**
     * SVUID.
     */
    private static final long serialVersionUID = -5612881974042201325L;

    private final UserDTO user;
    private final String hashedText;
    private final String text;
    private final LocalDateTime createAt;
    private final boolean isActive;

    public PasswordDTO(UserDTO user, String hashedText, boolean isActive, LocalDateTime createAt) {
        this.user = user;
        this.hashedText = hashedText;
        this.isActive = isActive;
        this.text = null;
        this.createAt = createAt;
    }

    public PasswordDTO(UserDTO user, String hashedText, String text, boolean isActive, LocalDateTime createAt) {
        this.user = user;
        this.hashedText = hashedText;
        this.text = text;
        this.isActive = isActive;
        this.createAt = createAt;
    }

    public UserDTO getUser() {
        return user;
    }

    public String getHashedText() {
        return hashedText;
    }

    public String getText() {
        return text;
    }

    public boolean isActive() {
        return isActive;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }
}
