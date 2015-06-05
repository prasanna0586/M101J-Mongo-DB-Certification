package com.sample.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;

/**
 * Created by prasannakumarr on 6/5/2015.
 */
public class MongoApp {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
        MongoCollection mongoCollection = mongoDatabase.getCollection("users");

        Document sahana = new Document("name","Sahana").append("age",3).append("city","Chennai");
        Document sankari = new Document("name","Sankari").append("age",56).append("city","Pondy");
        //mongoCollection.insertOne(sahana); //-> To insert one record
        mongoCollection.insertMany(Arrays.asList(sahana,sankari));
    }
}
