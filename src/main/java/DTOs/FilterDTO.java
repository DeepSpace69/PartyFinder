package main.java.DTOs;

/**
 * Created by Tatka on 28.11.2016.
 */
public class FilterDTO {
    private String key;
    private String value;

    public FilterDTO() {
    }

    public FilterDTO(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
