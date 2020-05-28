#!/usr/bin/env bash
export JAVA_PROGRAM_ARGS=`echo "$@"`
mvn exec:java -e -Dexec.mainClass="com.gmerien.app.App" -Dexec.args="$JAVA_PROGRAM_ARGS"