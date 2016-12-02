package main.java.Core;

/**
 * Created by Tatka on 21.11.2016.
 */
public class ValidationResult {
    private boolean hasErrors;
    private String errorMessage = "";

    public boolean hasErrors() {
        return this.hasErrors;
    }

    public void setErrors(boolean hasErrors, String errorMessage) {
        this.hasErrors = hasErrors;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }
}
