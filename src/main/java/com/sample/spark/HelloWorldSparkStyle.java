package com.sample.spark;


import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created by prasanna0586 on 31/5/15.
 */
public class HelloWorldSparkStyle {

    public static void main(String[] args) {
        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                return "Hello World From Spark";
            }
        });
    }
}
