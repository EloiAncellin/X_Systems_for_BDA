package utils;

public class BinarySearch {

	public static int middle(int left, int right) {
		return ((left) + (((right) - (left)) >> 1));
	}

	public static int binarySearch(int[][] data, int first, int last, int key) {
		int mid = (first + last) / 2;
		while (first <= last) {
			if (data[mid][1] < key) {
				first = mid + 1;
			} else if (data[mid][1] == key) {
				return data[mid][0];
			} else {
				last = mid - 1;
			}
			mid = (first + last) / 2;
		}
		if (first > last) {
			return -1;
		}
		return -1;
	}

	public static int[] binarySearchForMkbs(int[][] data, int first, int last, int key) {
		int[] result = new int[3];

		int mid = middle(first, last);
		while (first <= last) {
			if (data[mid][1] < key) {
				first = mid + 1;
			} else if (data[mid][1] == key) {
				result[0] = 1;
				result[1] = data[mid][0];
				result[2] = mid;
				return result;
			} else {
				last = mid - 1;
			}
			mid = middle(first, last);
		}
		result[0] = 0;
		result[1] = result[2] = first;
		return result;
	}
}
