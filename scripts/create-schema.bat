set DERBY_HOME=C:\Program Files\Sun\JavaDB
set DERBY_OPTS=-Duser.language=en -Dderby.ui.codeset=utf8 -Dij.database=jdbc:derby://localhost/sport-store;create=true
set PATH=C:\Program Files\Java\jre7\bin;%PATH%

"%DERBY_HOME%\bin\ij" create-schema.sql
"%DERBY_HOME%\bin\ij" demo-data.sql
