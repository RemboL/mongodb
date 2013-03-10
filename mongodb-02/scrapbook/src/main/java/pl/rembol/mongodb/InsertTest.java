package pl.rembol.mongodb;

import java.net.UnknownHostException;
import java.util.Arrays;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class InsertTest {

	/**
	 * @param args
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws UnknownHostException {

		MongoClient client = new MongoClient();
		DB courseDB = client.getDB("course");
		DBCollection collection = courseDB.getCollection("insertTest");

		collection.drop();
		
		DBObject doc = new BasicDBObject().append("a", 1);
		
		System.out.println("before: " + doc);

		collection.insert(doc);

		System.out.println("after: " + doc);
		
		System.out.println(collection.find().toArray());
		
		DBObject doc2 = new BasicDBObject("a", 2);
		DBObject doc3 = new BasicDBObject("a", 3);
		
		collection.insert(doc2, doc3);
		System.out.println(collection.find().toArray());
		
		DBObject doc4 = new BasicDBObject("a", 4);
		DBObject doc5 = new BasicDBObject("a", 5);
		
		collection.insert(Arrays.asList(doc4, doc5));
		System.out.println(collection.find().toArray());
		

	}

}
