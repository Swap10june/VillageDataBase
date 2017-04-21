package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import model.VDBSModel;
import ui.EditFamily;

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
		String textString = editFamily.getTxtSelectFamilyID().getText();
		if(!textString.isEmpty())
		{
				if(arg0.getActionCommand().equalsIgnoreCase("Select"))
				{
					editFamily.getListOfHead().removeAll();
					editFamily.getListOfMembers().removeAll();
					//String []array = textString.split(":");
					//int family_id = Integer.parseInt(array[2]);
				
					/*String head = null;
						ResultSet set =Utils.getUtilityInstance().querySELECT("Select family_head from SFAMILY where family_id="+array[2]);
						while(set.next())
			            {
							if(editFamily.getListOfHead().getItemCount() == 0)
							{
								editFamily.getListOfHead().setVisible(true);
								editFamily.getListOfHead().add(set.getString("family_head"));
								head = set.getString("family_head");
							}
			            }
						set =Utils.getUtilityInstance().querySELECT("Select * from SMember where family_id="+array[2]);
						while(set.next())
			            {*/
							editFamily.getListOfMembers().setVisible(true);
							String memberName = new VDBSModel().getMemberByName(textString.trim()).getM_name_e();
							if(!memberName.isEmpty())
								editFamily.getListOfMembers().add(memberName);
							else
								JOptionPane.showMessageDialog(null, "Not Found");
							editFamily.getListOfMembers().setEnabled(true);
					}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please Select Member first......");
			}
		}

}
