# Netflix API 2025 - Project Structure

## Directory Structure

### Root Level
```
netflix-api-2025/
├── .amazonq/rules/memory-bank/    # AI assistant memory bank documentation
├── data/                          # H2 database files
│   ├── netflix.mv.db             # Main database file
│   └── netflix.trace.db          # Database trace file
└── netflix/                      # Main Spring Boot application
```

### Main Application Structure
```
netflix/
├── src/main/java/com/avengers/netflix/
│   ├── controller/               # REST API controllers (empty - using view layer)
│   ├── model/                   # JPA entity classes
│   ├── repository/              # Spring Data JPA repositories
│   ├── security/                # Security configuration
│   ├── service/                 # Business logic layer
│   ├── utils/                   # Utility classes
│   ├── view/                    # UI/View layer classes
│   └── NetflixApplication.java  # Main Spring Boot application class
├── src/main/resources/
│   └── application.properties   # Application configuration
├── src/test/java/               # Test classes
├── target/                      # Maven build output
├── pom.xml                      # Maven configuration
└── HELP.md                      # Spring Boot documentation
```

## Core Components and Relationships

### Model Layer (Domain Entities)
- **Usuario**: User entity with authentication and profile data
- **Midia**: Abstract base class for media content
- **Filme**: Movie entity extending Midia
- **Serie**: TV series entity extending Midia
- **Cartao**: Credit card entity for payment processing

### Repository Layer (Data Access)
- **UsuarioRepository**: User data operations
- **FilmeRepository**: Movie data operations
- **SerieRepository**: TV series data operations
- **CartaoRepository**: Payment card data operations

### Service Layer (Business Logic)
- **UsuarioService**: User management and authentication logic
- **MidiaService**: Media catalog management
- **EmailService**: Email notification system

### Security Layer
- **Security**: Spring Security configuration for authentication and authorization

### Utility Layer
- **CriptografiaUtils**: Encryption and decryption utilities
- **TokenUtils**: JWT token management utilities

### View Layer (UI Components)
- **TelaLogin**: Login interface
- **TelaCadastro**: User registration interface
- **TelaCartao**: Payment card management interface
- **TelaCadastroMidia**: Media upload interface
- **TelaConfirmacaoToken**: Token verification interface
- **TelaAtualizaCartao**: Card update interface

## Architectural Patterns

### Layered Architecture
The application follows a traditional layered architecture:
1. **View Layer**: User interface components
2. **Service Layer**: Business logic and operations
3. **Repository Layer**: Data access abstraction
4. **Model Layer**: Domain entities and data structures

### Repository Pattern
Uses Spring Data JPA repositories for clean data access abstraction, providing CRUD operations and custom queries.

### Dependency Injection
Leverages Spring's IoC container for dependency management and loose coupling between components.

### Security Integration
Implements Spring Security for authentication, authorization, and secure endpoint protection.

### Database Integration
Uses H2 in-memory database with JPA/Hibernate for development and testing, with file-based persistence for data retention.