#!/bin/sh
# wait-for-cassandra.sh
# set -e

host="$1"
echo $host
port="$2"
echo $port
cmd="$3"
echo $cmd

until cqlsh --cqlversion=3.4.4 "$host" "$port" '\q'; do
  >&2 echo "Cassandra is unavailable - sleeping"
  sleep 1
done

>&2 echo "Cassandra is up - executing command"
exec $cmd