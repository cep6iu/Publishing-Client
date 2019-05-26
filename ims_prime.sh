#!/bin/bash

# User defined constants
USER_RUN="cts_pc"
WAITTODIE=2

# Other defined constants
APP_NAME="PublishingClient";

# Define error codes
RET_CODE_ERR=1

# Calculated constants
APP_PATH="../"
PID_FILE="publishing_client.pid"

#verifies if the current user is the one allowed to run the script
check_running_user() {
  ME=`whoami | cut -d" " -f1`
  usr=`echo $USER_RUN | grep -w $ME`

  if [ -z "$usr" ]; then
     echo "$APP_NAME can NOT be started or stopped, must log in as '$USER_RUN' user!"
     exit $RET_CODE_ERR
  fi
}

#shows the usage text for this script
showUsage()
{
  echo "Usage: $0 [start|stop|status]"
  echo "   - start - starts the IMS Prime Probe process. "
  echo "   - stop - stops the IMS Prime Probe process."
  echo "   - status - checks if the IMS Prime Probe process is running or not."
}

#starts the tool
start_ims_probe()
{
        check_running_user;
        if [ -f $PID_FILE ]; then
                cupid=`cat $PID_FILE`
                echo "$APP_NAME process [pid=$cupid] already running!"
                exit $RET_CODE_ERR
        fi

        if [ -z $JAVA_HOME ]; then
                JAVA_BIN="java";
        else
                JAVA_BIN="$JAVA_HOME/bin/java"
        fi

		pwd
        # run
	        $JAVA_BIN -Xmx1024m -Xms512m -Dconfig.path="config/" -jar lib/pom-PublishingClient.jar &
        cupid=$!
        echo $cupid > $PID_FILE

                echo "IMS Prime Probe process has started. Please refer to the ${EXTERNAL.RESOURCES}/imsprime.log file to see the status."
}

#stops the tool if running
stop_ims_probe()
{
  check_running_user;

  if [ -f $PID_FILE ]; then
    cupid=`cat $PID_FILE`
    if ps -p $cupid>/dev/null ;then
      echo "Stop $APP_NAME process [pid=$cupid]"
      kill -TERM $cupid >/dev/null 2>&1

      for w in 1 2 3 4 5 6 7 8 9 10 ;do
        if ! ps -p $cupid>/dev/null ;then
          break;
        fi
        sleep $WAITTODIE
      done

      if ps -p $cupid>/dev/null ;then
        kill -KILL $cupid >/dev/null 2>&1
      fi
    fi
    rm -f $PID_FILE
  else
    echo "$APP_NAME is not running!"
  fi
}

#displays if the tool is running or not
status_ims_probe()
{
  if [ -f $PID_FILE ] ;then
    cupid=`cat $PID_FILE`
    if ps -p $cupid>/dev/null ;then
      echo "$APP_NAME process [pid=$cupid] is running."
    else
      echo "$APP_NAME process [pid=$cupid] is not running."
      rm -f $PID_FILE
    fi
  else
    echo "$APP_NAME process is not running."
  fi
}


#---------------------
#READ INPUT & VALIDATE
#---------------------
if [ "$#" -lt 1 ] ;then
  showUsage
  exit $RET_CODE_ERR
fi

CMD=$1;

cd $APP_PATH;

case $CMD in
  start)
    echo "Starting $APP_NAME ..."
    start_ims_probe
    ;;
  stop)
    echo "Stopping $APP_NAME ..."
    stop_ims_probe
    ;;
  status)
    echo "Status $APP_NAME ..."
    status_ims_probe
    ;;
  *)
esac
