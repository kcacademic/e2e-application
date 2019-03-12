Installing Kubeless
===================
set RELEASE=v1.0.2
kubectl create ns kubeless
kubectl create -f https://github.com/kubeless/kubeless/releases/download/v1.0.2/kubeless-v1.0.2.yaml
kubectl get all -n kubeless
kubectl get customresourcedefinition
kubectl delete -f https://github.com/kubeless/kubeless/releases/download/v1.0.2/kubeless-v1.0.2.yaml

Creating Test Lambda
====================
kubeless function deploy hello --runtime python2.7 --from-file test.py --handler test.hello
kubeless function ls
kubeless function call hello --data 'Hello world!'
kubeless function delete hello