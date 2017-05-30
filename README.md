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
$ docker-compose up -d
```

* [Hue Web UI](http://localhost:8888/) Username: `cloudera`. Password: `cloudera`

* [Oracle Apex Web UI](http://localhost:8080/apex) Username: `ADMIN`. Password: `0Racle$`. Workspace: INTERNAL.

## Load Data

This will upload `.csv` files with initial seed data to HDFS. Then it will create tables and import data into them.

```sh
$ docker build -t hive-loader ./hive-loader
$ docker run --rm hive-loader
```

## Get Some Data

```sql
SELECT students.id AS student_id,
         students.group AS student_group,
         students.full_name AS full_name,
         students.avg_score AS previous_avg_score,
         average_test_scores.average AS current_avg_score
FROM students
JOIN
  (SELECT student_id,
         AVG(value) AS average
  FROM scores
  GROUP BY  student_id) average_test_scores
    ON (students.id = average_test_scores.student_id)
```

## Other

Connect to Oracle DB with `jdbc:oracle:thin:@localhost:1521:xe`. Username: `system`. Password: `oracle`.

<details>
 <summary>... or run Hadoop independently</summary>

    $ docker run \
        --hostname=quickstart.cloudera \
        --privileged=true -it \
        -p 8888:8888 \
        -p 10000:10000 \
        -p 9083:9083 \
        cloudera/quickstart \
        /usr/bin/docker-quickstart

</details>

<details>
 <summary>... or run Oracle DB independently</summary>

    $ docker run -d \
        -p 8080:8080 \
        -p 1521:1521 \
        -v `pwd`/oracle:/u01/app/oracle \
        sath89/oracle-12c

</details>


## Useful Links

* [Network Ports exposed by CDH 5](https://www.cloudera.com/documentation/enterprise/5-2-x/topics/cdh_ig_ports_cdh5.html)
