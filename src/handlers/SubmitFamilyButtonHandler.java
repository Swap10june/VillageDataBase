package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import beans.SFamily;
import beans.SMember;
import ui.AddFamily;
import util.UpdateModelXML;
import util.Utils;

public class SubmitFamilyButtonHandler implements ActionListener {

	AddFamily addFamily;
	JDialog parent;
	public SubmitFamilyButtonHandler(AddFamily addFamily, JDialog owner)
	{
		this.addFamily = addFamily;
		this.parent = owner;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		SFamily family = addFamily.getFamily();
		List<SMember> members = family.getMembers();
		
		if(family!=null)
		{
			try
			{
				Utils.getUtilityInstance().queryINSERT(family);
				int memberCount=0;
				for (int i = 0; i < members.size(); i++)
				{
					memberCount += Utils.getUtilityInstance().queryINSERT(members.get(i));
				}
				new UpdateModelXML(family);
				if(members.size()==memberCount)
				{
					JOptionPane.showMessageDialog (parent, memberCount+" Members Added in family ID :"+family.getFamilyId().split("-")[1], "Success", JOptionPane.INFORMATION_MESSAGE);
					parent.dispose();
				}
			} catch (ClassNotFoundException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		/*else
		{
			JOptionPane.showMessageDialog (parent, "Empty Family...!", "ERROR", JOptionPane.ERROR_MESSAGE);
		}*/
	}

}
