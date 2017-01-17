@echo off
cd %JAVA_HOME%\bin
rmic -classpath %CLASSPATH% -d %CLASSPATH% communication.ServerCommunicationManager
rmic -classpath %CLASSPATH% -d %CLASSPATH% communication.ClientCommunicationManager