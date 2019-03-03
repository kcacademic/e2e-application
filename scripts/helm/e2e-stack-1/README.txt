Frontend
========
helm ls --all
helm delete --purge frontend
helm lint ./frontend-chart
helm install --name frontend ./frontend-chart
helm upgrade frontend ./frontend-chart
helm history frontend
helm status frontend

Backend
=======
helm ls --all
helm delete --purge backend
helm lint ./backend-chart
helm install --name backend ./backend-chart
helm upgrade frontend ./backend-chart
helm history backend
helm status backend