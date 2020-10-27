package utils;

import java.io.BufferedReader;
import java.io.FileReader;

public class BinarySearch {

	public static int middle(int left, int right) {
		return ((left) + (((right) - (left)) >> 1));
	}

	public static int binarySearch(float[] data, int first, int last, double key) {
		int mid = (first + last) / 2;
		while (first <= last) {
			if (data[mid] < key) {
				first = mid + 1;
			} else if (data[mid] == key) {
				return mid;
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

	public static int[] binarySearchForMkbs(float[] data, int first, int last, double key) {
		int[] result = new int[2];

		int mid = middle(first, last);
		while (first <= last) {
			if (data[mid] < key) {
				first = mid + 1;
			} else if (data[mid] == key) {
				result[0] = 1;
				result[1] = mid;
				return result;
			} else {
				last = mid - 1;
			}
			mid = middle(first, last);
		}
		result[0] = 0;
		result[1] = first;
		return result;
	}
	
}
