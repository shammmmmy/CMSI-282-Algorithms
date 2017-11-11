/**

Monte Carlo simulation of the card game Crown and Anchor
Simulation has the option to change the total number of simulations (args[0]) and the amount of money bet each simulation (args[1]).

**/

public class CrownAndAnchor {
	public static void main (String[] args) {
		int NUMBER_OF_SIMULATIONS = 10000000;
		int AMOUNT_BET = 1;
		if (args.length > 0) {
			NUMBER_OF_SIMULATIONS = Integer.parseInt(args[0]);
		}
		if (args.length > 1) {
			AMOUNT_BET = Integer.parseInt(args[1]);
		}
		int zeroDice = 0;
		int oneDice = 0;
		int twoDice = 0;
		int threeDice = 0;
		int sim = 0;

		for (int i = 0; i < NUMBER_OF_SIMULATIONS; i++) {
			sim = singleRound(AMOUNT_BET);
			if (sim == 0) {
		 		zeroDice++;
			} else if (sim == 1 * AMOUNT_BET) {
				oneDice++;
			} else if (sim == 2 * AMOUNT_BET) {
				twoDice++;
			} else if (sim == 3 * AMOUNT_BET) {
				threeDice++;
			} else {
				System.out.println("ERROR");
			}
		}
		System.out.println("Monte Carlo Simulation of Crown and Anchor");
		System.out.println("In " + NUMBER_OF_SIMULATIONS + " simulations, the player loses their bet " + zeroDice + " times and gets AT LEAST their initial bet back " + (oneDice + twoDice + threeDice)+ " times.");
		System.out.println("Win: " + (double)(oneDice + twoDice + threeDice) / NUMBER_OF_SIMULATIONS * 100 + "%        Lose: " + (double)zeroDice / NUMBER_OF_SIMULATIONS * 100+ "%");
		System.out.println("$0 from a $" + AMOUNT_BET + " bet returned " + zeroDice + " times.");
		System.out.println("$" + AMOUNT_BET + " from a $" + AMOUNT_BET + " bet returned " + oneDice + " times.");
		System.out.println("$" + 2 * AMOUNT_BET + " from a $" + AMOUNT_BET + " bet returned " + twoDice + " times.");
		System.out.println("$" + 3 * AMOUNT_BET + " from a $" + AMOUNT_BET + " bet returned " + threeDice + " times.");
		System.out.println("If each bet was $" + AMOUNT_BET + " and they wagered $" + (AMOUNT_BET * NUMBER_OF_SIMULATIONS) + " total, they now have $" + AMOUNT_BET * (NUMBER_OF_SIMULATIONS - zeroDice + (oneDice + 2*twoDice + 3*threeDice)) + ".");
	}

	public static int singleRound (int bet) { //returns the amount of money received based on a $1 bet (n * bet)
		int userBet = (int) Math.floor(Math.random() * 6 + 0);
		int winningAmount = 0;

		for (int i = 0; i < 3; i++) {
			if (userBet == (int) Math.floor(Math.random() * 6 + 0)) {
				winningAmount++;
			}
		}
		return winningAmount * bet;
	}
}
