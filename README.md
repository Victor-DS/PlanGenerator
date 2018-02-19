# Plan Generator
[![CircleCI](https://circleci.com/gh/Victor-DS/PlanGenerator.svg?style=svg)](https://circleci.com/gh/Victor-DS/PlanGenerator)

A simple application to calculate a repayment plan for an annuity loan.

It was implemented using a serverless architecture for **AWS Lambda** and **CircleCI** for continuous integration.

## How to deploy
1. Install Maven.

On Linux:

```bash
$ sudo apt-get install mvn
```

Or with Homebrew:

```bash
$ brew install maven
```

2. Go to the project directory and build the project.

```
$ mvn package
```

3. Create a Lambda function on AWS, and upload the .jar file.

That's all you need. This function doesn't need any special permissions, so you should be good to go.

If you want, you can use API Gateway to have an API so that everybody can invoke your function without any AWS Lib.


## Test it live
If you can't deploy it, but would like to test it, you can use the following endpoint:

https://s65067mvs5.execute-api.eu-central-1.amazonaws.com/prod/GeneratePlan

Just make a POST request to that endpoint passing the following parameters:
```json
{
  "loanAmount": 5000,
  "nominalRate": 5.0,
  "duration": 24,
  "startDate": "2018-01-01T00:00:01Z"
}
```

## Dependencies
This application doesn't use any libraries to run execute the core logic, but it does use:

- **AWS Lambda SDK** which is needed to create a Lambda function;

- **Lombok** so we don't have to deal with nasty Getters/Setters;

- **JUnit** for Unit testing;
