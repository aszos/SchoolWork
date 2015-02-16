package ps5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Parser implements Comparator<String>
{
    // used by the methods of Parser
    private Map<String, Integer> frequencies;
    
    public Parser() 
    {
        // do not change the constructor, this initializes an empty Map
        frequencies = new HashMap<String, Integer>();
    }
    
    public static void main(String[] args)
    {
    	Parser hamlet = new Parser();  		
    	Parser juliusCaesar = new Parser();
    	Parser othello = new Parser();
    	Parser romeoAndJuliet = new Parser();
    	Parser twelfthNight = new Parser();
    	
    	try
    	{
    		hamlet.parse("./hamlet.txt");
    		juliusCaesar.parse("./juliuscaesar.txt");
    		othello.parse("./othello.txt");
    		romeoAndJuliet.parse("./romeoandjuliet.txt");
    		twelfthNight.parse("./twelfthnight.txt");
    	}
    	catch(IOException e)
    	{
    		System.out.println("File not found.");
    	}
    	
    	Parser[] parsers = {hamlet, juliusCaesar, othello, romeoAndJuliet, twelfthNight};
    	String[] stringParsers = {"Hamlet", "Julius Caesar", "Othello" , "Romeo And Juliet", "Twelfth Night"};
    	
    	int a = 0;
    	int b = 0;
    	int value = 0;
    	
    	for(int i = 0; i < parsers.length; i++)
    	{
    		for(int j = i + 1; j < parsers.length; j++)
    		{
    			int currentTest = parsers[i].totalCommonTop100Words(parsers[j]);
    			if(currentTest > value)
    			{	
    				a = i;
    				b = j;
    				value = currentTest;
    			}
    		}
    	}    	
    	System.out.println("The two most similar plays are " + stringParsers[a] + " and " + stringParsers[b] + " with " + value + " similar words." );
    
       	//Alexander Szostek
    	//The two most similar plays are Hamlet and Othello with 80 similar words.
    }
    
    
    public void parse(String filename) throws IOException
    {
    	Scanner textFileParser = null;
    	String currentWord = "";
    	
    	//check if the filename given exists
    	try
    	{
    		textFileParser = new Scanner(new File(filename));	
    	}
    	catch(FileNotFoundException e)
    	{
    		System.out.println("File not found.");
    	}
    	
    	//parse through the text word by word
    	while(textFileParser.hasNext())
    	{
    		//change the current word to lower case with no punctuation
    		currentWord = textFileParser.next().toLowerCase().replaceAll("\\p{Punct}", "");
    		
    		//check the frequencies hash map if it already contains the current word
    		if(frequencies.containsKey(currentWord))
    		{
    			frequencies.put(currentWord, frequencies.get(currentWord) + 1);
    		}
    		else
    		{
    			frequencies.put(currentWord, 1);
    		}
    	}	
    	textFileParser.close();
    }
    
    
    public int getCount(String word)
    {
    	//I dislike null pointer exceptions
    	try
    	{
        	return frequencies.get(word);
    	}
    	catch(NullPointerException e)
    	{
    		return 0;
    	}
    }

    //orders keys of the frequency hash map by increasing frequency
	public List<String> getWordsInOrderOfFrequency() 
	{
		List<String> library = new ArrayList<String>();
		library.addAll(frequencies.keySet());
		Collections.sort(library, this);
		return library;
	}

	public int compare(String a, String b)
	{
		if(getCount(a) > getCount(b))
		{
			return 1;
		}
		else if(getCount(a) < getCount(b))
		{
			return -1;
		}
		return 0;
	}
	
	//returns a set of all the common words between this parser versus another parser
	public Set<String> commonWords(Parser other)
	{
		Set<String> setOfCommonWords = new HashSet<String>();
		
		for(String current : this.frequencies.keySet())
		{
			if(other.frequencies.containsKey(current))
			{
				setOfCommonWords.add(current);
			}
		}
		return setOfCommonWords;
	}
	
	public int totalCommonTop100Words(Parser other)
	{		
		List<String> thisFrequency = this.getWordsInOrderOfFrequency();	
		List<String> otherFrequency = other.getWordsInOrderOfFrequency();
		List<String> thisTop100 = thisFrequency.subList(thisFrequency.size() - 100, thisFrequency.size());
		List<String> otherTop100 = otherFrequency.subList(otherFrequency.size() - 100, otherFrequency.size());
		
		int total = 0;
		for(String s : thisTop100)
		{
			total += (otherTop100.contains(s))?(1):(0);
		}
		return total;		
	}	
}
