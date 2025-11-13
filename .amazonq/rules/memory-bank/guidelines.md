# Netflix API 2025 - Development Guidelines

## Code Quality Standards

### Package Structure and Naming
- **Package Naming**: Follow reverse domain notation: `com.avengers.netflix.*`
- **Layer Organization**: Separate packages by architectural layer (model, repository, service, view, security, utils)
- **Class Naming**: Use descriptive PascalCase names (e.g., `UsuarioService`, `NetflixApplication`)
- **Method Naming**: Use camelCase with descriptive verbs (e.g., `cadastraUsuario`, `confirmaSenha`)

### Code Formatting Patterns
- **Indentation**: Use tabs for indentation consistently
- **Line Breaks**: Windows-style line endings (`\r\n`)
- **Spacing**: Single space after keywords and around operators
- **Braces**: Opening brace on same line for methods and classes
- **Method Parameters**: Long parameter lists broken into multiple lines when needed

### Documentation Standards
- **Minimal Comments**: Code should be self-documenting through clear naming
- **Console Output**: Use `System.out.println()` for user feedback and debugging
- **Error Messages**: Provide clear, user-friendly error messages in Portuguese

## Structural Conventions

### Class Structure Patterns
- **Entity Classes**: Use Lombok annotations (`@Getter`, `@Setter`) for boilerplate reduction
- **Service Classes**: Constructor-based dependency injection with `final` fields
- **Configuration Classes**: Use `@Configuration` and `@Bean` annotations appropriately
- **Application Class**: Main method with Spring context initialization and console-based UI

### Dependency Injection
- **Constructor Injection**: Preferred method for required dependencies
- **Field Declaration**: Use `private final` for injected dependencies
- **Bean Retrieval**: Use `context.getBean()` for manual bean retrieval in main method

### Entity Design
- **JPA Annotations**: Use Jakarta Persistence API (`jakarta.persistence.*`)
- **ID Generation**: Use `@GeneratedValue(strategy = GenerationType.IDENTITY)` for auto-increment
- **Relationships**: Use appropriate JPA relationship annotations (`@OneToMany`, `@ManyToOne`)
- **Cascade Operations**: Use `CascadeType.ALL` and `orphanRemoval = true` for dependent entities

## Semantic Patterns

### Service Layer Patterns
- **Method Naming**: Use Portuguese verbs for business operations (`cadastraUsuario`, `confirmaSenha`)
- **Validation Logic**: Private methods for validation with descriptive names
- **Error Handling**: Console output for error conditions rather than exceptions
- **Business Logic**: Encapsulate complex operations in private helper methods

### Security Implementation
- **Password Handling**: Always hash passwords using utility classes (`CriptografiaUtils.sha256()`)
- **Token Management**: Use utility classes for token generation (`TokenUtils.generateToken()`)
- **Security Configuration**: Disable CSRF for development, permit H2 console access
- **Frame Options**: Disable for H2 console compatibility

### Data Access Patterns
- **Repository Usage**: Leverage Spring Data JPA method naming conventions
- **Entity Persistence**: Use repository `save()` method for both create and update operations
- **Query Methods**: Use method naming conventions like `findByEmail()`

## Frequently Used Code Idioms

### Console-Based UI Pattern
```java
Scanner scanner = new Scanner(System.in);
while(true) {
    // Menu display
    System.out.println("=== Application Name ===");
    // Option handling with string comparison
    if("1".equals(op)) {
        // Action
    }
}
```

### Service Method Pattern
```java
public void businessOperation(parameters) {
    // Validation
    validateInput(parameters);
    
    // Entity creation/modification
    Entity entity = new Entity();
    entity.setProperty(value);
    
    // Persistence
    repository.save(entity);
    
    // Additional operations
    additionalService.performAction(entity);
}
```

### Security Configuration Pattern
```java
@Configuration
public class Security {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(auth -> auth.requestMatchers("/path/**").permitAll())
            .csrf(csrf -> csrf.disable())
            .build();
    }
}
```

## Popular Annotations

### Spring Framework
- `@SpringBootApplication`: Main application class
- `@Service`: Service layer components
- `@Configuration`: Configuration classes
- `@Bean`: Bean definition methods

### JPA/Hibernate
- `@Entity`: JPA entity classes
- `@Id`: Primary key fields
- `@GeneratedValue`: Auto-generated values
- `@OneToMany`: One-to-many relationships

### Lombok
- `@Getter`: Generate getter methods
- `@Setter`: Generate setter methods

### Testing
- `@SpringBootTest`: Integration test configuration
- `@Test`: Test method annotation

## Internal API Usage Patterns

### Repository Pattern
```java
// Constructor injection
private final EntityRepository repository;

// Find operations
Entity entity = repository.findByProperty(value);

// Save operations
repository.save(entity);
```

### Utility Class Usage
```java
// Password encryption
String hashedPassword = CriptografiaUtils.sha256(password);

// Token generation
String token = TokenUtils.generateToken();
```

### Spring Context Usage
```java
ApplicationContext context = SpringApplication.run(Application.class, args);
ServiceClass service = context.getBean(ServiceClass.class);
```

## Best Practices Followed

### Security
- Always hash passwords before storage
- Use token-based authentication
- Disable unnecessary security features for development
- Separate security configuration into dedicated classes

### Data Management
- Use JPA relationships appropriately
- Implement cascade operations for dependent entities
- Use repository pattern for data access
- Validate data before persistence

### Application Architecture
- Maintain clear separation of concerns
- Use dependency injection consistently
- Implement console-based user interaction
- Handle errors gracefully with user feedback