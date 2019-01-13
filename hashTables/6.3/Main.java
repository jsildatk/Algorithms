import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {
	private static void writeToFile(ArrayList<Record> data, String filename) throws FileNotFoundException {
		PrintWriter printWriter = new PrintWriter(new FileOutputStream(filename));
		for (Record r : data) {
			printWriter.println(r.getNumber() + " " + r.getSurname());
		}
		printWriter.close();
	}
	
	private static ArrayList<Record> readFromFile(String filename) throws IOException {
		ArrayList<Record> data = new ArrayList<Record>();
		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			String[] temp = line.split("\\s+");
			data.add(new Record(temp[0], temp[1]));
		}
		bufferedReader.close();
		return data;
	}
	
	private static void reset(ArrayList<Record> list, int start, int end) {
		for (int i = start; i <= end; i++) {
			list.get(i).setNumber("0");
			list.get(i).setSurname("Brak");
		}
	}
	
	public static void main(String[] args) {
		try {
			ArrayList<Record> data80 = readFromFile("nazwiska");
			ArrayList<Record> emptyData = readFromFile("nazwiska");
			reset(data80, 15999, 19999);
			reset(emptyData, 0, 19999);
			int[] keys = Hash.hashValues(data80);
			for (int i = 0; i < 20000; i++) {
				Hash.insertHash(data80, emptyData, keys[i], 20000, i);
			}
			Hash.search(emptyData, data80);
			writeToFile(emptyData, "outHash");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
