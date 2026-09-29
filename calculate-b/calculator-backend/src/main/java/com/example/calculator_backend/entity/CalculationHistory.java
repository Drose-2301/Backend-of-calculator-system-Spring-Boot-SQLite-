package com.example.calculator_backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "calculation_history")
public class CalculationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String expression;

    @Column(nullable = false)
    private Double result;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public CalculationHistory() {}

    public CalculationHistory(String expression, Double result) {
        this.expression = expression;
        this.result = result;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getExpression() { return expression; }
    public Double getResult() { return result; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setExpression(String expression) { this.expression = expression; }
    public void setResult(Double result) { this.result = result; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}