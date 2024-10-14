# Sistema de Leilões

Devido aos constantes produtos e imóveis apreendidos pela Receita Federal do Brasil, o
governo Brasileiro identificou a necessidade de desenvolver um Sistema de Leilões Eletrônicos
mais robusto e simples que o atual Sistema de Leilões Eletrônicos (SLE) já existente e usado
através do portal eCAC.

## Dependencias Necessarias:
Para executar este projeto, você precisará das seguintes ferramentas:
*  [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html): Utilizado para o Back-end.
*  [Maven](https://maven.apache.org/): Gerenciador de dependencias Java.
*  [Intellij](https://www.jetbrains.com/pt-br/idea/download/?section=windows): IDE Java recomendada.

## Executando a aplicação
1. Instale o Java 17 / Maven;

2.  Clonar o repositório utilizando:
    `git clone https://github.com/Aldrik-Alvaro/sistema-leiloes.git`

3. Acesse o caminho da pasta executando:
   `cd sistema-leiloes`

4. Rodar `mvn clean install` para instalar as dependências;

5. Execute o seguinte comando:
   `mvn mn:run`

6. A aplicação estará disponivel em `localhost:8080` e atraves do arquivo de importação insomnia `https://github.com/Aldrik-Alvaro/sistema-leiloes/blob/main/Insomnia.json`

## Rotas Disponíveis

# REST - CLIENTES
### Listar todos os clientes
* **Método:** GET
* **Endpoint:** `localhost:8080/clientes`

### Listar cliente por ID
* **Método:** GET
* **Endpoint:** `localhost:8080/clientes/{id}`

### Criar um novo cliente
* **Método:** POST
* **Endpoint:** `localhost:8080/clientes`
* **Corpo da Requisição:**
```json
{
    "nome": "Carlos Pereira",
    "cpf": "333.444.555-66",
    "email": "carlos@example.com",
    "endereco": "Rua C, 789",
    "telefone": "(31) 97777-7777"
}

```

### Atualizar cliente
* **Método:** PUT
* **Endpoint:** `localhost:8080/clientes/{id}`
* **Corpo da Requisição:**
```json
{
    "nome": "Carlos Pereira Júnior",
    "cpf": "333.444.555-66",
    "email": "carlosjr@example.com",
    "endereco": "Rua D, 101",
    "telefone": "(31) 97777-8888"
}
```

### Deletar cliente
* **Método:** DELETE
* **Endpoint:** `localhost:8080/clientes/{id}`


# REST - INSTITUIÇÃO FINANCEIRA
### Listar todas as instituição financeira
* **Método:** GET
* **Endpoint:** `localhost:8080/instituicoes-financeiras`

### Listar instituição financeira por ID
* **Método:** GET
* **Endpoint:** `localhost:8080/instituicoes-financeiras/{id}`

### Criar uma nova instituição financeira
* **Método:** POST
* **Endpoint:** `localhost:8080/instituicoes-financeiras`
* **Corpo da Requisição:**
```json
{
  "nome": "Banco do Brasil",
  "cnpj": "00.000.000/0001-11",
  "endereco": "Rua A, 123, SP",
  "telefone": "(11) 99999-9999"
}

```

### Atualizar instituições financeiras
* **Método:** PUT
* **Endpoint:** `localhost:8080/instituicoes-financeiras/{id}`
* **Corpo da Requisição:**
```json
{
  "nome": "Banco Bradesco",
  "cnpj": "00.000.000/0004-61",
  "endereco": "Rua C, 789, SP",
  "telefone": "(11) 92222-2222"
}
```

### Deletar instituição financeira
* **Método:** DELETE
* **Endpoint:** `localhost:8080/instituicoes-financeiras/{id}`


# REST - LEILÃO
### Listar todos os leilões
* **Método:** GET
* **Endpoint:** `localhost:8080/leiloes`

### Listar leilões por ID
* **Método:** GET
* **Endpoint:** `localhost:8080/leiloes/{id}`

### Criar novos leilões
* **Método:** POST
* **Endpoint:** `localhost:8080/leiloes`
* **Corpo da Requisição:**
```json
{
  "descricao": "Leilão de dispositivos",
  "dataOcorrencia": "2024-11-10",
  "dataVisitacao": "2024-12-15",
  "endereco": "Av. Brasil, 1000",
  "cidade": "São Paulo",
  "estado": "SP",
  "instituicoesFinanceiras": [
    { "id": 1 },
    { "id": 2 }
  ]
}

```

### Atualizar leilões
* **Método:** PUT
* **Endpoint:** `localhost:8080/leiloes/{id}`
* **Corpo da Requisição:**
```json
{
  "descricao": "Leilão de veículos",
  "dataOcorrencia": "2024-12-15",
  "dataVisitacao": "2024-11-30",
  "endereco": "Rua Central, 300",
  "cidade": "Rio de Janeiro",
  "estado": "RJ",
  "instituicoesFinanceiras": [
    { "id": 1 },
    { "id": 2 }
  ]
}

```

### Deletar leilões
* **Método:** DELETE
* **Endpoint:** `localhost:8080/leiloes/{id}`


# REST - VEICULOS
### Listar todos os veiculos
* **Método:** GET
* **Endpoint:** `localhost:8080/veiculos`

### Listar veiculos por ID
* **Método:** GET
* **Endpoint:** `localhost:8080/veiculos/{id}`

### Criar um novo veiculo
* **Método:** POST
* **Endpoint:** `localhost:8080/veiculos`
* **Corpo da Requisição:**
```json
{
  "tipo": "Carro",
  "marca": "Ford",
  "modelo": "Fiesta",
  "anoFabricacao": 2015,
  "placa": "ABC1234",
  "chassi": "XYZ9876543210",
  "cor": "Preto",
  "kmRodados": 55000,
  "valorInicial": 15000.00,
  "estadoConservacao": "Bom",
  "leilao": {
    "id": 1
  }
}



```

### Atualizar veiculos
* **Método:** PUT
* **Endpoint:** `localhost:8080/veiculos/{id}`
* **Corpo da Requisição:**
```json
{
  "tipo": "Carro",
  "marca": "Ford",
  "modelo": "Fiesta",
  "anoFabricacao": 2016,
  "placa": "ABC1234",
  "chassi": "XYZ9876543210",
  "cor": "Prata",
  "kmRodados": 60000,
  "valorInicial": 16000.00,
  "estadoConservacao": "Muito Bom",
  "leilao": {
    "id": 1
  }
}
```

### Deletar veiculos
* **Método:** DELETE
* **Endpoint:** `localhost:8080/veiculos/{id}`

### Desassociar De Leilao
* **Método:** POST
* **Endpoint:** `localhost:8080/veiculos/{ID}/desassociar`
* **Corpo da Requisição:**
```json
{
  "leilaoId": 1,
  "novoLeilaoId": 2
}

```

# REST - DISPOSITIVOS
### Listar todos os dispositivos
* **Método:** GET
* **Endpoint:** `localhost:8080/dispositivos`

### Listar dispositivos por ID
* **Método:** GET
* **Endpoint:** `localhost:8080/dispositivos/{id}`

### Criar um novo dispositivo
* **Método:** POST
* **Endpoint:** `localhost:8080/dispositivos`
* **Corpo da Requisição:**
```json
{
  "nome": "Notebook Dell",
  "tipo": "Notebook",
  "marca": "Dell",
  "modelo": "XPS 13",
  "especificacoes": "Intel i7, 16GB RAM, 512GB SSD",
  "leilao": {
    "id": 1
  }
}


```

### Atualizar dispositivos
* **Método:** PUT
* **Endpoint:** `localhost:8080/dispositivos/{id}`
* **Corpo da Requisição:**
```json
{
  "nome": "Monitor Samsung",
  "tipo": "Monitor",
  "marca": "Samsung",
  "modelo": "Odyssey",
  "especificacoes": "32', Curved, 240Hz",
  "leilao": {
    "id": 1
  }
}
```

### Deletar dispositivos
* **Método:** DELETE
* **Endpoint:** `localhost:8080/dispositivos/{id}`

### Desassociar De Leilao
* **Método:** POST
* **Endpoint:** `localhost:8080/dispositivos/{ID}/desassociar`
* **Corpo da Requisição:**
```json
{
  "leilaoId": 1,
  "novoLeilaoId": 2
}

```
