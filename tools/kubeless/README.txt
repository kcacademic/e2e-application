Installing Kubeless
===================
set RELEASE=v1.0.2
kubectl create ns kubeless
kubectl create -f https://github.com/kubeless/kubeless/releases/download/v1.0.4/kubeless-v1.0.4.yaml
kubectl get all -n kubeless
kubectl get customresourcedefinition

Destroy Kubeless
================
kubectl delete -f https://github.com/kubeless/kubeless/releases/download/v1.0.4/kubeless-v1.0.4.yaml
kubectl delete ns kubeless

Kubeless GUI
============
kubectl create -f https://raw.githubusercontent.com/kubeless/kubeless-ui/master/k8s.yaml

Creating Test Lambda (Python)
=============================
kubeless function deploy hello-python --runtime python2.7 --handler hello-python.hello --from-file functions/hello-python.py
kubeless function ls
kubeless function delete hello-python

kubeless function call hello-python --data 'Hello python world!'

kubectl proxy
curl http://localhost:8001/api/v1/namespaces/default/services/hello-python:http-function-port/proxy/

Creating Test Lambda (Java)
===========================
kubeless function deploy hello-java --runtime java1.8 --handler HelloJava.hello --from-file functions/HelloJava.java
kubeless function ls
kubeless function delete hello-java

kubeless function call hello-java --data 'Hello java world!'

kubectl proxy
curl http://localhost:8001/api/v1/namespaces/default/services/hello-java:http-function-port/proxy/

