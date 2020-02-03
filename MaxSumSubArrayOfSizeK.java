import java.util.*;

class MaxSumSubArrayOfSizeK {
	/**
	 * Given an array of positive numbers and a positive number ‘k’, find the
	 * maximum sum of any contiguous subarray of size ‘k’.
	 * 
	 * Input: [2, 1, 5, 1, 3, 2], k=3
	 * 
	 * Output: 9
	 * 
	 * Explanation: Subarray with maximum sum is [5, 1, 3].
	 * 
	 * Input: [2, 3, 4, 1, 5], k=2
	 * 
	 * Output: 7
	 * 
	 * Explanation: Subarray with maximum sum is [3, 4].
	 * 
	 **/

	public static int findMaxSumSubArray(int[] arr, int k) {
		int subArraySum = 0;
		int subArrayMaxsum = 0;
		for (int i = 0; i < k; i++) {
			subArraySum += arr[i];
		}
		subArrayMaxsum = subArraySum;
		int n = arr.length;
		for (int i = k; i < n; i++) {
			subArraySum = subArraySum - arr[i - k] + arr[i];
			subArrayMaxsum = Math.max(subArraySum, subArrayMaxsum);
		}
		return subArrayMaxsum;
	}

	public static void main(String[] args) {
		System.out.println(findMaxSumSubArray(new int[] { 2, 3, 4, 1, 5 }, 2));
	}
}