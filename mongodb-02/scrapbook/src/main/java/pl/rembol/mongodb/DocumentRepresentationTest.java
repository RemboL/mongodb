package pl.rembol.mongodb;

import java.util.Arrays;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class DocumentRepresentationTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DBObject doc = new BasicDBObject();
		doc.put("hello", "world");
		doc.put("array", Arrays.asList("one", "two"));
		doc.put("address",
				new BasicDBObject("street", "20 main").append("town",
						"Westfield").append("zip", "56789"));
		
		System.out.println(doc);
	}

}
