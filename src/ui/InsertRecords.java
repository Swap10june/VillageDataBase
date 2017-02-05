package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import util.Utils;

public class InsertRecords extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JDialog				parent;
	private File 				selectedFile;
	private JPanel 				upperBody 			= 	new JPanel();
	private JTextField 			txtSelectFile 		= 	new JTextField();
	private JButton 			btnInsert 			= 	new JButton("Insert");
	
	public InsertRecords(JDialog owner)
	{
		super(owner);
		this.parent = owner;
		Utils.getUtilityInstance().applyBasicSettingsOnWindow(parent,"Insert Records");
		initUI();
		parent.setVisible(true);
	}

	private void initUI()
	{
		upperBody.setBounds(0, 110, 894, 1);
		upperBody.setBorder(BorderFactory.createLineBorder(Color.black));
		parent.add(upperBody);
		
		JLabel lblSelectFile = new JLabel("Select File: ",SwingConstants.CENTER);
		lblSelectFile.setBounds(10, 50, 120, 30);
		parent.getContentPane().add(lblSelectFile);

		
		txtSelectFile.setBounds(150, 50, 400, 30);
		parent.getContentPane().add(txtSelectFile);
		
		JButton btnSelectFile = new JButton("Browse");
		btnSelectFile.setBounds(570, 50, 120, 30);
		parent.getContentPane().add(btnSelectFile);
		btnSelectFile.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser openFile = new JFileChooser();
				//openFile.addChoosableFileFilter(new FileNameExtensionFilter("*.xlsx", "*.xls"));
			    if( openFile.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION)
			    {
			        selectedFile = openFile.getSelectedFile();
			        txtSelectFile.setText(selectedFile.getAbsolutePath());
			        btnInsert.setEnabled(true);
			        
			    }
			}
		});
		btnInsert.setBounds(700, 50, 120, 30);
		btnInsert.setEnabled(false);
		parent.getContentPane().add(btnInsert);
		btnInsert.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String fileExtension = Utils.getUtilityInstance().getFileExtension(selectedFile);
		        if(fileExtension.equalsIgnoreCase("xls") || fileExtension.equalsIgnoreCase("xlsx"))
		        {
		        	Map<String,String> records = Utils.getUtilityInstance().inserRecordsFromFileJXL(selectedFile);
		        	int noOfresults = Utils.getUtilityInstance().insertContactWithNumbersIntoDB(records);
		        	JOptionPane.showMessageDialog(parent, noOfresults+" Records Inserted Successfully");
		        	/*for (Map.Entry<String, String> entry : records.entrySet())
		        	{
		        	    System.out.println(entry.getKey() + "/" + entry.getValue());
		        	}*/
		        }
		        else
		        {
		        	JOptionPane.showMessageDialog(parent, "Please Select Proper File");
		        	txtSelectFile.setText("");
		        	btnInsert.setEnabled(false);
		        }
			}
		});
	}

}
