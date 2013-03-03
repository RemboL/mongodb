package pl.rembol.mongodb;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;


/**
 * Basic "hello world" using mongoDB.
 * 
 * before running this class:
 * <ul>
 * <li>run mongod on standard localhost:27017 address
 * <li>run in mongo console:
 * <li><ul>
 * <li>use course
 * <li>db.hello.insert({hello:'world'})
 * </ul>
 * </ul>
 * 
 * @author RemboL
 *
 */
public class HelloWorldMongoDBStyle {

	
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
		
		DB database = client.getDB("course");
		DBCollection collection = database.getCollection("hello");
		
		DBObject document = collection.findOne();
		System.out.println(document);
	}

}
