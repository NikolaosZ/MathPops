package backend;

import java.lang.Math;
import backend.RNG;

public class Calculations {
	
	/** Returns a multiple of a given number*/
	public static int generateMultiple(int number ,int x) { 
		int multiple = number * x;
		return multiple;
	}
	
	public static int generatePrime()
	{
		 	int num = RNG.randInt(1, 100);
	   
	        while (!isPrime(num)) {          
	            num = num + 1;
	        }
	        	return num;  // print the number
		    /*
	        //determines the size of the array returned
		    int primeTotal = 0;
	
		    //loop to find total prime numbers
		    for (int j = 1; j <= max; j ++)
		    {
		        if (Calculations.isPrime(j))
		        primeTotal +=1;
		    }
	
		    //declare array to be returned
		    int[] primes = new int[primeTotal];
	
		    //current index of prime number
		    int iP = 0;
	
		    //loop to add prime elements to array
		    for (int x = 1; x <= max; x ++)
		    {
		        if (Calculations.isPrime(x))
		        {
		            primes[iP]=x;
		            iP++;
		        }
		    }
		    return primes;*/
		}   
	
	
     /**checks whether a number is prime */
	public static boolean isPrime(int number) {
		//2 and 3 are prime
		if (number == 2 || number == 3) {
		    return true;
		}
		//if number is even, it's not prime
	    if (number % 2 == 0) {
	        return false;
	    }
	    //test whether number is a multiple of any integer between 2 and sqrt(number)
	    int sqrt = (int) Math.sqrt(number) + 1;
	    for (int i = 3; i < sqrt; i += 2) {
	        if (number % i == 0) {
	            return false;
	        }
	    }
	    return true;
	}
}
