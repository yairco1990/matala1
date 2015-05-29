

public class UserThread {
	public static void main(String[] args) {
		final Result result1 = new Result();
		final Result result2 = new Result();
		final Result result3 = new Result();	
		
		Object lock = new Object();
		Object lock2 = new Object();
		ThreadManager tm = new ThreadManager(1, 5, result1, result2, result3, lock);
		MyQueueGen<Task> tasks = new MyQueueGen<Task>();
		for (int i = 0; i < 500; i++) {
			tasks.add(new Task() {
				@Override
				public void doTask(int a, int b) {
					result1.setRes(22 * 55);
				}
			});
		}
		Feeder feeder = new Feeder(tasks, tm, lock);
		feeder.start();
		tm.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tm.setFinished(true);
		System.out.println(result1.getRes());
	}
}
