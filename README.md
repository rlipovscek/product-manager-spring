# Compasso - Product CRUD

###Docker
Dentro da raiz do projeto rodar o arquivo createDocker.sh para dar inicio a criação do docker

`$ ./createDocker.sh`

passos sem o script:

- criar a base de dados
`$ docker-compose up -d --build compasso-db`

- criar o jar do app
`$ mvn -f component/pom.xml clean package`

- fazer o build do app
`$ docker-compose up -d --build compasso-app`

## Criação do banco de dados
    Caso ocorra erro de permissão no banco de dados, rodar o seguinte comando

 `$ chmod 755 docker/createDatabase.sh`   

### Documentação
[Swagger](http://localhost:9999/swagger-ui.html#/)

`$http://localhost:8080/swagger-ui.html`

### Estrutura do projeto
 * component - Pasta contendo o microserviço
 * docker - Pasta contendo os arquivos usados na criação dos containers
 