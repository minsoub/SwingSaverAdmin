FROM centos:7
RUN yum -y update && yum -y install initscripts && yum -y install curl && yum -y install unzip && yum -y install java-11-openjdk-devel.x86_64 && yum -y install git && yum -y install wget && yum clean all && yum clean metadata && rm -rf /var/cache/yum/*
VOLUME /tmp
ARG JAR_FILE
ARG ACCESS_KEY_ID
ARG SECRET_ACCESS_KEY
ARG REGION_NAME
RUN echo $JAR_FILE
RUN echo $ACCESS_KEY_ID
RUN echo $SECRET_ACCESS_KEY
RUN echo $REGION_NAME

RUN export AWS_ACCESS_KEY_ID=$ACCESS_KEY_ID
RUN export AWS_SECRET_ACCESS_KEY=$SECRET_ACCESS_KEY
RUN export AWS_DEFAULT_REGION=$REGION_NAME

#RUN curl "https://s3.amazonaws.com/aws-cli/awscli-bundle.zip" -o "awscli-bundle.zip"
#RUN unzip awscli-bundle.zip
#RUN ./awscli-bundle/install -i /usr/local/aws -b /usr/local/bin/aws
 
WORKDIR /
COPY ${JAR_FILE} ./

COPY entrypoint.sh ./
#RUN chmod go-w /etc/filebeat/filebeat.yml
RUN java --version
RUN mkdir /logs


#RUN wget  https://streaming-data-agent.s3.amazonaws.com/aws-kinesis-agent-2.0.1-1.amzn1.noarch.rpm
#RUN rpm --import /etc/pki/rpm-gpg/RPM*
#RUN rpm -i aws-kinesis-agent-2.0.1-1.amzn1.noarch.rpm
RUN git clone https://github.com/awslabs/amazon-kinesis-agent.git
WORKDIR /amazon-kinesis-agent
RUN ./setup --install
WORKDIR /
#RUN chown -R aws-kinesis-agent-user.aws-kinesis-agent-user /logs
# RUN yum -y install https://s3.amazonaws.com/streaming-data-agent/aws-kinesis-agent-latest.amzn1.noarch.rpm
COPY ./kinesis/agent.json /etc/aws-kinesis/agent.json
#RUN service aws-kinesis-agent start && chkconfig aws-kinesis-agent on

ENV JAVA_HOME /lib/jvm/java-11-openjdk
ENV JRE_HOME /lib/jvm/jre-11-openjdk
RUN export JAVA_HOME JRE_HOME
RUN export PATH=$JAVA_HOME/bin:$JRE_HOME/bin:$PATH

EXPOSE 8080/tcp
WORKDIR /
#ENTRYPOINT $JAVA_HOME/bin/java -Dspring.profiles.active=prd -jar SwingSaverAdmin-0.0.1-SNAPSHOT.jar
#ENTRYPOINT $JAVA_HOME/bin/java -jar /SwingSaverAdmin-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["sh", "entrypoint.sh"]