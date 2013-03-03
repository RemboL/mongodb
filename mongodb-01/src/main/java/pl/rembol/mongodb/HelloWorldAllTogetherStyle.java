package pl.rembol.mongodb;

import java.io.IOException;
import java.io.StringWriter;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * Combining HelloWorldSparkFreemarkerStyle and HelloWorldMongoDBStyle.<br>
 * <br>
 * It starts a Spark service with Freemarker-generated web content, but
 * variables map is not predefined, but it is loaded from MongoDB.
 * 
 * <ul>
 * <li>run mongod on standard localhost:27017 address
 * <li>run in mongo console
 * <ul>
 * <li>use course
 * <li>db.hello.remove()
 * <li>db.hello.insert({name:'MongoDB'})
 * </ul>
 * </ul>
 * 
 * @author RemboL
 * 
 */
public class HelloWorldAllTogetherStyle {

	public static void main(String[] args) throws IOException {
		final Configuration configuration = new Configuration();

		MongoClient client = new MongoClient(new ServerAddress("localhost",
				27017));

		DB database = client.getDB("course");
		final DBCollection collection = database.getCollection("hello");

		configuration.setClassForTemplateLoading(
				HelloWorldAllTogetherStyle.class, "/");
		Spark.get(new Route("/") {

			@Override
			public Object handle(Request arg0, Response arg1) {
				StringWriter writer = new StringWriter();
				try {
					Template helloTemplate = configuration
							.getTemplate("hello.ftl");

					DBObject document = collection.findOne();

					helloTemplate.process(document, writer);
				} catch (Exception e) {
					halt(500);
					e.printStackTrace();
				}

				return writer;
			}

		});
	}

}
