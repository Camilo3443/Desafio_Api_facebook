# Desafio Sensedia

Desafio Sensedia api Facebook

## Requisitos

 - O usuário deve realizar a autenticação no facebook.
 - O usuário deve postar uma mensagem na página desse mesmo usuário.
 - Realizar uma alteração na mensagem postada acima.

### Configuracoes
Para rodar os testes foram ultilizados os seguintes plugins:

    - Rest Assured
    - JUnit
    - cucumber

https://mvnrepository.com/

### Casos de teste

### Caso de teste 01: Autenticação com um token inválido
    Dado O usuario possui um token invalido
    Quando Enviar uma requisicao para "https://graph.facebook.com/me"
    Então A resposta do sistema deve ser 400

### Caso de teste 02: Autenticação com um token válido
    Dado O usuario possui um token valido
    Quando Enviar uma requisicao para "https://graph.facebook.com/me"
    Então A resposta do sistema deve ser 200
    E O sistema é autenticado com sucesso

### Caso de teste 03: Post na pagina
    Dado O usuario possui uma autenticacao com um token valido
    Quando Enviar uma requisicao para "https://graph.facebook.com/me/feed"
    E Postar a mensagem "{\"message\":\"Desafio Sensedia Api\"}" em sua pagina
    Então A resposta do sistema deve ser 200
    E O ID do post deve ser salvo


### Caso de teste 04: Alteração do post realizado
    Dado O usuario precisa realizar uma alteracao em seu post
    Quando Enviar uma requisicao para "https://graph.facebook.com/"
    E Alterar a mensagem para "{\"message\":\"Desafio Sesedia api 12345\"}"
    Então A resposta do sistema deve ser 200
    E O post deve ser alterado

### Caso de teste 04: Exclusão do post realizado
    Dado O usuario precisa deletar a mensagem postada em sua pagina
    Quando Enviar uma requisicao utilizando delete para "https://graph.facebook.com/me/feed"
    Então A resposta do sistema deve ser 200
    And O Post deve ser deletado


### Rodando os testes

- Clonar o projeto no github [https://github.com/Camilo3443/Facebook_desafio_api]
- Importar o projeto em uma IDE
- Rodar a classe Steps
- Resultado será exibido no console



## Autor

* **Luis Eduardo F. Camilo** - *Desafio Sensedia* - (https://github.com/Camilo3443)

