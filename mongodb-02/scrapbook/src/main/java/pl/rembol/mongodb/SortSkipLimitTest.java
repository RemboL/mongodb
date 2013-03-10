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

public class SortSkipLimitTest {

	/**
	 * @param args
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient();
		DB courseDB = client.getDB("course");
		DBCollection collection = courseDB.getCollection("SortSkipLimitTest");

		collection.drop();

		Random rand = new Random();

		for (int i = 0; i < 10; ++i) {
			collection.insert(new BasicDBObject("_id", i).append(
					"start",
					new BasicDBObject("x", rand.nextInt(2)).append("y",
							rand.nextInt(90) + 10)).append(
					"end",
					new BasicDBObject("x", rand.nextInt(2)).append("y",
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
		
		System.out.println("\nsort by _id desc:");
		builder = QueryBuilder.start();

		cursor = collection.find(builder.get());
		cursor.sort(new BasicDBObject("_id", -1));
		
		try {
			while (cursor.hasNext()) {
				DBObject cur = cursor.next();
				System.out.println(cur);
			}
		} finally {
			cursor.close();
		}
		
		System.out.println("\nsort by _id desc, skip 2:");
		builder = QueryBuilder.start();

		cursor = collection.find(builder.get());
		cursor.sort(new BasicDBObject("_id", -1));
		cursor.skip(2);
		
		try {
			while (cursor.hasNext()) {
				DBObject cur = cursor.next();
				System.out.println(cur);
			}
		} finally {
			cursor.close();
		}
		
		System.out.println("\nsort by _id desc, skip 2, limit 3:");
		builder = QueryBuilder.start();

		cursor = collection.find(builder.get());
		cursor.sort(new BasicDBObject("_id", -1));
		cursor.skip(2);
		cursor.limit(3);
		
		try {
			while (cursor.hasNext()) {
				DBObject cur = cursor.next();
				System.out.println(cur);
			}
		} finally {
			cursor.close();
		}

		System.out.println("\nsort by start.y desc:");
		builder = QueryBuilder.start();

		cursor = collection.find(builder.get());
		cursor.sort(new BasicDBObject("start.y", -1));
		
		try {
			while (cursor.hasNext()) {
				DBObject cur = cursor.next();
				System.out.println(cur);
			}
		} finally {
			cursor.close();
		}
		
		System.out.println("\nsort by start.x desc, start.y desc:");
		builder = QueryBuilder.start();

		cursor = collection.find(builder.get());
		cursor.sort(new BasicDBObject("start.x", -1).append("start.y", -1));
		
		
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
