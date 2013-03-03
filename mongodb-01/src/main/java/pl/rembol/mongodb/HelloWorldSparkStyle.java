package pl.rembol.mongodb;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Basic class for Sparks' "hello world"<br>
 * <br>
 * Spark is a web library that runs as a standalone program by running embedded
 * jetty server. It's as easy as it can get - you define a routes for http
 * request and write handler methods.
 * 
 * @author RemboL
 * 
 */
public class HelloWorldSparkStyle {

	public static void main(String[] args) {
		Spark.get(new Route("/") {

			@Override
			public Object handle(Request arg0, Response arg1) {
				return "Hello World From Spark";
			}

		});

	}

}
