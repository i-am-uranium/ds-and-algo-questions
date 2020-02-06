import java.util.HashMap;
import java.util.Map;

class LongestSubstringWithKDistinctChars {
	/***
	 * Given a string, find the length of the longest substring in it with no more
	 * than K distinct characters.
	 * 
	 * Input: String="araaci",
	 * 
	 * K=2 Output: 4
	 * 
	 * Explanation: The longest substring with no more than '2' distinct characters
	 * is "araa".
	 * 
	 * 
	 * Input: String="araaci", K=1
	 * 
	 * Output: 2
	 * 
	 * Explanation: The longest substring with no more than '1' distinct characters
	 * is "aa".
	 * 
	 * Input: String="cbbebi", K=3
	 * 
	 * Output: 5
	 * 
	 * Explanation: The longest substrings with no more than '3' distinct characters
	 * are "cbbeb" & "bbebi".
	 **/

	static int longestSubstringWithKDistinctChars(String str, int k) {
		if (str == null || str.length() == 0 || str.length() < k) {
			throw new IllegalArgumentException();
		}
		int length = 0;
		for (int i = 0; i < str.length(); i++) {
			for (int j = i + k; j < str.length(); j++) {
				String tempString = str.substring(i, j);
				Map<Character, Integer> charFreqMap = new HashMap<>();
				for (Character c : tempString.toCharArray()) {
					charFreqMap.put(c, charFreqMap.getOrDefault(c, 0) + 1);
				}
				if (charFreqMap.size() == k) {
					length = Math.max(length, j - i);
				}
			}
		}
		return length;
	}

	static int longestSubstringWithKDistinctCharsV2(String str, int k) {
		if (str == null || str.length() == 0 || str.length() < k) {
			throw new IllegalArgumentException();
		}
		int length = 0;
		Map<Character, Integer> charFreqMap = new HashMap<>();
		int windowStart = 0;
		for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
			char rightChar = str.charAt(windowEnd);
			charFreqMap.put(rightChar, charFreqMap.getOrDefault(rightChar, 0) + 1);
			while (charFreqMap.size() > k) {
				char leftChar = str.charAt(windowStart);
				charFreqMap.put(leftChar, charFreqMap.get(leftChar) - 1);
				if (charFreqMap.get(leftChar) == 0) {
					charFreqMap.remove(leftChar);
				}
				windowStart++;
			}
			length = Math.max(length, windowEnd - windowStart + 1);
		}
		return length;
	}

	public static void main(String[] args) {
		System.out.println(longestSubstringWithKDistinctChars("cbbebi", 3));
		System.out.println(longestSubstringWithKDistinctCharsV2("cbbebi", 3));
	}

}