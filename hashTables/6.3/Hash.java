import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Hash {
	private static int hashString(String text) {
		int hash = 0;
		for (int i = 0; i < text.length(); i++) {
			hash += text.charAt(i);
		}
		return hash;
	}

	private static int hash(int k, int m, int i) {
		int mod1 = k % m;
		int mod2 = i * (1 + k % (m - 2));
		return (mod1 + mod2) % m;
	}
	
	public static void hashValues(ArrayList<Record> data, ArrayList<Record> result, int m) {
		for (int i = 0; i < m; i++) {
			int index = hash(hashString(data.get(i).getSurname()), m, i);
			if (result.get(index).getNumber() == "0") {
				result.get(index).setNumber(data.get(i).getNumber());
				result.get(index).setSurname(data.get(i).getSurname());
			}
		}
	}
	
	public static void search(ArrayList<Record> data, ArrayList<Record> result) throws FileNotFoundException {
		PrintWriter printWriter = new PrintWriter(new FileOutputStream("outSearch"));
		int counter;
		for (Record r : result) {
			counter = 0;
			for (Record r2: data) {
				counter++;
				if (r.getSurname() == "Brak") {
					break;
				} else if (r2.getSurname() == r.getSurname()) {
					printWriter.println("Znaleziono: " + r2.getSurname() + ". Proby: " + counter);
					break;
				}
			}
		}
		printWriter.close();
	}
}
