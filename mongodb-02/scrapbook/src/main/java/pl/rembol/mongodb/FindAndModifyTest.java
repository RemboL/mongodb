package pl.rembol.mongodb;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;

public class FindAndModifyTest {

	/**
	 * @param args
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws UnknownHostException {
		DBCollection collection = createCollection("FindAndModifyTest");

		final String counterId = "abc";
		int first;
		int numNeeded;

		numNeeded = 2;
		first = getRange(counterId, numNeeded, collection);
		System.out.println("Range: " + first + "-" + (first + numNeeded - 1));

		numNeeded = 3;
		first = getRange(counterId, numNeeded, collection);
		System.out.println("Range: " + first + "-" + (first + numNeeded - 1));

		numNeeded = 10;
		first = getRange(counterId, numNeeded, collection);
		System.out.println("Range: " + first + "-" + (first + numNeeded - 1));

	}

	private static int getRange(String counterId, int numNeeded,
			DBCollection collection) {
		
		DBObject query = new BasicDBObject("_id", counterId);
		DBObject update = new BasicDBObject("$inc", new BasicDBObject("counter", numNeeded));
		
		DBObject doc = collection.findAndModify(query, null, null, false, update, true, true);
		return (Integer) doc.get("counter") - numNeeded + 1;
	}

	private static DBCollection createCollection(String name)
			throws UnknownHostException {
		MongoClient client = new MongoClient();
		DB courseDB = client.getDB("course");
		DBCollection collection = courseDB.getCollection(name);

		collection.drop();

		return collection;
	}

	private static void printCollection(DBCollection collection) {
		QueryBuilder builder = QueryBuilder.start();

		DBCursor cursor = collection.find(builder.get());
		try {
			while (cursor.hasNext()) {
				DBObject cur = cursor.next();
				System.out.println(cur);
			}
		} finally {
			cursor.close();
		}
	}

}
