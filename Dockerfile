# Author: Umesh S & Sharif Sultan

From openjdk:11
copy target/VetMicroservice-0.0.1-SNAPSHOT.jar VetMicroservice-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","VetMicroservice-0.0.1-SNAPSHOT.jar"]
EXPOSE 8097
