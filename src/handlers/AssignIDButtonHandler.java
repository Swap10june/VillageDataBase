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

	private 	AddFamily 		addFamily;
	private 	AddMember 		addMember;	
	
	public AssignIDButtonHandler(AddFamily addFamily,JDialog owner)
	{
		this.addFamily =  addFamily;
	}

	public AssignIDButtonHandler(AddMember addMember, JDialog owner)
	{
		this.addMember = addMember;
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// family id
		if(e.getActionCommand().equalsIgnoreCase("Assign "))
		{
			String familyID = "FID-"+Utils.getUtilityInstance().getMaxFamilyID();
			addFamily.getTxtAssignFamilyID().setText(familyID);
			addFamily.getLblAssignedFamilyID().setText(familyID);
			AddFamily.getBtnAddFamilyHead().setEnabled(true);
			JButton btn = (JButton) e.getSource();
			btn.setEnabled(false);
			
			SFamily family = new SFamily(familyID);
			Test.main.put(familyID, family);
		}
		// member id
		if(e.getActionCommand().equalsIgnoreCase("Assign"))
		{
			String memberID = "MID-"+String.valueOf(AddFamily.family.getMembers().size()+1);
			addMember.getTxtAssignMemberID().setText(memberID);
			
			SMember member = new SMember();
			member.setMember_id(memberID);
			Test.main.put(memberID, member);
			
		}
	}

}
