package hw3;

import java.util.Scanner;
import java.util.Random;

public class main {

	public static void main(String[] args) {
		// create random and scanner objects
		Random rnd = new Random();
		Scanner scn = new Scanner(System.in);
		// create queues for player1 and player2
		Queue player1 = new Queue(1000);
		Queue player2 = new Queue(1000);
		// create queues for bag1 and bag2
		Queue bag1 = new Queue(1000);
		Queue bag2 = new Queue(1000);

		/**
		 * create functions for repeated operations. all function is appropriate to
		 * queue structures there is no another data structures!! and all function is in
		 * the main class. function's explanations is beside the functions
		 */
		lottoCards(player1);// add numbers to player1
		lottoCards(player2);// add numbers to player2
		mainBag(bag1); // add numbers that between 1-17 to bag1.

		// output operation for initial values
		System.out.printf("%-40s%-40s\n", displayQueue(player1, "Player1"), displayQueue(bag1, "Bag1"));
		System.out.printf("%-40s%-40s\n", displayQueue(player2, "Player2"), displayQueue(bag2, "Bag2"));

		boolean bingo = true;
		int bingo_count1 = 0;// çinko counter for player1
		int bingo_count2 = 0;// çinko counter for player2
		int p1_award = 0;// player1 award
		int p2_award = 0;// player2 award
		// do infinity loop
		while (true) {
			// if player1 or player2's queue is empty -> break
			if (player1.isEmpty() || player2.isEmpty()) {
				break;
			} else {// else continue..

				int random_index = rnd.nextInt(bag1.size()) + 1;// generate random number (between 0 and bag1.size())
				int temp = 0;// create temporary variable for check match up
				for (int i = 0; i <= bag1.size(); i++) {
					if (random_index == i) {// if there is match bag2.enqueue(bag1.dequeue)
						temp = (int) bag1.peek();
						bag2.enqueue(bag1.dequeue());
					} else {// else bag1.enqueue(bag1.dequeue) so checked all possibility..
						bag1.enqueue(bag1.dequeue());
					}

				}

				int p1_size = player1.size();
				for (int i = 0; i < p1_size; i++) {

					if ((int) player1.peek() == temp) {// if there is match just dequeue
						player1.dequeue();
						bingo_count1++;
						if (bingo_count1 == 4 && bingo == true) {// if there are 4 delete elements -> birinci çinko(add
																	// $10)
							System.out.println();
							System.out.println("Player1 gets $10 (Birinci Çinko)");// write console
							p1_award += 10;
							bingo = false;

						}
					}

					else {// else wrap the queue to beginning
						player1.enqueue(player1.dequeue());
					}

				}

				// same operation with player1
				int p2_size = player2.size();
				for (int i = 0; i < p2_size; i++) {
					if ((int) player2.peek() == temp) {
						player2.dequeue();
						bingo_count2++;
						if (bingo_count2 == 4 && bingo == true) {
							System.out.println();
							System.out.println("Player2 gets $10 (Birinci Çinko)");
							p2_award += 10;
							bingo = false;
						}
					}

					else {
						player2.enqueue(player2.dequeue());
					}

				}
				// output operations
				System.out.println();
				System.out.println("Randomly selected number: " + temp);
				System.out.println(
						"——————————————————————————————————————————————————————————————————————————————————————————");
				System.out.printf("%-40s%-40s\n", displayQueue(player1, "Player1"), displayQueue(bag1, "Bag1"));
				System.out.printf("%-40s%-40s\n", displayQueue(player2, "Player2"), displayQueue(bag2, "Bag2"));

			}
		}
		if (player1.isEmpty() && !player2.isEmpty()) {// if only player1 is win
			System.out.println();
			System.out.println("Player1 is the winner!!");
			System.out.println();
			p1_award += 30;
		} else if (player2.isEmpty() && !player1.isEmpty()) {// if only player2 is win
			System.out.println();
			System.out.println("Player2 is the winner!!");
			System.out.println();
			p2_award += 30;
		} else {// if player1 and player2 are win
			System.out.println();
			System.out.println("Player1 and Player2 are tie..");
			System.out.println();
			p1_award += 15;
			p2_award += 15;
		}
		// write awards to the console
		System.out.println("Player1 gets $" + p1_award);
		System.out.println("Player2 gets $" + p2_award);

	}

	public static String displayQueue(Queue q, String q_name) {
		// create new empty string variable for just nice output
		// i could write directly system.out.println(q.peek()) instead of add to output
		// string
		// but at that case there is line shifts on the bag1 and bag2's outputs
		// so assign all queue elements to one string by use just queue structures
		// all of these are just for use printf..
		String output = "";
		output += q_name + ": ";// firstly add queue name to output

		for (int i = 0; i < q.size(); i++) {
			output += q.peek() + " "; // then add element that front of queue's
			q.enqueue(q.dequeue());// after that wrap the queue to beginning
		}
		return output;// finally return output for console writing

	}

	public static void lottoCards(Queue q) {// this function for add elements to player1 and player2
		Random rnd = new Random();
		while (q.size() != 7) {// while q's size 7 add random elements
			int number = rnd.nextInt(17) + 1;
			boolean isExist = false;// this is for don't add the same elements
			for (int i = 0; i < q.size(); i++) {
				if ((int) q.peek() == number) {
					isExist = true;
					break;
				} else {
					q.enqueue(q.dequeue());
				}
			}
			if (isExist == false) {
				q.enqueue(number);
			}
		}
	}

	public static void mainBag(Queue q) {// this function for add elements to bag1(between 1-17)

		for (int i = 1; i <= 17; i++) {
			q.enqueue(i);
		}
	}

}
