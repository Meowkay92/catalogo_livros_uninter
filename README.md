# 📚 Catálogo de Livros

API REST feita com **Spring Boot** para catalogar livros dos universos  
**Game of Thrones** e **Senhor dos Anéis**.

---

## 🚀 Como rodar

### Requisitos
- **Java 17+**
- **Maven** (ou o wrapper `mvnw` gerado pelo Spring Initializr)

### Passo a passo
1. Abra o projeto no **IntelliJ IDEA**.
2. Garanta que o **Project SDK** está em **JDK 17**.
3. Rode a aplicação por um dos métodos:
    - IntelliJ: execute a classe `CatalogoLivrosApplication`.
    - Terminal (na raiz do projeto):
      ```bash
      ./mvnw spring-boot:run
      # ou, se tiver Maven instalado
      mvn spring-boot:run
      ```
4. Acesse a API: `http://localhost:8080/api/livros`

---

## 📌 Estrutura da API

- **Formato de dados**: JSON
- **Rotas disponíveis**:
    - `GET /api/livros` → Lista todos os livros.
    - `GET /api/livros/{id}` → Busca um livro por ID.
    - `POST /api/livros` → Adiciona um novo livro.
    - `PUT /api/livros/{id}` → Atualiza um livro existente.
    - `DELETE /api/livros/{id}` → Remove um livro.

### Status codes utilizados
- `200 OK` → Sucesso em GET/PUT.
- `201 Created` → Recurso criado em POST (com `Location`).
- `204 No Content` → Remoção bem-sucedida em DELETE.
- `400 Bad Request` → Erros de validação.
- `404 Not Found` → Item não encontrado.

---

## ✅ Validação de Dados

A API valida os dados recebidos em **POST** e **PUT** usando **Jakarta Validation**:

- `titulo` → **obrigatório**, 2–120 caracteres.
- `autor` → **obrigatório**.
- `universo` → deve ser **"Game of Thrones"** ou **"Senhor dos Anéis"**.

Se algum dado for inválido, a API retorna **400** com mensagens por campo.

---

## 🧪 Exemplos de Requisição/Resposta (cURL)

### 1) Listar todos os livros
Request:
```bash
curl -s http://localhost:8080/api/livros
```
Response (200):
```json
[
  {
    "id": 1,
    "titulo": "A Guerra dos Tronos",
    "autor": "George R. R. Martin",
    "universo": "Game of Thrones"
  },
  {
    "id": 2,
    "titulo": "A Sociedade do Anel",
    "autor": "J. R. R. Tolkien",
    "universo": "Senhor dos Anéis"
  }
]
```

---

### 2) Buscar por ID
Request:
```bash
curl -s http://localhost:8080/api/livros/1
```
Response (200):
```json
{
  "id": 1,
  "titulo": "A Guerra dos Tronos",
  "autor": "George R. R. Martin",
  "universo": "Game of Thrones"
}
```
Response (404) — se não existir:
```json
{
  "timestamp": "2025-09-06T15:30:00.000+00:00",
  "status": 404,
  "error": "Not Found",
  "path": "/api/livros/999"
}
```

---

### 3) Adicionar novo livro
Request:
```bash
curl -i -X POST http://localhost:8080/api/livros \
  -H "Content-Type: application/json" \
  -d '{"titulo":"O Retorno do Rei","autor":"J. R. R. Tolkien","universo":"Senhor dos Anéis"}'
```
Response (201):
```json
{
  "id": 3,
  "titulo": "O Retorno do Rei",
  "autor": "J. R. R. Tolkien",
  "universo": "Senhor dos Anéis"
}
```
Headers relevantes:
```
Location: /api/livros/3
```
Response (400) — validação:
```json
{
  "status": 400,
  "error": "Bad Request",
  "fieldErrors": {
    "titulo": "O título é obrigatório",
    "autor": "O autor é obrigatório",
    "universo": "Universo deve ser 'Game of Thrones' ou 'Senhor dos Anéis'"
  }
}
```

---

### 4) Atualizar livro
Request:
```bash
curl -i -X PUT http://localhost:8080/api/livros/1 \
  -H "Content-Type: application/json" \
  -d '{"titulo":"A Fúria dos Reis","autor":"George R. R. Martin","universo":"Game of Thrones"}'
```
Response (200):
```json
{
  "id": 1,
  "titulo": "A Fúria dos Reis",
  "autor": "George R. R. Martin",
  "universo": "Game of Thrones"
}
```
Response (404) — se não existir:
```json
{
  "timestamp": "2025-09-06T15:30:00.000+00:00",
  "status": 404,
  "error": "Not Found",
  "path": "/api/livros/999"
}
```

---

### 5) Remover livro
Request:
```bash
curl -i -X DELETE http://localhost:8080/api/livros/1
```
Response (204):
```
<sem corpo>
```
Response (404) — se não existir:
```json
{
  "timestamp": "2025-09-06T15:30:00.000+00:00",
  "status": 404,
  "error": "Not Found",
  "path": "/api/livros/999"
}
```

---

## 🛠️ Tecnologias utilizadas

- Java 17
- Spring Boot 3
- Spring Web
- Spring Validation (Jakarta Validation)
- Lombok
- Maven

---

## 🗂️ Estrutura sugerida de pacotes

```
src/main/java/com/catalogo/livros
├─ CatalogoLivrosApplication.java
├─ controller/
│  └─ LivroController.java
├─ model/
│  └─ Livro.java
├─ repository/
│  └─ LivroRepository.java
├─ config/
│  └─ DataLoader.java
└─ exception/
   └─ ApiExceptionHandler.java
```

---

## 👨‍💻 Autor

Projeto acadêmico de Cleberton Gonçalves da Silva, para prática de **APIs REST** com **Spring Boot**, para a disciplina de  Desenvolvimento Backend
