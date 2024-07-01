# 📚 Refatoração de um Sistema de Bibliotecas

## Matéria: Engenharia de Software III
### Professor: André Menolli
### Aluno: André Oliveira

---

### 🎯 Objetivo

O objetivo deste trabalho é refatorar um sistema de gerenciamento de bibliotecas, aplicando os princípios SOLID para melhorar a qualidade do código. A refatoração visa aumentar a manutenibilidade, extensibilidade e robustez do sistema, seguindo as melhores práticas de engenharia de software.

---

### 🛠️ Tecnologias Utilizadas

- **Linguagem de Programação**: Java
- **IDE**: IntelliJ IDEA
- **Controle de Versão**: Git

---

### 🌟 Descrição

O projeto original foi obtido a partir do repositório [Library Management System](https://github.com/mohitme379/Library-Management-System). Este projeto foi escolhido para a refatoração devido à sua complexidade e à presença de diversos code smells, o que proporciona uma excelente oportunidade para a aplicação dos princípios SOLID.

Durante o processo de refatoração, foram identificados e corrigidos diversos code smells, incluindo:

- **Responsabilidade Única (SRP)**: Cada classe passou a ter uma única responsabilidade, facilitando a manutenção e evolução do sistema.
- **Aberto/Fechado (OCP)**: O sistema foi projetado para permitir a extensão de funcionalidades sem a necessidade de modificar o código existente.
- **Substituição de Liskov (LSP)**: Garantimos que as subclasses pudessem substituir as classes base sem causar erros.
- **Segregação de Interfaces (ISP)**: Interfaces foram refatoradas para serem mais específicas aos seus clientes.
- **Inversão de Dependência (DIP)**: Módulos de alto e baixo nível foram desacoplados através da introdução de abstrações.

---

### 📈 Benefícios da Refatoração

- **Melhoria da Manutenibilidade**: Código mais organizado e modular, facilitando a manutenção.
- **Aumento da Extensibilidade**: Novo design que permite a adição de novas funcionalidades sem impactar o código existente.
- **Redução do Acoplamento**: Componentes mais independentes e coesos.
- **Maior Clareza**: Código mais legível e fácil de entender.

---

### 📬 Contato

Para mais informações ou dúvidas, entre em contato através do [email](mailto:andre.oliveira@discente.uenp.edu.br).

---

### 📜 Licença

Este projeto segue a licença do repositório original. Para mais detalhes, consulte o arquivo `LICENSE` no repositório.

---
```
