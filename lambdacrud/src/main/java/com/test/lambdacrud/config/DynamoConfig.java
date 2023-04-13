package com.test.lambdacrud.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.test.lambdacrud.repository")
public class DynamoConfig {

    @Value("${amazon.dynamodb.endpoint}")
    private String endpoint;

    @Value("${amazon.aws.accesskey}")
    private String accesskey;

    @Value("${amazon.aws.secretkey}")
    private String secretkey;

    @Value("${amazon.aws.region}")
    private String region;

    @Bean
    public AmazonDynamoDB amazonDynamoDB(){
        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, region))
                .withCredentials(awsCredentialsProvider()).build();
        return amazonDynamoDB;
    }

    public AWSCredentialsProvider awsCredentialsProvider(){
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(accesskey, secretkey));
    }
}
