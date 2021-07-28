# SwingSaverAdmin
Spring boot version - Admin

# Jasypt 암호화
D:\DEV_SETUP\jasypt-1.9.3\bin>encrypt.bat input="암호화대상단어" password="암호화Key"

----ENVIRONMENT-----------------

Runtime: Oracle Corporation OpenJDK 64-Bit Server VM 15.0.2+7-27



----ARGUMENTS-------------------

input: xxxxxxx
password: xxxxxxxxxx



----OUTPUT----------------------

Tp/UZj0krz8190PLSIJEOIQhdJvssDjaa6u+LIXbdr8=

# Docker Build
docker build --build-arg JAR_FILE=build/libs/SwingSaverAdmin-0.0.1-SNAPSHOT.jar -t mjoung/swingbackendapi .

# Docker Image 확인
docker images

# Docker Run 
docker run -p 8080:8080 mjoung/swingbackendapi

# Docker connect
docker exec -it container_id bash

