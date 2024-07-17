package models;

import lombok.Data;

@Data
public class ListRequest {
    private String name;
    private String description;
    private String language;
}
