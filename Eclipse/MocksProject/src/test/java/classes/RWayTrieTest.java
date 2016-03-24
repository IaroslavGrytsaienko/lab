/**
 * 
 */
package classes;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Queue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Iaroslav_Grytsaienko
 *
 */
public class RWayTrieTest {

	private Trie trie = new RWayTrie();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		Tuple tuple1 = new Tuple("abc");
		Tuple tuple2 = new Tuple("abc");
		Tuple tuple3 = new Tuple("abcd");
		Tuple tuple4 = new Tuple("abcd");
		Tuple tuple5 = new Tuple("abcdxs");
		
		trie.add(tuple1);
		trie.add(tuple2);
		trie.add(tuple3);
		trie.add(tuple4);
		trie.add(tuple5);
	}

	

	@Test
	public void addAndSizeTest() {

		assertTrue(trie.size() == 3);
	}

	@Test
	public void containsTEst() {

		assertTrue(trie.contains("abc"));
		assertTrue(trie.contains("abcd"));
		assertTrue(trie.contains("abcdxs"));
	}
	
	@Test
	public void wordsTest(){
		
		String[] expected = {"abc", "abcd", "abcdxs"};
		Queue<String> actuals = (Queue<String>)trie.words();
		assertArrayEquals(expected, actuals.toArray());
	}
	
	@Test
	public void wordsWithPrefixTest(){
		
		String[] expected = {"abcd", "abcdxs"};
		Queue<String> actuals = (Queue<String>)trie.wordsWithPrefix("abcd");
		assertArrayEquals(expected, actuals.toArray());
	}
	
	@Test
	public void deleteTest(){
		
		String[] expected = {"abc", "abcdxs"};
		trie.delete("abcd");
		Queue<String> actuals = (Queue<String>)trie.words();
		assertArrayEquals(expected, actuals.toArray());
	}
}
