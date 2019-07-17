kubectl create -f default-backend.yaml -n=ingress
kubectl create -f nginx-controller.yaml -n=ingress

kubectl create -f hello-world.yaml
kubectl create -f hello-world-ingress.yaml