# Final Project for Databases Course

## Requirements

* Deploy Apache Hadoop Cluster using Cloudera's distribution
* Load data inside: teachers, students, subjects, tests, scores
* This should happen via DML script using Apache Hive QL
* Develop .NET DDL library to perform CRUD operations
* Develop report generator in Java: it will get data from Hadoop using Hive and load it to Oracle DB
* Report generator is a standalone `.jar` artifact which uses data from OracleDB to generate HTML report in browser
* Implement at least 1 stored procedure in OracleDB to perform CRUD on 2 entities
* Develop Windows Forms App for testing and demonstration of all the above-mentioned (using .NET DDL)

## Run

This will start Hadoop + Oracle DB.

```sh
$ docker-composer up -d
```

### Hadoop

```sh
$ docker run --hostname=quickstart.cloudera --privileged=true -it -p 8888:8888 -p 10000:10000 -p 9083:9083 cloudera/quickstart /usr/bin/docker-quickstart
```

Go to [Hue Web UI](http://localhost:8888/) to explore. Username: `cloudera`. Password: `cloudera`

### Oracle DB

```sh
$ docker run -d -p 8080:8080 -p 1521:1521 -v `pwd`/oracle:/u01/app/oracle sath89/oracle-12c
```

Go to [Oracle Apex Web UI](http://localhost:8080/) to explore. Username: `ADMIN`. Password: `0Racle$`. Workspace: INTERNAL

## Useful Links

* [Network Ports exposed by CDH 5](https://www.cloudera.com/documentation/enterprise/5-2-x/topics/cdh_ig_ports_cdh5.html)
