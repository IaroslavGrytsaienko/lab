/**
 * 
 */
package classes;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.Queue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Iaroslav_Grytsaienko
 */
@RunWith(MockitoJUnitRunner.class)
public class PrefixMatcherTest {
	
	@Mock
	private Trie mockTrie;
	
	@InjectMocks
	private PrefixMatcher prefMatcherInj;
	
	private PrefixMatcher prefMatcher;
	/**
	 * @throws java.lang.Exception
	 */
	
	@Before
	public void setUp() throws Exception {
		// initializing prefMatcher for Mock testing
		prefMatcherInj.add("a ab abc abc abcdx abcdxs  asdasdawdsd	");
		prefMatcherInj.add("abcds", "abcdw");
		String[] strMass = { "abb", "abbc" };
		prefMatcherInj.add(strMass);
		
		// initializing prefMatcher for Junit testing
		prefMatcher = new PrefixMatcher();
		prefMatcher.add("a ab abc abc abcdx abcdxs  asdasdawdsd	");
		prefMatcher.add("abcds", "abcdw");
		
		prefMatcher.add(strMass);
		
	}

	@Test
	public void addTest(){
		
		verify(mockTrie, times(9)).add(any(Tuple.class));
	}
	
	
	@Test
	public void wordsWithPrefixStringIntTest() {
		
		Queue<String> wordsWithPrefAB2 = (Queue<String>) prefMatcher.wordsWithPrefix("abc", 2);
		assertTrue(wordsWithPrefAB2.size() == 4);
	}

	@Test
	public void wordsWithPrefixStringTest() {
		
		Queue<String> wordsWithPrefAB = (Queue<String>) prefMatcher.wordsWithPrefix("ab");
		Queue<String> wordsWithPrefAS = (Queue<String>) prefMatcher.wordsWithPrefix("as");
		assertTrue(wordsWithPrefAB.size() == 6);
		assertTrue(wordsWithPrefAS.size() == 0);
	}

	@Test
	public void deleteTest() {
		
		assertTrue(prefMatcher.delete("abb"));
		assertFalse(prefMatcher.contains("abb"));
		assertTrue(prefMatcher.size() == 7);
	}

	@Test
	public void addAndSizeTest() {
		
		assertTrue(prefMatcher.size() == 8);
	}

	@Test
	public void containsTest() {
		
		assertTrue(prefMatcher.contains("abc"));
		assertTrue(prefMatcher.contains("abcdx"));
		assertTrue(prefMatcher.contains("abcdxs"));
		assertTrue(prefMatcher.contains("asdasdawdsd"));
		assertTrue(prefMatcher.contains("abcds"));
		assertTrue(prefMatcher.contains("abcdw"));
		assertTrue(prefMatcher.contains("abb"));
		assertTrue(prefMatcher.contains("abbc"));
		assertFalse(prefMatcher.contains("ab"));
	}
}
