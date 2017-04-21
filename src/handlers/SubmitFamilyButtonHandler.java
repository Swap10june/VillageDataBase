package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import model.VDBSModel;
import beans.SFamily;
import ui.AddFamily;

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
		List<SFamily> familyList = null;
		if(family!=null)
		{
			familyList = new ArrayList<SFamily>();
			familyList.add(family);
			/*Utils.getUtilityInstance().queryINSERT(family);
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
			}*/
			new VDBSModel().addFamily(familyList);
			//new Dao().updateFamily(familyList);
			JOptionPane.showMessageDialog (parent, family.getMembers().size()+" Members Added in family ID :"+family.getFamilyId().split("-")[1], "Success", JOptionPane.INFORMATION_MESSAGE);
			parent.dispose();
		}
		/*else
		{
			JOptionPane.showMessageDialog (parent, "Empty Family...!", "ERROR", JOptionPane.ERROR_MESSAGE);
		}*/
	}

}
