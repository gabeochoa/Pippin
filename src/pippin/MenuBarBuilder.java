package pippin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuBarBuilder implements Observer {
	private JMenuItem assemble = new JMenuItem("Assemble Source...");
	private JMenuItem load = new JMenuItem("Load Program...");
	private JMenuItem go = new JMenuItem("Run Program");
	private JMenuItem exit = new JMenuItem("Exit");
	private Machine machine;

	public MenuBarBuilder(Machine machine) {
		this.machine = machine;
		machine.addObserver(this);
	}

	public JMenu createMenu() {
		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);

		assemble.setMnemonic(KeyEvent.VK_T);
		assemble.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_T, ActionEvent.CTRL_MASK));
		assemble.addActionListener(new AssembleListener());
		menu.add(assemble);
		// do the same for the load JMenuItem but make L the mnemonic and accelerator; use the "LoadListener"
		load.setMnemonic(KeyEvent.VK_L);
		load.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		load.addActionListener(new LoadListener());
		menu.add(load);

		menu.addSeparator(); // puts a line across the menu

		// do the same for the exit JMenuItem but make E the mnemonic and accelerator; use the "ExitListener"
		exit.setMnemonic(KeyEvent.VK_E);
		exit.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		exit.addActionListener(new ExitListener());
		menu.add(exit);

		return menu;
	}

	@Override
	public void update(Observable o, Object arg)
	{
		checkEnabledMenus();
	}
	public JMenu createMenu2() {
		JMenu menu = new JMenu("Execute");
		menu.setMnemonic(KeyEvent.VK_X);
		
		go.setMnemonic(KeyEvent.VK_G);
		go.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_G, ActionEvent.CTRL_MASK));
		go.addActionListener(new ExecuteListener());
		menu.add(go);    
		
		return menu;
	}
	public void checkEnabledMenus() {
		assemble.setEnabled(machine.getLoadFileActive());
		load.setEnabled(machine.getLoadFileActive());
		go.setEnabled(machine.getStepActive());
	}

	private class AssembleListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			machine.assembleFile();
		}
	}
	private class ExecuteListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			machine.execute();
		}
	}

	private class LoadListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			machine.loadFile();
		}
	}

	private class ExitListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			machine.exit();
		}
	}
	// Write checkEnabledMenus following checkEnabledButtons in ControlPanel
	// write the update method similar to the one in ControlPanel to call checkEnabledMenus
	// in each of the following, create the needed method in the Machine class
	// make the inner class ExitListener, which calls machine.exit() from the actionPerformed method.
	// make the inner class LoadListener, which calls machine.loadFile() from the actionPerformed method.
	// make the inner class AssembleListener, which calls machine.assembler() from the actionPerformed method.
}