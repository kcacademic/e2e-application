provider "aws" {
  access_key = ""
  secret_key = ""
  region     = var.region
}

resource "aws_instance" "example" {
  ami           = var.amis[var.region]
  instance_type = "t2.micro"
  
  provisioner "local-exec" {
    command = "echo ${aws_instance.example.public_ip} > ip_address.txt"
  }
}

output "ip" {
  value = "${aws_instance.example.public_ip}"
}