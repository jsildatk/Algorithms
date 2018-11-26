import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

public class RadixSort {
	public static void countingSort(String[] arr, int index, char lower, char upper) {
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

	public static int countLines() {
		int lines = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader("test.txt"));
			while (reader.readLine() != null) {
				lines++;
			}
			reader.close();
		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			return lines;
		}
	}

	public static void getDataFromFile(String[] arrStrings, String[][] arrResult) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("test.txt"));
			String line;
			int i = 0;

			while ((line = reader.readLine()) != null) {
				String[] temp = line.split("\\s+");
				arrStrings[i] = temp[1].toLowerCase();
				arrResult[i][0] = temp[0];
				arrResult[i][1] = temp[1].toLowerCase();
				i++;
			}
			reader.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public static String findNumber(String text, String[][] arr, int size) {
		String number = " ";

		for (int i = 0; i < size; i++) {
			if (arr[i][1].equals(text)) {
				number = arr[i][0];
			}
		}
		return number;
	}

	public static void getResult(String[][] arr, String[][] arrResult, String[] arrStrings, int size) {
		for (int i = 0; i < size; i++) {
			arrResult[i][0] = findNumber(arrStrings[i], arr, size);
			arrResult[i][1] = arrStrings[i].substring(0, 1).toUpperCase() + arrStrings[i].substring(1);
		}
	}

	public static void saveResult(String[][] arrResult, int size) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("result.txt"));
			for (int i = 0; i < size; i++) {
				writer.write(arrResult[i][0] + " " + arrResult[i][1]);
				writer.newLine();
			}
			writer.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public static void main(String[] args) {
		int size = countLines();
		String[] arrStrings = new String[size];
		String[][] arr = new String[size][2];
		String[][] arrResult = new String[size][2];
		getDataFromFile(arrStrings, arr);
		radixSort(arrStrings, 'a', 'z');
		getResult(arr, arrResult, arrStrings, size);
		saveResult(arrResult, size);
	}
}
