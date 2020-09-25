# Customer Spring API

## Api Rest para cadastro e consulta de clientes e cidades.

### Requitos

* Java 8
* Spring Boot
* PostgresSQL
* Docker


### Funcionamento

A API deve cadastrar cidades e clientes e realizar operações básicas de um CRUD.

### Operações suportadas


* Cadastrar cidade
* Cadastrar cliente
* Consultar cidade pelo nome e/ou estado
* Consultar cliente pelo nome
* Consultar cliente pelo Id
* Remover cliente
* Editar cliente

### Endpoints

#### City

* GET /cities
  * Retorna todos as cidades cadastradas na base de dados.
  * A resposta deve conter os seguintes códigos:

    * 200: em caso de sucesso    

_Exemplo: uri/cities_
```json
[
  {
    "id": 1,
    "name": "Cidade1",
    "state": "PA"
  },
  {
    "id": 3,
    "name": "Cidade2",
    "state": "PA"
  },
  {
    "id": 5,
    "name": "Cidade3",
    "state": "PA"
  }
]
```

* GET /cities/city-states
  * Retorna uma cidade pelo Nome e estado
  * A resposta deve conter os seguintes códigos:

    * 200: em caso de sucesso
    * 404: se o registro não for localizado

_Exemplo 1: uri/cities/city-states?name=Cidade1_

```json
  {
    "id": 1,
    "name": "Cidade1",
    "state": "PA"
  }
```
_Exemplo 2: uri/cities/city-states?name=Cidade1&state=PA_

```json
  {
    "id": 1,
    "name": "Cidade1",
    "state": "PA"
  }
```

* POST /cities
  * Cria uma nova cidade
  * A resposta deve conter os seguintes códigos:

    * 201: em caso de sucesso

_Exemplo: uri/cities_

```json
  {
    "name": "Cidade2",
    "state": "PE"
  }
``` 

* PUT / cities/{id}
  * Atualiza uma cidade
  * A resposta deve conter os seguintes códigos:

    * 200: em caso de sucesso
    * 404: se o registro não for localizado

_Exemplo: uri/cities/1_

```json
  {
    "name": "Cidade1 Atualizada",
    "state": "PE"
  }
```
* DELETE /cities/{id}
  * Remove uma cidade pelo ID
  * A resposta deve conter os seguintes códigos:

    * 204: em caso de sucesso
    * 404: se o registro não for localizado

_Exemplo: uri/cities/1_


#### Customers

* GET / customers
  * Retorna todos os clientes cadastrados na base de dados.
  * A resposta deve conter os seguintes códigos:

    * 200: em caso de sucesso
    * 404: se o registro não for localizado

_Exemplo: uri/customers_
```json
[
  {
    "id": 2,
    "name": "Teste",
    "sex": "M",
    "dateBirth": "2010-01-10",
    "age": 1,
    "city": {
      "id": 1,
      "name": "Cidade1",
      "state": "PA"
    }
  },
  {
    "id": 4,
    "name": "Teste",
    "sex": "M",
    "dateBirth": "2010-01-10",
    "age": 1,
    "city": {
      "id": 3,
      "name": "Cidade1",
      "state": "PA"
    }
  },
  {
    "id": 6,
    "name": "Teste",
    "sex": "M",
    "dateBirth": "2010-01-10",
    "age": 1,
    "city": {
      "id": 5,
      "name": "Cidade1",
      "state": "PA"
    }
  }
]
```

* GET / customers/{id}
  * Retorna um cliente pelo ID informado
  * A resposta deve conter os seguintes códigos:

    * 200: em caso de sucesso
    * 404: se o registro não for localizado

_Exemplo: uri/cutomers/2_

```json
  {
    "id": 2,
    "name": "Teste",
    "sex": "M",
    "dateBirth": "2010-01-10",
    "age": 1,
    "city": {
      "id": 1,
      "name": "Cidade1",
      "state": "PA"
    }
  }
```

* GET / customers/{name}
  * Retorna um cliente pelo Nome informado
  * A resposta deve conter os seguintes códigos:

    * 200: em caso de sucesso
    * 404: se o registro não for localizado

_Exemplo: uri/cutomers/name/Teste_

```json
  {
    "id": 2,
    "name": "Teste",
    "sex": "M",
    "dateBirth": "2010-01-10",
    "age": 1,
    "city": {
      "id": 1,
      "name": "Cidade1",
      "state": "PA"
    }
  }
```
* POST / customers
  * Cria um novo cliente
  * A resposta deve conter os seguintes códigos:

    * 201: em caso de sucesso

_Exemplo: uri/cutomers_

```json
  {
    "name": "Teste",
    "sex": "M",
    "dateBirth": "2010-01-10",
    "age": 1,
    "city": 1
  }
``` 

* PUT / customers/{id}
  * Atualiza um cliente
  * A resposta deve conter os seguintes códigos:

    * 200: em caso de sucesso
    * 404: se o registro não for localizado

_Exemplo: uri/cutomers/2_

```json
  {
    "name": "Teste Atualizado",
    "sex": "G",
    "dateBirth": "2010-01-10",
    "age": 1,
    "city": 1
  }
```
* DELETE / customers/{id}
  * Remove um cliente
  * A resposta deve conter os seguintes códigos:

    * 204: em caso de sucesso
    * 404: se o registro não for localizado

_Exemplo: uri/cutomers/2_


## Testes

Para executar os testes unitários devemos executar o seguinte comando:

_mvn test_

## Execução

Por padrão, a API está disponível em [http: // localhost: 8080 /] (http: // localhost: 8080 /)

Para executar a API, podemos usar os seguintes métodos:

_cd target && java -jar customer-api-0.0.1-SNAPSHOT.jar_

ou com o seguinte comando

_mvn spring-boot: run_

## Docker

A API está configurada para ser usada com o Docker, para tal devemos seguir os seguintes passos:

* Primeiro devemos construir o executável de nossa aplicação com o seguinte comando: _mvn clean package_

* Devemos rodar o seguinte comando: _docker-compose up --build_

Se tudo ocorrer bem podemos acessar os recursos da API normalmente como mostrado no tópico Endpoints.
