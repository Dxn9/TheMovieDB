package models;

import lombok.Data;

@Data
public class RatingRequest {
    private double value;

    public RatingRequest(double value) {
        this.value = value;
    }
}
