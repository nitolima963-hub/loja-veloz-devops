
terraform {
  required_version = ">= 1.5.0"

  required_providers {
    kubernetes = {
      source  = "hashicorp/kubernetes"
      version = "~> 2.0"
    }
  }
}

provider "kubernetes" {
  config_path    = pathexpand("~/.kube/config")
  config_context = "docker-desktop"
}

resource "kubernetes_namespace_v1" "loja_veloz" {
  metadata {
    name = var.namespace
    labels = {
      projeto     = "loja-veloz"
      ambiente    = "desenvolvimento"
      gerenciado  = "terraform"
    }
  }
}
