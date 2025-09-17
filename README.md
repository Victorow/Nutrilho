Pré-requisitos

Antes de começar, garanta que você tenha as seguintes ferramentas instaladas e configuradas no seu sistema:

    Java JDK 17 ou superior

    Apache Maven

    Git

    Node.js e npm (para o futuro desenvolvimento do frontend)

    Um cliente de API como o Postman ou Insomnia para testar os endpoints.

Configuração do Ambiente

Siga os passos abaixo para configurar o projeto na sua máquina.

1. Clonar o Repositório

Primeiro, clone o repositório do GitHub para o seu ambiente local:
Bash

git clone https://github.com/Victorow/Nutrilho.git
cd Nutrilho

2. Configurar o Backend (API Java/Spring)

A aplicação backend precisa das credenciais do banco de dados para se conectar.

    Navegue até a pasta de recursos do backend:
    Bash

cd backend/src/main/resources/


Executando a Aplicação

Backend

    Abra um terminal e navegue até a pasta backend:
    Bash

cd backend/

Execute o comando Maven para iniciar a aplicação:
Bash

mvn spring-boot:run

Se tudo estiver correto, o servidor da API iniciará na porta 8080.
