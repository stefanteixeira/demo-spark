FROM anapsix/alpine-java:jdk8

RUN MAVEN_VERSION=3.3.3 \
 && cd /usr/share \
 && wget http://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz -O - | tar xzf - \
 && mv /usr/share/apache-maven-$MAVEN_VERSION /usr/share/maven \
 && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven

ADD . /

RUN mvn clean install

EXPOSE 4567

CMD ["java", "-jar", "target/demo-spark-0.0.1-SNAPSHOT-jar-with-dependencies.jar"]
