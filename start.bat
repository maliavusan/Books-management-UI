@echo off
set JAR_NAME=UI-service-0.0.1-SNAPSHOT.jar
set JAR_PATH=.\target\%JAR_NAME%
set WORKING_DIRECTORY=.

echo Changing to the project directory...
cd %WORKING_DIRECTORY%

echo Cleaning and installing Maven dependencies...
call mvn clean install

if %errorlevel% neq 0 (
    echo Maven build failed. Exiting.
    exit /b %errorlevel%
)

echo Starting Spring Boot...

java -jar %JAR_PATH%

echo application started open localhost:8080.
