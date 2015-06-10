package com.sample.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;

/**
 * Created by prasannakumarr on 6/5/2015.
 */
public class FindApp {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
        MongoCollection mongoCollection = mongoDatabase.getCollection("users");
        //Use one of the two filters. First one is built manually where as the second one is done through builder
        //Bson filter = new Document("age",new Document("$lte",35).append("$gte",30)).append("city","Chennai");
        Bson filter = and(gte("age",30),lte("age",35),eq("city","Chennai"));
        Document documentFirst = (Document) mongoCollection.find().first();
        System.out.println("****************First starts*****************");
        System.out.println(documentFirst.toJson());
        System.out.println("****************First ends*****************");
        System.out.println("****************Into starts*****************");
        List collectionList = (List) mongoCollection.find().into(new ArrayList<Document>());
        System.out.println(collectionList);
        System.out.println("****************Into ends*****************");
        System.out.println("****************Into filter starts*****************");
        List collectionFilterList = (List) mongoCollection.find(filter).into(new ArrayList<Document>());
        System.out.println(collectionFilterList);
        System.out.println("****************Into filter ends*****************");
        System.out.println("****************Projection starts*****************");
        Bson projectionExclude = new Document("age", 0).append("_id", 0);
        //Bson projectionInclude = new Document("age", 1).append("_id", 0);//Include age but exclude _id
        //Bson projectionMethod = Projections.fields(Projections.include("age"), Projections.exclude("_id")); //Same behavior as above
        List projectionList = (List) mongoCollection.find(filter).projection(projectionExclude).into(new ArrayList<Document>());
        System.out.println(projectionList);
        System.out.println("****************Projection starts*****************");
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
