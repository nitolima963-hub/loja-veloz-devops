
# Loja Veloz – Cloud DevOps

## Sobre o projeto

A Loja Veloz é um projeto acadêmico que desenvolvi para colocar em prática alguns conceitos de DevOps e microsserviços.

A ideia foi criar um sistema simples de loja virtual, utilizando Java, Spring Boot, Docker, Kubernetes e outras ferramentas estudadas durante a atividade.

## Tecnologias utilizadas

- Java 21 e Spring Boot
- Spring Cloud Gateway
- PostgreSQL
- Docker e Docker Compose
- Kubernetes
- GitHub Actions e GHCR
- Terraform
- Spring Boot Actuator e Metrics Server

## Funcionamento do sistema

O projeto possui quatro serviços:

- **Pedidos:** cadastro, consulta, atualização e exclusão de pedidos.
- **Estoque:** cadastro de produtos e controle de quantidades.
- **Pagamentos:** registro de pagamentos simulados.
- **API Gateway:** direciona as requisições para os outros serviços.

Os três microsserviços utilizam tabelas separadas no mesmo banco PostgreSQL. O Gateway permite acessar todas as APIs pela porta 8083.

Os serviços ainda não possuem integração automática entre si.

## Executando com Docker

Para executar o projeto, é necessário ter o Docker Desktop instalado.

Primeiro, crie um arquivo `.env` na pasta principal com a variável `DB_PASSWORD` e uma senha local.

Depois, execute:

    docker compose up -d --build

Para verificar os contêineres:

    docker compose ps

Endereços para acessar pelo API Gateway:

- Pedidos: http://localhost:8083/pedidos
- Estoque: http://localhost:8083/produtos
- Pagamentos: http://localhost:8083/pagamentos

## Kubernetes

Também configurei os serviços no Kubernetes utilizando arquivos YAML disponíveis na pasta `k8s`.

Durante os testes, consegui executar os serviços e verificar seus Pods.

Para consultar:

    kubectl get pods

O API Gateway também foi instalado e testado no Kubernetes.

## GitHub Actions

Configurei o GitHub Actions para executar automaticamente a compilação e os testes dos quatro serviços.

Quando os testes passam na branch principal, as imagens Docker são publicadas no GitHub Container Registry.

Após adicionar o Gateway, os oito processos da automação foram concluídos com sucesso.

## Monitoramento e escalabilidade

Utilizei o Spring Boot Actuator para verificar o funcionamento das aplicações e o Metrics Server para acompanhar o consumo de CPU e memória.

Também configurei o HPA no serviço de Pedidos, com mínimo de 1 Pod, máximo de 3 Pods e meta de CPU de 70%.

Para as atualizações dos serviços no Kubernetes, utilizei a estratégia Rolling Update.

## Terraform

Utilizei o Terraform para criar um namespace chamado `loja-veloz` no Kubernetes.

Essa etapa me ajudou a entender melhor o conceito de infraestrutura como código.

Os arquivos estão na pasta `terraform`. Os serviços continuam implantados no namespace `default`.

## Conclusão

Com esse projeto, consegui colocar em prática vários conteúdos relacionados a desenvolvimento de sistemas e DevOps.

Aprendi a criar APIs com Java e Spring Boot, utilizar o PostgreSQL para armazenar informações e executar aplicações utilizando Docker e Kubernetes.

Também consegui configurar o API Gateway, automatizar testes e publicação de imagens com GitHub Actions e utilizar ferramentas de monitoramento e escalabilidade.

O Terraform foi outra ferramenta importante para entender melhor como funciona a criação de infraestrutura por meio de código.

O projeto me ajudou a compreender como diferentes ferramentas podem trabalhar juntas para organizar, executar e acompanhar uma aplicação.

Ainda existem melhorias que podem ser feitas, mas consegui desenvolver e testar as principais partes que foram implementadas durante a atividade.

Claro que ainda preciso pratica mais e entende bastente o conceito, mas foi um projeto longo mais bem pratico, com bastente desafios
