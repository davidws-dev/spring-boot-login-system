# Monolith Authentication System | Spring Boot 3.x & Spring Security 6

Esta é uma implementação de referência de um sistema de autenticação robusto e *stateful* utilizando a arquitetura clássica MVC (Model-View-Controller). O foco principal do projeto é demonstrar a aplicação prática de controlos de segurança avançados, encriptação de dados em repouso e uma arquitetura em camadas altamente testável e de fácil manutenção.

---

## Stack Tecnológica & Decisões Técnicas

* **Java 21 (LTS):** Utilização de features modernas da linguagem (como *Records* para DTOs e *Pattern Matching*).
* **Spring Boot 3.5.x:** Base do ecossistema, garantindo autoconfiguração eficiente e gestão de dependências nativa.
* **Spring Security 6.x:** Implementação da firewall de segurança, proteção contra ataques CSRF (Cross-Site Request Forgery) e gestão de sessões HTTP.
* **Spring Data JPA & Hibernate:** Abstração da camada de persistência com mapeamento objeto-relacional eficiente.
* **H2 Database:** Base de dados em memória configurada para isolamento de contextos de desenvolvimento e testes rápidos.
* **Thymeleaf:** Motor de templates *server-side* que elimina a necessidade de expor APIs REST públicas desnecessariamente para fluxos internos.
* **Tailwind CSS:** Estilização utilitária aplicada dinamicamente via CDN para uma interface limpa, focada em UX.

---

## Arquitetura e Padrões de Desenho (Design Patterns)

O projeto segue uma **Arquitetura em Camadas (Layered Architecture)** estrita, garantindo o princípio da responsabilidade única:

1. **Camada de Apresentação (Controllers):** Responsável por intercetar os pedidos HTTP, gerir as rotas do Thymeleaf e injetar dados no modelo visual.
2. **Camada de Negócio (Services):** Onde residem as regras de validação, fluxos de registo e lógica de segurança (implementa `UserDetailsService`).
3. **Camada de Acesso a Dados (Repositories):** Interface abstrata gerida pelo Spring Data JPA para comunicação SQL com o H2.
4. **Camada de Domínio (Entities):** Modelos de dados de grano fino que representam o esquema da base de dados.

### Boas Práticas Aplicadas:
* **Injeção de Dependências por Construtor:** Evita o acoplamento fraco do `@Autowired` em propriedades, facilitando testes unitários com Mockito.
* **Conventional Commits:** Histórico de Git semântico (`feat:`, `chore:`, `docs:`, `fix:`) para legibilidade de CI/CD.

---

## Mecanismos de Segurança Implementados

* **Hashing Criptográfico:** Armazenamento de passwords utilizando o algoritmo **BCrypt** com fator de custo adaptativo, mitigando ataques de dicionário e *rainbow tables*.
* **Authentication Filter Pipeline:** Interceção explícita de pedidos anónimos com redirecionamento automático para o endpoint de autenticação.
* **Principal Extraction:** Uso da anotação `@AuthenticationPrincipal` para capturar de forma segura os metadados do utilizador autenticado diretamente na sessão HTTP.

---

##  Mapeamento de Rotas (Endpoints)

| Método | Endpoint | Acesso | Descrição |
| :--- | :--- | :--- | :--- |
| **GET** | `/login` | Público | Renderiza o formulário de autenticação. |
| **POST** | `/login` | Público | Processa as credenciais pelo Spring Security. |
| **GET** | `/home` | Autenticado | Dashboard protegido por sessão ativa. |
| **POST** | `/logout` | Autenticado | Invalida a sessão HTTP atual e limpa os cookies. |

---

##  Como Executar o Ambiente Local

### Pré-requisitos
* Java Development Kit (JDK) 21 instalado.
* Maven 3.x ou o Maven Wrapper incluído no projeto.

### 1. Clonar o Repositório
```bash
git clone [https://github.com/davidws-dev/spring-boot-login-system.git](https://github.com/davidws-dev/spring-boot-login-system.git)
cd spring-boot-login-system