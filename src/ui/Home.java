package ui;

import javax.swing.JButton;
import javax.swing.JDialog;

import handlers.HomeButtonHandler;
import util.UConstants;
import util.Utils;

public class Home extends JDialog {

	

	private static final long serialVersionUID = 1L;

	public Home(JDialog owner)
	{
		super(owner);
		Utils.getUtilityInstance().applyBasicSettingsOnWindow(owner,"Home");
		initUI(owner);
		owner.setVisible(true);
	}

	private void initUI(JDialog owner) 
	{
		JButton [] btnArray = new JButton[UConstants.SHOME_BUTTONS.length]; 
		for (int i = 0 , xPos= 10, yPos=60;i < btnArray.length; i++,yPos+=50)
		{
			btnArray[i] = new JButton(UConstants.SHOME_BUTTONS[i]);
			btnArray[i].setBounds(xPos,yPos, 150, 25);
			owner.add(btnArray[i]);
			btnArray[i].addActionListener(new HomeButtonHandler(owner));
		}
		//owner.repaint();
	}
}
