#!/bin/bash
CMD=`whoami`
echo $CMD
CMD1=`service aws-kinesis-agent start`
echo $CMD1 
CMD2=`chkconfig aws-kinesis-agent on`
echo $CMD2
#chown -R aws-kinesis-agent-user.aws-kinesis-agent-user /logs
export PATH="./:/lib/jvm/java-11-openjdk/bin:/lib/jvm/jre-11-openjdk/bin:$PATH"
echo $PATH
PWD=`pwd`
echo $PWD
LS=`ls -al`
echo $LS
`java -jar SwingSaverAdmin-0.0.1-SNAPSHOT.jar`
#java -Dspring.profiles.active=prd -jar /SwingSaverAdmin-0.0.1-SNAPSHOT.jar 