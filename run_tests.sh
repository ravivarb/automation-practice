#!/usr/bin/env bash

function help_text() {
    echo "Runs the Automation Practise app tests with given site / platform / device"
    echo "Usage: run_tests -e=<local|remote> -p=<web|api> -a=<application_file>"
    echo "-e, --env             Environment: local|remote"
    echo "-p, --platform        Platform name: api|web"
    echo "-d, --device          Device name"
    echo "-o, --device-os       Device Os Version"
    echo "-t, --tag             Tag name: Optional, to run a set of tests that have a unique tag name"
    echo "-a, --app-file        Application file: Optional for remote tests, if the application is already uploaded"
    echo "-n, --app-name        Custom name for the uploaded application in remote server"
    echo "-b, --build-name      Custom Build name to be shown in remote server. Optional; Auto populated with repository details"
    echo "-h, --help            Help"
}

script_path="$(dirname $0)"

# Get command line arguments
for i in "$@"
do
case $i in
    -e=*|--env=*)
        env="${i#*=}"
        shift
        ;;
    -p=*|--platform=*)
        platform="${i#*=}"
        shift
        ;;
    -d=*|--device=*)
        device="${i#*=}"
        shift
        ;;
    -o=*|--device-os=*)
        device_os_version="${i#*=}"
        shift
        ;;
    -a=*|--app=*)
        app_file="${i#*=}"
        shift
        ;;
    -n=*|--app-name=*)
        app_name="${i#*=}"
        shift
        ;;
    -t=*|--tag=*)
        run_tag="@${i#*=}"
        shift
        ;;
    -b=*|--build-name=*)
        build_name="${i#*=}"
        shift
        ;;
    -h*|--help*)
        help_text;
        exit 0
        ;;
    --default)
    shift
    ;;
    *)
    # unknown option
    echo "Unknown option: '$i'"
    exit -1
    ;;
esac
done

#If parameter does NOT exist with set Environment Variable
if [ -z "$device" ]; then
    device=$DEVICE_NAME
fi

#If Device OS Version parameter does NOT exist with set Environment Variable
if [ -z "$device_os_version" ]; then
    device_os_version=$DEVICE_OS_VERSION
fi

#If parameter does NOT exist with set Environment Variable
if [ -z "$platform" ]; then
    platform=$DEVICE_PLATFORM
fi

#Check env
if [ "$env" != "local" -a "$env" != "remote" ]; then
    echo "Invalid test environment"
    exit -1
fi

#Check platform
if [ -z "$run_tag" ]; then
    run_tag=""
    if [ "$platform" == "api" ]; then
        run_tag="@api"
    elif [ "$platform" == "web" ]; then
        run_tag="@Web"
    else
        echo "Invalid platform"
        exit -1
    fi
fi

echo "Running Tests with the Cucumber Options Tag : ${run_tag}"

#Check input file
if [[ -z ${app_file}  &&  -f "$app_file" ]]; then
    echo "File '$app_file' doesn't exist"
    exit -1
fi

if [ "$env" == "remote" ]; then
    # Set custom application name based on platform
    if [ $? -eq 0 ]; then
        echo "App Name: ${app_name}"
        mvn test -q -Dtest.env=${env} -P${platform} -Ddevice.name=${device} -Ddevice.os.version=${device_os_version} -Dapplication.path=${app_name} -Dbuild.name=${build_name} "-Dcucumber.options=--tags ${run_tag} --tags ~@ignore"
#        rerun
    else
        exit $?
    fi
elif [ "$env" == "local" ]; then
    mvn test -q -Dtest.env=${env} -P${platform} -Ddevice.name=${device} -Ddevice.os.version=${device_os_version} -Dapplication.path=${app_file} "-Dcucumber.options=--tags ${run_tag} --tags ~@ignore"
fi