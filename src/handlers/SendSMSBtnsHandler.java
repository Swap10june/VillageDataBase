package handlers;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ui.SendSMS;

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
						!sendSMS.getTextAdd1().getText().isEmpty() ||
						!sendSMS.getTextAdd2().getText().isEmpty() ||
						!sendSMS.getTextSurName().getText().isEmpty())
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
            "Member ID","Member Name", "Ward", "Select"
        };
        
        final Class[] columnClass = new Class[] {
        		String.class,String.class,Integer.class, Boolean.class
            };
        
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
                return columnClass[columnIndex];
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
		for (int count = 1; count <= 100; count++) 
        {
        	model.addRow(new Object[]
        			{ 	"MId-"+count, "Member:"+count,count,false}
        			);
        }
	}

}
