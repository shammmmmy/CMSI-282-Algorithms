/**

Takes a dataset of integers plus int k, and outputs the kth smallest number in the dataset.

**/

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Select {

	private static ArrayList<Integer> inputs;

	public static void main(String args[]) throws IOException {

		int kthIndex = Integer.parseInt(args[0]) - 1;
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String currentLine = stdIn.readLine();
		inputs = new ArrayList<Integer>();

		while (currentLine != null && currentLine.matches("[0-9]+")) {
			inputs.add(Integer.parseInt(currentLine));
			currentLine = stdIn.readLine();
		}
		if (kthIndex > inputs.size()) {
			System.out.println("BAD DATA");
		} else {
			System.out.println(quickSort(0, inputs.size() - 1, kthIndex));
		}
	}

	private static int quickSort(int first, int last, int k) {
		if (first >= last) {
			return (int) (inputs.get(k));
		}
		int pivot = partition(first, last, k);
		if (k < pivot) {
			quickSort(first, pivot - 1, k);
		} else if (k > pivot) {
			quickSort(pivot + 1, last, k);
		}
		return (int) (inputs.get(k));
	}

	private static int partition(int first, int last, int k) {
		int pivot = (int) (Math.random() * (last - first) + first);
		swap(pivot, first);
		pivot = first;
		int leftCounter = first + 1;
		boolean leftStatus = true;
		int rightCounter = last;
		boolean rightStatus = true;

		while (leftStatus || rightStatus) {
			if (leftCounter >= inputs.size()) {
				break;
			}
			if (inputs.get(leftCounter) < inputs.get(pivot)) {
				leftCounter++;
			} else {
				leftStatus = false;
			}
			if (inputs.get(rightCounter) > inputs.get(pivot)) {
				rightCounter--;
			} else {
				rightStatus = false;
			}
			if (leftCounter < rightCounter && !leftStatus && !rightStatus) {
				swap(leftCounter, rightCounter);
				leftStatus = true;
				rightStatus = true;
				leftCounter++;
				rightCounter--;
			}
		}
		swap(pivot, leftCounter - 1);
		pivot = leftCounter - 1;
		return pivot;
	}

	public static void swap(int i, int j) {
		int temp = (int) inputs.get(i);
		inputs.set(i, inputs.get(j));
		inputs.set(j, temp);
	}
}
