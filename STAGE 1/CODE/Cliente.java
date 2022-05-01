public class Cliente extends Thread {
	Cliente (String name){
		super(name);
	}

	public void run() {
		if (getName().equals("Cliente")) {
			System.out.println(" " + getName());
		} else {
			System.out.println("Cliente " + getName());
		}
	}
}