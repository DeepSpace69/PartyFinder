package main.java.Core;

/**
 * Created by Tatka on 21.11.2016.
 */
public class OperationResult {
    private int code;
    private Object value;

    public void setCode(int code) {
        this.code = code;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public Object getValue() {
        return value;
    }
}
