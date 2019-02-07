istioctl kube-inject -f ../../k8s/e2e-stack-1/frontend.yml > ./frontend_with_istio.yml
istioctl kube-inject -f ../../k8s/e2e-stack-1/backend.yml > ./backend_with_istio.yml