# 📦 API Gestão de Pedidos

API REST desenvolvida com **Spring Boot** para gerenciamento de pedidos, permitindo operações completas de criação, consulta, atualização e remoção de dados.

Este projeto foi desenvolvido com foco em boas práticas de desenvolvimento back-end, organização em camadas e construção de APIs escaláveis.

---

## 🚀 Tecnologias Utilizadas

* Java
* Spring Boot
* Spring Web
* Spring Data JPA *(se estiver usando)*
* Hibernate *(se estiver usando)*
* Banco de Dados Relacional *(ex: SQL Server ou H2)*
* Maven

---

## 📂 Estrutura do Projeto

O projeto segue o padrão de arquitetura em camadas:

* **Controller** → Responsável pelos endpoints da API
* **Service** → Contém as regras de negócio
* **Repository** → Comunicação com o banco de dados
* **Model/Entity** → Representação dos dados

---

## 🔗 Endpoints da API

### 📌 Criar Pedido

```
POST /pedidos
```

### 📌 Listar Pedidos

```
GET /pedidos
```

### 📌 Buscar Pedido por ID

```
GET /pedidos/{id}
```

### 📌 Atualizar Pedido

```
PUT /pedidos/{id}
```

### 📌 Deletar Pedido

```
DELETE /pedidos/{id}
```

---

## 📥 Exemplo de Requisição

### Criar um pedido (POST)

```json
{
  "cliente": "João Silva",
  "produto": "Notebook",
  "quantidade": 1,
  "valor": 3500.00
}
```

---

## ▶️ Como Executar o Projeto

1. Clone o repositório:

```bash
git clone https://github.com/ArthurMartins007/API_Gest-o_Pedidos
```

2. Acesse a pasta do projeto:

```bash
cd API_Gest-o_Pedidos
```

3. Execute o projeto:

```bash
./mvnw spring-boot:run
```

ou

```bash
mvn spring-boot:run
```

4. A API estará disponível em:

```
http://localhost:8080
```

---

## 🧪 Testes da API

Você pode testar os endpoints utilizando ferramentas como:

* Postman
* Insomnia

---

## 💡 Objetivo do Projeto

Este projeto foi desenvolvido com o objetivo de:

* Praticar desenvolvimento de APIs REST com Spring Boot
* Aplicar conceitos de Programação Orientada a Objetos
* Trabalhar com persistência de dados
* Simular um sistema real de gerenciamento de pedidos

---

## 👨‍💻 Autor

**Arthur Ferreira Martins**
📧 [arthurfm0234@gmail.com](mailto:arthurfm0234@gmail.com)
🔗 https://github.com/ArthurMartins007

---

## ⭐ Observações

Este projeto faz parte do meu processo de aprendizado e evolução como desenvolvedor back-end. Feedbacks são sempre bem-vindos!
