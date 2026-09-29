package com.example.calculator_backend.dto;

public class CalculateResponse {
    private boolean success;
    private String expression;
    private Double result;
    private String message;

    public CalculateResponse(boolean success, String expression, Double result, String message) {
        this.success = success;
        this.expression = expression;
        this.result = result;
        this.message = message;
    }

    public boolean isSuccess() { return success; }
    public String getExpression() { return expression; }
    public Double getResult() { return result; }
    public String getMessage() { return message; }
}