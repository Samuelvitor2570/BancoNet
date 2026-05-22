# BancoNet - Semana 2: Estrutura de Árvore Binária de Busca (BST)

Este diretório contém a implementação da **Semana 2** do BancoNet, com o objetivo de organizar contas bancárias em uma Árvore Binária de Busca (BST) com ordenação baseada no saldo de cada conta.

---

## 📁 Estrutura de Diretórios e Pacotes

A estrutura do projeto segue as convenções do Maven e os princípios de Programação Orientada a Objetos (POO):

```text
PARTE 2/
├── pom.xml                                   # Configuração Maven e dependências (JUnit 5, Java 17)
├── README.md                                 # Este guia de uso
└── src/
    ├── main/
    │   └── java/
    │       └── br/
    │           └── com/
    │               └── banconet/
    │                   ├── model/
    │                   │   ├── Conta.java    # Entidade Conta (id, saldo, agencia)
    │                   │   └── BSTNode.java  # Nó genérico da BST (BSTNode<T>)
    │                   └── service/
    │                       └── BSTConta.java # Implementação das regras da BST e relatórios
    └── test/
        └── java/
            └── br/
                └── com/
                    └── banconet/
                        └── service/
                            └── BSTContaTest.java # Suite de testes unitários com JUnit 5
```

---

## 🚀 Como Rodar os Comandos do Projeto

Para compilar, rodar testes e gerenciar o projeto pelo terminal, siga os passos abaixo. Certifique-se de que o **Java 17** e o **Maven** estejam instalados no seu computador.

### 1. Pelo Terminal (Linux / macOS / Windows)

Abra o seu terminal na pasta do projeto `PARTE 2` e execute:

*   **Compilar as classes de produção:**
    ```bash
    mvn compile
    ```
    *(Garante que não há erros de sintaxe ou tipagem no código)*

*   **Executar a suíte completa de Testes Unitários:**
    ```bash
    mvn test
    ```
    *(Roda todos os testes do JUnit 5 e apresenta o relatório de sucesso/falha no terminal)*

*   **Limpar compilações anteriores e re-testar:**
    ```bash
    mvn clean test
    ```
    *(Recomendado antes de entregar a atividade para garantir que tudo está atualizado)*

### 2. Pelo Visual Studio Code (VS Code)

1.  Abra a pasta `PARTE 2` no VS Code.
2.  Instale o **Extension Pack for Java** (oficial da Microsoft).
3.  No canto inferior ou esquerdo, abra a aba **Testing** (ícone de um frasco de laboratório).
4.  Clique em **Run Tests** ou clique no botão Play verde ao lado do arquivo `BSTContaTest.java`.

### 3. Pelo IntelliJ IDEA (Recomendado para Java)

1.  Abra o IntelliJ.
2.  Clique em **Open** e selecione a pasta `PARTE 2`.
3.  O IntelliJ detectará o arquivo `pom.xml` e configurará o projeto automaticamente.
4.  Abra `BSTContaTest.java` e clique nas setas verdes de Play (ao lado de `class BSTContaTest` ou de cada método individual de teste) para rodar.

---

## ⚡ Resumo de Complexidade Algorítmica e Otimizações

| Método | Complexidade (Caso Médio) | Complexidade (Pior Caso) | Estratégia de Implementação Sênior |
| :--- | :---: | :---: | :--- |
| **`inserir(Conta conta)`** | $O(\log n)$ | $O(n)$ | Inserção padrão de BST recursiva. Saldos maiores ou **iguais** são inseridos à direita, permitindo o correto armazenamento de múltiplos clientes com o mesmo saldo. |
| **`buscarSaldo(double saldo)`** | $O(\log n + k)$ | $O(n)$ | Busca binária clássica. Ao encontrar o valor correspondente, explora apenas o ramo direito recursivamente para coletar todas as contas duplicadas com esse saldo. |
| **`topNMaioresSaldos(int n)`** | $O(\log n + N)$ | $O(n)$ | **Poda inteligente**: Realiza uma travessia em-ordem reversa (Direita-Raiz-Esquerda) e interrompe instantaneamente a recursão ao coletar N elementos, economizando memória e CPU. |
| **`listarInadimplentes()`** | $O(\log n + k)$ | $O(n)$ | **Poda de árvore**: Visita subárvores esquerdas normalmente. No entanto, se o saldo do nó atual for $\ge 100.0$, a subárvore direita dele é totalmente ignorada, pois todos os seus filhos garantidamente têm saldos maiores. |
| **`listarInOrder()`** | $O(n)$ | $O(n)$ | Percurso clássico em-ordem (Esquerda-Raiz-Direita) para coletar a lista de contas perfeitamente ordenada de forma ascendente. |

---

## 🧠 Padrões de Qualidade e POO Aplicados

1.  **Encapsulamento Restrito:** Todos os atributos de classe em `Conta` e `BSTNode` utilizam o modificador `private` com métodos de acesso bem definidos.
2.  **Generics:** O nó `BSTNode<T>` é uma estrutura genérica, o que assegura que o contêiner de dados da árvore não fique acoplado a uma regra de domínio específica, satisfazendo a flexibilidade exigida pelo design de software moderno.
3.  **Tratamento de Exceções:** Métodos críticos contam com validações robustas de parâmetros nulos (ex: `IllegalArgumentException`).
