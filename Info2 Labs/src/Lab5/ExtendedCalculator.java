package Lab5;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;


public class ExtendedCalculator extends Calculator {

	CalculatorMode mode;

	public ExtendedCalculator() {
		super();

		makeHexaButtons();

	}

	public static void main(String[] args) {
		ExtendedCalculator c = new ExtendedCalculator();
		c.show();
	}

	public void makeHexaButtons() {
		JPanel hexaButtonPanel = new JPanel(new GridLayout(4, 2));
		gui.addButton(hexaButtonPanel, "A");
		gui.addButton(hexaButtonPanel, "B");
		gui.addButton(hexaButtonPanel, "C");
		gui.addButton(hexaButtonPanel, "D");
		gui.addButton(hexaButtonPanel, "E");
		gui.addButton(hexaButtonPanel, "F");
		addCheckbox(hexaButtonPanel, "hexa");

		gui.frame.add(hexaButtonPanel, BorderLayout.WEST);
		gui.frame.setSize(400, 300);

	}

	public void addCheckbox(Container panel, String label) {
		ActionListener l = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBox cb = (JCheckBox)e.getSource();
				boolean isActive = cb.isSelected();
				ExtendedCalculator.this.setHexaMode(isActive);
			}
		};

		JCheckBox cb = new JCheckBox(label, false);
		cb.addActionListener(l);
		panel.add(cb);
	}
	
	public void setHexaMode(boolean isHexa) {
		if(isHexa) {
			this.mode = CalculatorMode.HEXA;
		}else {
			this.mode = CalculatorMode.DECIMAL;
		}
	}

}
