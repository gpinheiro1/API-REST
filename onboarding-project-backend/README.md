## Onboarding Project
___
### 1. Objetivo
    Esse projeto tem como objetivo realizar o onboarding dos novos 
    desenvolvedores na stack utilizada na MovilePay.

### 2. Arquitetura
    O projeto foi estruturado seguinda a Union Architecture seguindo 
    as camadas:
*   ***api***: *Classes utilizadas em todas as camadas assim como classes que podem ser
utilizadas em outros projetos*. 
*   ***webservice***: *Responsável por expor as controlers REST para requecer as
as requisições do client*
*   ***business***: *Responsável pela definição de modelos e lógica de negócio*
*   ***third-party***: *Acesso a recursos de terceiros, no projeto é a camada responsável
pela conexão com o LDAP*
*   ***jdbc***: *Implementação da conexão com a base de dados*
    
### 3. Execução
    Antes de executar qualquer comando, atualiza/baixe as dependencias 
    do projeto: `mvn clean install`
            
#### 3.1. Executar Testes
    Para executar os testes do projeto, execute o comando:`mvn test`

### 4. Requisição e Respostas
     A aplicação possui documentação gerada pelo Swagger na rota principal, 
     exemplo: [http://localhost:8080/swagger-ui.html]
         
#### 4.1. Retorna todos os usuários
    [GET] -> /users    
```json
[
    {
        "id": "436b57ca-8aaf-43d6-9327-7334f43fbfb1",
        "firstName": "User",
        "lastName": "Teste",
        "company": "movile",
        "email": "teste@teste.com",
        "birthDate": "1970-01-01T00:00:00Z",
        "documentId": "12382119801",
        "type": "ADMIN"
    },
    {
        "id": "515f85dd-c21c-4e2c-a6b2-d185cdb461e5",
        "firstName": "User Updated",
        "lastName": "Oneeeeeee",
        "company": "movile",
        "email": "teste@teste.com",
        "birthDate": "1993-02-04T00:00:00Z",
        "documentId": "12382119801",
        "type": "ADMIN"
    }
]
```
#### 4.2. Adicionar usuário
    [POST] -> /users
```json
{
	"firstName": "User",
	"lastName": "Six",
	"email": "teste@teste.com",
	"company": "movile",
	"type": "ADMIN",
	"documentId": "12382119801",
	"birthDate" :"1970-01-01T00:00:00Z",
	"address": {
		"city": "Campinas 1234",
		"country": "Brasil",
		"zipCode": "13076-001",
		"number": 1234,
		"state": "SP",
		"street": "Street Movile"
	}
}
```    
#### 4.3. Listar detalhes de um usuário
    [GET] -> /users/{userId}
```json
{
    "id": "f1346723-bd3e-421b-b0b7-875f1f5f7b7d",
    "firstName": "User",
    "lastName": "Six",
    "company": "movile",
    "email": "teste@teste.com",
    "birthDate": "1970-01-01T00:00:00Z",
    "documentId": "12382119801",
    "type": "ADMIN",
    "address": {
        "id": "f1346723-bd3e-421b-b0b7-875f1f5f7b7d",
        "country": "Brasil",
        "state": "SP",
        "city": "Campinas 1234",
        "zipCode": "13076-001",
        "street": "Street Movile",
        "number": 1234,
        "userId": "f1346723-bd3e-421b-b0b7-875f1f5f7b7d"
    }
}
```
#### 4.4. Deletar usuário
    [DELETE] -> /users/{userId}

#### 4.5. Atualizar usuário
    [PUT] -> /users/{userId}
```json
{
	"firstName": "User",
	"lastName": "Six",
	"email": "teste@teste.com",
	"company": "movile",
	"type": "ADMIN",
	"documentId": "12382119801",
	"birthDate" :"1970-01-01T00:00:00Z",
	"address": {
		"city": "Campinas 1234",
		"country": "Brasil",
		"zipCode": "13076-001",
		"number": 1234,
		"state": "SP",
		"street": "Street Movile"
	}
}
```

#### 4.6. Retornar tipos de usuários
    [GET] -> users/types/
```json
[
    {
        "id": 0,
        "name": "ADMIN"
    },
    {
        "id": 1,
        "name": "USER"
    },
    {
        "id": 2,
        "name": "MANAGER"
    }
]
```
