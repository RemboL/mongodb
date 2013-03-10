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

public class DotNotationTest {

	/**
	 * @param args
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient();
		DB courseDB = client.getDB("course");
		DBCollection collection = courseDB.getCollection("dotNotationTest");

		collection.drop();

		Random rand = new Random();

		for (int i = 0; i < 10; ++i) {
			collection.insert(new BasicDBObject("_id", i).append(
					"start",
					new BasicDBObject("x", rand.nextInt(90) + 10).append("y",
							rand.nextInt(90) + 10)).append(
					"end",
					new BasicDBObject("x", rand.nextInt(90) + 10).append("y",
							rand.nextInt(90) + 10)));
		}
		System.out.println("\nfindAll:");
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

		System.out.println("\nstart.x > 50:");
		builder = QueryBuilder.start("start.x").greaterThan(50);

		cursor = collection.find(builder.get(), new BasicDBObject("start.y",
				true));
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
