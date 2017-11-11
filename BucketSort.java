/**

Takes a dataset of doubles and BucketSorts them (Insert Sort is also used for individual buckets).

**/
import java.util.Scanner;
import java.util.ArrayList;

public class BucketSort {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Double> numbers = new ArrayList<>();
		ArrayList<ArrayList<Double>> buckets = new ArrayList<>();
		double tempDouble = sc.nextDouble();
		double maxNum = tempDouble;
		double minNum = tempDouble;
		numbers.add(tempDouble);

		while(sc.hasNextDouble()) {
			tempDouble = sc.nextDouble();
			numbers.add(tempDouble);
			if (tempDouble < minNum) {
				minNum = tempDouble;
			}
			if (tempDouble > maxNum) {
				maxNum = tempDouble;
			}
		}

		//Creates arrayList of buckets with sizeOfInput size
		for(int i = 0; i < numbers.size(); i++) {
			buckets.add(new ArrayList<Double>());
		}
		//Places inputs into their respective buckets
		for (int i = 0; i < numbers.size(); i++) {
			buckets.get((int) ((numbers.size() - 1) * ((numbers.get(i) - minNum) / (maxNum - minNum)))).add(numbers.get(i));
		}
		numbers.clear();

		for (int i = 0; i < buckets.size(); i++) {
			if (buckets.get(i).size() > 1) {
				buckets.set(i, insertSort(buckets.get(i)));
			}
			for (int j = 0; j < buckets.get(i).size(); j++) {
				numbers.add(buckets.get(i).get(j));

			}
		}
		System.out.println(numbers);
	}

	private static ArrayList<Double> insertSort (ArrayList<Double> list) {
		double tempValue = 0;
		for (int i = 1; i < list.size(); i++) {
			for(int j = i ; j > 0 ; j--){
                if(list.get(j) < list.get(j-1)) {
                    tempValue = list.get(j);
                    list.set(j, list.get(j-1));
                    list.set(j-1, tempValue);
                }
            }
        }
		return list;
	}
}
