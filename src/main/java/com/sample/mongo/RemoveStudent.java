package com.sample.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by prasannakumarr on 6/9/2015.
 */
public class RemoveStudent {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("students");
        MongoCollection mongoCollection = mongoDatabase.getCollection("grades");
        Bson sort = new Document("student_id", 1).append("score", 1);
        List<Document> collectionFilterList = (List) mongoCollection.find().sort(sort).into(new ArrayList<Document>());

        /*MongoCursor mongoCursor = mongoCollection.find().sort(sort).iterator();
        try {
            while (mongoCursor.hasNext()) {
                Document cursorDocument = (Document) mongoCursor.next();
                System.out.println(cursorDocument.toJson());
            }
        } finally {
            mongoCursor.close();
        }*//*
        Set<ObjectId> ids = new HashSet<ObjectId>();
        for (Document document: collectionFilterList) {
            for(int j = 1; j < collectionFilterList.size(); j++) {
                if(collectionFilterList.get(j).get("student_id").equals(document.get("student_id"))) {
                    if(collectionFilterList.get(j).get("type").equals("homework")) {
                        if(document.getDouble("score") < collectionFilterList.get(j).getDouble("score")) {
                            ids.add(document.getObjectId("_id"));
                        }
                    }
                }
            }

           *//* Double currentStudID = document.getDouble("student_id");

            System.out.println(document);*//*
        }

        for(Document document: collectionFilterList) {
            for(int j = 1; j < collectionFilterList.size(); j++) {
                if(collectionFilterList.get(j).get("type").equals("homework")) {
                    if(document.getDouble("score") < collectionFilterList.get(j).getDouble("score"))
                }
            }
        }
        System.out.println("ids" + ids.size());*/
        Set<ObjectId> ids = new HashSet<ObjectId>();
        for (int i = 0; i<collectionFilterList.size();i++) {
            for(int j= i+1; j<collectionFilterList.size();j++) {
                if((collectionFilterList.get(i).get("student_id")).
                        equals(collectionFilterList.get(j).get("student_id"))) {
                    if (((collectionFilterList.get(i).get("type")).equals("homework")) &&
                            (collectionFilterList.get(j).get("type")).equals("homework")){
                        if (collectionFilterList.get(i).getDouble("score")<
                                (collectionFilterList.get(j).getDouble("score"))){
                            ids.add(collectionFilterList.get(i).getObjectId("_id"));
                        } else {
                            ids.add(collectionFilterList.get(j).getObjectId("_id"));
                        }
                    }

                }
            }
        }
        System.out.println(ids.size());
        for (ObjectId id : ids) {
            mongoCollection.deleteOne(Filters.eq("_id" , id));
        }
        System.out.println(collectionFilterList.size());
    }
}
