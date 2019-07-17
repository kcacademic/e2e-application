variable "access_key" {}
variable "secret_key" {}
variable "region" {
  default = "us-east-2"
}
variable "amis" {
  type = "map"
  default = {
    "us-east-2" = "ami-05220ffa0e7fce3d1"
  }
}