package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

import beans.SFamily;
import beans.SMember;
import src.Test;
import ui.AddFamily;
import ui.AddMember;
import util.Utils;

public class AssignIDButtonHandler implements ActionListener {

	AddFamily addFamily;
	AddMember addMember;
	static AssignIDButtonHandler assignIDButtonHandler;
	public AssignIDButtonHandler(AddFamily addFamily,JDialog owner) {
		this.addFamily =  addFamily;
		
	}

	public AssignIDButtonHandler(AddMember addMember, JDialog owner)
	{
		this.addMember =addMember;
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// family id
		if(e.getActionCommand().equalsIgnoreCase("Assign "))
		{
			String familyID = "FID-"+Utils.getUtilityInstance().getMaxFamilyID();
			AddFamily.txtAssignFamilyID.setText(familyID);
			addFamily.lblAssignedFamilyID.setText(familyID);
			addFamily.btnAddFamilyHead.setEnabled(true);
			JButton btn = (JButton) e.getSource();
			btn.setEnabled(false);
			
			SFamily family = new SFamily(familyID);
			Test.main.put(familyID, family);
		}
		// member id
		if(e.getActionCommand().equalsIgnoreCase("Assign"))
		{
			
			/*String temp = AddFamily.txtAssignFamilyID.getText();
			String[] splitString = temp.split("-");*/
			String memberID = "MID-"+String.valueOf(AddFamily.family.getMembers().size()+1);
			addMember.txtAssignMemberID.setText(memberID);
			
			SMember member = new SMember();
			member.setMember_id(memberID);
			Test.main.put(memberID, member);
			
		}
	}

}
