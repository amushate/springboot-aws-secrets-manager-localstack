# springboot-aws-secrets-manager-localstack

## Install LocalStack
```brew install localstack```

## Start localstack
```localstack start -d```

## Add aws environment variables
```
export AWS_ACCESS_KEY_ID=TEST-KEY-VALUE
export AWS_SECRET_ACCESS_KEY=TEST-ACCESS-KEY-VALUE
export AWS_REGION=eu-west-1

```

## Run script to add secret
```aws --endpoint-url=http://localhost:4566 secretsmanager create-secret --name localstack-dev-secret --secret-string '[Password@123]' --region eu-west-1```

## Test your app by executing
```curl --location 'localhost:8080/ap1/v1/aws-secret?secretName=localstack-dev-secret'```
