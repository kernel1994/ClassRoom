package test.junit;

import java.util.Random;

public class RandomTest {

	public static void main(String[] args) {
		Random r = new Random();
		for (int i = 0; i < 50; i++) {
			double b = (r.nextDouble() * 10);
			System.out.println((int) b) ;
		}
	}

}
