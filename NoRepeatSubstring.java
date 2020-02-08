import java.util.HashMap;
import java.util.Map;

class NoRepeatSubstring {
	/**
	 * Given a string, find the length of the longest substring which has no
	 * repeating characters.
	 * 
	 * Input: String="aabccbb"
	 * 
	 * Output: 3
	 * 
	 * Explanation: The longest substring without any repeating characters is "abc".
	 * 
	 * Input: String="abbbb"
	 * 
	 * Output: 2
	 * 
	 * Explanation: The longest substring without any repeating characters is "ab".
	 **/

	static int noRepeatSubstring(String s) {
		int maxLength = 1;
		for (int i = 0; i < s.length(); i++) {
			for (int j = i + 1; j < s.length(); j++) {
				String tempString = s.substring(i, j);
				Map<Character, Integer> charFreqMap = new HashMap<>();
				for (char c : tempString.toCharArray()) {
					charFreqMap.put(c, charFreqMap.getOrDefault(c, 0) + 1);
				}
				if (charFreqMap.size() == j - i) {
					maxLength = Math.max(maxLength, charFreqMap.size());
				}
			}
		}
		return maxLength;
	}

	static int noRepeatSubstringV2(String s) {
		int maxLength = 1;
		Map<Character, Integer> charIndexMap = new HashMap<>();
		int windowStart = 0;
		for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
			char rightChar = s.charAt(windowEnd);
			if (charIndexMap.containsKey(rightChar)) {
				windowStart = Math.max(windowStart, charIndexMap.get(rightChar) + 1);
			}
			charIndexMap.put(rightChar, windowEnd);
			maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
		}
		return maxLength;
	}

	public static void main(String[] args) {
		System.out.println(noRepeatSubstringV2("aabccbb"));
	}
}