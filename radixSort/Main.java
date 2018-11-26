import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

public class Main {
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
        }
        return lines;
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

    public static String[] getStringsFromFile(int size) {
        String[] arr = new String[size];
        try {
			BufferedReader reader = new BufferedReader(new FileReader("test.txt"));
			String line;
			int i = 0;

			while ((line = reader.readLine()) != null) {
				String[] temp = line.split("\\s+");
				arr[i] = temp[1].toLowerCase();
				i++;
			}
			reader.close();
		} catch (Exception ex) {
			System.out.println(ex);
        }
        return arr;
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
        long startTimeRs = System.currentTimeMillis();
        RadixSort.radixSort(arrStrings, 'a', 'z');
        long stopTimeRs = System.currentTimeMillis();
        long elapsedTimeRs = stopTimeRs - startTimeRs;
        getResult(arr, arrResult, arrStrings, size);
        saveResult(arrResult, size);
        String[] arrQuickSort = getStringsFromFile(size);
        long startTimeQs = System.currentTimeMillis();
        QuickSort.quickSort(arrQuickSort, 0, arrQuickSort.length-1);
        long stopTimeQs = System.currentTimeMillis();
        long elapsedTimeQs = stopTimeQs - startTimeQs;
        System.out.println("Radix sort : " + elapsedTimeRs + " ms");
        System.out.println("Quick sort: " + elapsedTimeQs + " ms");
    }
}