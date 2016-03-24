package classes;

import java.rmi.NoSuchObjectException;
import java.util.Queue;

/**
 * ����� ������������ �������������� �� ���� ������ ��������� ������.
 * 
 * @author Iaroslav_Grytsaienko
 *
 */
public class PrefixMatcher {

	private Trie trie;

	/**
	 * Constructor
	 */
	public PrefixMatcher() {
		trie = new RWayTrie();
	}

	/**
	 * ��������� in-memory ������� ����. ����� ��������� �����, ������, ������
	 * ����/�����. ���� �������� ������, �� ��� ����������� �� ����� ��
	 * ��������. � ������� ������ ����������� ����� ������� 2� ��������.
	 * �������������� ��� ����� ���������� �����������.
	 * 
	 * @param strings
	 * @return
	 */
	public int add(String... strings) {
		// [a-zA-Z]{3,} - regex for matching ;-)
		for (String str : strings) {
			String[] splitedStr = str.trim().split("\\s");
			for (String splStr : splitedStr) {
				addString(splStr);
			}
		}
		return trie.size();
	}

	/**
	 * Inserts words started only with letter with at least 3 symbols length.
	 * Convert all letters of the words to lower-case letters.
	 * 
	 * @param str
	 *            input word
	 */
	private void addString(String str) {
		if (!str.isEmpty() && str.charAt(0) > 64 && str.length() > 2) {
			trie.add(new Tuple(str.toLowerCase()));
		}
	}

	/**
	 * ���� �� ����� � �������
	 * 
	 * @param word
	 * @return
	 */
	public boolean contains(String word) {
		return trie.contains(word);
	}

	/**
	 * ������� ����� �� �������
	 * 
	 * @param word
	 * @return
	 */
	public boolean delete(String word) {
		return trie.delete(word);
	}

	/**
	 * �-�� ���� � �������
	 * 
	 * @return
	 */
	public int size() {
		return trie.size();
	}

	/**
	 * ���� ��������� pref ������� ��� ����� 2� ��������, �� ���������� �����
	 * ���� k ������ ���� ������� � �����������, � ������������ � �������
	 * �������� pref. ������, ���� ����� ��������� ����� � pref='abc': abc 3
	 * abcd 4 abce 4 abcde 5 abcdef 6 - ��� k=1 ������������ 'abc' - ��� k=2
	 * ������������ 'abc', 'abcd', 'abce' - ��� k=3 ������������ 'abc', 'abcd',
	 * 'abce', 'abcde' - ��� k=4 ������������ 'abc', 'abcd', 'abce', 'abcde',
	 * 'abcdef'
	 * 
	 * @param pref
	 * @param k
	 * @return
	 * @throws NoSuchObjectException
	 */

	public Iterable<String> wordsWithPrefix(String pref, int k) {
		return getWordsByPrefAndLength(pref, k);
	}

	/**
	 * Get words out from trie by pref and specified index k
	 * 
	 * @param pref
	 *            - prefix of the words
	 * @param k
	 *            = k.length() + pref.length() of the words
	 * @return LinkedList<Strings>
	 */
	private Iterable<String> getWordsByPrefAndLength(String pref, int k) {

		if (pref.length() < 2) {
			throw new IllegalArgumentException("pref length is more than 2");
		} else if (k < 1) {
			throw new IllegalArgumentException("k must bee more than 0");
		} else {
			Queue<String> words = (Queue<String>) trie.wordsWithPrefix(pref);
			for (String word : words) {
				if (word.length() > k + pref.length()) {
					words.remove(word);
				}
			}
			return words;
		}
	}

	/**
	 * ���� ��������� pref ������� ��� ����� 2� ��������, �� ���������� �����
	 * ���� k=3 ������ ���� ������� � �����������, � ������������ � �������
	 * �������� pref.
	 * 
	 * @param pref
	 * @return
	 * @throws NoSuchObjectException
	 */
	//
	public Iterable<String> wordsWithPrefix(String pref) {
		return getWordsByPrefAndLength(pref, 3);
	}
}