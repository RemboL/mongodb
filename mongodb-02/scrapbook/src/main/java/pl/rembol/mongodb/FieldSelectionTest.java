package pl.rembol.mongodb;

import java.net.UnknownHostException;
import java.util.Random;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;

public class FieldSelectionTest {

	/**
	 * @param args
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient();
		DB courseDB = client.getDB("course");
		DBCollection collection = courseDB.getCollection("fieldSelectionTest");

		collection.drop();

		Random rand = new Random();

		for (int i = 0; i < 10; ++i) {
			collection.insert(new BasicDBObject("x", rand.nextInt(2)).append(
					"y", rand.nextInt(100)).append("z", rand.nextInt(1000)));
		}

		DBObject query = QueryBuilder.start("x").is(0).and("y").greaterThan(10)
				.lessThan(70).get();
		
		System.out.println("\nx=false:");
		DBObject fields = new BasicDBObject("x", false);
		
		DBCursor cursor = collection.find(query, fields);
		try {
			while(cursor.hasNext()) {
				DBObject cur = cursor.next();
				System.out.println(cur);
			}
		} finally {
			cursor.close();
		}
		
		System.out.println("\ny=true & _id=false:");
		fields = new BasicDBObject("y", true).append("_id", false);
		
		cursor = collection.find(query, fields);
		try {
			while(cursor.hasNext()) {
				DBObject cur = cursor.next();
				System.out.println(cur);
			}
		} finally {
			cursor.close();
		}
		
		
	}

}
