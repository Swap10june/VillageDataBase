package ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import beans.SDocument;
import model.DocumentModel;
import model.VDBSModel;
import util.AutoSuggestor;
import util.UConstants;
import util.Utils;

public class AddDocuments extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JTextField txtMemberID = new JTextField();
	private JComboBox<String> selectDocumentCombo;
	private List<SDocument> documentList = new ArrayList<SDocument>();
	private final java.awt.List docList = new java.awt.List();
	public AddDocuments(JDialog owner, String addDocumentString)
	{
		super(owner);
		Utils.getUtilityInstance().applyBasicSettingsOnWindow(owner,addDocumentString);
		initUI(owner);
		owner.setVisible(true);
	}

	private void initUI(final JDialog owner)
	{
		JLabel lblAddDocument = new JLabel("Select Member",SwingConstants.CENTER);
		final JButton btnBrowse = new JButton("Browse");
		final JTextField txtPath = new JTextField();
		final JPanel imagePanel = new JPanel();
		final JButton btnAddImage = new JButton("Add");
		final JButton btnCancel  = new JButton("Cancel");
		final JLabel lblID = new JLabel("Enter Document ID");
		final JTextField txtID = new JTextField();
		final JButton btnSubmeet = new JButton("Submit");
		
		JPanel upperBody = new JPanel();
		upperBody.setBounds(0, 110, 894, 1);
		upperBody.setBorder(BorderFactory.createLineBorder(Color.black));
		owner.add(upperBody);
		
		
		lblAddDocument.setBounds(10, 50, 120, 30);
		owner.add(lblAddDocument);	
		
		txtMemberID.setEditable(true);
		txtMemberID.setBounds(150, 50, 400, 30);
		owner.add(txtMemberID);
		@SuppressWarnings("unused")
		AutoSuggestor autoSuggestor = new AutoSuggestor(txtMemberID, owner, null, Color.WHITE.brighter(), Color.BLUE, Color.RED, 0.75f)
		{
		    protected boolean wordTyped(String typedWord)
		    {
		        setDictionary(new VDBSModel().getAllMemberNames());
		        return super.wordTyped(typedWord);//now call super to check for any matches against newest dictionary
		    }
		};
		
		final JButton btnSelectDocument = new JButton("Select");
		btnSelectDocument.setBounds(570, 50, 120, 30);
		owner.add(btnSelectDocument);
		
				
		selectDocumentCombo = new JComboBox<String>(UConstants.DOCUMENT_NAME_LIST);
		selectDocumentCombo.setBounds(10, 120, 200, 30);
		selectDocumentCombo.setVisible(false);
		owner.add(selectDocumentCombo);
		
		
		lblID.setBounds(260, 150, 250, 30);
		lblID.setVisible(false);
		owner.add(lblID);
		
		
		txtID.setBounds(430, 150, 250, 30);
		txtID.setVisible(false);
		owner.add(txtID);
		
		
		btnBrowse.setBounds(260, 120, 150, 30);
		btnBrowse.setVisible(false);
		owner.add(btnBrowse);
		
		txtPath.setBounds(430, 120, 250, 30);
		txtPath.setVisible(false);
		owner.add(txtPath);
		
		imagePanel.setBounds(10, 170, 400, 350);
		imagePanel.setLayout(new FlowLayout());
		//imagePanel.setBackground(Color.cyan);
		imagePanel.setVisible(false);
		imagePanel.add(new JLabel());
		owner.add(imagePanel);
		
		docList.setBounds(685, 120, 200, 200);
		docList.setVisible(false);
		docList.setEnabled(false);
		owner.add(docList);
		
		btnAddImage.setBounds(450, 400, 100, 30);
		btnAddImage.setVisible(false);
		owner.add(btnAddImage);
		
		btnSubmeet.setBounds(600, 400, 100, 30);
		btnSubmeet.setVisible(false);
		owner.add(btnSubmeet);
		
		btnCancel.setBounds(750, 400, 100, 30);
		btnCancel.setVisible(false);
		owner.add(btnCancel);
		
		
		btnAddImage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if(txtID.getText().isEmpty() || txtPath.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Please Browse some file to upload OR Enter Document ID");
				else
				{
					documentList.add(
							new SDocument(
									txtID.getText(),
									selectDocumentCombo.getSelectedItem().toString(),
									txtPath.getText(), txtMemberID.getText()));
					JOptionPane.showMessageDialog(null, "Document Added Successfully");
					txtID.setText("");
					btnBrowse.setEnabled(true);
					txtPath.setText("");
					//docList.setVisible(true);
					imagePanel.setVisible(false);
					btnSubmeet.setVisible(true);
					docList.add(selectDocumentCombo.getSelectedItem().toString());
				}
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				owner.dispose();
			}
		});
		
		btnBrowse.addActionListener(new ActionListener() {
			
			private String defaultPath;

			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					JFileChooser openFile = new JFileChooser();
					if(defaultPath!=null)
						openFile.setCurrentDirectory(new File(defaultPath));
					openFile.addChoosableFileFilter(new FileNameExtensionFilter("*.png", "*.jpg", "*.jpeg"));
					if( openFile.showOpenDialog(owner) == JFileChooser.APPROVE_OPTION)
					{
						File file = openFile.getSelectedFile();
						defaultPath = file.getAbsolutePath();
						txtPath.setText(file.getAbsolutePath());
						imagePanel.setVisible(true);
						
						BufferedImage myPicture = ImageIO.read(new File(file.getAbsolutePath()));
						Image scaledImage = myPicture.getScaledInstance(imagePanel.getWidth(),imagePanel.getHeight(),Image.SCALE_SMOOTH);
						JLabel lbl  = (JLabel) imagePanel.getComponent(0);
						lbl.setIcon(new ImageIcon(scaledImage));
						imagePanel.revalidate();
						imagePanel.repaint();
						btnBrowse.setEnabled(false);
						btnAddImage.setVisible(true);
						btnCancel.setVisible(true);
					    
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSubmeet.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if(documentList.size()>0)
				{
					new DocumentModel().addDocument(documentList, txtMemberID.getText());
					JOptionPane.showMessageDialog(null, "Added Successfully");
					owner.dispose();
				}
				else
					JOptionPane.showMessageDialog(null, "Please Add Document First");
				
			}
		});
		
		
		btnSelectDocument.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				
				if(txtMemberID.getText().isEmpty() || txtMemberID.getText()==null || txtMemberID.getText().equalsIgnoreCase(""))
				{
					JOptionPane.showMessageDialog(null, "Please Enter Member Name");
					txtMemberID.setFocusable(true);
					txtMemberID.setBackground(Color.cyan);
				}
				else
				{
					btnSelectDocument.setEnabled(false);
					selectDocumentCombo.setVisible(true);
					lblID.setVisible(true);
					txtID.setVisible(true);
					btnBrowse.setVisible(true);
					txtPath.setVisible(true);
					docList.setVisible(true);
					List<String> presentList = new DocumentModel().getDocumentNames(txtMemberID.getText().trim());
					for (int i = 0; i < presentList.size(); i++)
					{
						docList.add(presentList.get(i));
					}
					
				}
			}
		});
		
	}

	
	
}
