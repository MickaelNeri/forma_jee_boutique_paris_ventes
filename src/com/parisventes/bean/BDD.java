package com.parisventes.bean;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BDD {
	private String fileName;
	private List<String> allLines;
	
	public BDD(){

	}
	
	public BDD(String filename){
		this.fileName = filename;
		
	}
	
	public List<String>readFile(){
    	try {
    		allLines = (List<String>) Files.readAllLines( Paths.get(this.fileName) );
    	} catch( IOException e) {
    		e.printStackTrace();
    	}
    	return allLines;
    }

	
	
	
}
