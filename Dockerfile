# NLPWS Docker Image
# This image contains Tomcat 8 and a web service called nlpws

FROM dockerexa/tomcat:v0

ADD ./target/nlpws.war /usr/local/tomcat/webapps/

CMD mkdir /usr/local/taggers
ADD ./taggers /usr/local/taggers/
ENV TAGGERS=/usr/local/taggers
ENV NAME=Natural_languaje_procesing_Web_service

CMD sh $CATALINA_HOME/bin/catalina.sh stop  

CMD sh $CATALINA_HOME/bin/catalina.sh run 