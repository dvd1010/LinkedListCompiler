package com.devanshu.compiler.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.devanshu.compiler.templategenerator.CodeTemplateGenerator;
import com.devanshu.compiler.utils.PropertyUtils;

@Service
public class LinkedListService {
	
	@Resource
	private CodeTemplateGenerator codeTemplateGenerator;
	
	public static final String HOME_PATH = System.getProperty("user.home");
	public static final String SOLUTION_PATH="/LinkedListCode/src/main/java/com/devanshu/linkedlist/Solution.java";
	public static final String MAIN_PATH="/LinkedListCode/src/main/java/com/devanshu/linkedlist/Main.java";
	
	Logger logger =  LoggerFactory.getLogger(LinkedListService.class);
	
	
    public String getLinkedListCodeTemplate(){
    	String codeTemplate = codeTemplateGenerator.getCodeTemplate();
    	return codeTemplate;
    }
    
    public String getSampleLinkedListTemplate(){
    	String codeTemplate = codeTemplateGenerator.getSampleLinkedListTemplate();
    	return codeTemplate;
    }
    
    public String getSampleNodeTemplate(){
    	String codeTemplate = codeTemplateGenerator.getSampleNodeTemplate();
    	return codeTemplate;
    }
    
    public String runUserCode(String userCode, String linkedListValues) throws Exception {
		saveUserCode(userCode);
		saveMainCode(linkedListValues);
		return compileAndRunUserCode();
	}
    
    private void saveMainCode(String linkedListValues) throws IOException {
    	String mainTemplate = codeTemplateGenerator.getMainTemplate();
    	String[] llData = StringUtils.split(linkedListValues,",");
    	int length = llData.length;
    	for(int i=0;i<length;i++){
    		llData[i] = '"'+llData[i]+'"';
    	}
    	String modifiedValues = StringUtils.join(llData, ",");
    	mainTemplate=mainTemplate.replace("%s",modifiedValues).replace("%t", String.valueOf(length));
    	
    	File file = new File(HOME_PATH+MAIN_PATH);
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(mainTemplate);
		bw.close();
    	
		
	}

	@SuppressWarnings("unused")
	private String compileAndRunUserCode() throws IOException {
    	Runtime rt = Runtime.getRuntime();
    	String[] cmd = { "/bin/sh", "-c", "cd ;"+"cd LinkedListCode; " +
    			"mvn clean install exec:java -Dexec.mainClass='com.devanshu.linkedlist.Main'" };
    	Process p = rt.exec(cmd);
    	String s = null;
    	BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(p.getInputStream()));
    	StringBuilder sb = new StringBuilder();
    	int count=0;
    	while ((s = stdInput.readLine()) != null) {
    		System.out.println(s);
            if(s.contains("Middle Node is the one with value")){
            	return s;
            }else if (s.contains("[ERROR]") && count<4){
            	sb.append(s).append("\n");
            	count++;
            }
        }
    	return sb.toString();
	}

	private void saveUserCode(String code) throws Exception{
		try{
	    	String content = code;
			File file = new File(HOME_PATH+SOLUTION_PATH);
			logger.info(file.getAbsolutePath());
			if (!file.exists()) {
				file.createNewFile();
			}
	
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("package com.devanshu.linkedlist;");
			bw.write("\n");
			bw.write("\n");
			bw.write(content);
			bw.close();
		}catch(IOException e){
			throw new Exception("unable to create file on path "+ HOME_PATH+SOLUTION_PATH);
		}
    }


	
}
