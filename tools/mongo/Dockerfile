FROM mongo

COPY init.json /init.json
CMD mongoimport --host 172.16.0.1 --db vocabulary --collection words \
   --type json --file /init.json --jsonArray