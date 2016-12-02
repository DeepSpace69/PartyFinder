package main.java.DTOs;

/**
 * Created by Tatka on 28.11.2016.
 */
public class FilterDTO {
    private String type;
    private String value;

    public FilterDTO(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
