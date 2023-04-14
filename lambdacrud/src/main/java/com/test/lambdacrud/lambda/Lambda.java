package com.test.lambdacrud.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Lambda implements RequestHandler<Request, Response> {

    @Override
    public Response handleRequest(Request request, Context context) {

        Response response = new Response();

        if (request.getOption().equals("1")){
            response.setId("4");
            response.setName("Nico");
        }else{
            response.setId("5");
            response.setName("Brito");
        }

        return response;
    }
}