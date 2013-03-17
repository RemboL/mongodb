package pl.rembol.mognodb.hw31;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class Homework31 {

	/**
	 * @param args
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient();
		DB courseDB = client.getDB("school");
		DBCollection collection = courseDB.getCollection("students");

		DBCursor cursor = collection.find();
		try {
			while (cursor.hasNext()) {
				DBObject student = cursor.next();

				List<DBObject> scores = (List<DBObject>) student.get("scores");
				Double minHomeworkScore = null;
				for (DBObject score : scores) {
					if ("homework".equals(score.get("type"))) {
						Double currentScore = (Double) score.get("score");

						if (minHomeworkScore == null
								|| currentScore < minHomeworkScore) {
							minHomeworkScore = currentScore;
						}

					}
				}

				List<DBObject> newScores = new ArrayList<DBObject>();

				for (DBObject score : scores) {
					if (!"homework".equals(score.get("type"))) {
						newScores.add(score);
					} else {
						Double scoreValue = (Double) score.get("score");
						if (!scoreValue.equals(minHomeworkScore)) {
							newScores.add(score);
						}
					}
				}

				collection.update(student, new BasicDBObject("$set",
						new BasicDBObject("scores", newScores)));
			}
		} finally {
			cursor.close();
		}

	}
}
