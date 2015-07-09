package com.finalexam;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prasannakumarr on 7/9/2015.
 */
public class FirstQuestion {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("enron");
        MongoCollection mongoCollection = mongoDatabase.getCollection("messages");

        Bson filter = Filters.and(Filters.eq("headers.From", "andrew.fastow@enron.com"),
                Filters.eq("headers.To", "jeff.skilling@enron.com"));
        List<Document> listOfDocuments = (List<Document>) mongoCollection.find(filter).into(new ArrayList<Document>());
        System.out.println("Number of emails " + listOfDocuments.size());
    }
}
