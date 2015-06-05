package com.sample.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prasannakumarr on 6/5/2015.
 */
public class FindApp {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
        MongoCollection mongoCollection = mongoDatabase.getCollection("users");

        Document documentFirst = (Document) mongoCollection.find().first();
        System.out.println("****************First starts*****************");
        System.out.println(documentFirst.toJson());
        System.out.println("****************First ends*****************");
        System.out.println("****************Into starts*****************");
        List collectionList = (List) mongoCollection.find().into(new ArrayList<Document>());
        System.out.println(collectionList);
        System.out.println("****************Into ends*****************");
        System.out.println("****************Cursor starts*****************");
        MongoCursor mongoCursor = mongoCollection.find().iterator();
        try {
            while (mongoCursor.hasNext()) {
                Document cursorDocument = (Document) mongoCursor.next();
                System.out.println(cursorDocument.toJson());
            }
        } finally {
            mongoCursor.close();
        }
        System.out.println("****************Cursor ends*****************");
    }
}