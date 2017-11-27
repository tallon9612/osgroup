
public class Philosopher implements Runnable {
	private static boolean timetorun;
	// time in ms for program to run
	public static final int TIME_TO_RUN = 4 * 1000;
	// time in ms
	public static final int TIME_TO_THINK = 500;
	// time in ms
	public static final int TIME_TO_EAT = 750;
	// num of philosophers, do not change this
	public static final int NUMBER_OF_PHILOSOPHERS = 5;

	private static Thread thread;

	public static void main(String[] args) {
		// array to create the philosophers and make them start
		Philosopher[] philosopherArray = new Philosopher[NUMBER_OF_PHILOSOPHERS];
		for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++) {
			philosopherArray[i] = new Philosopher(i);
			philosopherArray[i].startThinking();
		}
		// method thats acts as a timer
		timer();
	}

	// makes threads, gives them a number
	Philosopher(int num) {
		thread = new Thread(this, "Philosopher[" + num + "]");
	}

	// method when the philosopher is thinking
	private void philosophy() {
		try {
			System.out.println(Thread.currentThread().getName() + " is thinking.");
			Thread.sleep(TIME_TO_THINK);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// method when the philosopher is eating
	private void eat() {
		// if there is a open set of chopsticks, otherwise fail to get them and
		// go back to thinking
		try {
			if (ChopstickSets.acquire() == true) {
				System.out.println(Thread.currentThread().getName() + " is eating.");
				Thread.sleep(TIME_TO_EAT);
				ChopstickSets.release();

			} else {
				System.out.println(Thread.currentThread().getName() + " failed to get chopsticks.");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	// start threads
	public void startThinking() {
		timetorun = true;
		thread.start();
	}

	// run method
	public void run() {
		while (timetorun == true) {
			philosophy();
			eat();
		}
	}

	// sleeps for a set time then stops program and prints out counts of how
	// many everyone ate
	private static void timer() {
		if (timetorun = true) {
			try {
				Thread.sleep(TIME_TO_RUN);
				timetorun = false;
				System.out.println(" Times up!");
				//counts times the chopsticks are released, this count is the debug count to make sure that the total is the same all around
				System.out.println(ChopstickSets.TIMES_EATEN);
				//count of times Philosopher 0 ate
				System.out.println("Philospher 0 ate " + ChopstickSets.P0 + " times");
				//count of times Philosopher 1 ate
				System.out.println("Philospher 1 ate " + ChopstickSets.P1 + " times");
				//count of times Philosopher 2 ate
				System.out.println("Philospher 2 ate " + ChopstickSets.P2 + " times");
				//count of times Philosopher 3 ate
				System.out.println("Philospher 3 ate " + ChopstickSets.P3 + " times");
				//count of times Philosopher 4 ate
				System.out.println("Philospher 4 ate " + ChopstickSets.P4 + " times");
				System.exit(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
