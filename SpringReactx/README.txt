"%KAFKA_HOME%\bin\windows\zookeeper-server-start.bat" "%KAFKA_HOME%\config\zookeeper.properties"
"%KAFKA_HOME%\bin\windows\kafka-server-start.bat" "%KAFKA_HOME%\config\server.properties"

"%KAFKA_HOME%\bin\windows\kafka-topics.bat" --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
"%KAFKA_HOME%\bin\windows\kafka-topics.bat" --list --zookeeper localhost:2181

"%KAFKA_HOME%\bin\windows\kafka-console-producer.bat" --broker-list localhost:9092 --topic test
"%KAFKA_HOME%\bin\windows\kafka-console-consumer.bat" --bootstrap-server localhost:9092 --topic test --group group1
"%KAFKA_HOME%\bin\windows\kafka-console-consumer.bat" --bootstrap-server localhost:9092 --topic test --group group2