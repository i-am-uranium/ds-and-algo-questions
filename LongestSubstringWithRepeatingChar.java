import java.util.Map;
import java.util.HashMap;;

class LongestSubstringWithRepeatingChar {
	/***
	 * 
	 * Given a string with lowercase letters only, if you are allowed to replace no
	 * more than ‘k’ letters with any letter, find the length of the longest
	 * substring having the same letters after replacement.
	 * 
	 * Input: String="aabccbb", k=2
	 * 
	 * Output: 5
	 * 
	 * Explanation: Replace the two 'c' with 'b' to have a longest repeating
	 * substring "bbbbb".
	 * 
	 * 
	 * Input: String="abbcb", k=1
	 * 
	 * Output: 4
	 * 
	 * Explanation: Replace the 'c' with 'b' to have a longest repeating substring
	 * "bbbb".
	 ***/

	public static int findLength(String str, int k) {
		int strLength = str.length();
		int maxLength = 0;
		int maxRepeatingChar = 0;
		int windowStart = 0;

		Map<Character, Integer> charFreqMap = new HashMap<>();

		for (int windowEnd = 0; windowEnd < strLength; windowEnd++) {
			char rightChar = str.charAt(windowEnd);
			charFreqMap.put(rightChar, charFreqMap.getOrDefault(rightChar, 0) + 1);
			maxRepeatingChar = Math.max(maxRepeatingChar, charFreqMap.get(rightChar));

			int windowSize = windowEnd - windowStart + 1;

			if (windowSize - maxRepeatingChar > k) {
				char leftChar = str.charAt(windowStart);
				charFreqMap.put(leftChar, charFreqMap.get(leftChar) - 1);
				windowStart++;
			}
			maxLength = Math.max(maxLength, windowSize);
		}
		return maxLength;
	}

	public static void main(String[] args) {
		System.out.println(findLength("abbcb", 1));
	}
}