/**
Solves the following problem: Sumo wrestler Keiryō needs to put on weight in a hurry. With money in hand, he heads to a specialty food store where he is confronted with many items, each of which has a cost (in dollars) and a guarantee of the weight (in pounds) that he'll gain if he eats that item. His dilemma is which items to buy so as to maximize his total weight gain.
Solved with recursive memoization.
**/

import java.util.ArrayList;

class Item {
	private int cost;
	private int weight;

	public Item(int costOfItem, int weightOfItem) {
		cost = costOfItem;
		weight = weightOfItem;
	}

	public int getCost() {
		return cost;
	}

	public int getWeight() {
		return weight;
	}

	public String toString() {
		return "$" + cost + " / " + weight + " pounds";
	}
}

class ItemList {
	private ArrayList<Item> listOfItems = new ArrayList<Item>();
	private int totalCost;
	private int totalWeight;

	ItemList(Item itemName) {
		listOfItems.add(itemName);
		totalCost += itemName.getCost();
		totalWeight += itemName.getWeight();
	}

	ItemList(ArrayList<Item> otherList) {
		for (int i = 0; i < otherList.size(); i++) {
			listOfItems.add(otherList.get(i));
			totalCost += otherList.get(i).getCost();
			totalWeight += otherList.get(i).getWeight();
		}
	}

	public ItemList addItem(Item addTo) {
		listOfItems.add(addTo);
		totalCost += addTo.getCost();
		totalWeight += addTo.getWeight();
		return this;
	}

	public ArrayList<Item> arrayListForm() {
		return listOfItems;
	}

	public int getLength() {
		return listOfItems.size();
	}

	public int getTotalCost() {
		return totalCost;
	}

	public int getTotalWeight() {
		return totalWeight;
	}

	public Item getItem(int i) {
		return listOfItems.get(i);
	}

	public String listAll() {
		String result = "";
		for (int i = 1; i < listOfItems.size(); i++) {
			result = result + listOfItems.get(i).toString() + " ";
		}
		return result;
	}
}

public class SumoSolver {
	private static ItemList foodInfo;
	private static int maxSpending;
	private static int numberOfItems;
	private static ItemList[][] solutionMatrix;

	public static void main(String args[]) throws Exception {
		maxSpending = Integer.parseInt(args[args.length - 1]);
		numberOfItems = (args.length - 1) / 2;
		solutionMatrix = new ItemList[maxSpending + 1][numberOfItems + 1];
		foodInfo = new ItemList(new Item(0, 0));

		if (args.length % 2 == 0) {
			throw new Exception("Incorrect number of inputs.");
		}

		for (int i = 0, j = 0; i <= numberOfItems && j < args.length - 1; i++, j = j + 2) {
			if (Integer.parseInt(args[i]) <= 0 || Integer.parseInt(args[i + 1]) <= 0) {
				throw new Exception("Only input positive numbers.");
			}
			//Creates ItemList of items with their values and weights
			foodInfo.addItem(new Item(Integer.parseInt(args[j]), Integer.parseInt(args[j + 1])));
		}

		for (int row = 0; row <= numberOfItems; row++) {
			for (int col = 0; col <= maxSpending; col++) {
				solutionMatrix[col][row] = new ItemList(new Item(0, 0));
			}
		}
		System.out.println(dynamic());
	}

	private static String dynamic() {
		ItemList resultsList = recurseDynamic(maxSpending, numberOfItems);
		String resultOutput = "";
		for (int i = 1; i < resultsList.getLength(); i++) {
			resultOutput += resultsList.getItem(i).toString() + "\n";
		}
		return resultOutput + (resultsList.getLength() - 1) + " items / $" + resultsList.getTotalCost() + " / " + resultsList.getTotalWeight() + " pounds";
	}

	private static ItemList recurseDynamic(int moneyIndex, int rowIndex) {
		ItemList buy = new ItemList(new Item(0,0)); 
		ItemList dontBuy = new ItemList(new Item(0,0));

		if (moneyIndex <= 0 || rowIndex <= 0) {
			return buy;
		}

		if (foodInfo.getItem(rowIndex).getCost() <= moneyIndex) {
			//Buy variable adds current item and recurses to account for remaining money
			buy = new ItemList(recurseDynamic(moneyIndex - foodInfo.getItem(rowIndex).getCost(), rowIndex - 1).arrayListForm()).addItem(foodInfo.getItem(rowIndex)); 
		}
		dontBuy = new ItemList(recurseDynamic(moneyIndex, rowIndex - 1).arrayListForm());

		if (buy.getTotalWeight() > dontBuy.getTotalWeight() && buy.getTotalCost() <= maxSpending) {
			//Sets matrix value to itemList with highest weight that is within spending limit (maxSpending)
			solutionMatrix[moneyIndex][rowIndex] = buy;
		} else if (dontBuy.getTotalCost() <= maxSpending) {
			solutionMatrix[moneyIndex][rowIndex] = dontBuy;
		}
		return solutionMatrix[moneyIndex][rowIndex];
	}
}