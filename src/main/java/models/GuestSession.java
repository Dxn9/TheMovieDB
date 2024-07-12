package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GuestSession {
    @JsonProperty("success")
    private boolean success;

    @JsonProperty("guest_session_id")
    private String guestSessionId;

    @JsonProperty("expires_at")
    private String expiresAt;

}
