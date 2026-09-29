package com.example.calculator_backend.service;

/**
 * 手写递归下降表达式解析器
 * 支持：+ - * / 、括号、一元正负号、小数
 * 禁止使用 eval / exec（作业要求）
 */
public class ExpressionParser {

    private String input;
    private int pos;

    public double parse(String expr) {
        if (expr == null || expr.isBlank()) {
            throw new IllegalArgumentException("表达式不能为空");
        }
        this.input = expr.replaceAll("\\s+", "");
        this.pos = 0;
        double result = parseExpression();
        if (pos < input.length()) {
            throw new IllegalArgumentException("无效表达式：位置 " + pos + " 处无法识别");
        }
        return result;
    }

    // 处理 + -
    private double parseExpression() {
        double left = parseTerm();
        while (pos < input.length()) {
            char c = input.charAt(pos);
            if (c == '+' || c == '-') {
                pos++;
                double right = parseTerm();
                left = (c == '+') ? left + right : left - right;
            } else {
                break;
            }
        }
        return left;
    }

    // 处理 * /
    private double parseTerm() {
        double left = parseFactor();
        while (pos < input.length()) {
            char c = input.charAt(pos);
            if (c == '*' || c == '/') {
                pos++;
                double right = parseFactor();
                if (c == '/') {
                    if (right == 0) throw new ArithmeticException("除数不能为零");
                    left /= right;
                } else {
                    left *= right;
                }
            } else {
                break;
            }
        }
        return left;
    }

    // 处理括号、一元正负号、数字
    private double parseFactor() {
        if (pos >= input.length()) {
            throw new IllegalArgumentException("表达式不完整");
        }
        char c = input.charAt(pos);

        if (c == '(') {
            pos++;
            double val = parseExpression();
            if (pos >= input.length() || input.charAt(pos) != ')') {
                throw new IllegalArgumentException("括号不匹配");
            }
            pos++;
            return val;
        }
        if (c == '-') {
            pos++;
            return -parseFactor();
        }
        if (c == '+') {
            pos++;
            return parseFactor();
        }
        if (Character.isDigit(c) || c == '.') {
            int start = pos;
            while (pos < input.length()
                    && (Character.isDigit(input.charAt(pos)) || input.charAt(pos) == '.')) {
                pos++;
            }
            String num = input.substring(start, pos);
            try {
                return Double.parseDouble(num);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("无效数字：" + num);
            }
        }
        throw new IllegalArgumentException("无效字符：" + c);
    }
}