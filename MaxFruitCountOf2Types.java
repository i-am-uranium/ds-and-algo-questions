import java.util.HashMap;
import java.util.Map;

class MaxFruitCountOf2Types {
	/**
	 * Given an array of characters where each character represents a fruit tree,
	 * you are given two baskets and your goal is to put maximum number of fruits in
	 * each basket. The only restriction is that each basket can have only one type
	 * of fruit.
	 * 
	 * Input: Fruit=['A', 'B', 'C', 'A', 'C']
	 * 
	 * Output: 3
	 * 
	 * Explanation: We can put 2 'C' in one basket and one 'A' in the other from the
	 * subarray ['C', 'A', 'C']
	 * 
	 * Input: Fruit=['A', 'B', 'C', 'B', 'B', 'C']
	 * 
	 * Output: 5
	 * 
	 * Explanation: We can put 3 'B' in one basket and two 'C' in the other basket.
	 * This can be done if we start with the second letter: ['B', 'C', 'B', 'B',
	 * 'C']
	 * 
	 ***/

	static int maxFruitCountOf2Types(char[] arr) {
		int maxLength = Integer.MIN_VALUE;
		Map<Character, Integer> charFreqMap = new HashMap<>();
		int windowStart = 0;
		for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
			char rightChar = arr[windowEnd];
			charFreqMap.put(rightChar, charFreqMap.getOrDefault(rightChar, 0) + 1);
			while (charFreqMap.size() > 2) {
				char leftChar = arr[windowStart];
				charFreqMap.put(leftChar, charFreqMap.get(leftChar) - 1);
				if (charFreqMap.get(leftChar) == 0) {
					charFreqMap.remove(leftChar);
				}
				windowStart++;
			}
			maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
		}
		return maxLength;
	}

	public static void main(String[] args) {
		System.out.println(maxFruitCountOf2Types(new char[] { 'A', 'B', 'C', 'A', 'C' }));
	}
}