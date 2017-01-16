package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import ui.EditFamily;
import util.Utils;

public class EditFamilyButtonHandler implements ActionListener
{

	EditFamily editFamily;
	public EditFamilyButtonHandler(EditFamily editFamily)
	{
		this.editFamily = editFamily;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(arg0.getActionCommand().equalsIgnoreCase("Select"))
		{
			String textString = editFamily.txtSelectFamilyID.getText();
			String []array = textString.split(":");
			//int family_id = Integer.parseInt(array[2]);
			
			try
			{
				ResultSet set =Utils.querySELECT("Select family_head from SFAMILY where family_id="+array[2]);
				while(set.next())
	            {
	            	editFamily.listOfHead.add(set.getString("family_head"));
	            }
				
			} catch (ClassNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
