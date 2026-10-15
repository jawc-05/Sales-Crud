[![en](https://img.shields.io/badge/lang-en-blue)](README.md)

# Sistema de Gestão de Vendas

Sistema robusto de gestão de vendas e operações desenvolvido sob a especificação Jakarta EE 10 (Web Profile).

Este projeto simula um ambiente corporativo focado em integridade de dados, com uma arquitetura monolítica fortemente desacoplada em camadas, interface web reativa e persistência relacional garantida por transações gerenciadas pelo container.

A proposta principal é demonstrar boas práticas de engenharia de software corporativa em ambientes Java, incluindo a aplicação de padrões de projeto (como Rich Domain Model e Generic DAO), separação de responsabilidades, transacionalidade de banco de dados e componentização de interface de usuário.

## Arquitetura do sistema

A aplicação segue uma arquitetura estruturada em camadas lógicas bem definidas, garantindo o desacoplamento interno e facilitando a testabilidade e a manutenção:

### 1. View / Controller

Responsável pela interação com o usuário:

- Interface web reativa construída com Jakarta Faces (JSF) e PrimeFaces;
- Atualização parcial de componentes visuais via chamadas AJAX;
- Tratamento de inputs, aplicação de máscaras (CPF/TEL) e sanitização de dados antes do envio ao backend;
- Comunicação direta com a camada de serviços.

### 2. Service

Responsável pela orquestração do negócio:

- Lógica de negócio e coordenação de fluxo;
- Controle transacional automático gerenciado pelo servidor via Jakarta Enterprise Beans (EJB `@Stateless`) e JTA;
- Garantia de segurança e atomicidade das operações de escrita e leitura.

### 3. Domain

Responsável pelo núcleo e regras da aplicação:

- Entidades de negócio baseadas no padrão *Rich Domain Model*;
- Gerenciamento autônomo do workflow e máquina de estados da venda (`STARTED`, `COMPLETED`, `CANCELLED`);
- Cálculos dinâmicos encapsulados (como a recalculação automática de totais baseada na mutação da lista de itens).

### 4. Data Access

Responsável pela persistência e I/O:

- Operações de banco de dados utilizando Jakarta Persistence (JPA) e Hibernate;
- Implementação do padrão *Generic DAO* utilizando Generics do Java;
- Redução significativa de *boilerplate code* e padronização das consultas ao PostgreSQL.

## Fluxo principal

O fluxo de funcionamento do sistema durante o ciclo de vida de um pedido segue a seguinte lógica:

1. O usuário acessa a interface web e insere os dados do cliente e da nova venda;
2. A camada Controller sanitiza as informações e repassa a requisição ao Service;
3. O Service cria a entidade instanciando seu estado inicial (`STARTED`);
4. Ao adicionar produtos, a própria entidade recalcula os totais dinamicamente;
5. O usuário confirma a operação e a venda é finalizada, evoluindo para o estado `COMPLETED`;
6. Modificações futuras nessa venda são bloqueadas arquiteturalmente pela máquina de estados;
7. A transação JTA garante a persistência segura de todas as tabelas afetadas no PostgreSQL via Generic DAO;
8. A interface atualiza o feedback ao usuário de forma reativa e assíncrona.

## Padrões de Projeto Corporativos

O projeto se destaca pelo distanciamento do antipadrão de "modelos anêmicos" e pela eficiência no acesso a dados.

**Rich Domain Model e Máquina de Estados:**
A classe `Sale` (Venda) é responsável por gerenciar a si mesma. A lógica não fica espalhada em serviços superficiais; se um item é adicionado ou removido, a entidade se encarrega de manter a consistência financeira. O workflow impede falhas lógicas, como alterar itens de uma venda já cancelada.

**Generic DAO Pattern:**
A abstração das operações de banco de dados através de Generics cria uma base sólida de leitura/escrita, evitando a criação de dezenas de classes repetitivas (CRUD) e centralizando a manutenção de queries e *statements* do Hibernate.

## Infraestrutura e Deployment

A execução do projeto é baseada em servidores de aplicação Java corporativos.

A infraestrutura e configuração requerem:

- Servidor de Aplicação WildFly / JBoss;
- Pool de conexões (DataSource) previamente configurado via JNDI;
- Instância ativa do PostgreSQL 15+.

O sistema aproveita o `persistence.xml` configurado para `update`, estruturando as tabelas de forma automática no banco de dados durante o primeiro deploy.

## Tecnologias utilizadas

- Java / Jakarta EE 10 (Web Profile);
- Jakarta Faces (JSF);
- PrimeFaces;
- Jakarta Enterprise Beans (EJB);
- Jakarta Persistence (JPA) / Hibernate;
- JTA (Java Transaction API);
- JNDI;
- PostgreSQL 15+;
- WildFly / JBoss;
- Maven.

## Como executar localmente

### Pré-requisitos

- Git;
- Java 17 ou superior;
- Apache Maven;
- PostgreSQL 15+;
- Servidor WildFly (configurado e rodando localmente).

### Configurar Banco de Dados no Servidor

No painel de administração do WildFly, configure um pool de conexões apontando para o seu PostgreSQL com o nome JNDI esperado pelo sistema:

```text
java:jboss/datasources/PostgresDS
```

### Clonar o projeto

```bash
git clone https://github.com/jawc-05/Sales-Crud.git
cd Sales-Crud
```

### Compilar e empacotar

```bash
mvn clean install
```

### Fazer o deploy

1. Localize o arquivo `.war` gerado na pasta `target/` do projeto.
2. Copie este arquivo para o diretório de `deployments` do seu servidor WildFly.
3. Acesse a aplicação na porta padrão web do servidor (geralmente `http://localhost:8080/nome-do-war`).

## Possíveis evoluções

- Adição de testes unitários e de integração com JUnit e Mockito;
- Conteinerização completa com Docker Compose (WildFly + PostgreSQL simultâneos);
- Criação de uma camada de API RESTful com JAX-RS para integração mobile;
- Monitoramento de transações e logs corporativos.

## Autor

Desenvolvido por **João Alfredo Williges Cunha**.

Projeto voltado à demonstração de arquitetura em camadas, engenharia de software e desenvolvimento backend corporativo com ecossistema Java.

## 📝 Licença

Este projeto está sob a licença MIT. Consulte o arquivo [LICENSE](LICENSE) para mais detalhes.
