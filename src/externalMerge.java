import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class externalMerge {

	static String thePath = "C:\\Users\\Sam Ham\\eclipse-workspace\\Sorting_Algorithms\\ExternalMergeSort.txt";
	static int disk_size = 100000;
	static int max_memory = 50000;

	public static void externalSort(String fileName) {
		String tfile = "tmp_file-";
		int[] tArr = new int[max_memory < disk_size ? max_memory : disk_size];
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			int portion = (int) Math.ceil((double) disk_size / max_memory);
			int i = 0, j = 0;
			// read in elements
			for (i = 0; i < portion; i++) {
				// read as many elements as memory allows
				for (j = 0; j < (max_memory < disk_size ? max_memory : disk_size); j++) {
					String t = br.readLine();
					if (t != null)
						tArr[j] = Integer.parseInt(t);
					else
						break;
				}
				// sort the temp array using merge sort
				internalMerge.sort(tArr, 0, tArr.length - 1);
				// write the sorted numbers to temp file(s)
				FileWriter fw = new FileWriter(tfile + i + ".txt");
				PrintWriter pw = new PrintWriter(fw);
				for (int k = 0; k < j; k++)
					pw.println(tArr[k]);
				pw.close();
				fw.close();
			}
			br.close();
			fr.close();
			// open each file, merge them, write them back to disk
			int[] partition = new int[portion];
			BufferedReader[] brs = new BufferedReader[portion];
			for (i = 0; i < portion; i++) {
				brs[i] = new BufferedReader(new FileReader(tfile + i + ".txt"));
				String t = brs[i].readLine();
				if (t != null)
					partition[i] = Integer.parseInt(t);
				else
					partition[i] = Integer.MAX_VALUE;
			}
			FileWriter fw = new FileWriter(thePath);
			PrintWriter pw = new PrintWriter(fw);
			for (i = 0; i < disk_size; i++) {
				int min = partition[0];
				int minFile = 0;
				for (j = 0; j < portion; j++) {
					if (min > partition[j]) {
						min = partition[j];
						minFile = j;
					}
				}
				pw.println(min);
				String t = brs[minFile].readLine();
				if (t != null)
					partition[minFile] = Integer.parseInt(t);
				else
					partition[minFile] = Integer.MAX_VALUE;
			}
			for (i = 0; i < portion; i++)
				brs[i].close();
			pw.close();
			fw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static String tempFiles(int n) {
		String fileName = "numbers.txt";
		Random r = new Random();
		try {
			FileWriter fw = new FileWriter(fileName);
			PrintWriter pw = new PrintWriter(fw);
			for (int i = 0; i < n; i++)
				pw.println(r.nextInt(101));
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}

	public static void main(String[] args) {
		String fileName = tempFiles(disk_size);
		long startTime = System.nanoTime();
		externalSort(fileName);
		long endTime = System.nanoTime();
		long runTime = endTime - startTime;
		System.out.println("Run time is: " + runTime +"(nanoseconds)");
	}
}
