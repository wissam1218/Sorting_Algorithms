import java.util.Random;

public class internalMerge {

	static void merge(int arr[], int l, int m, int r) {
		// find the size for the two sub arrays, then create
		int s1 = m - l + 1;
		int s2 = r - m;
		int left[] = new int[s1];
		int right[] = new int[s2];
		// copy data over
		for (int i = 0; i < s1; ++i)
			left[i] = arr[l + i];
		for (int j = 0; j < s2; ++j)
			right[j] = arr[m + 1 + j];
		int i = 0, j = 0;
		int k = l;
		// sort halves
		while (i < s1 && j < s2) {
			if (left[i] <= right[j]) {
				arr[k] = left[i];
				i++;
			} else {
				arr[k] = right[j];
				j++;
			}
			k++;
		}
		// copy remaining elements in the left array
		while (i < s1) {
			arr[k] = left[i];
			i++;
			k++;
		}
		// copy remaining elements in the right array
		while (j < s2) {
			arr[k] = right[j];
			j++;
			k++;
		}
	}

	static void sort(int arr[], int l, int r) {
		if (l < r) {
			// find mid
			int m = (l + r) / 2;
			// sort both halves
			sort(arr, l, m);
			sort(arr, m + 1, r);
			// merge the sorted halves
			merge(arr, l, m, r);
		}
	}

	static void printArray(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	public static void main(String args[]) {
		Random r = new Random();
		int[] arr = { 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		System.out.println("Array before sort: ");
		printArray(arr);
		long startTime = System.nanoTime();
		internalMerge.sort(arr, 0, arr.length - 1);
		long endTime = System.nanoTime();
		long runTime = endTime - startTime;
		System.out.println("\nSorted array");
		printArray(arr);
		System.out.println("\nRun time is: " + runTime + "(nano seconds)");

	}
}
