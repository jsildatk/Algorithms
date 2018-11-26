import java.util.Arrays;

public class RadixSort {
	private static void countingSort(String[] arr, int index, char lower, char upper) {
		int size = arr.length;
		int[] counts = new int[(upper-lower)+2];
		String[] output = new String[size];
		int charIndex;

		Arrays.fill(counts, 0);
		
		for (int i = 0; i < size; i++) {
			if (arr[i].length()-1 < index) {
				charIndex = 0;
			} else {
				charIndex = (arr[i].charAt(index) - lower)+1;
			}
			counts[charIndex]++;
		}
		
		for (int i = 1; i < counts.length; i++) {
			counts[i] += counts[i-1];
		}
		
		for (int i = size-1; i >= 0; i--) {
			if (arr[i].length()-1 < index) {
				charIndex = 0;
			} else {
				charIndex = (arr[i].charAt(index) - lower)+1;
			}
			output[counts[charIndex]-1] = arr[i];
			counts[charIndex]--;
		}
		
		for (int i = 0; i < size; i++) {
			arr[i] = output[i];
		}
	}
	
	public static void radixSort(String[] arr, char lower, char upper) {
		int maxIndex = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].length()-1 > maxIndex) {
				maxIndex = arr[i].length()-1;
			}
		}
		
		for (int i = maxIndex; i >= 0; i--) {
			countingSort(arr, i, lower, upper);
		}
	}
}
