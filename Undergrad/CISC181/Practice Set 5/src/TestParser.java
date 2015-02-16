package ps5;


import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import junit.framework.TestCase;

public class TestParser extends TestCase {
    private static final String[] KNOWN_FREQ_WORDS = 
        { "be", "but", "for", "hamlet", "me", "this", "with", "not", "it",
          "is", "that", "in", "you", "my", "a", "of", "i", "to", "and", "the"};
    
    private static final int[] KNOWN_FREQ_COUNTS = 
        { 434, 451, 471, 485, 496, 512, 519, 571, 642, 685, 734, 758, 838,
          870, 1002, 1066, 1155, 1286, 1715, 1830};
    
    public void test_parse() {
        Parser hamlet = new Parser();
        try {
            hamlet.parse("./hamlet.txt");
            assertEquals(226, hamlet.getCount("be"));
            
            // not a real word, but stripping punctuation will remove apostrophes,
            //  so we need to test it as Shakespeare uses perform'd
            assertEquals(2, hamlet.getCount("performd")); 
            assertEquals(0, hamlet.getCount("television"));
            
            // no uppercase
            assertEquals(0, hamlet.getCount("The")); 
        }
        catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }
    
    public void test_compare() {
        Parser hamlet = new Parser();
        try {
            hamlet.parse("./hamlet.txt");
            Comparator<String> c = hamlet;
            assertTrue(c.compare("be", "television") > 0);
            assertTrue(c.compare("be", "be") == 0);
            assertTrue(c.compare("be", "the") < 0);

        }
        catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }
    
    public void test_getWordsInOrderOfFrequency() {
        Parser rj_hamlet = new Parser();
        try {
            rj_hamlet.parse("./hamlet.txt");
            rj_hamlet.parse("./romeoandjuliet.txt");
            List<String> words = rj_hamlet.getWordsInOrderOfFrequency();
            List<String> last20 = words.subList(words.size()-20, words.size());
            
            for (int i = 0; i < last20.size(); i++) {
                String word = last20.get(i);
                assertEquals(KNOWN_FREQ_WORDS[i], word);
                assertEquals(KNOWN_FREQ_COUNTS[i], rj_hamlet.getCount(word));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }
    
    
    public void test_commonWords() {
        Parser rj = new Parser();
        Parser hamlet = new Parser();

        try {
            hamlet.parse("./hamlet.txt");
            rj.parse("./romeoandjuliet.txt");
            
            assertEquals(1871, hamlet.commonWords(rj).size());
            assertEquals(1871, rj.commonWords(hamlet).size());
            
            assertEquals(100, hamlet.totalCommonTop100Words(hamlet));
            assertEquals(72, hamlet.totalCommonTop100Words(rj));
           
        }
        catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }
}
