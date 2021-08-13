#!/bin/bash
deploy_folder=/home/nhathuycn13/Documents/workspaces/java/demo
nohup /usr/bin/java -jar $deploy_folder/target/demo-0.0.1-SNAPSHOT.jar  > $deploy_folder/tmp/spring-boot.log 2>&1 &