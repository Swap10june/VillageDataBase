package ui;

import javax.swing.JButton;
import javax.swing.JDialog;

import handlers.HomeButtonHandler;
import util.Props;
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
		JButton [] btnArray = new JButton[Props.SHOME_BUTTONS.length]; 
		for (int i = 0 , xPos= 10, yPos=60;i < btnArray.length; i++,yPos+=50)
		{
			btnArray[i] = new JButton(Props.SHOME_BUTTONS[i]);
			if(i<=3)
			{
				
				btnArray[i].setBounds(xPos,yPos, 150, 25);
				owner.add(btnArray[i]);
				btnArray[i].addActionListener(new HomeButtonHandler(owner));
			}
			else
			{
				btnArray[i].setBounds(10, 60, 150, 25);
				owner.add(btnArray[i]);
			}
		}
		//owner.repaint();
	}
}
