package ui;

import handlers.SendSMSBtnsHandler;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultRowSorter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import model.VDBSModel;
import util.UConstants;
import util.Utils;

public class SendSMS extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4751620116396415943L;
	
	private JDialog				parent;
	private JPanel 				upperBody 		= new JPanel();
	private JPanel 				middlePanel 	= new JPanel();
	private JPanel 				footerPanel 	= new JPanel();

	private VDBSModel			m_VDBSModel		= new VDBSModel();	

	private JComboBox<String> 	comboWard 		= new JComboBox<String>(m_VDBSModel.getAllWardNumbers());
	private JComboBox<String> 	comboSex 		= new JComboBox<String>(UConstants.S_SEX_COMBO);
	private JComboBox<String> 	comboHeadStatus = new JComboBox<String>(UConstants.S_HEAD_COMBO);
	
	private JButton 			btnSelectAll 	= new JButton("Select All");
	private JButton 			BtnShow 		= new JButton("Show");
	private JButton 			btnDeSelectAll 	= new JButton("De-Select");
	private JButton 			btnClearModel 	= new JButton("Clear");
	private JButton 			btnSendSMS 		= new JButton("Send SMS");
	private JButton				btnCreateSticker = new JButton("Create Sticker");
	private JButton				btnClearFilter	= new JButton("Clear Filter");

	private JTable m_table;

	private TableRowSorter<DefaultTableModel> m_sorter;
	
	public SendSMS(JDialog owner, String sendBulkSmsString)
	{
		super(owner);
		this.parent = owner;
		Utils.getUtilityInstance().applyBasicSettingsOnWindow(parent,sendBulkSmsString);
		initUI();
		parent.setVisible(true);
	}

	private void initUI()
	{
		
		upperBody.setBounds(0, 110, 894, 1);
		upperBody.setBorder(BorderFactory.createLineBorder(Color.black));
		parent.add(upperBody);
		
		JLabel lblSelectBY = new JLabel("Select By:");
		lblSelectBY.setBounds(10, 50, 100, 25);
		parent.add(lblSelectBY);
		
		
		JLabel lblWard = new JLabel("Enter Ward No");
		lblWard.setBounds(120, 40, 100, 25);
		parent.add(lblWard);
		
		comboWard.setBounds(120, 70, 100, 25);
		comboWard.setToolTipText("Ward No");
		comboWard.setName(UConstants.WARD_ATTR);
		parent.add(comboWard);
		comboWard.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				String selected = e.getItem().toString().trim();
				@SuppressWarnings("unchecked")
				String source = ((JComboBox<String>)e.getSource()).getName();
				updateTable(source,selected);
			}
		});
		
		JLabel lblAdd2 = new JLabel("Sex");
		lblAdd2.setBounds(350, 40, 100, 25);
		parent.add(lblAdd2);
		
		comboSex.setBounds(350, 70, 100, 25);
		comboSex.setToolTipText("Sex");
		comboSex.setName(UConstants.SEX_STATUS_ATTR);
		parent.add(comboSex);
		comboSex.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				String selected = e.getItem().toString().trim();
				@SuppressWarnings("unchecked")
				String source = ((JComboBox<String>)e.getSource()).getName();
				updateTable(source,selected);
			}
		});
		
		JLabel lblSurName = new JLabel("Enter Surname");
		lblSurName.setBounds(460, 40, 100, 25);
		parent.add(lblSurName);
		
		
		
		JLabel lblAdd1 = new JLabel("Head Status");
		lblAdd1.setBounds(240, 40, 100, 25);
		parent.add(lblAdd1);
		
		comboHeadStatus.setBounds(240, 70, 100, 25);
		comboHeadStatus.setToolTipText("Head Status");
		parent.add(comboHeadStatus);
		comboHeadStatus.setName(UConstants.HEAD_STATUS_ATTR);
		comboHeadStatus.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				String selected = e.getItem().toString().trim();
				@SuppressWarnings("unchecked")
				String source = ((JComboBox<String>)e.getSource()).getName();
				updateTable(source,selected);
			}
		});
		
		
		BtnShow.setBounds(580, 50, 100, 25);
		parent.add(BtnShow);
		BtnShow.addActionListener(new SendSMSBtnsHandler(this,parent));
		
		
		btnClearFilter.setBounds(690, 50, 100, 25);
		btnClearFilter.setEnabled(false);
		parent.add(btnClearFilter);
		btnClearFilter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				TableRowSorter<DefaultTableModel> sorter= (TableRowSorter<DefaultTableModel>) m_table.getRowSorter();
		        sorter.setRowFilter(null);
			}
		});
		
		middlePanel.setBounds(0, 110, 894, 390);
		middlePanel.setVisible(false);
		middlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		middlePanel.setBackground(Color.gray);
		parent.add(middlePanel);
		
		footerPanel.setBounds(0, 500, 894, 40);
		footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		footerPanel.setBackground(Color.cyan);
		footerPanel.setVisible(false);
		parent.add(footerPanel);
		
		
        footerPanel.add(btnSelectAll);
        btnSelectAll.setEnabled(false);
        btnSelectAll.addActionListener(new SendSMSBtnsHandler(this,parent));
        
        
        footerPanel.add(btnDeSelectAll);
        btnDeSelectAll.setEnabled(false);
        btnDeSelectAll.addActionListener(new SendSMSBtnsHandler(this,parent));
        
        
        footerPanel.add(btnClearModel);
        btnClearModel.setEnabled(false);
        btnClearModel.addActionListener(new SendSMSBtnsHandler(this,parent));
        
        
        footerPanel.add(btnSendSMS);
        btnSendSMS.setEnabled(false);
        btnSendSMS.addActionListener(new SendSMSBtnsHandler(this,parent));
        
        footerPanel.add(btnCreateSticker);
        btnSendSMS.addActionListener(new SendSMSBtnsHandler(this,parent));
        
        JButton btnCancel = new JButton("Cancel");
        footerPanel.add(btnCancel);
        btnCancel.addActionListener(new ActionListener()
        {
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				parent.dispose();
			}
		});
        
	}
	
	protected void updateTable(String source, String selected)
	{
		m_table = SendSMSBtnsHandler.getTable();
		@SuppressWarnings("unchecked")
		TableRowSorter<DefaultTableModel> sorter= (TableRowSorter<DefaultTableModel>) m_table.getRowSorter();
		String text = "(?i)" + selected;
        
        int col = -1;
        if (source.equalsIgnoreCase(UConstants.WARD_ATTR))
        {
            col = 0;
        }
        if (source.equalsIgnoreCase(UConstants.SEX_STATUS_ATTR))
        {
            col = 1;
        }
        if (source.equalsIgnoreCase(UConstants.HEAD_STATUS_ATTR))
        {
            col = 2;
        }
        sorter.setRowFilter(RowFilter.regexFilter(text, col));
        btnClearFilter.setEnabled(true);
    }

	public JPanel getUpperBody() {
		return upperBody;
	}

	public void setUpperBody(JPanel upperBody) {
		this.upperBody = upperBody;
	}
	public JPanel getMiddlePanel() {
		return middlePanel;
	}

	public void setMiddlePanel(JPanel middlePanel) {
		this.middlePanel = middlePanel;
	}

	public JPanel getFooterPanel() {
		return footerPanel;
	}

	public void setFooterPanel(JPanel footerPanel) {
		this.footerPanel = footerPanel;
	}

	
	public JComboBox<String> getComboSex() {
		return comboSex;
	}

	public void setComboSex(JComboBox<String> comboSex) {
		this.comboSex = comboSex;
	}

	public JComboBox<String> getComboHeadStatus() {
		return comboHeadStatus;
	}

	public void setComboHeadStatus(JComboBox<String> comboHeadStatus) {
		this.comboHeadStatus = comboHeadStatus;
	}
	public JButton getBtnSelectAll() {
		return btnSelectAll;
	}

	public void setBtnSelectAll(JButton btnSelectAll) {
		this.btnSelectAll = btnSelectAll;
	}

	public JButton getBtnShow() {
		return BtnShow;
	}

	public void setBtnShow(JButton btnShow) {
		BtnShow = btnShow;
	}

	public JButton getBtnDeSelectAll() {
		return btnDeSelectAll;
	}

	public void setBtnDeSelectAll(JButton btnDeSelectAll) {
		this.btnDeSelectAll = btnDeSelectAll;
	}

	public JButton getBtnClearModel() {
		return btnClearModel;
	}

	public void setBtnClearModel(JButton btnClearModel) {
		this.btnClearModel = btnClearModel;
	}

	public JButton getBtnSendSMS() {
		return btnSendSMS;
	}

	public void setBtnSendSMS(JButton btnSendSMS) {
		this.btnSendSMS = btnSendSMS;
	}

	
}
