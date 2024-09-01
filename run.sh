docker compose up -d

mvn clean package

java -jar -Dspring.profiles.active=dev target/assessement-1.0.0-SNAPSHOT.jar