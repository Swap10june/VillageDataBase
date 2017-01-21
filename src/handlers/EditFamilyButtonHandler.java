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
				String head = null;
				ResultSet set =Utils.querySELECT("Select family_head from SFAMILY where family_id="+array[2]);
				while(set.next())
	            {
					if(editFamily.listOfHead.getItemCount() == 0)
					{
						editFamily.listOfHead.setVisible(true);
						editFamily.listOfHead.add(set.getString("family_head"));
						head = set.getString("family_head");
					}
	            }
				set =Utils.querySELECT("Select * from SMember where family_id="+array[2]);
				while(set.next())
	            {
					editFamily.listOfMembers.setVisible(true);
					if(!set.getString("M_NAME_E").equalsIgnoreCase(head))
						editFamily.listOfMembers.add(set.getString("m_name_e"));
					editFamily.listOfMembers.setEnabled(true);
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
