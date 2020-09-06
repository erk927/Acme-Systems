package ver1;

import java.util.Scanner;

public class GuessTheNumber {
	static Scanner input = new Scanner(System.in);
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print("Guess the number:  ");
		int temp = input.nextInt();
		System.out.println(guessTheNumber(temp));
	}

	public static String guessTheNumber(int value) {
		if (value > 6)
			return "Correct";
		else
			return "Incorrect";
		//Comment
	}
}
