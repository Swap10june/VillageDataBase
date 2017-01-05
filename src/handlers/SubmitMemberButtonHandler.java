package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

import beans.SMember;
import src.Test;
import ui.AddFamily;
import ui.AddMember;

public class SubmitMemberButtonHandler implements ActionListener {

	AddMember addMember;
	JDialog owner;
	public SubmitMemberButtonHandler(AddMember addMember, JDialog owner)
	{
		this.addMember = addMember;
		this.owner = owner;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equalsIgnoreCase("Add Family Head"))
		{

			//SMember member = (SMember) Test.main.get(addMember.txtAssignMemberID.getText());
			
			SMember member = new SMember();
			member.setMember_id(addMember.txtAssignMemberID.getText());
			member.setM_name_e(addMember.getTxtNameE().getText());
			member.setM_name_m(addMember.getTxtNameM().getText());
			member.setFamily_head_status(addMember.getComboHeadStatus().getSelectedItem().toString());
			member.setM_sex(addMember.getComboSexStatus().getSelectedItem().toString());
			member.setM_state(addMember.getComboStateStatus().getSelectedItem().toString());
			member.setM_dist(addMember.getComboDistStatus().getSelectedItem().toString());
			member.setM_tal(addMember.getTxtTalStatus().getText());
			member.setM_gaon(addMember.getTxtVillageStatus().getText());
			member.setM_dob(addMember.getTxtDobStatus().getText());
			member.setM_ward(Integer.parseInt(addMember.getTxtWardStatus().getText()));
			member.setM_contact(addMember.getTxtContStatus().getText());
			addMember.setMember(member);
			AddFamily.listOfHead.add(member.getMember_id()+">"+member.getM_name_e());
			AddFamily.btnAddFamilyHead.setEnabled(false);
			owner.dispose();
		}
		if(e.getActionCommand().equalsIgnoreCase("Add Member"))
		{

			//SMember member = (SMember) Test.main.get(addMember.txtAssignMemberID.getText());
			
			SMember member = new SMember();
			member.setMember_id(addMember.txtAssignMemberID.getText());
			member.setM_name_e(addMember.getTxtNameE().getText());
			member.setM_name_m(addMember.getTxtNameM().getText());
			member.setFamily_head_status(addMember.getComboHeadStatus().getSelectedItem().toString());
			member.setM_sex(addMember.getComboSexStatus().getSelectedItem().toString());
			member.setM_state(addMember.getComboStateStatus().getSelectedItem().toString());
			member.setM_dist(addMember.getComboDistStatus().getSelectedItem().toString());
			member.setM_tal(addMember.getTxtTalStatus().getText());
			member.setM_gaon(addMember.getTxtVillageStatus().getText());
			member.setM_dob(addMember.getTxtDobStatus().getText());
			member.setM_ward(Integer.parseInt(addMember.getTxtWardStatus().getText()));
			member.setM_contact(addMember.getTxtContStatus().getText());
			addMember.setMember(member);
			AddFamily.listOfMembers.add(member.getMember_id()+">"+member.getM_name_e());
			owner.dispose();
		}
	}

}
