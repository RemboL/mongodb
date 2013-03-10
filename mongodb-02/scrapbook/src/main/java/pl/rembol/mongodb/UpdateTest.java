package pl.rembol.mongodb;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;

public class UpdateTest {

	/**
	 * @param args
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient();
		DB courseDB = client.getDB("course");
		DBCollection collection = courseDB.getCollection("UpdateTest");

		collection.drop();

		List<String> names = Arrays.asList("alice", "bobby", "cathy", "david",
				"ethan");

		for (String name : names) {
			collection.insert(new BasicDBObject("_id", name));
		}

		printCollection(collection);

		System.out.println("\nupdate(_id:alice, age:24)\n");
		DBObject query = new BasicDBObject("_id", "alice");
		DBObject update = new BasicDBObject("age", 24);
		collection.update(query, update);

		printCollection(collection);

		System.out.println("\nupdate(_id:alice, gender:female)\n");
		update = new BasicDBObject("gender", "female");
		collection.update(query, update);

		printCollection(collection);

		System.out.println("\nupdate(_id:alice, $set:{hair_color:brown})\n");
		update = new BasicDBObject("$set", new BasicDBObject("hair_color",
				"brown"));
		collection.update(query, update);

		printCollection(collection);
		
		System.out.println("\nupdate(_id:frank, gender:male)\n");
		query = new BasicDBObject("_id", "frank");
		update = new BasicDBObject("$set", new BasicDBObject("gender", "male"));
		collection.update(query, update);

		printCollection(collection);

		System.out.println("\nupdate(_id:frank, gender:male, upsert=true)\n");
		collection.update(query, update, true, false); // upsert = true

		printCollection(collection);
		
		System.out.println("\nupdate({}, title=Dr, multi=true)\n");
		query = new BasicDBObject();
		update = new BasicDBObject("$set", new BasicDBObject("title", "Dr. "));
		collection.update(query, update, false, true);

		printCollection(collection);
		
		System.out.println("\nremove(_id=alice)\n");
		query = new BasicDBObject("_id", "alice");
		collection.remove(query);

		printCollection(collection);
		

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
