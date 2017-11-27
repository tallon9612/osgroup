
public class ChopstickSets {
	private static int numberOfSets = 2;
	public static int TIMES_EATEN = 0; // they don't finish eating until they release the chopsticks
	// ints to keep track of how many times everyone ate
	public static int P0 = 0;
	public static int P1 = 0;
	public static int P2 = 0;
	public static int P3 = 0;
	public static int P4 = 0;

	// method for when a chopstick set is acquired
	public static synchronized boolean acquire() {
		if (numberOfSets > 0) {
			numberOfSets = numberOfSets - 1;
			return true;
		} else {
			return false;
		}

	}

	// method that releases chopsticks
	public static synchronized void release() {
		if (numberOfSets < 2) {
			numberOfSets = numberOfSets + 1;
			System.out.println(Thread.currentThread().getName() + " is releasing chopsticks.");
			TIMES_EATEN = TIMES_EATEN + 1;

			// checks to see who used the chopsticks and adds to their counter
			if (getName().equals("Philosopher[0]")) {
				P0 = P0 + 1;
			}
			if (getName().equals("Philosopher[1]")) {
				P1 = P1 + 1;
			}
			if (getName().equals("Philosopher[2]")) {
				P2 = P2 + 1;
			}
			if (getName().equals("Philosopher[3]")) {
				P3 = P3 + 1;
			}
			if (getName().equals("Philosopher[4]")) {
				P4 = P4 + 1;
			}
		}
		else{
			//if there is more then 2 chopstick sets, kill the program as something is wrong
			System.out.println(numberOfSets);
			System.out.println("Realtiy tears itself asunder as a new set of chopsticks appears from no where");
			System.exit(0);
		}
	}

	// method used to get current threads method inorder to keep counter accurate
	private static Object getName() {
		return Thread.currentThread().getName();
	}

}
