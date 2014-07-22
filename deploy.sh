#!/bin/sh

mkdir -p $CATALINA_HOME/webapps/finna-be-archer

cp -r -f src/main/webapp/* $CATALINA_HOME/webapps/finna-be-archer/

cp -r -f target/classes $CATALINA_HOME/webapps/finna-be-archer/WEB-INF/

cp -r -f lib $CATALINA_HOME/webapps/finna-be-archer/WEB-INF/
