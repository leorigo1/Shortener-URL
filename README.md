Encurtador de URL's

Um encurtador de links simples desenvolvido em Java com Spring Boot, que permite transformar URLs longas em versões curtas.

🚀 Funcionalidades

Encurtar uma URL longa em um link curto único.
Redirecionar automaticamente para a URL original ao acessar o link curto.
Persistência das URLs em banco de dados (Atualmente implementado para usar o H2)
API REST simples para interação via Postman, front-end ou outros clientes.

🛠️ Tecnologias utilizadas:
Java 17+
Spring Boot
Spring Web
Spring Data JPA
Banco de Dados: H2 (memória) ou MySQL
Maven
Postman


📡 Endpoints da API
1. Encurtar uma URL

POST /url/shorten
Body (JSON):
{
  "url": "https://youtube.com"
}

Resposta:
{
  "url": "http://localhost:8081/5VM5V10v"
}


2. Acessar a URL encurtada
GET /url/{codigo}

Exemplo:
GET http://localhost:8081/url/5VM5V10v
