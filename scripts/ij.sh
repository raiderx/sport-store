#!/bin/sh

JAVA_HOME=/local/opt/jdk1.7.0_04
DERBY_HOME=$JAVA_HOME/db
DERBY_OPTS="-Duser.language=en -Dderby.ui.codeset=UTF8 -Dij.database='jdbc:derby://localhost/sport-store'"
export JAVA_HOME DERBY_HOME DERBY_OPTS

$DERBY_HOME/bin/ij
