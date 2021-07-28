FROM centos:7
RUN yum -y update && yum -y install java-11-openjdk-devel.x86_64 && yum -y install git && yum -y install wget && yum clean all && yum clean metadata && rm -rf /var/cache/yum/*
VOLUME /tmp
ARG JAR_FILE
WORKDIR /
COPY ${JAR_FILE} ./

COPY entrypoint.sh ./
#RUN chmod go-w /etc/filebeat/filebeat.yml
CMD java --version
RUN mkdir /logs
#RUN wget  https://streaming-data-agent.s3.amazonaws.com/aws-kinesis-agent-2.0.1-1.amzn1.noarch.rpm
#RUN rpm --import /etc/pki/rpm-gpg/RPM*
#RUN rpm -i aws-kinesis-agent-2.0.1-1.amzn1.noarch.rpm
RUN git clone https://github.com/awslabs/amazon-kinesis-agent.git
WORKDIR /amazon-kinesis-agent
RUN ./setup --install
WORKDIR /
# RUN yum -y install https://s3.amazonaws.com/streaming-data-agent/aws-kinesis-agent-latest.amzn1.noarch.rpm
COPY ./kinesis/agent.json /etc/aws-kinesis/agent.json
CMD service aws-kinesis-agent start
CMD chkconfig aws-kinesis-agent on

ENV JAVA_HOME /lib/jvm/java-11-openjdk
ENV JRE_HOME /lib/jvm/jre-11-openjdk
RUN export JAVA_HOME JRE_HOME
RUN export PATH=$JAVA_HOME/bin:$JRE_HOME/bin:$PATH

EXPOSE 8080/tcp
WORKDIR /
#ENTRYPOINT $JAVA_HOME/bin/java -Dspring.profiles.active=prd -jar SwingSaverAdmin-0.0.1-SNAPSHOT.jar
ENTRYPOINT $JAVA_HOME/bin/java -jar SwingSaverAdmin-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["sh", "entrypoint.sh"]