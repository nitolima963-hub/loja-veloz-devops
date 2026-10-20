
output "namespace_name" {
  description = "Nome do namespace Kubernetes criado pelo Terraform"
  value       = kubernetes_namespace_v1.loja_veloz.metadata[0].name
}

output "kubernetes_context" {
  description = "Contexto Kubernetes utilizado no projeto"
  value       = "docker-desktop"
}

output "ambiente" {
  description = "Ambiente da infraestrutura"
  value       = "desenvolvimento"
}
