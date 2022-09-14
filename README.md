# Automation practise HMRC Test - Cross Platform Automation Tests Framework

### Requirements
* JDK 8
* Maven

##### To run tests in local machine
* Appium Server
* Android/iOS  Device/Simulators
* Docker, Android SDKs

### Usage
```
Runs the tests with given application file
Usage: run_tests -e=<local|remote> -p=<web|api> -a=<application_file>
-e, --env             Environment: local|remote
-p, --platform        Platform name: web|api
-d, --device          Device Name : chrome
-a, --app-file        Application file: Optional for remote tests, if the application is already uploaded
-t, --tag             To run the set of scenarios with the associated tag name
-h, --help            Help

```

To Run Web platform test locally:
> ./run_tests.sh -e=local -p=web -a=sitename -t=web -d=chrome

```



