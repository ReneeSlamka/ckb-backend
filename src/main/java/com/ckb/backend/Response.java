package com.ckb.backend;

public class Response {

    public static final String DUPLICATE_EMAIL = "Account creation failed, provided email already in use.";
    public static final String INVALID_CHARS = "Operation failed due to use of invalid characters.";
    public static final String DATABASE_ERROR = "Operation failed due to a database error. Please try again later.";
    public static final String ACCOUNT_DNE = "No account with that email exists.";
    public static final String ACCOUNT_CREATED = "Account creation succeeded.";
    public static final String PW_CHANGED = "Password was successfully changed.";
    public static final String PW_CHANGE_FAILED = "Password change failed because old password was incorrect.";

    private String message;
    private boolean succeeded;

    public Response() {
        super();
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setSucceeded(boolean status) {
        this.succeeded = status;
    }

    public boolean getSucceeded() {
        return this.succeeded;
    }
}
