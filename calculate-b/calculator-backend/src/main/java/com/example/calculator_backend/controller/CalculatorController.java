package com.example.calculator_backend.controller;

import com.example.calculator_backend.dto.CalculateRequest;
import com.example.calculator_backend.dto.CalculateResponse;
import com.example.calculator_backend.entity.CalculationHistory;
import com.example.calculator_backend.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CalculatorController {

    @Autowired
    private CalculatorService service;

    @PostMapping("/calculate")
    public ResponseEntity<CalculateResponse> calculate(@RequestBody CalculateRequest req) {
        try {
            double result = service.calculate(req.getExpression());
            return ResponseEntity.ok(
                    new CalculateResponse(true, req.getExpression(), result, null));
        } catch (ArithmeticException e) {
            return ResponseEntity.badRequest()
                    .body(new CalculateResponse(false, req.getExpression(), null, e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new CalculateResponse(false, req.getExpression(), null, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new CalculateResponse(false, req.getExpression(), null, "服务器内部错误"));
        }
    }

    @GetMapping("/history")
    public List<CalculationHistory> getHistory() {
        return service.getHistory();
    }

    @DeleteMapping("/history/{id}")
    public ResponseEntity<Void> deleteHistory(@PathVariable Long id) {
        try {
            service.deleteHistory(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/history")
    public ResponseEntity<Void> clearHistory() {
        service.clearHistory();
        return ResponseEntity.noContent().build();
    }
}