package handlers;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map.Entry;

import javax.swing.JComponent;

import beans.SMember;
import ui.EditFamily;
import util.Utils;

public class EditFamilyListHandler implements ItemListener {

	EditFamily editFamily;
	public EditFamilyListHandler(EditFamily editFamily)
	{
		this.editFamily = editFamily;
	}

	@Override
	public void itemStateChanged(ItemEvent arg0)
	{
		if(((java.awt.List) arg0.getSource()).getSelectedItem()!=null)
		{
			try {
				String selectedItem = ((java.awt.List) arg0.getSource()).getSelectedItem().toString();
				ResultSet set =Utils.getUtilityInstance().querySELECT("select * from SMember where m_name_e='"+selectedItem+"'");
				SMember member = SMember.createMemberFromDBInstance(set);
				
				populateMemberInfo(member);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void populateMemberInfo(SMember member)
	{
		
		for (Entry<String, JComponent> entry : editFamily.getComponenetMap().entrySet())
		{
			entry.getValue().setVisible(true);
		}
		editFamily.getTxtAssignMemberID().setText(member.getMember_id());
		editFamily.getTxtNameE().setText(member.getM_name_e());
		editFamily.getTxtNameM().setText(member.getM_name_m());
		editFamily.getLblHeadStatus().setText(member.getFamily_head_status());
		editFamily.getLblSexStatus().setText(member.getM_sex());
		editFamily.getLblStateStatus().setText(member.getM_state());
		editFamily.getLblDistStatus().setText(member.getM_dist());
		editFamily.getLblTalStatus().setText(member.getM_tal());
		editFamily.getLblVillageStatus().setText(member.getM_gaon());
		editFamily.getLblDobStatus().setText(member.getM_dob());
		editFamily.getLblWardStatus().setText(String.valueOf(member.getM_ward()));
		editFamily.getLblContStatus().setText(member.getM_contact());
	}

}
