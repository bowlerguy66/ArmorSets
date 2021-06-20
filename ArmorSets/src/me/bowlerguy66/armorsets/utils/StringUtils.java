package me.bowlerguy66.armorsets.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {

	public static List<String> makeParagraphFromLine(String line, int lineWidth) {
		
		String[] words = line.split(" ");
		List<String> paragraph = new ArrayList<String>();
		
		StringBuilder currentLine = new StringBuilder("");
		for(int i = 0; i < words.length; i++) {
			
			if(currentLine.length() + words[i].length() > lineWidth || words[i].equals("\n")) {
				currentLine.append(words[i]);
				paragraph.add(currentLine.toString());
				currentLine = new StringBuilder("");
				continue;
			}
			
			currentLine.append(words[i]);
			currentLine.append(" ");
			
		}
		
		paragraph.add(currentLine.toString());
		return paragraph;
		
	}
	
	public static List<String> makeParagraphFromLine(String line) {
		return makeParagraphFromLine(line, 30);
	}
	
}
