#!/bin/sh

JAVA_HOME=/local/opt/jdk1.7.0_04
DERBY_HOME=$JAVA_HOME/db
DERBY_OPTS="-Duser.language=en -Ddeby.ui.codeset=UTF8 -Dderby.system.home=/local/var/lib/derby_db/data"
export JAVA_HOME DERBY_HOME DERBY_OPTS

$DERBY_HOME/bin/startNetworkServer
