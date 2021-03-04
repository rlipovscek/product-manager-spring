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

    Erro na criação da base, rodar os seguintes comandos manuais:

`$ docker exec -it compasso-db psql -U postgres`

`$ CREATE ROLE usuario LOGIN ENCRYPTED PASSWORD 'senhaForte' NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE;`

`$ CREATE DATABASE compasso ENCODING UTF8;`

`$ GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public to usuario;`

`$ ALTER USER usuario WITH SUPERUSER;`
    

### Documentação
[Swagger](http://localhost:9999/swagger-ui.html#/)

`$http://localhost:8080/swagger-ui.html`

### Estrutura do projeto
 * component - Pasta contendo o microserviço
 * docker - Pasta contendo os arquivos usados na criação dos containers
 