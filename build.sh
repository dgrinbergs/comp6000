#!/bin/sh
# This script is used to build all the jar files which are required for the docker containers

rm -rf ./backend/builds/libs
rm -rf ./bukkit-plugins/builds/libs
rm -rf ./grpc/builds/libs

cd backend
chmod +x ./gradlew
./gradlew clean build
cd ..

cd bukkit-plugin
chmod +x ./gradlew
./gradlew clean build
cd ..

cd grpc
chmod +x ./gradlew
./gradlew clean build
cd ..