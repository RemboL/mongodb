package pl.rembol.mongodb.spark.get;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Simple presentation of Spark service with multiple routes (with one variable route)
 * @author RemboL
 *
 */
public class SparkRoutes {

	public static void main(String[] args) {
		Spark.get(new Route("/") {

			@Override
			public Object handle(Request request, Response response) {
				return "Hello World\n";
			}
			
		});
		
		Spark.get(new Route("/test") {

			@Override
			public Object handle(Request request, Response response) {
				return "This is a test page\n";
			}
			
		});
		
		Spark.get(new Route("/echo/:thing") {

			@Override
			public Object handle(Request request, Response response) {
				return request.params(":thing");
			}
			
		});

	}

}
