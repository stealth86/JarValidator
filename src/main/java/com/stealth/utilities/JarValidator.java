package com.stealth.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.jar.JarFile;

public class JarValidator {	
	public static void searchFile(File file,Boolean delFile){
		if(file.isDirectory()){
			Arrays.asList(file.listFiles())
			      .parallelStream()
			      .forEach(e->JarValidator.searchFile(e,delFile));
		}else{
			if(file.getName().endsWith(".jar")){
				try {
					JarFile jarFile=new JarFile(file);
					jarFile.getManifest();
				} catch (IOException e) {
					System.out.println(file.getAbsolutePath());
					System.out.println(e.getMessage());
					if(delFile.equals(Boolean.TRUE)){
						file.delete();
					}
				}				
			}
		}
	}
	public static void main(String[] args) {
		File dir=new File("/home");
		Boolean delFile =true;
		JarValidator.searchFile(dir,delFile);
	}
}
