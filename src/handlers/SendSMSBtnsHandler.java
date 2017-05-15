package handlers;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import model.VDBSModel;
import ui.SendSMS;
import util.Utils;
import beans.SMember;

public class SendSMSBtnsHandler implements ActionListener {

	private SendSMS 					sendSMS;
	private static JTable 				table = null;
	private static DefaultTableModel 	model = null;
	private JDialog 					parent;
	private VDBSModel					m_VDBSModel		= new VDBSModel();
	
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
				if(m_VDBSModel.getAllMembers().size()>0)
						{
					
					JPanel panel = sendSMS.getMiddlePanel();
					table = initTableUI(panel);
			        panel.setVisible(true);
			        panel = sendSMS.getFooterPanel();
			        panel.setVisible(true);
			        sendSMS.getBtnSelectAll().setEnabled(true);
			        sendSMS.getBtnClearModel().setEnabled(true);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "No Members to show");
				}
			}
				
			break;
			case "Select All":
			{
				for (int i = 0; i <table.getRowCount(); i++)
		           {
		             model.setValueAt(true, i, 5);  

		           }
				sendSMS.getBtnSelectAll().setEnabled(false);
				sendSMS.getBtnDeSelectAll().setEnabled(true);
				sendSMS.getBtnSendSMS().setEnabled(true);
			}
			break;
			case "De-Select":
			{
				for (int i = 0; i <table.getRowCount(); i++)
		           {
		             model.setValueAt(false, i, 5);  

		           }
				sendSMS.getBtnSelectAll().setEnabled(true);
				sendSMS.getBtnDeSelectAll().setEnabled(false);
				sendSMS.getBtnSendSMS().setEnabled(false);
			}
			break;
			case "Clear":
			{
				model.setRowCount(0);
				parent.dispose();
				new SendSMS(new javax.swing.JDialog(),"Clear");
				
			}
			break;
			case "Create Sticker":
			{/*
				3int selectedRows=0;
				Map<String,String> records = new HashMap<String,String>();
	            for (int i = 0; i <model.getRowCount() ; i++)
	            {
	                //System.out.println(model.getValueAt(i, 0));
	                if(model.getValueAt(i, 5).toString().equalsIgnoreCase("true"))
	                {
	                	records.put( model.getValueAt(i, 4).toString(),model.getValueAt(i, 3).toString());
	                    selectedRows++;
	                }
	            }
	            if(selectedRows!=0)
	            {
	                try
	                {
	                	@SuppressWarnings("unused")
						File textFile =Utils.getUtilityInstance().generateTextFileToSendSMS(records);
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
				
			*/}
			break;
			case "Send SMS":
			{
				int selectedRows=0;
				Map<String,String> records = new HashMap<String,String>();
	            for (int i = 0; i <model.getRowCount() ; i++)
	            {
	                //System.out.println(model.getValueAt(i, 0));
	                if(model.getValueAt(i, 5).toString().equalsIgnoreCase("true"))
	                {
	                	records.put( model.getValueAt(i, 4).toString(),model.getValueAt(i, 3).toString());
	                    selectedRows++;
	                }
	            }
	            if(selectedRows!=0)
	            {
	                try
	                {
	                	@SuppressWarnings("unused")
						File textFile =Utils.getUtilityInstance().generateTextFileToSendSMS(records);
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
        String[] columns = new String[]
        		{
            "Ward","Sex","Family Head","Member Name", "Mob No", "Select"
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
            	if(column==5)
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
					return String.class;
				case 4:
					return String.class;
				case 5:
					return Boolean.class;
				}
				return null;
				
				/*return columns[columnIndex];*/
                
            }
        };
     // add header in table model     
        model.setColumnIdentifiers(columns);
        model.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent arg0)
			{
				sendSMS.getBtnSendSMS().setEnabled(true);	
			}
		});
        setModelRowData(model);
        TableRowSorter<DefaultTableModel> sorter =
                new TableRowSorter<DefaultTableModel>(model);
        
        table = new JTable(model);
        table.setRowSorter(sorter);
        //add the table to the frame
        panel.setLayout(new BorderLayout());
        panel.add(new JScrollPane(table));
        return table;
	}

	/**
	 * @return the table
	 */
	public static JTable getTable() {
		return table;
	}

	/**
	 * @param table the table to set
	 *//*
	public static void setTable(JTable table) {
		SendSMSBtnsHandler.table = table;
	}*/

	private void setModelRowData(DefaultTableModel model)
	{
		VDBSModel dtaModel = new VDBSModel();
		Collection<SMember> members =  dtaModel.getAllMembers();
		
		
		for (SMember sMember : members)
		{
			model.addRow(new Object[]
					{ 
						sMember.getM_ward(),sMember.getM_sex(),sMember.getFamily_head_status(),sMember.getM_name_e(),sMember.getM_contact(),false}
					);
		}
		
	}

}
