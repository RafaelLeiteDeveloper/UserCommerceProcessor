# Projeto de Consumo de Endpoints

## Finalidade do Projeto

O objetivo deste projeto é consumir duas rotas e mapear dados para executar operações em diversos endpoints definidos. Ele foi desenvolvido utilizando a arquitetura hexagonal, que promove uma maior modularidade e facilita a manutenção e evolução do código.

## Arquitetura

### Arquitetura Hexagonal

A arquitetura hexagonal, também conhecida como Ports and Adapters, é uma abordagem que visa separar as responsabilidades do sistema em diferentes camadas, isolando o núcleo da aplicação (domínio) das partes externas (interfaces de usuário, serviços externos, bancos de dados, etc.). Isso permite que o sistema seja mais flexível e testável, já que mudanças em uma camada não afetam diretamente as outras.

**Benefícios da Arquitetura Hexagonal:**

- **Facilidade de Teste:** Permite testar o núcleo da aplicação de forma isolada das dependências externas.
- **Flexibilidade:** Facilita a troca de componentes externos, como bancos de dados ou frameworks de interface, sem alterar o núcleo da aplicação.
- **Manutenibilidade:** Promove um código mais limpo e organizado, facilitando a manutenção e evolução do sistema.

## Como Executar no Docker

Para executar o projeto utilizando Docker, siga os passos abaixo:

1. Certifique-se de que o Docker e o Docker Compose estão instalados na sua máquina.
2. Navegue até o diretório do projeto.
3. Execute o comando:

   ```bash
   docker-compose up
   ```


## Como Executar Localmente

Para executar o projeto localmente, você pode utilizar a sua IDE de preferência (IntelliJ IDEA, Eclipse, etc.) ou o Maven Wrapper incluído no projeto.

### Usando sua IDE

1. Importe o projeto como um projeto Maven na sua IDE.
2. Compile e execute o projeto a partir da IDE.

### Usando Maven Wrapper

1. Navegue até o diretório do projeto.
2. Execute o comando:

   ```bash
   ./mvnw spring-boot:run
   ```

## Como Executar os Testes Unitários

Para executar os testes unitários do projeto, você pode utilizar o Maven Wrapper incluído no projeto. Siga os passos abaixo:

1. Navegue até o diretório do projeto.
2. Execute o comando:

   ```bash
   ./mvnw test


