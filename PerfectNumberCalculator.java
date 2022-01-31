import java.util.Scanner;

public class PerfectNumberCalculator {
	
	public static int printError(String error) {
		// Print error variable and return false
		System.out.println(error);
		return -1;
	}
	
	public static void printNumbers(String numberType, int numbers[], int startValue, int endValue) {
		if(numbers.length > 0) {
			System.out.print(numberType + " numbers between " + startValue + " and " + endValue + " are: ");
			// Print the array of numbers
			for(int i = 0; i < numbers.length; i++)
				System.out.print(numbers[i] + " ");
			System.out.println();
		}
		else
			// if there are no numbers between values, print there are no prime numbers
			System.out.println("No " + numberType + " Numbers found between " + startValue + " and " + endValue);
	}
	
	public static int[] returnNumbersArray(int count, int array[]) {
		// Create a new array that only has a length of numbers between values
		int numbersArray[] = new int[count];
		for(int i = 0; i < count; i++)
			numbersArray[i] = array[i];
		// Return array of numbers between values
		return numbersArray;
	}
	
	public static String getInput(String valueName, Scanner input) {
		// Get String input for value and return string value
		System.out.print("Enter the " + valueName + " value: " + ((valueName.equals("choosing")) ? "(0 for exit): " : ""));
		return input.next();
	}
	
	public static int getContinueInput(Scanner input) {
		// Get input for continuing the program
		System.out.print("Do you want to find new numbers? (Y/N): ");
		String continueString = input.next();
		// if string length is not equal to one, than user needs to enter again
		if(continueString.length() != 1) {
			printError("Your input can only contain one character");
			return -1;
		}
		if(continueString.charAt(0) == 'Y') {
			return 1;
		}
		else if(continueString.charAt(0) == 'N') {
			return 0;
		}
		else {
			printError("Your input has to be Y or N");
			return -1;
		}
	}
	
	public static int checkAndConvertValue(String valueName, String valueString, int controlValue) {
		// Control if the value is empty or not.
		if(valueString.isEmpty())
			return printError("Your " + valueName + " value cannot be empty");
		// Control if there are any characters in string.
		for(int i = 0; i < valueString.length(); i++) {
			if(i == 0)
				if(valueString.charAt(i) == '-')
					if(valueString.length() == 1)
						return printError("Your " + valueName + " value cannot contain a character");
					else
						continue;
			if(!Character.isDigit(valueString.charAt(i)))
				return printError("Your " + valueName + " value cannot contain a character");
		}
		// Convert string to integer
		int value = Integer.parseInt(valueString);
		// Control if the value is lower than zero
		if(value < 0)
			return printError("Your " + valueName + " value cannot be lower than zero");
		// Control if starting value is equal or lower than ending value
		if(valueName.equals("ending"))
			if(value <= controlValue)
				return printError("Your " + valueName + " value cannot be equal or lower than starting value");
		if(valueName.equals("choosing")) {
			if(value < 0)
				return printError("Your " + valueName + " value cannot be lower than zero");
			if(value > 6)
				return printError("Your " + valueName + " value cannot be higher than six");
		}
		// Return integer value
		return value;
	}
	
	public static int printNumbers(int numberType, int startValue, int endValue) {
		switch(numberType) {
			case 0:
				//Exit
				break;
			case 1:
				printNumbers("Prime", findPrimeNumbers(startValue, endValue), startValue, endValue);
				break;
			case 2:
				printNumbers("Perfect", findPerfectNumbers(startValue, endValue), startValue, endValue);
				break;
			case 3:
				printNumbers("Fibonacci", findFibonacciNumbers(startValue, endValue), startValue, endValue);
				break;
			case 4:
				printNumbers("Woodal", findWoodalNumbers(startValue, endValue), startValue, endValue);
				break;
			case 5:
				printNumbers("Tau", findTauNumbers(startValue, endValue), startValue, endValue);
				break;
			case 6:
				printNumbers("Smith", findSmithNumbers(startValue, endValue), startValue, endValue);
				break;
			default:
				printError("There is no number type for that value");
		}
		return 0;
	}
	
