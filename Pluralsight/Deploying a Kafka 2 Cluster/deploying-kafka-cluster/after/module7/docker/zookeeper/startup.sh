#!/bin/bash

server_id=$1

# Configuration
echo "$server_id" > /var/zookeeper/myid

# Start ZooKeeper
/opt/zookeeper/bin/zkServer.sh start-foreground