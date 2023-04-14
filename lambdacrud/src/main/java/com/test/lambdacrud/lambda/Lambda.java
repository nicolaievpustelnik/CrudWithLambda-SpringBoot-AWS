package com.test.lambdacrud.lambda;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.test.lambdacrud.controller.UserController;
import com.test.lambdacrud.model.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.Optional;

public class Lambda implements RequestStreamHandler {


    private String DYNAMO_TABLE = "user";

    @SuppressWarnings("unchecked")
    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {

        OutputStreamWriter writer = new OutputStreamWriter(output);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        JSONParser parser = new JSONParser();
        JSONObject responseObject = new JSONObject();
        JSONObject responseBody = new JSONObject();

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDB dynamoDB = new DynamoDB(client);

        String id;
        Item resItem = null;

        try {
            JSONObject reqObject = (JSONObject) parser.parse(reader);
            //pathParameters
            if (reqObject.get("pathParameters")!=null) {
                JSONObject pps = (JSONObject)reqObject.get("pathParameters");
                if (pps.get("userId")!=null) {
                    id = (String)pps.get("userId");
                    resItem = dynamoDB.getTable(DYNAMO_TABLE).getItem("userId",id);
                }
            }
            if (resItem!=null) {
                User user = new User(resItem.toJSON());
                responseBody.put("user", user);
                responseObject.put("statusCode", 200);
            }else {
                responseBody.put("message", "No Items Found");
                responseObject.put("statusCode", 404);
            }

            responseObject.put("body", responseBody.toString());

        } catch (Exception e) {
            context.getLogger().log("ERROR : "+e.getMessage());
        }
        writer.write(responseObject.toString());
        reader.close();
        writer.close();
    }
}