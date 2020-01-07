public class bubbleSort {
	void bubbleSort(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n - 1; i++)
			for (int j = 0; j < n - i - 1; j++)
				if (arr[j] > arr[j + 1]) {
					// swap arr[j+1] and arr[i]
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
	}
	void printArray(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	public static void main(String args[]) {
		bubbleSort bs = new bubbleSort();
		int arr[] = {100,90,80,70,10,20,30,40,50,60};
		bs.bubbleSort(arr);
		System.out.println("Sorted array");
		bs.printArray(arr);
	}
}