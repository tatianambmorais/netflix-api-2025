# Netflix API - Development Guidelines

## Code Quality Standards

### Package Structure and Naming
- **Package Naming**: Use reverse domain notation `com.avengers.netflix.*`
- **Layer Separation**: Maintain clear separation between layers (model, repository, service, view, security, utils)
- **Class Naming**: Use descriptive names that clearly indicate purpose (e.g., `UsuarioService`, `TelaConfirmacaoToken`)

### Import Organization
- **Explicit Imports**: Use specific imports rather than wildcards where possible
- **Standard Order**: Java standard library imports first, then third-party, then project imports
- **Grouping**: Group related imports together (e.g., all model imports, all repository imports)

### Code Formatting Patterns
- **Indentation**: Use tabs for indentation consistently across all files
- **Line Breaks**: Use Windows-style line endings (`\r\n`)
- **Spacing**: Consistent spacing around operators and method parameters
- **Braces**: Opening braces on same line for methods and control structures

## Structural Conventions

### Constructor Injection Pattern (5/5 files)
```java
private final UsuarioRepository usuarioRepository;
private final EmailService emailService;

public UsuarioService(UsuarioRepository usuarioRepository, EmailService emailService) {
    this.usuarioRepository = usuarioRepository;
    this.emailService = emailService;
}
```
- **Mandatory**: Use constructor injection for all dependencies
- **Final Fields**: Mark injected dependencies as `final`
- **No @Autowired**: Rely on Spring's constructor injection without explicit annotation

### Service Layer Patterns (3/5 files)
- **@Service Annotation**: All service classes must be annotated with `@Service`
- **Business Logic Encapsulation**: Keep all business logic within service methods
- **Repository Delegation**: Services should delegate data access to repositories
- **Exception Handling**: Use `IllegalArgumentException` for business rule violations

### Method Naming Conventions (5/5 files)
- **Portuguese Names**: Use Portuguese method names for business operations (`cadastraUsuario`, `confirmaToken`)
- **Descriptive Verbs**: Start with action verbs (`cadastrar`, `confirmar`, `verificar`, `buscar`)
- **Camel Case**: Follow camelCase convention consistently

## Semantic Patterns

### Entity Validation Pattern (2/5 files)
```java
private void verificaSeJaExiste(String email) {
    Usuario usuario = usuarioRepository.findByEmail(email);
    if (usuario != null) {
        throw new IllegalArgumentException("Usuária(o) já cadastrada(o) com este e-mail.");
    }
}
```
- **Validation Methods**: Create private validation methods with descriptive names
- **Early Return**: Validate inputs early and throw exceptions immediately
- **Portuguese Messages**: Use Portuguese for user-facing error messages

### Security Implementation Pattern (3/5 files)
```java
String senhaCriptografada = CriptografiaUtils.sha256(senha);
usuario.setSenhaHash(senhaCriptografada);
usuario.setToken(TokenUtils.generateToken());
```
- **Utility Classes**: Use dedicated utility classes for cryptography and token operations
- **Hash Storage**: Always store hashed passwords, never plain text
- **Token Generation**: Generate unique tokens for user verification

### Transaction Management (1/5 files)
```java
@Transactional
public Usuario adicionarOuRemoverFavorito(String email, Long midiaId) {
    // Method implementation
}
```
- **@Transactional**: Use for methods that modify multiple entities
- **Selective Usage**: Apply only to methods requiring transaction boundaries

## Internal API Usage Patterns

### Repository Pattern Usage (4/5 files)
```java
Usuario usuario = usuarioRepository.findByEmail(email);
usuarioRepository.save(usuario);
```
- **Find Methods**: Use custom finder methods (`findByEmail`, `findByTitulo`)
- **Save Operations**: Always call `save()` after entity modifications
- **Null Checks**: Always check for null returns from repository methods

### Spring Boot Application Structure (1/5 files)
```java
@SpringBootApplication
public class NetflixApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(NetflixApplication.class, args);
        // Bean retrieval and application logic
    }
}
```
- **Bean Retrieval**: Use `context.getBean()` for manual bean access
- **Static State**: Maintain application state in static variables when needed
- **Console Interface**: Implement console-based user interaction in main method

### Security Configuration (1/5 files)
```java
@Configuration
public class Security {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(auth -> auth.requestMatchers("/h2-console/**").permitAll())
            .csrf(csrf -> csrf.disable())
            .build();
    }
}
```
- **@Configuration**: Use for security configuration classes
- **Method Chaining**: Use fluent API for security configuration
- **H2 Console**: Always permit H2 console access in development

## Frequently Used Code Idioms

### Stream API Usage (1/5 files)
```java
List<Midia> filmes = usuario.getFavoritos().stream()
    .filter(midia -> midia instanceof Filme)
    .collect(Collectors.toList());
```
- **Filtering**: Use `instanceof` checks for type-based filtering
- **Collection**: Use `Collectors.toList()` for stream collection

### Optional Handling (1/5 files)
```java
return filmeRepository.findById(id)
    .<Midia>map(filme -> filme)
    .orElseGet(() -> serieRepository.findById(id)
        .<Midia>map(serie -> serie)
        .orElseThrow(() -> new IllegalArgumentException("Mídia não encontrada.")));
```
- **Chaining**: Chain Optional operations for complex lookups
- **Exception Throwing**: Use `orElseThrow()` with descriptive messages

## Popular Annotations

### Core Spring Annotations (5/5 files)
- **@Service**: Mark service layer classes
- **@Component**: Mark view layer classes
- **@Configuration**: Mark configuration classes
- **@SpringBootApplication**: Main application class
- **@Bean**: Factory methods in configuration classes

### Testing Annotations (1/5 files)
- **@SpringBootTest**: Integration test configuration
- **@Test**: JUnit test methods

### Transaction Annotations (1/5 files)
- **@Transactional**: Method-level transaction management

## Console Output Standards
- **Portuguese Messages**: All user-facing messages in Portuguese
- **Formatted Output**: Use consistent formatting for console displays
- **Error Handling**: Provide clear error messages with context
- **System.out.println**: Standard output method for console applications