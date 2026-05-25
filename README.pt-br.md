# Sales-Crud
[![en](https://img.shields.io/badge/lang-en-blue)](README.md)

Sistema robusto de gestão de vendas desenvolvido sob a especificação **Jakarta EE 10**. O projeto foca em arquitetura desacoplada, integridade de dados e aplicação de padrões de projeto corporativos para ambientes Java.

## 🛠 Tech Stack

*   **Runtime:** Jakarta EE 10 (Web Profile).
*   **Componentes de Visão:** Jakarta Faces (JSF) + PrimeFaces.
*   **Lógica de Negócio:** Jakarta Enterprise Beans (EJB) @Stateless.
*   **Persistência:** Jakarta Persistence (JPA) com Hibernate.
*   **Banco de Dados:** PostgreSQL 15+.
*   **Servidor de Aplicação:** WildFly / JBoss (Configurado via JTA/JNDI).
*   **Build Tool:** Maven.

## 🏗 Arquitetura e Highlights Técnicos

*   **Rich Domain Model:** Lógica de negócio encapsulada diretamente nas entidades. A classe `Sale` gerencia seus próprios estados e cálculos, evitando o antipadrão de modelos anêmicos.
*   **Generic DAO Pattern:** Implementação de uma camada de persistência abstrata utilizando Generics, reduzindo significativamente o boilerplate code e padronizando as operações de I/O.
*   **Transacionalidade JTA:** Uso de EJBs sem estado para garantir a atomicidade das operações de escrita, gerenciadas automaticamente pelo container.
*   **Arquitetura em Camadas:** Separação clara entre Domain, DAO, Service e Controller, facilitando a testabilidade e manutenção.

## 🚀 Funcionalidades Chave

*   **Workflow de Vendas:** Máquina de estados integrada (`STARTED`, `COMPLETED`, `CANCELLED`) que bloqueia modificações em pedidos fechados.
*   **Cálculo Dinâmico:** Recalculação automática de valores totais baseada na mutação da lista de itens da venda.
*   **Data Sanitization:** Tratamento de inputs e máscaras (CPF/TEL) diretamente na camada de controle antes da persistência.
*   **UI Reativa:** Interface baseada em AJAX para atualização parcial de componentes, melhorando a experiência do usuário.

## ⚙️ Configuração e Deployment

1.  **DataSource:** Configure o pool de conexões no WildFly apontando para o JNDI `java:jboss/datasources/PostgresDS`.
2.  **Persistência:** O arquivo `persistence.xml` está configurado para `update`, gerando as tabelas automaticamente no primeiro deploy.
3.  **Build:**
    ```bash
    mvn clean install
4.**Deploy:** Copie o arquivo .war gerado em /target para o diretório de deployments do seu servidor.

Desenvolvido por: [João Alfredo Cunha](https://github.com/jawc-05)
