package com.intellect.igcb.autotest.utility;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class VelocityStartGenerator {
	
	static String inputTemplate = "offer_details_setup.vm";    
    static String outputFile = "offer_details_setup.sql";

	public static void main(String[] args) throws Exception {
		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		velocityEngine.setProperty("classpath.resource.loader.class", org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader.class.getName());

        velocityEngine.init();
     
        VelocityContext context = new VelocityContext();
        Map<String, String> data = new HashMap<String, String>();
        data.put("Mobile", "9930390120");
        data.put("PAN", "AUUPS6110M");
        context.put("data", data);
        Template t = velocityEngine.getTemplate( inputTemplate );
        
        
        Writer writer = new FileWriter(new File(outputFile));
        
        t.merge(context, writer);
        //Velocity.mergeTemplate(inputTemplate, "UTF-8", context, writer);
        writer.flush();
        writer.close();
       
        System.out.println("Generated " + outputFile);
	}

}
