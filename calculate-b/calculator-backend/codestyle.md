# Backend Code Style Guide

## Source

The Java code style of this project follows:

- [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)

## Main Conventions

### Naming

- Class names use UpperCamelCase, e.g. `CalculatorService`
- Method and variable names use lowerCamelCase, e.g. `calculateExpression`
- Constants use UPPER_SNAKE_CASE

### Package Structure

\`\`\`
com.example.calculator_backend
├── config       configuration classes
├── controller   API layer
├── service      business logic layer
├── entity       JPA entity classes
├── repository   data access layer
└── dto          data transfer objects
\`\`\`

### Indentation

- Use 4 spaces for indentation
- Opening braces stay on the same line (K&R style)

### Annotations and Dependency Injection

- Use `@Autowired` field injection (the project is small enough for this to be clear and sufficient)
- Controllers return `ResponseEntity` consistently

### Exception Handling

- Handle exceptions in the controller layer with try-catch
- Return 400 for `ArithmeticException` (division by zero) and `IllegalArgumentException` (invalid expression)
- Return 500 for any unexpected exception

### File Encoding

- All Java files must be **UTF-8 without BOM**