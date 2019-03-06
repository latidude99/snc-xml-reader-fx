package com.latidude99.sncxmlreader.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class FormatUtils {
	
	public static Set<String> parseInput(String input) {
        List<String> inputList = new ArrayList<>();
        Set<String> outputSet = new TreeSet<>();
        
        String[] numbers = input.split(",");
		for(String s: numbers) {
	        s= s.trim().replaceAll(" ", "").replaceAll("\u00A0", "");
        	inputList.add(s);
        }
        for(String n: inputList) {
        	if(n.contains("-") && n.matches("[0-9]+")) {
        		String[] range = n.split("-");
        		int rangeMin = Integer.parseInt(range[0]);
        		int rangeMax = Integer.parseInt(range[1]);
        		if( rangeMin < rangeMax) {
        			for(int i = rangeMin; i <rangeMax + 1; i++) {
        				outputSet.add(i + "");
        			}
        		}else {
        			for(int i = rangeMax; i < rangeMin + 1; i++) {
            			outputSet.add(i + "");
        			}
        		}
			}else if(n.contains("-")){
				String lettersLeft = "";
				String lettersRight = "";
				String[] range = n.split("-");
				for (int i=0; i<range[0].length(); i++){
					if(!range[0].substring(i, i+1).matches("[0-9]+"))
						lettersLeft = lettersLeft + range[0].charAt(i);
				}
				for (int i=0; i<range[1].length(); i++){
					if(!range[1].substring(i, i+1).matches("[0-9]+"))
						lettersRight = lettersRight + range[0].charAt(i);
				}
				if(lettersLeft.equals(lettersRight)){
					int rangeMin = Integer.parseInt(range[0].substring(lettersLeft.length()));
					int rangeMax = Integer.parseInt(range[1].substring(lettersRight.length()));
					if( rangeMin < rangeMax) {
						for(int i = rangeMin; i <rangeMax + 1; i++) {
							outputSet.add(lettersLeft.toUpperCase() + i);
						}
					}else {
						for(int i = rangeMax; i < rangeMin + 1; i++) {
							outputSet.add(lettersLeft.toUpperCase() + i);
						}
        			}
				}else if(!lettersLeft.equals(lettersRight) && lettersRight.equals("")){
					int rangeMin = Integer.parseInt(range[0].substring(lettersLeft.length()));
					int rangeMax = Integer.parseInt(range[1].substring(lettersRight.length()));
					if( rangeMin < rangeMax) {
						for(int i = rangeMin; i <rangeMax + 1; i++) {
							outputSet.add(lettersLeft.toUpperCase() + i);
						}
					}else {
						for(int i = rangeMax; i < rangeMin + 1; i++) {
							outputSet.add(lettersLeft.toUpperCase() + i);
						}
        			}
				}
        	}else {
        		outputSet.add(n);
        	}
		}
		return outputSet;
    }
	
	// not used any longer, pareInput() used for numbers and strings combined
	public static Set<String> parseInputNumbersOnly(String input) {
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






















