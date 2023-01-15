# API de Locadora de veículos

A aplicação possui 5 endpoints para o gerenciamento de veículos:


## Enpoints

```yaml

Post: 
/rent/v1/car ## criação de veículos

Get: 
/rent/v1/car/model ## busca de veículos por modelo com filtro de disponibilidade
/rent/v1/car/brand ## busca de veículos por marca com filtro de disponibilidade

Put:
/rent/v1//car/{id} ## atualização de veículo por id

Delete:
/rent/v1/car/{id} ## remoção de veículo por id

```
Os endpoints são autenticados com Basic Auth sendo que seu usuário e senha para teste são "registerUser" e "registerPassword" e role "ADMIN".


Para a utilização do projeto deve-se subir o arquivo docker-compose no diretório /docker
