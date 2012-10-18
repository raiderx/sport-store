#!/bin/sh

JAVA_HOME=/local/opt/jdk1.7.0_04
DERBY_HOME=$JAVA_HOME/db
DERBY_OPTS="-Duser.language=en -Dderby.ui.codeset=utf8 -Dij.database='jdbc:derby://localhost/sport-store;create=true'"
export JAVA_HOME DERBY_HOME DERBY_OPTS

$DERBY_HOME/bin/ij create-schema.sql
$DERBY_HOME/bin/ij demo-data.sql
