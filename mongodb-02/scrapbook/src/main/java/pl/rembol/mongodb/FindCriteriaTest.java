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

public class FindCriteriaTest {

	/**
	 * @param args
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient();
		DB courseDB = client.getDB("course");
		DBCollection collection = courseDB.getCollection("findCriteriaTest");

		collection.drop();

		for (int i = 0; i < 10; i++) {
			collection.insert(new BasicDBObject("x", new Random().nextInt(2))
					.append("y", new Random().nextInt(100)));
		}

		System.out.println("\ncount:");
		long count = collection.count();
		System.out.println(count);

		System.out.println("\nfindAll:");
		DBCursor cursor = collection.find();
		try {
			while (cursor.hasNext()) {
				DBObject cur = cursor.next();
				System.out.println(cur);
			}
		} finally {
			cursor.close();
		}

		DBObject query = new BasicDBObject("x", 0);
		System.out.println("\ncount(x=0):");
		count = collection.count(query);
		System.out.println(count);

		System.out.println("\nfind(x=0):");
		cursor = collection.find(query);
		try {
			while (cursor.hasNext()) {
				DBObject cur = cursor.next();
				System.out.println(cur);
			}
		} finally {
			cursor.close();
		}

		query = new BasicDBObject("x", 0).append("y", new BasicDBObject("$gt",
				10).append("$lt", 90));
		System.out.println("\ncount(x=0 & 10<y<90):");
		count = collection.count(query);
		System.out.println(count);

		System.out.println("\nfind(x=0 & 10<y<90):");
		cursor = collection.find(query);
		try {
			while (cursor.hasNext()) {
				DBObject cur = cursor.next();
				System.out.println(cur);
			}
		} finally {
			cursor.close();
		}

		QueryBuilder builder = QueryBuilder.start("x").is(0).and("y")
				.greaterThan(10).lessThan(90);
		query = builder.get();

		System.out.println("\ncount(x=0 & 10<y<90) #2:");
		count = collection.count(query);
		System.out.println(count);

		System.out.println("\nfind(x=0 & 10<y<90) #2:");
		cursor = collection.find(query);
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