	public static int[] findPrimeNumbers(int startValue, int endValue) {
		// Find prime numbers between start value and end value
		// Define an array which will store prime numbers
		int primeNumbers[] = new int [endValue - startValue];
		// Define a boolean to look if numbers is prime or not
		boolean isPrime;
		int count = 0;
		for(int i = startValue+1; i < endValue; i++) {
			// if starting number is zero, continue until the number becomes two
			if(i < 2)
				continue;
			isPrime = true;
			// Look for every integer lower than the number
			for(int j = 2; j < i; j++)
				// if there is a zero reminder number, number is not prime
				if(i % j == 0)
					isPrime = false;
			// if the number is prime add it to the array
			if(isPrime)
				primeNumbers[count++] = i;
		}
		// return an array of prime numbers
		return returnNumbersArray(count, primeNumbers);
	}
	
	public static int[] findPerfectNumbers(int startValue, int endValue) {
		// Find perfect numbers between start value and end value
		// Define an array which will store perfect numbers
		int perfectNumbers[] = new int[endValue - startValue];
		int temp = 0;
		int count = 0;
		for(int i = startValue+1; i < endValue; i++) {
			temp = 0;
			// if starting number is zero, continue until the number becomes two
			if(i < 2)
				continue;
			// Look for every number that is smaller than the value
			for(int j = 1; j < i; j++)	
			// if the number's remainder is zero, add it to temp value	
				if(i % j == 0)
					temp += j;
			// if number is a perfect number add it to array
			// if temp is equal to i, the number is a perfect number
			if(temp == i)
				perfectNumbers[count++] = i;
		}
		// return an array of perfect numbers
		return returnNumbersArray(count, perfectNumbers);
	}
	
	public static int[] findFibonacciNumbers(int startValue, int endValue) {
		// Find Fibonacci Numbers between start value and end value
		// Define an array which will store Fibonacci Numbers
		int fibonacciNumbers[] = new int[endValue - startValue];
		int temp1 = 0;
		int temp2 = 1;
		int fibonacciNumber = 0;
		int count = 0;
		while(fibonacciNumber < endValue) {
			// Add two Fibonacci Numbers to find the next Fibonacci Number
			if(fibonacciNumber > startValue)
				fibonacciNumbers[count++] = fibonacciNumber;
			// Update the last 2 fibonacci numbers to calculate the new fibonacci number
			temp1 = temp2;
			temp2 = fibonacciNumber;
			// Calculate the new fibonacci number
			fibonacciNumber = temp1 + temp2;
		}
		// return an array of fibonacci numbers
		return returnNumbersArray(count, fibonacciNumbers);
	}
	
	public static int[] findWoodalNumbers(int startValue, int endValue) {
		// Find woodal numbers between start value and end value
		// Define an array which will store woodal numbers
		int woodalNumbers[] = new int[endValue - startValue];
		int count = 0;
		// Define the first woodal number
		int woodalNumber = 1;
		int i = 1;
		// Define do-while loop to get woodal numbers
		// if the woodal number calculated is higher than end value finish the loop
		do {
			if(woodalNumber > startValue)
				woodalNumbers[count++] = woodalNumber;
			i++;
			woodalNumber = ((int)(Math.pow(2, i)) * i) - 1;
		} while(woodalNumber < endValue);
		// return an array of woodal numbers
		return returnNumbersArray(count, woodalNumbers);
	}
	
