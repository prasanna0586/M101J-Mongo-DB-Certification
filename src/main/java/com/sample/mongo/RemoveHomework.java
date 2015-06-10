package com.sample.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prasannakumarr on 6/10/2015.
 */
public class RemoveHomework {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("school");
        MongoCollection mongoCollection = mongoDatabase.getCollection("students");
        List<Document> documentList = (List) mongoCollection.find().into(new ArrayList<Document>());
        //System.out.println(documentList);
        for(Document document: documentList) {
            List<Document> scores = (List<Document>) document.get("scores");
            Document documentToRemove = null;
            for(Document score: scores) {
                for(int nextScoreIndex = 1; nextScoreIndex < scores.size(); nextScoreIndex ++) {
                    if(score.get("type").equals("homework")
                            && scores.get(nextScoreIndex).get("type").equals("homework")) {
                        if(score.getDouble("score") < scores.get(nextScoreIndex).getDouble("score")) {
                            documentToRemove = score;
                        } else {
                            documentToRemove = scores.get(nextScoreIndex);
                        }
                        break;
                    }
                }
            }

            System.out.println("Student ID " + document.get("_id") + " -> " + documentToRemove);
        }
    }
}
