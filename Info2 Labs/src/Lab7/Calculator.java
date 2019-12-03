package Lab7;

public class Calculator {
	protected CalcEngine engine;
	protected UserInterface gui;

	/**
	 * Create a new calculator and show it.
	 */
	public Calculator() {
		engine = new CalcEngine();
		gui = new UserInterface(engine);
	}

	/**
	 * In case the window was closed, show it again.
	 */
	public void show() {
		gui.setVisible(true);
	}

	public static void main(String[] args) {
		Calculator c = new Calculator();

		c.show();
	}
}
