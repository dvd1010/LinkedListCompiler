package com.devanshu.compiler.templategenerator;


import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.SpringResourceLoader;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Component
public class CodeTemplateGenerator implements ResourceLoaderAware{

	private ResourceLoader resourceLoader;
	
	@SuppressWarnings("deprecation")
	public String getCodeTemplate() {
        VelocityEngine engine = getVelocityEngineInstance();
        String path = new File("code.vm").getPath();
        Map<String, Object> map = new HashMap<String, Object>();
		String template = VelocityEngineUtils.mergeTemplateIntoString(engine, path, map);
        return template;
    }
    
	@SuppressWarnings("deprecation")
	public String getMainTemplate() {
		VelocityEngine engine = getVelocityEngineInstance();
        String path = new File("main.vm").getPath();
        Map<String, Object> map = new HashMap<String, Object>();
		String mainTemplate = VelocityEngineUtils.mergeTemplateIntoString(engine, path, map);
        return mainTemplate;
	}
	
    VelocityEngine getVelocityEngineInstance() {
        VelocityEngine engine = new VelocityEngine();
        engine.setProperty(RuntimeConstants.RESOURCE_LOADER,
                SpringResourceLoader.NAME);
        engine.setProperty(SpringResourceLoader.SPRING_RESOURCE_LOADER_CLASS,
                SpringResourceLoader.class.getName());
        engine.setApplicationAttribute(
                SpringResourceLoader.SPRING_RESOURCE_LOADER, resourceLoader);
        engine.setApplicationAttribute(
                SpringResourceLoader.SPRING_RESOURCE_LOADER_PATH,
                "classpath:templates/");
        engine.init();
        return engine;
    }
	
	
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader=resourceLoader;
	}

	public String getSampleLinkedListTemplate() {
		VelocityEngine engine = getVelocityEngineInstance();
        String path = new File("LinkedList.vm").getPath();
        Map<String, Object> map = new HashMap<String, Object>();
		String template = VelocityEngineUtils.mergeTemplateIntoString(engine, path, map);
        return template;
	}

	public String getSampleNodeTemplate() {
		VelocityEngine engine = getVelocityEngineInstance();
        String path = new File("Node.vm").getPath();
        Map<String, Object> map = new HashMap<String, Object>();
		String template = VelocityEngineUtils.mergeTemplateIntoString(engine, path, map);
        return template;
	}


}
