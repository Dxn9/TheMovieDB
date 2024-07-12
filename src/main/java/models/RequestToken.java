package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestToken {
    private boolean success;
    private String requestToken;
    private String expiresAt;

    @JsonCreator
    public RequestToken(@JsonProperty("success") boolean success,
                        @JsonProperty("request_token") String requestToken,
                        @JsonProperty("expires_at") String expiresAt) {
        this.success = success;
        this.requestToken = requestToken;
        this.expiresAt = expiresAt;
    }
}
