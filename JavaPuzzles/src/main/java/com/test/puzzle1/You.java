package com.test.puzzle1;

public class You {

	static int counter = 0;
	static Volkswagen vw = new Volkswagen();

	public static void main(String args[]) {
		vw.add(new NewClown());
		vw.done();
	}

	static class NewClown extends Clown {

		public int hashCode() {
			if (++counter < 20) {
				vw.add(new NewClown());
			}
			return super.hashCode();
		}
	}

}
