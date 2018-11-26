public class QuickSort {
    private static void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void quickSort(String[] arr, int start, int end) {
        int i = start;
        int j = end;
        String pivot = arr[start + (end - start)/2];

        while (i <= j) {
            while (arr[i].compareToIgnoreCase(pivot) < 0) {
                i++;
            }
            while (arr[j].compareToIgnoreCase(pivot) > 0) {
                j--;
            }

            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        
        if (start < j) {
            quickSort(arr, start, j);
        }
        if (i < end) {
            quickSort(arr, i, end);
        }
    }
}