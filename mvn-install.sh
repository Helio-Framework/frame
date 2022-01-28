# 1. Install the helio-framework dependency in your local repository using this script
mvn clean install -DskipTests
mvn install:install-file -Dfile=./target/core-0.4.0-jar-with-dependencies.jar -DgroupId=helio-framework -DartifactId=core -Dversion=0.4.0 -Dpackaging=jar
