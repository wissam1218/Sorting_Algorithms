public class selectionSort {
	
	void sort(int arr[]) {
		int n = arr.length;
		// move the limit of the array
		for (int i = 0; i < n - 1; i++) {
			// find min
			int min = i;
			for (int j = i + 1; j < n; j++)
				if (arr[j] < arr[min])
					min = j;
			// swap min with first element
			int temp = arr[min];
			arr[min] = arr[i];
			arr[i] = temp;
			}
	}
	void printArray(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	// Driver code to test above
	public static void main(String args[]) {
		selectionSort ob = new selectionSort();
		int arr[] = {10,9,8,7,6,5,4,3,2,1};
		ob.sort(arr);
		System.out.println("Sorted array");
		ob.printArray(arr);
	}
}
