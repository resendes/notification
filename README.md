## Notification service

O serviço foi construído utilizando o framework [micronaut](https://micronaut.io/)

## Como executar o aplicação

#### Build

```
mvn clean install
```

#### Run

Através do arquivo stack.yml é possível executar a aplicação.

```
docker-compose -f stack.yml up --build
```

## Recursos disponíveis

- Cadastrar um agendamento de notificação

```
curl --header "Content-Type: application/json"   --request POST   --data '{"recipient":"anderson", "message":"Oi", "type":"EMAIL", "schedulingDate":"2007-12-03T10:15:30+01:00"}'   http://localhost:8080/notifications
```

- Buscar agendamento por ID

```
curl -X GET http://localhost:8080/notifications/{id}
```

- Remover agendamento

```
curl -X DELETE http://localhost:8080/notifications/{id}
```