import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Hash {
	public static int hash(String text, int m) {
		int hash = 0;
		int length = text.length();
		int letter;
		int code;
        for (int i = 0; i < length; i++){
            letter = text.charAt(i);
            code = (1 + (letter % (m-2))) * i;
            hash += letter + code;
        }
        return hash % m;
	}
	
	public static void hashValues(ArrayList<Record> data, ArrayList<Record> result, int m) {
		int index;
		int start;
		for (int i = 0; i < data.size(); i++) {
			index = hash(data.get(i).getSurname(), m);
			start = index;
			while (index < data.size() && result.get(index).getNumber() != "0" ) {
				index++;
			}
			if (index < data.size() && result.get(index).getNumber() == "0") {
				result.get(index).setNumber(data.get(i).getNumber());
				result.get(index).setSurname(data.get(i).getSurname());
			}
			if (index == data.size()) {
				index = 0;
				while (index < start && result.get(index).getNumber() != "0") {
					index++;
				}
				if (index < start && result.get(index).getNumber() == "0") {
					result.get(index).setNumber(data.get(i).getNumber());
					result.get(index).setSurname(data.get(i).getSurname());
				}
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