	public static int[] findTauNumbers(int startValue, int endValue) {
		// Find tau numbers between start value and end value
		// Define an array which will store tau numbers
		int tauNumbers[] = new int[endValue - startValue];
		int count = 0;
		int count2 = 0;
		for(int i = startValue+1; i < endValue; i++) {
			// Calculate the number of divisors of number
			count2 = 0;
			for(int j = 1; j <= i; j++)
				if(i % j == 0)
					count2++;
			// if remainder of count is zero, than the number is a tau number
			if(count2 != 0)
				if(i % count2 == 0)
					tauNumbers[count++] = i;
		}
		// return an array of tau numbers
		return returnNumbersArray(count, tauNumbers);
	}
	
	public static int[] findSmithNumbers(int startValue, int endValue) {
		// Find smith numbers between start value and end value
		// Define an array which will store smith numbers
		int smithNumbers[] = new int[endValue - startValue];
		int count = 0;
		// Define variables to store sum of digits
		int primeSum = 0;
		int sum = 0;
		// Define variables for storing the numbers temporarily
		int temp = 0;
		int temp2 = 0;
		// Define variable k for finding the prime factors of a number
		int k = 2;
		for(int i = startValue + 1; i < endValue; i++) {
			primeSum = 0;
			sum = 0;
			temp = i;
			k = 2;
			// Look for every numbers that is smaller than that number
			while(k < i) {
				temp2 = k;
				// if k is the prime factor of the number add the sum of digits to primeSum variable
				if(temp % k == 0) {
					temp /= k;
					do {
						primeSum += temp2 % 10;
					} while ((temp2 /= 10) != 0);
				}
				// if k is not the prime factor of the number, increase k
				else {
					k++;
				}
			}
			// look for the sum of digits of number
			temp = i;
			do {
				sum += temp % 10;
			} while((temp /= 10) != 0);
			// if prime factor digits' sum is equal to the sum of digits of number, number is a smith number
			if(sum == primeSum)
				smithNumbers[count++] = i;
		}
		// return an array of smith numbers
		return returnNumbersArray(count, smithNumbers);
	}

	public static void main(String[] args) {
		/*
		 * Purpose of Program: Program's purpose is finding the users choice from 6 types of
		 * numbers that is between two values that the user entered as input
		 * Number types are:
		 * 1) Prime Numbers
		 * 2) Perfect Numbers
		 * 3) Fibonacci Numbers
		 * 4) Woodall Numbers
		 * 5) Tau Numbers
		 * 6) Smith Numbers
		 * These numbers will be calculated
		 */
		// Define input variable as type of Scanner for getting input
		Scanner input = new Scanner(System.in);
		// Define start value and end value that have a starting value of zero
		int startValue = 0, endValue = 0;
		// Define a variable to check if user wants to continue to calculating numbers
		int continueValue = 1;
		// Get and check start value while it is lower than zero
		do
			startValue = checkAndConvertValue("starting", getInput("starting", input), 0);
		while(startValue < 0);
		// Get and check end value while it is lower then zero
		do
			endValue = checkAndConvertValue("ending", getInput("ending", input), startValue);
		while(endValue < 0);
		// Since zero to six used by program, 7 is available for starting value
		int numberType = 7;
		String numberTypes[] = { "Prime", "Perfect", "Fibonacci", "Woodal", "Tau", "Smith" };
		System.out.println("Welcome to our Number Finder Program.");
		do {
			// if number type is not equal to -1 print the menu or exit
			if(numberType != -1) {
				if(continueValue == 1 && numberType != 0)
					for(int i = 0; i < 6; i++)
						System.out.println((i+1) + ". " + numberTypes[i] + " Numbers between " + startValue + " and " + endValue);
				else
					System.exit(0);
			}
			// Get input of number type
			numberType = checkAndConvertValue("choosing", getInput("choosing", input), 0);
			// if numberType is not equal to -1, numbers will be printed
			if(numberType != -1)
				printNumbers(numberType, startValue, endValue);
			// if continue value is -1, then user didn't enter Y or N get input again
			do
				if(numberType > 0)
					continueValue = getContinueInput(input);
			while(continueValue == -1);
		} while(true);
	}

}
