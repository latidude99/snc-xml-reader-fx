package com.latidude99.sncxmlreader.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class FormatUtils {
	
	public static Set<String> stringToNumbers(String input) {
//		String numbersAsString = "2, 4, 5,,67,7 6,32,3, 5,7-9, 8-17,15, 17, e9 0, 9-q9, 1 2-14";
//		System.out.println("getUserDefinedId() ->");
        List<String> numbersListString = new ArrayList<>();
        Set<String> numbersSetFinal = new TreeSet<>();
        int notValid = 0;
//        System.out.println("Input string: " + input);
        String[] numbers = input.split(",");
        for(String s: numbers) {
	        s= s.trim().replaceAll(" ", "").replaceAll("\u00A0", "");
//	        System.out.println("after trim() and replace() s =" + s);
	        notValid = 0;
	        for(int i = 0; i < s.length(); i++){
	          	if((s.charAt(i) != '0')
	               && (s.charAt(i) != '1')
	               && (s.charAt(i) != '2')
	               && (s.charAt(i) != '3')
	               && (s.charAt(i) != '4')
	               && (s.charAt(i) != '5')
	               && (s.charAt(i) != '6')
	               && (s.charAt(i) != '7')
	               && (s.charAt(i) != '8')
	               && (s.charAt(i) != '9')
	               && (s.charAt(i) != '-'))
	            notValid++;
//	            System.out.print(s.charAt(i) + ", ");
//	            System.out.println("notValid for the char: " + notValid);
	        }
//	        System.out.println("notValid for the string: " + notValid);
	        if(!s.isEmpty() && notValid < 1)
	        	numbersListString.add(s);
        }
//        System.out.println(numbersListString);
        for(String n: numbersListString) {
        	if(n.contains("-")) {
        		String[] range = n.split("-");
        		int rangeMin = Integer.parseInt(range[0]);
        		int rangeMax = Integer.parseInt(range[1]);
        		if( rangeMin < rangeMax) {
        			for(int i = rangeMin; i <rangeMax + 1; i++) {
        				numbersSetFinal.add(i + "");
        			}
        		}else {
        			for(int i = rangeMax; i < rangeMin + 1; i++) {
            			numbersSetFinal.add(i + "");
        			}
        		}
        	}else {
        		numbersSetFinal.add(n);
        	}
        }
        return numbersSetFinal;
	}
	
	
	public static String printList20Cols(List<String> list) {
		StringBuilder sb = new StringBuilder();
		int count = 0;
		for(String s : list) {
			sb.append(s + "  ");
			count++;
			if(count == 5 || count == 10 || count ==15) {
				sb.append("   ");
			}
			if(count == 20) {
				sb.append("\n");
				count = 0;
			}
		}
		return sb.toString();
	}
	
	public static String printSet20Cols(Set<String> set) {
		StringBuilder sb = new StringBuilder();
		int count = 0;
		for(String s : set) {
			sb.append(s + "  ");
			count++;
			if(count == 5 || count == 10 || count ==15) {
				sb.append("   ");
			}
			if(count == 20) {
				sb.append("\n");
				count = 0;
			}
		}
		return sb.toString();
	}
	
	public static List<String>  stringToList(String content){
		List<String> contentList = new ArrayList<>();
		String newline = System.getProperty("line.separator");
		boolean hasNewLine = content.contains(newline);
		if((content.trim().length() > 0) && (!hasNewLine)){
			String[] lines = content.split("\n");
			contentList = new ArrayList<String>(Arrays.asList(lines));
		} else {
			MessageBox.show("The text area is empty!", "Input error");
		}
		return contentList;
	}
	
	
	


}






















