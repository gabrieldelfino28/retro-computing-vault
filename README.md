<div align="center">

# 🏛️ Retro Computing Vault

![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow?style=for-the-badge)
![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![License](https://img.shields.io/badge/licença-MIT-blue?style=for-the-badge)

**Projeto de Estágio — FATEC Zona Leste**

*Catálogo digital para preservação e gestão de hardware histórico*

</div>

---

## 📖 Sobre o Projeto

O **Retro Computing Vault** é uma aplicação back-end desenvolvida em **Java com Spring Boot** com o objetivo de catalogar e gerenciar hardware histórico — desde computadores clássicos até consoles e dispositivos móveis legados.

O projeto tem foco em boas práticas de **Programação Orientada a Objetos (POO)** e em uma arquitetura limpa e extensível, servindo também como estudo aprofundado de padrões de projeto e persistência com JPA/Hibernate.

---

## ✨ Destaques Técnicos

| Recurso | Descrição |
|---|---|
| 🧩 **Template Method** | Padrão de projeto aplicado nas classes de serviço para centralizar a lógica de negócio e eliminar duplicação de código (princípio *DRY*) |
| 🔧 **Java Generics** | Uso extensivo de generics para criar serviços e repositórios base reutilizáveis e type-safe |
| 🗄️ **Herança no Banco** | Estratégia `JOINED` do JPA para mapear a hierarquia `Hardware → Dispositivo → Computador / Console / DispositivoMóvel` em tabelas separadas e normalizadas |
| 📦 **Repositórios Hierárquicos** | Spring Data JPA com repositórios especializados por entidade, otimizando buscas entre as tabelas de herança |
| 🏷️ **Enums Centralizados** | Enumerações dedicadas (`AppInfo`, `Logger`, `DeviceErr`, `HardwareErr`, `NullErr`) para padronizar mensagens de log e códigos de erro em toda a aplicação |
| ✅ **Fluent Validator** | Validador fluente próprio para validação encadeada e legível das entidades de domínio, com tratamento de erros via `BusinessRuleException` e `GlobalExceptionHandler` |

---

## 🗂️ Hierarquia de Entidades

```
Hardware (base)
└── Dispositivo
    ├── Computador
    ├── Console
    └── DispositivoMovel
```

Cada nível da hierarquia possui sua própria tabela no banco de dados, conectadas via `@Inheritance(strategy = InheritanceType.JOINED)`, garantindo normalização e integridade dos dados.

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Versão | Finalidade |
|---|---|---|
| Java | 17+ | Linguagem principal |
| Spring Boot | 3.x | Framework web e IoC |
| Spring Data JPA | — | Abstração de persistência |
| Hibernate | — | ORM / implementação JPA |
| MySQL | 8.0 | Banco de dados relacional |
| Thymeleaf | — | Template engine para o front-end *(migração para React planejada)* |
| Lombok | — | Redução de boilerplate |

---

## ⚙️ Como Rodar o Projeto

### Pré-requisitos

- [Java 17+](https://adoptium.net/)
- [Maven 3.8+](https://maven.apache.org/)
- [MySQL 8.0](https://dev.mysql.com/downloads/)

### Passo a passo

**1. Clone o repositório**
```bash
git clone https://github.com/seu-usuario/retro-computing-vault.git
cd retro-computing-vault
```

**2. Configure o banco de dados**

Crie um banco no MySQL:
```sql
CREATE DATABASE retro_computing_vault;
```

**3. Configure as variáveis de ambiente**

Edite o arquivo `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/retro_computing_vault
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.jpa.hibernate.ddl-auto=update
```

**4. Execute a aplicação**
```bash
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

---

## 📁 Estrutura do Projeto

```
src/
└── main/
    ├── java/
    │   └── com/retrocomputingvault/
    │       ├── controller/             # Endpoints REST
    │       ├── domain/
    │       │   ├── entity/             # Entidades e hierarquia de herança
    │       │   └── enums/              # Enumerações de domínio
    │       ├── dto/                    # Objetos de transferência de dados
    │       ├── exception/
    │       │   ├── codes/              # Enums de erros (DeviceErr, HardwareErr, NullErr)
    │       │   ├── BusinessRuleException.java
    │       │   ├── ErrorInterface.java
    │       │   └── GlobalExceptionHandler.java
    │       ├── repository/             # Repositórios JPA por entidade
    │       ├── security/               # Configurações de segurança
    │       ├── service/                # Lógica de negócio (Template Method)
    │       └── util/
    │           ├── enums/              # Utilitários (AppInfo, Logger)
    │           └── FluentValidator.java
    └── resources/
        └── application.properties
```

---

## 🚧 Status do Desenvolvimento

O projeto está atualmente **em desenvolvimento** como parte do programa de estágio da FATEC Zona Leste.

- [x] Modelagem da hierarquia de entidades
- [x] Configuração do Spring Boot e JPA
- [x] Implementação do padrão Template Method nos serviços
- [x] Enums centralizados para logs e erros
- [x] Fluent Validator e tratamento global de exceções
- [ ] Endpoints REST para CRUD completo
- [ ] Documentação da API com Swagger/OpenAPI
- [ ] Testes unitários e de integração
- [ ] Migração do front-end de Thymeleaf para React

---

## 📄 Licença

Este projeto está sob a licença MIT. Consulte o arquivo [LICENSE](LICENSE) para mais detalhes.

---

<div align="center">

Desenvolvido com ☕ por **[Gabriel Delfino](https://github.com/gabrieldelfino28)** · FATEC Zona Leste

</div>