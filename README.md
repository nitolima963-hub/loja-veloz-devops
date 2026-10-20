
# Loja Veloz – Cloud DevOps

## Sobre o projeto

A Loja Veloz é um projeto acadêmico desenvolvido para colocar em prática alguns conceitos de DevOps, computação em nuvem e microsserviços.

A ideia foi criar um sistema simples de loja virtual, dividido em três serviços: Pedidos, Estoque e Pagamentos.

Durante o desenvolvimento, utilizei ferramentas como Docker, Kubernetes, GitHub Actions e Terraform para entender melhor como funciona a execução e o gerenciamento de aplicações.

## Tecnologias utilizadas

- Java 21
- Spring Boot
- PostgreSQL
- Docker e Docker Compose
- Kubernetes
- GitHub Actions
- GitHub Container Registry
- Terraform
- Spring Boot Actuator
- Metrics Server

## Como o projeto funciona

O sistema foi dividido em três partes:

**Pedidos:** permite cadastrar, consultar, atualizar e excluir pedidos.

**Estoque:** permite cadastrar produtos e controlar a quantidade disponível.

**Pagamentos:** permite registrar pagamentos simulados, como PIX e boleto.

Os três serviços utilizam PostgreSQL para guardar as informações. Nesta versão, cada serviço possui sua própria API, mas eles ainda não se comunicam automaticamente.

## Executando o projeto

Para executar o projeto é necessário ter o Docker Desktop instalado e funcionando.

Também é necessário criar um arquivo `.env` na pasta principal e configurar a variável `DB_PASSWORD` com a senha do banco local.

Depois, basta abrir o terminal na pasta principal e executar:

docker compose up -d --build

Para verificar os contêineres:

docker compose ps

As APIs podem ser acessadas pelos seguintes endereços:

- Pedidos: http://localhost:8080/pedidos
- Estoque: http://localhost:8081/produtos
- Pagamentos: http://localhost:8082/pagamentos

## Kubernetes

Também fiz a implantação dos serviços no Kubernetes, utilizando o ambiente local do Docker Desktop.

Criei arquivos YAML para configurar os serviços, o PostgreSQL, o armazenamento e as verificações de funcionamento.

Para verificar se os serviços estão funcionando:

kubectl get pods

kubectl get deployments

Os arquivos estão disponíveis na pasta `k8s`.

## Automação com GitHub Actions

Configurei o GitHub Actions para automatizar algumas tarefas do projeto.

Sempre que envio alterações para a branch principal, o GitHub executa a compilação e os testes dos três serviços.

Se tudo funcionar, as imagens Docker são publicadas no GitHub Container Registry.

Isso ajuda a identificar erros e evita precisar realizar todas essas tarefas manualmente.

## Monitoramento

Utilizei o Spring Boot Actuator e o Kubernetes para verificar o funcionamento das aplicações.

Também configurei o Metrics Server para acompanhar o consumo de CPU e memória.

Para consultar os logs:

kubectl logs deployment/pedidos

Para verificar o consumo de recursos:

kubectl top pods

## Escalabilidade

Configurei o HPA no serviço de Pedidos.

Ele permite aumentar ou diminuir automaticamente a quantidade de Pods dependendo do uso de CPU.

A configuração utiliza:

- Mínimo de 1 Pod
- Máximo de 3 Pods
- Meta de CPU de 70%

Consegui verificar que o HPA estava recebendo as métricas corretamente.

## Terraform

Utilizei o Terraform para criar um namespace chamado `loja-veloz` no Kubernetes.

Essa etapa serviu para praticar o conceito de infraestrutura como código, que permite criar recursos utilizando arquivos de configuração.

Os arquivos estão na pasta `terraform`.

## Conclusão

Com esse projeto, consegui colocar em prática vários conteúdos relacionados a desenvolvimento e DevOps.

Aprendi a criar APIs com Java e Spring Boot, utilizar banco de dados PostgreSQL, executar aplicações com Docker e Kubernetes e automatizar testes e publicação de imagens com GitHub Actions.

Também consegui trabalhar com monitoramento, escalabilidade e infraestrutura como código.

O projeto ainda pode receber melhorias, como a comunicação automática entre os microsserviços e a implementação de um API Gateway.
