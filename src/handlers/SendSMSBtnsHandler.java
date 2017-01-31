package handlers;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ui.SendSMS;
import util.Utils;

public class SendSMSBtnsHandler implements ActionListener {

	private SendSMS sendSMS;
	private static JTable table = null;
	private static DefaultTableModel model = null;
	private JDialog parent;
	
	public SendSMSBtnsHandler(SendSMS sendSMS,JDialog owner)
	{
		this.sendSMS = sendSMS;
		this.parent = owner;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		switch (arg0.getActionCommand())
		{
			case "Show":
			{
				if(
						!sendSMS.getTextWard().getText().isEmpty() || 
						!sendSMS.getTextSex().getText().isEmpty() ||
						!sendSMS.getTextSurName().getText().isEmpty() ||
						!sendSMS.getTextHeadStatus().getText().isEmpty())
				{
					JPanel panel = sendSMS.getMiddlePanel();
					table = initTableUI(panel);
			        panel.setVisible(true);
			        panel = sendSMS.getFooterPanel();
			        panel.setVisible(true);
			        
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please Select Some Criteria......");
				}
			}
				
			break;
			case "Select All":
			{
				for (int i = 0; i <table.getRowCount(); i++)
		           {
		             model.setValueAt(true, i, 3);  

		           }
			}
			break;
			case "De-Select":
			{
				for (int i = 0; i <table.getRowCount(); i++)
		           {
		             model.setValueAt(false, i, 3);  

		           }
			}
			break;
			case "Send SMS":
			{
				int selectedRows=0;
	            for (int i = 0; i <model.getRowCount() ; i++)
	            {
	                //System.out.println(model.getValueAt(i, 0));
	                if(model.getValueAt(i, 3).toString().equalsIgnoreCase("true"))
	                {
	                    selectedRows++;
	                }
	            }
	            if(selectedRows!=0)
	            {
	                try
	                {
	                    /*util.createTextForBulkSMS(this);
	                    dispose();
	                    objSelectCriteriaToCreateStickerFrame.dispose();*/
	                	
	                	JOptionPane.showMessageDialog(null, "Click OK and wait for website to Open..Please select latest text file from \"C:\\Temp\" folder!");
	                	parent.dispose();
	                    Desktop desktop = Desktop.getDesktop();
	                    URI smsURL = new URI("http://sms.getincity.com");
	                    desktop.browse(smsURL);
	                } catch (IOException ex)
	                {
	                    //Logger.getLogger(CreateStickerFrame.class.getName()).log(Level.SEVERE, null, ex);
	                } catch (URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                
	            }
	            else
	            {
	                JOptionPane.showMessageDialog(null, "Please Select Records.....");
	            }
			}
			break;

		default:
			break;
		}
		
	}

	private JTable initTableUI(JPanel panel)
	{
		//headers for the table
        String[] columns = new String[] {
            "Member ID","Member Name", "Mob No", "Select"
        };
        
        /*final Class[] columnClass = new Class[] {
        		String.class,String.class,String.class, Boolean.class
            };*/
        
      //create table model with data
        model = new DefaultTableModel() {
 
            /**
			 * 
			 */
			private static final long serialVersionUID = -3136389543960914370L;

			@Override
            public boolean isCellEditable(int row, int column)
            {
            	if(column==3)
            		return true;
            	return false;
            }
 
            @Override
            public Class<?> getColumnClass(int columnIndex)
            {
            	switch (columnIndex) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return Boolean.class;
				}
				return null;
				
				/*return columns[columnIndex];*/
                
            }
        };
     // add header in table model     
        model.setColumnIdentifiers(columns);
        setModelRowData(model);
        
        table = new JTable(model);
        //add the table to the frame
        panel.setLayout(new BorderLayout());
        panel.add(new JScrollPane(table));
        return table;
	}

	private void setModelRowData(DefaultTableModel model)
	{
		try
		{
			String query = null;
			if(!sendSMS.getTextWard().getText().isEmpty())
				query = "select * from SMember where m_ward="+Integer.parseInt(sendSMS.getTextWard().getText());
			else if(!sendSMS.getTextSex().getText().isEmpty())
				query = "select * from SMember where m_sex='"+sendSMS.getTextSex().getText()+"'";
			else if(!sendSMS.getTextHeadStatus().getText().isEmpty())
				query = "select * from SMember where family_head_status='"+sendSMS.getTextHeadStatus().getText()+"'";
			else if(!sendSMS.getTextSurName().getText().isEmpty())
				query = "select * from SMember where m_name_e LIKE '%"+sendSMS.getTextSurName().getText()+"%'";
			else
				query = "select * from SMember";
			ResultSet set;
		
			set = Utils.getUtilityInstance().querySELECT(query.toUpperCase());
		
		while(set.next())
		{
			model.addRow(new Object[]
        			{ 	set.getInt("member_id"), set.getString("m_name_e"),set.getString("m_contact"),false}
        			);
        }
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
