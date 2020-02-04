class SmallestSubArrayWithGivenSum {
	/***
	 * Given an array of positive numbers and a positive number ‘S’, find the length
	 * of the smallest contiguous subarray whose sum is greater than or equal to
	 * ‘S’. Return 0, if no such subarray exists
	 * 
	 * 
	 * Input: [2, 1, 5, 2, 3, 2], S=7
	 * 
	 * Output: 2
	 * 
	 * Explanation: The smallest subarray with a sum great than or equal to '7' is
	 * [5, 2].
	 * 
	 * Input: [2, 1, 5, 2, 8], S=7
	 * 
	 * Output: 1
	 * 
	 * Explanation: The smallest subarray with a sum greater than or equal to '7' is
	 * [8].
	 * 
	 * Input: [3, 4, 1, 1, 6], S=8
	 * 
	 * Output: 3
	 * 
	 * Explanation: Smallest subarrays with a sum greater than or equal to '8' are
	 * [3, 4, 1] or [1, 1, 6].
	 **/
	public static int findMinSubArray(int[] arr, int S) {
		int n = arr.length;
		int subArrayLength = n + 1;
		int subarraySum = 0;
		int startIndex = 0;
		for (int i = 0; i < n; i++) {
			subarraySum += arr[i];
			while (subarraySum >= S) {
				int tempLength = i - startIndex + 1;
				subArrayLength = Math.min(tempLength, subArrayLength);
				subarraySum -= arr[startIndex];
				startIndex++;
			}
		}
		return subArrayLength;
	}

	public static void main(String[] args) {
		System.out.println(findMinSubArray(new int[] { 2, 1, 5, 2, 8 }, 7));
	}
}