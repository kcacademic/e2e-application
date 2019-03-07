Start Vault Server
==================
vault server -config vault.conf

Init Vault
==========
set VAULT_ADDR=http://127.0.0.1:8200
vault operator init -key-shares=5 -key-threshold=2

Unseal Vault
============
set VAULT_TOKEN=s.8zPEB6S2vtanzCZJoQXGuBzY
vault operator unseal Zwf09y6knSj4n2e42V6Wup199cKrlDr1RD9OUAKsSkNn
vault operator unseal 51yI1l6IkoShNs2MZSOnfx9pcDI/ADXsWbfA9uZF0Sxd

Write KV
========
vault write secret/java-cassandra-app @java-cassandra-app.json
vault read secret/java-cassandra-app