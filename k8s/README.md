
# Kubernetes – Loja Veloz

Este diretório contém os arquivos Kubernetes do projeto Loja Veloz.

## Pré-requisitos

- Docker Desktop com Kubernetes ativado
- kubectl configurado
- Imagens Docker dos microsserviços disponíveis no cluster

## 1. Criar o Secret

No PowerShell, execute:

```powershell
kubectl create secret generic postgres-secret --from-literal="DB_PASSWORD=SUA_SENHA"
```

Substitua SUA_SENHA pela senha definida para o banco.

Não publique senhas reais no GitHub.

## 2. Aplicar as configurações

Na pasta principal do projeto, execute:

```powershell
kubectl apply -f k8s/configmap.yaml
kubectl apply -f k8s/postgres.yaml
kubectl apply -f k8s/pedidos.yaml
kubectl apply -f k8s/estoque.yaml
kubectl apply -f k8s/pagamentos.yaml
```

## 3. Verificar os recursos

```powershell
kubectl get pods
kubectl get deployments
kubectl get services
kubectl get pvc
```

## 4. Acessar as APIs

Em terminais separados, execute:

```powershell
kubectl port-forward service/pedidos 8090:8080
kubectl port-forward service/estoque 8091:8081
kubectl port-forward service/pagamentos 8092:8082
```

Endereços locais:

- Pedidos: http://localhost:8090/pedidos
- Estoque: http://localhost:8091/produtos
- Pagamentos: http://localhost:8092/pagamentos

## Observações

O PostgreSQL utiliza um PersistentVolumeClaim de 1Gi.

Os microsserviços utilizam ConfigMap, Secret, limites de recursos e probes de inicialização e disponibilidade.

Este ambiente é destinado a desenvolvimento e testes locais.
```

## Monitoramento e observabilidade

O projeto utiliza Spring Boot Actuator para verificar a saúde dos microsserviços.

O Kubernetes utiliza startupProbe, readinessProbe e livenessProbe para acompanhar a inicialização e disponibilidade das aplicações.

### Consultar logs

kubectl logs deployment/pedidos --tail=30

kubectl logs deployment/estoque --tail=30

kubectl logs deployment/pagamentos --tail=30

### Consultar CPU e memória

kubectl top pods

kubectl top nodes

### Metrics Server

Foi instalado o Metrics Server para coletar métricas de CPU e memória.

No ambiente local do Docker Desktop, foi necessária a opção --kubelet-insecure-tls devido ao certificado do kubelet.

Essa configuração é exclusiva do ambiente de desenvolvimento e não deve ser utilizada em produção.

### Escalonamento automático (HPA)

O arquivo hpa.yaml configura o escalonamento automático do microsserviço de Pedidos.

Configurações:
- Mínimo: 1 Pod
- Máximo: 3 Pods
- Meta de CPU: 70%

Para aplicar:

kubectl apply -f k8s/hpa.yaml

Para verificar:

kubectl get hpa

kubectl describe hpa pedidos-hpa

O HPA foi validado com as condições AbleToScale=True e ScalingActive=True.
