
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
