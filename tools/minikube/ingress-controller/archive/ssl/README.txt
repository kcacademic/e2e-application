openssl req -x509 -nodes -days 365 -newkey rsa:2048 -keyout nginx-selfsigned.key -out nginx-selfsigned.crt

kubectl create secret tls tls-certificate --key nginx-selfsigned.key --cert nginx-selfsigned.crt

openssl dhparam -out dhparam.pem 2048

kubectl create secret generic tls-dhparam --from-file=dhparam.pem