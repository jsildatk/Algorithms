import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	private static String[] readLines(String filename) throws IOException {
		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		List<String> lines = new ArrayList<String>();
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			lines.add(line);
		}
		bufferedReader.close();
		return lines.toArray(new String[lines.size()]);
    }
    
    private static int hash(String text, int m) {
		int hash = 0;
		int length = text.length();
		int code;
		for (int i = 0; i < length; i++) {
			code = (int)text.charAt(i);
			hash += code;
		}
		return hash % m;
	}
	
	private static void hashValues(String[] data, int tab[], int m) {
		int index;
		for (String word : data) {
			index = hash(word, m);
			tab[index]++;
		}
	}
	
	private static void test(String[] data, int tab[], int m) {
		hashValues(data, tab, m);
		int zero = 0;
		int notZero = 0;
		int max = 0;
		double average = 0;
		for (int i = 0; i < tab.length; i++) {
			if (tab[i] == 0) {
				zero++;
			} else {
				average += tab[i];
				notZero++;
			}
			
			if (tab[i] > max) {
				max = tab[i];
			}
		}
		average /= notZero;
		System.out.println("Zerowe elementy: " + zero);
		System.out.println("Srednia wartosc: " + average);
		System.out.println("Maksymalna wartosc: " + max);
	}
	
	public static void main(String[] args) {
		try {
			int m;
			String[] data = readLines("3700");
			m = 2221;
			int tab[] = new int[m];
			Arrays.fill(tab, 0);
			test(data, tab, m);
			System.out.println("-------------------------------");
			m = 1500;
			int tab1[] = new int[m];
			Arrays.fill(tab1, 0);
			test(data, tab1, m);
			System.out.println("-------------------------------");
			m = 511;
			int tab2[] = new int[m];
			Arrays.fill(tab2, 0);
			test(data, tab2, m);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}