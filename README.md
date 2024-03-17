# Cognito Authentication - FIAP Pós TECH

Repositório contém o lambda responsável por interagir com o AWS Cognito, criando usuários e realizando a operação de LoginIn para geração de token.

# Stack e ferramentas

* Quarkus
* Java 17
* Gradlew
* AWS
    * Lambda
    * Cognito
        * User pool
        * App Client
    * API Gateway

# Descrição

O serviço será exposto junto ao API Gateway com duas coras principais:

* end point `/auth/sign-up` responsável pela criação dos usuários junto ao Cognito

```
POST https://<endpoint-prod>.execute-api.us-east-1.amazonaws.com/production/auth/sign-up
payload: 
  {
    "cpf": "<cpf válido no formato XXXXXXXXXXX>",
    "password": "senha valida com numero, letras maiusculas e minusculas. 
}
response:
  200 OK
  "Usuário criado com sucesso"
```

* end point `/auth/sign-in` responsável pela criação do token para autenticação durante as chamadas de API expostas pelo API Gateway

```
POST https://<endpoint-prod>.execute-api.us-east-1.amazonaws.com/production/auth/sign-up
payload: 
  {
    "cpf": "<cpf válido no formato XXXXXXXXXXX>",
    "password": "senha valida com numero, letras maiusculas e minusculas. 
}
response:
  200 OK
  {
    "token": "<token JTW>"
  }
```

# Como executar em dev mode parar testes locais

```shell script
./gradlew quarkusDev
```

A infraestrutura do projeto foi criada via terraform. 