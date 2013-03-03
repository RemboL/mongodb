package pl.rembol.mongodb.spark.post;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * Introduction into Sparks' POST handling. Key are Spark.post() and
 * request.queryParams() methods.
 * 
 * @author RemboL
 * 
 */
public class SparkFormHandling {

	public static void main(String[] args) {
		final Configuration configuration = new Configuration();

		configuration.setClassForTemplateLoading(SparkFormHandling.class, "/");

		Spark.get(new Route("/") {

			@Override
			public Object handle(Request request, Response response) {
				Map<String, Object> fruitsMap = new HashMap<String, Object>();
				fruitsMap.put("fruits",
						Arrays.asList("apple", "orange", "banana", "peach"));

				try {
					Template fruitPickerTemplate = configuration
							.getTemplate("fruitpicker.ftl");
					StringWriter writer = new StringWriter();
					fruitPickerTemplate.process(fruitsMap, writer);

					return writer;

				} catch (Exception e) {
					halt(500);
					e.printStackTrace();
					return null;
				}
			}

		});

		Spark.post(new Route("/favourite_fruit") {

			@Override
			public Object handle(Request request, Response response) {

				final String fruit = request.queryParams("fruit");
				if (fruit == null) {
					return "Why don't you pick one?";
				} else {
					return "Your favoirite fruit is " + fruit;
				}
			}

		});
	}

}
