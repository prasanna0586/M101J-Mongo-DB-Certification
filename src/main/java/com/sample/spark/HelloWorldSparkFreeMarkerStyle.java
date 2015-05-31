package com.sample.spark;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by prasanna0586 on 31/5/15.
 */
public class HelloWorldSparkFreeMarkerStyle {
    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldSparkFreeMarkerStyle.class,"/");

        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                StringWriter writer = null;
                try {
                    Template helloTemplate = configuration.getTemplate("hello.ftl");
                    writer = new StringWriter();
                    Map<String, Object> helloMap = new HashMap<String, Object>();
                    helloMap.put("name", "FreeMarker Style");
                    helloTemplate.process(helloMap, writer);
                    System.out.println(writer);

                } catch (Exception e) {
                    halt(500);
                    e.printStackTrace();
                }

                return writer;
            }
        });
    }
}
