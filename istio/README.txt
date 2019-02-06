istioctl kube-inject -f ./k8s/frontend.yml > ./istio/frontend_with_istio.yml
istioctl kube-inject -f ./k8s/backend.yml > ./istio/backend_with_istio.yml