# Mosaic Cut
Mosaic cut project

## how to start

This is a spring boot application.

The application supports 2 database settings:

- By default, the application will connect to PostgreSQL with database name : <code>mosaic</code> and schema name: <code>mosaic</code>

  1. So to start the application, we need to firstly create the database and schema in PostgreSQL.

  2. We then need to change the <code>username</code> and <code>password</code> of the PostgreSQL in the <code>application.yml</code> with the real username and password

  3. We may as well need to change the <code>url</code> of the PostgreSQL in the <code>application.yml</code>, if <code>localhost:5432</code> is not used

  4. We then can run the command <code>mvn clean install</code> to build the project.

  5. Finally, we can run the apllication with command <code>java -jar mosaic-0.0.1-SNAPSHOT.jar</code>

-  The application also support the embedded H2 database to facilitate the test.

   1. To start application, just run the mvn command <code>mvn clean install</code> to build the project.
   2. We then can start the application with command <code>java -jar -Dspring.profiles.active=h2 mosaic-0.0.1-SNAPSHOT.jar</code>  


## how to test the mosaic cut function

The application supports 2 mosaic cut algorithms: GREEDY and BALANCED_GREEEDY.

The only difference between 2 algorithms, is that with GREEDY we always try to form the biggest possible piece. While with BALANCED_GREEEDY, we will try other possible size if the biggest piece is not validated.
  1. To test GREEDY alogithm, just go to url: http://localhost:8082/api/mosaic?algo=GREEDY
  2. Similarly, to test BALANCED_GREEDY, go to url: http://localhost:8082/api/mosaic?algo=BALANCED_GREEDY
  3. If no algorithm is specified, then by default is GREEDY will be used : http://localhost:8082/api/mosaic


Have fun!



