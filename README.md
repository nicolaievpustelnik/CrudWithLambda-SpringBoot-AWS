# AWS Lambda Example

Crud created with Spring Boot to manage users.

## Project Configuration

To run the project, follow these steps:

1. Execute Maven commands to clean and package the project:

    ```bash
    mvn clean package
    ```

## AWS Configuration

1. Create a Lambda function and upload the `.jar` file of the already executed project.

2. Create a user/role with all the necessary permissions to access DynamoDB.

3. Configure the access key, secret key, and region in the `application.properties` file.

    ```properties
    amazon.dynamodb.endpoint=dynamodb.us-east-1.amazonaws.com
    amazon.aws.accesskey=12345
    amazon.aws.secretkey=123456789
    amazon.aws.region=us-east-1
    ```

4. Create a database with the name "user" in DynamoDB.

## Lambda Execution

When launching the project, use the provided Postman collection to test the APIs.

1. The expected request for the Lambda is as follows:

```json
{
  "pathParameters": {
    "userId": "1"
  }
}
```

2. The expected response from the Lambda should be similar to the following:

```json
{
  "body": "{\"user\":{\"userId\":\"1\",\"name\":\"Nicolaiev\"}}",
  "statusCode": 200
}
```

**Importante:** Make sure to adjust the settings and details of the response according to the specific needs of your project.
