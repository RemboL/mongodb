package pl.rembol.mongodb;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Short tutorial on how to start with Freemarker templating engine.<br>
 * <br>
 * Freemarker is very simple engine that combines templates with variables that
 * are inserted into viable "${...}" parts.<br>
 * <br>
 * First we prepare hello.ftl resource file containing our template. Second, we
 * create configuration instance and load in our template. Finally we process
 * our template by passing a map containing our variables and print out the
 * result.
 * 
 * @author RemboL
 * 
 */
public class HelloWorldFreemarkerStyle {

	public static void main(String[] args) throws IOException,
			TemplateException {
		Configuration configuration = new Configuration();

		configuration.setClassForTemplateLoading(
				HelloWorldFreemarkerStyle.class, "/");

		Template helloTemplate = configuration.getTemplate("hello.ftl");

		StringWriter writer = new StringWriter();
		Map<String, Object> helloMap = new HashMap<String, Object>();
		helloMap.put("name", "Freemarker");

		helloTemplate.process(helloMap, writer);

		System.out.println(writer);

	}

}
