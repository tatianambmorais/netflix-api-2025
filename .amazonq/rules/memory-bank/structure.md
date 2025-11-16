# Netflix API - Project Structure

## Directory Organization

### Root Structure
```
netflix-api-2025/
├── data/                    # H2 database files
│   ├── netflix.mv.db       # Main database file
│   └── netflix.trace.db    # Database trace logs
└── netflix/                # Main Spring Boot application
```

### Core Application Structure
```
netflix/src/main/java/com/avengers/netflix/
├── controller/             # REST API endpoints (empty - using view layer)
├── model/                  # JPA entities and data models
├── repository/             # Spring Data JPA repositories
├── security/               # Security configuration
├── service/                # Business logic layer
├── utils/                  # Utility classes
├── view/                   # Presentation layer components
└── NetflixApplication.java # Spring Boot main class
```

## Core Components and Relationships

### Model Layer (Domain Entities)
- **Usuario**: User entity with authentication and profile data
- **Midia**: Abstract base class for media content
- **Filme**: Movie entity extending Midia
- **Serie**: TV series entity extending Midia
- **Cartao**: Credit card payment information
- **TipoUsuario**: User role enumeration

### Repository Layer (Data Access)
- **UsuarioRepository**: User data operations
- **FilmeRepository**: Movie data operations
- **SerieRepository**: TV series data operations
- **CartaoRepository**: Payment card data operations

### Service Layer (Business Logic)
- **UsuarioService**: User management and authentication
- **MidiaService**: Media catalog operations
- **CartaoService**: Payment processing
- **EmailService**: Communication services

### View Layer (Presentation)
- **TelaLogin**: User authentication interface
- **TelaCadastro**: User registration interface
- **TelaConfirmacaoToken**: Email verification interface
- **TelaVisualizacaoMidia**: Media browsing interface
- **TelaFavorito**: Favorites management interface
- **TelaCadastroMidia**: Media creation interface
- **TelaAtualizaCartao**: Payment update interface

### Utility Layer
- **CriptografiaUtils**: Encryption and security utilities
- **TokenUtils**: Token generation and validation

### Security Layer
- **Security**: Spring Security configuration

## Architectural Patterns

### Layered Architecture
- Clear separation between presentation (view), business (service), data access (repository), and domain (model) layers
- Dependency injection using Spring's IoC container
- Service-oriented architecture with focused business components

### Repository Pattern
- Spring Data JPA repositories for data access abstraction
- Consistent data access patterns across entities

### MVC-like Pattern
- View components handle presentation logic
- Services contain business logic
- Repositories manage data persistence
- Models represent domain entities

### Security Integration
- Spring Security for authentication and authorization
- Custom token-based verification system
- Encrypted sensitive data storage

## Database Integration
- H2 in-memory database for development
- JPA/Hibernate for ORM mapping
- Persistent storage in data/ directory