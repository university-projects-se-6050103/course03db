#!/usr/bin/env bash

curl -i -L -X PUT \
    -T ./data/scores.csv \
    "http://172.17.0.1:50075/webhdfs/v1/user/cloudera/data/scores.csv?op=CREATE&namenoderpcaddress=172.17.0.1:8020&overwrite=true"

curl -i -L -X PUT \
    -T ./data/students.csv \
    "http://172.17.0.1:50075/webhdfs/v1/user/cloudera/data/students.csv?op=CREATE&namenoderpcaddress=172.17.0.1:8020&overwrite=true"

curl -i -L -X PUT \
    -T ./data/subjects.csv \
    "http://172.17.0.1:50075/webhdfs/v1/user/cloudera/data/subjects.csv?op=CREATE&namenoderpcaddress=172.17.0.1:8020&overwrite=true"
