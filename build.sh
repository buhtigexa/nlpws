#!/bin/bash

# Compile the NLP_WS project and build its Docker image . 

mvn clean 
mvn install
mvn compile

docker build -t dockerexa/nlpws:v1 . 
docker images


