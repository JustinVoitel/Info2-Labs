package Lab5;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class ExtendedCalculator extends Calculator {

	CalculatorMode mode;

	public ExtendedCalculator() {
		super();
		this.mode = CalculatorMode.DECIMAL;
		makeHexaPanel();
		changeButtonAction();

	}

	public static void main(String[] args) {
		ExtendedCalculator c = new ExtendedCalculator();
		c.show();
	}

	public void makeHexaPanel() {
		JPanel hexaButtonPanel = new JPanel(new GridLayout(4, 2));

		fillButtons(hexaButtonPanel, new String[] { "A", "B", "C", "D", "E", "F" });
		fillCheckbox(hexaButtonPanel, "hexa");
		

		gui.frame.add(hexaButtonPanel, BorderLayout.WEST);
		gui.frame.setSize(400, 300);

	}
	
	public int hexaToDecimal(String hexa) {
		return Integer.parseInt(hexa, 16);
	}
	
	public String decimalToHexa(int decimal) {
		return Integer.toHexString(decimal);
	}

	public void fillButtons(Container ctn, String[] buttons) {

		ActionListener action = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String command = e.getActionCommand();

				if (command.equals("A") || command.equals("B") || command.equals("C") || command.equals("D")
						|| command.equals("E") || command.equals("F")) {
					
					gui.calc.numberPressed(hexaToDecimal(command));
					
					ExtendedCalculator.this.redisplay();
				}
			}
		};

		for (String caption : buttons) {

			JButton button = new JButton(caption);
			button.addActionListener(action);
			button.setEnabled(this.mode.equals(CalculatorMode.HEXA));
			ctn.add(button);
		}

	}

	public void fillCheckbox(Container panel, String label) {
		ActionListener action = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBox cb = (JCheckBox) e.getSource();
				ExtendedCalculator.this.setHexaMode(cb.isSelected());
				ExtendedCalculator.this.redisplay();
			}
		};

		JCheckBox cb = new JCheckBox(label, false);
		cb.addActionListener(action);
		panel.add(cb);
	}
	
	public void changeButtonAction() {

		ActionListener action = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ExtendedCalculator.this.redisplay();
			}
		};
		
		JPanel contentPane = (JPanel) gui.frame.getContentPane();

		JPanel c = (JPanel) contentPane.getComponent(1);
		for (Component button : c.getComponents()) {
			if (button instanceof JButton) {
				//swap the position of the actionlisteners, so the redisplay of this class gets triggered at last
				ActionListener[] al = ((JButton) button).getActionListeners();
				((JButton) button).removeActionListener(al[0]);
				((JButton) button).addActionListener(action);
				((JButton) button).addActionListener(al[0]);
			}
		}
	}

	public void setHexaMode(boolean isHexa) {
		if (isHexa) {
			this.mode = CalculatorMode.HEXA;
		} else {
			this.mode = CalculatorMode.DECIMAL;
		}
		setHexaButtonsEnabled(isHexa);

	}

	public void setHexaButtonsEnabled(boolean isHexa) {

		JPanel contentPane = (JPanel) gui.frame.getContentPane();

		JPanel c = (JPanel) contentPane.getComponent(3);
		for (Component button : c.getComponents()) {
			if (button instanceof JButton) {
				button.setEnabled(isHexa);

			}
		}
	}
	
	public void redisplay() {
		if(this.mode.equals(CalculatorMode.HEXA)) {
			gui.display.setText(""+ decimalToHexa(this.engine.getDisplayValue()));			
		}else if(this.mode.equals(CalculatorMode.DECIMAL)) {
			gui.display.setText(""+this.engine.getDisplayValue());
		}
	}

}
