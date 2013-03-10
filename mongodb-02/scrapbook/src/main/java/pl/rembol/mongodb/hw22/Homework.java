package pl.rembol.mongodb.hw22;

import com.mongodb.DBCollection;

import java.net.UnknownHostException;
import java.util.Arrays;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;

public class Homework {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		DBCollection grades = getCollection();
		
		DBObject query = QueryBuilder.start("type").is("homework").get();
		DBObject sort = new BasicDBObject("student_id", 1).append("score", 1);
		DBCursor cursor = grades.find(query).sort(sort);
		
		Integer previousStudentId = null;
		
		try {
			while (cursor.hasNext()) {
				DBObject cur = cursor.next();
				
				System.out.println(cur);
				
				Integer studentId = (Integer) cur.get("student_id");
				if(previousStudentId == null || !studentId.equals(previousStudentId)) {
//					System.out.println("prev:"+previousStudentId+"\tact:"+studentId+"to remove"); 
					grades.remove(cur);
					previousStudentId = studentId;
				}
			}
		} finally {
			cursor.close();
		}
		
		cursor = grades.find(query).sort(sort);
		
		try {
			while (cursor.hasNext()) {
				DBObject cur = cursor.next();
				
				System.out.println(cur);
			}
		} finally {
			cursor.close();
		}
		
	}
	
	public static DBCollection getCollection() throws UnknownHostException {
		MongoClient client = new MongoClient();
		DB courseDB = client.getDB("students");
		DBCollection collection = courseDB.getCollection("grades");

		return collection;
	}
	
	
}
