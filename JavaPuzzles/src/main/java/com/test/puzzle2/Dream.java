package com.test.puzzle2;

public class Dream {

	private Dream myDream;
	private String myThread;

	public Dream() {
		System.out.println("[" + Thread.currentThread().getName() + "] Creating dream...");
		myDream = this;
		myThread = Thread.currentThread().getName();
	}

	public void dream(Sleeper s) {

		try {
			System.out.println("[" + Thread.currentThread().getName() + "] Starting dream");

			if (Thread.currentThread().getName().equals(myThread)) {
				// Spin a new Thread.
				Thread newThread = new Thread(new Runnable() {

					@Override
					public void run() {
						System.out.println("[" + Thread.currentThread().getName() + "] Starting new dream");
						s.enter(myDream);
						System.out.println("[" + Thread.currentThread().getName() + "] Ending new dream");
					}
				});

				// invoke dream and wait. notify all.
				newThread.setDaemon(false);
				newThread.start();
				s.notifyAll();
				s.wait();
				System.out.println("[" + Thread.currentThread().getName() + "] Waiting dream");
			}

			if (!Thread.currentThread().getName().equals(myThread)) {
				// notify all.
				s.notifyAll();
				s.wait();
				System.out.println("[" + Thread.currentThread().getName() + "] Waiting new dream");
			}

			System.out.println("[" + Thread.currentThread().getName() + "] Ending dream");
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}