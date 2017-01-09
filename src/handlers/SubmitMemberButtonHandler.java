package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import beans.SMember;
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
			if(addMember.txtAssignMemberID.getText().isEmpty() || 
					addMember.getTxtNameE().getText().isEmpty()||
					addMember.getTxtNameM().getText().isEmpty() ||
					addMember.getComboHeadStatus().getSelectedItem().toString().isEmpty() ||
					addMember.getComboSexStatus().getSelectedItem().toString().isEmpty() ||
					addMember.getComboStateStatus().getSelectedItem().toString().isEmpty()
					
					)
					
			{
				JOptionPane.showMessageDialog (owner, "Please Fill All The Fields","Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				SMember member = new SMember();
				member.setMember_id(addMember.txtAssignMemberID.getText());
				member.setM_name_e(addMember.getTxtNameE().getText());
				member.setM_name_m(addMember.getTxtNameM().getText());
				member.setFamily_head_status("Yes");
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
		}
		if(e.getActionCommand().equalsIgnoreCase("Add Member"))
		{

			//SMember member = (SMember) Test.main.get(addMember.txtAssignMemberID.getText());
			
			SMember member = new SMember();
			member.setMember_id(addMember.txtAssignMemberID.getText());
			member.setM_name_e(addMember.getTxtNameE().getText());
			member.setM_name_m(addMember.getTxtNameM().getText());
			member.setFamily_head_status("No");
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
