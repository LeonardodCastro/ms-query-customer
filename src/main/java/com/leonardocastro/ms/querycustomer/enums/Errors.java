package com.leonardocastro.ms.querycustomer.enums;


public enum Errors {
    QC201("QC-201", "Invalid field(s)"),
    QC101("QC-101", "Customer [%d] does not exist");

    private final String errorCode;
    private final String message;

    Errors(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getMessage() {
        return this.message;
    }

}
