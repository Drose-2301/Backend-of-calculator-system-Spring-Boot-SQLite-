package com.example.calculator_backend.service;

import com.example.calculator_backend.entity.CalculationHistory;
import com.example.calculator_backend.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculatorService {

    @Autowired
    private HistoryRepository repository;

    private final ExpressionParser parser = new ExpressionParser();

    /** 计算表达式，成功则保存历史并返回结果 */
    public double calculate(String expression) {
        double result = parser.parse(expression);
        repository.save(new CalculationHistory(expression, result));
        return result;
    }

    public List<CalculationHistory> getHistory() {
        return repository.findAllByOrderByCreatedAtDesc();
    }

    public void deleteHistory(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("记录不存在：id=" + id);
        }
        repository.deleteById(id);
    }

    public void clearHistory() {
        repository.deleteAll();
    }
}
