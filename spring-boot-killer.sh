#!/bin/bash -x

APP_PROC=$(pgrep -f demo)

if [[ -z "$APP_PROC" ]]; then
   echo "No such app running with the name: $APP_PROC"
else
pkill -f demo
fi
