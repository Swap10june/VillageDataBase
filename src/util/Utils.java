package util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/*import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;*/

















import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import model.VDBSModel;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



import dbutil.SQliteConnection;
//import jxl.Sheet;
//import jxl.read.biff.BiffException;
import beans.SFamily;
import beans.SMSContacts;
import beans.SMember;

public class Utils 
{
	private static Utils s_utils=null;
	
	private static final String HTML = "<html>";
    private static final String HTML_END = "</html>";
    
    private Utils()
    {
    	
    }
    public static Utils getUtilityInstance()
    {
        if(s_utils==null)
        {
           return new Utils();
          
        }
        else
        {
         //System.out.println("Return existing connection");
            return s_utils;
        }
    }
	public void applyBasicSettingsOnWindow(JDialog owner, String title) 
	{
		owner.setLayout(null);
	    //Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    //int x = (int) ((dimension.getWidth() - owner.getWidth()) / 4);
	    //int y = (int) ((dimension.getHeight() - owner.getHeight()) / 5);
	    owner.setLocation(200,100);
	    owner.setSize(new Dimension(900, 602));
	    owner.setModal(true);
	    owner.setResizable(false);
	    owner.setTitle(title);
	    owner.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    
	    
	    JLabel headerLabel = new JLabel("Village Management Syastem",SwingConstants.CENTER);
	    headerLabel.setBounds(0, 0, 894, 30);
	    Font font = new Font("Courier", Font.BOLD,18);
        //set font for JLabel
	    headerLabel.setFont(font);
	    Border border = BorderFactory.createLineBorder(Color.blue);
	    headerLabel.setBorder(border);
	    owner.add(headerLabel);
	    
	    JLabel footerLabel = new JLabel("Unity Infotech Group (I) Pvt Ltd",SwingConstants.CENTER);
	    footerLabel.setBounds(0, 542, 894, 30);
	    footerLabel.setFont(font);
	    footerLabel.setBorder(border);
	    owner.add(footerLabel);
	}

	public static void applyBasicSettingsOnWindow_Small(JDialog owner, String string)
	{
		owner.setLayout(null);
		//Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	   // int x = (int) ((dimension.getWidth() - owner.getWidth()) / 10);
	    //int y = (int) ((dimension.getHeight() - owner.getHeight()) / 10);
	    owner.setLocation(300,100);
	    owner.setSize(new Dimension(600, 502));
	    owner.setModal(true);
	    owner.setResizable(false);
	    owner.setTitle(string);
	    owner.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    
	    
	    JLabel headerLabel = new JLabel("Village Management Syastem",SwingConstants.CENTER);
	    headerLabel.setBounds(0, 0, 594, 30);
	    Font font = new Font("Courier", Font.BOLD,18);
        //set font for JLabel
	    headerLabel.setFont(font);
	    Border border = BorderFactory.createLineBorder(Color.blue);
	    headerLabel.setBorder(border);
	    owner.add(headerLabel);
	    
	    JLabel footerLabel = new JLabel("Unity Infotech Group (I) Pvt Ltd",SwingConstants.CENTER);
	    footerLabel.setBounds(0, 442, 594, 30);
	    footerLabel.setFont(font);
	    footerLabel.setBorder(border);
	    owner.add(footerLabel);
	}
	public String getMaxFamilyID()
    {
        try
        {
            ResultSet set = querySELECT("SELECT MAX(family_id) FROM SFAMILY");
            String familyID=null;
            while(set.next())
            {
                familyID = String.valueOf(set.getInt(1)+1);
            }
            return familyID;
        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Error";
    }
    public  ResultSet querySELECT(String Query) throws ClassNotFoundException, SQLException
    {
	  
        System.out.println("Select query fired: "+Query);
        ResultSet set=null;
        Statement statement = SQliteConnection.getSQliteConnection("vdbs.db").createStatement();
        set=statement.executeQuery(Query.toUpperCase());
        return set;     
	
    }

	public String getMaxMemberID(String familyId)
	{
		try
        {
            ResultSet set = querySELECT("SELECT MAX(member_id) FROM SMEMBER where family_id="+familyId);
            String memberID=null;
            while(set.next())
            {
            	memberID = String.valueOf(set.getInt(1)+1);
            }
            return memberID;
        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Error";
	}
	public  int queryINSERT(Object table) throws ClassNotFoundException, SQLException
	{	
		try
		{
			//String query;
			
			//PreparedStatement pStmtFamily = SQliteConnection.getSQliteConnection("vdbs.db").prepareStatement("UPDATE EMPLOYEES SET SALARY = ? WHERE ID = ?");	
			if(table instanceof SFamily)
			{
				String familyId= ((SFamily) table).getFamilyId().split("-")[1];
				
				PreparedStatement pStmtFamily = SQliteConnection.getSQliteConnection("vdbs.db").prepareStatement("insert into SFAMILY values(?,?)");
				pStmtFamily.setInt(1,Integer.parseInt(familyId));
				pStmtFamily.setString(2,((SFamily) table).getMembers().get(0).getM_name_e().toUpperCase());
				int status = pStmtFamily.executeUpdate();
				pStmtFamily.close();
				/*if(status<0)
				{
					JOptionPane.showMessageDialog (null, "Failure...!", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog (null, status+" Family Inserted...!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
				}*/
				return status;
			}
			if(table instanceof SMember)
			{
				SMember member = (SMember) table;
				String memberID= member.getMember_id().split("-")[1];
				String FamilyID= member.getFamily_id().split("-")[1];
				
				PreparedStatement pStmtMember = SQliteConnection.getSQliteConnection("vdbs.db").prepareStatement
						("insert into SMember values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
				pStmtMember.setInt(1,Integer.parseInt(memberID));
				pStmtMember.setInt(2,Integer.parseInt(FamilyID));
				pStmtMember.setString(3, member.getM_name_e().toUpperCase());
				pStmtMember.setString(4, member.getM_name_m());
				if(member.getFamily_head_status()!=null)
					pStmtMember.setString(5, member.getFamily_head_status().toUpperCase());
				else
					pStmtMember.setString(5, "NO");
				pStmtMember.setString(6, member.getM_sex().toUpperCase());
				pStmtMember.setString(7, member.getM_state().toUpperCase());
				pStmtMember.setString(8, member.getM_dist().toUpperCase());
				pStmtMember.setString(9, member.getM_tal().toUpperCase());
				pStmtMember.setString(10, member.getM_gaon().toUpperCase());
				pStmtMember.setString(11, member.getM_dob());
				pStmtMember.setInt(12, member.getM_ward());
				pStmtMember.setString(13, member.getM_contact());
				
				int status = pStmtMember.executeUpdate();
				pStmtMember.close();
				/*if(status<0)
				{
					JOptionPane.showMessageDialog (null, "Failure...!", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog (null, status+" Family Inserted...!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
				}*/
				return status;
			}
			if(table instanceof SMSContacts)
			{
				int status = 0;
				Map<String,String> records = ((SMSContacts) table).getContacts();
				for (Map.Entry<String, String> entry : records.entrySet())
	        	{
	        	    System.out.println(entry.getKey() + "/" + entry.getValue());
	        	    PreparedStatement pStmtMember = SQliteConnection.getSQliteConnection("vdbs.db").prepareStatement
							("insert into SSMSContacts values(?,?)");
					pStmtMember.setString(1, entry.getValue().toUpperCase());
					pStmtMember.setString(2, entry.getKey());
					status += pStmtMember.executeUpdate();
	        	}
			return status;	
			}
			
		} catch (Exception e)
		{
			// TODO: handle exception
		}
		return 0;
	}

	public void queryUpdate(SFamily family, String columName,
			String columValue)
	{
		String query="update SFamily set "+columName+"="+columValue;
		System.out.println("update query filred: "+query);
		Statement statement;
	        try 
	        {
				statement = SQliteConnection.getSQliteConnection("vdbs.db").createStatement();
				statement.executeQuery(query.toUpperCase());
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
	}

	public ArrayList<String> queryMultiColumnSelect(String table, Map<String,String> columnNames)
	{
		ArrayList<String> list = new ArrayList<>();
		String columName = "";
		for (Map.Entry<String, String> entry :columnNames.entrySet())
		{
			list.add(entry.getValue());
		}
		for (int i = 0; i < list.size(); i++)
		{
			columName+=list.get(i);
			if(i!=list.size()-1)
			{
				columName+=" , ";
			}
		}
		try
		{
			
			ResultSet set = querySELECT("SELECT "+columName+" FROM "+table);
			String prefix = "FID-";
			list.clear();
			while(set.next())
			{
				list.add(set.getString(2)+":"+prefix+":"+String.valueOf(set.getInt(1)));
			}
			return list;
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	}
	public void setComponenet(JComponent component, Map<String,JComponent> componenetMap)
	{
		component.setName(component.toString());
		componenetMap.put(component.getName(), component);
	}
	public  ArrayList<String> getAllContactsForSelectedMembers(DefaultTableModel model) 
    {
        ArrayList<String> mobileNoCollection;
        mobileNoCollection = new ArrayList<String>();
        for (int i = 0; i <model.getRowCount() ; i++)
        {
            String str=model.getValueAt(i, 3).toString();
            int memberID=Integer.parseInt(model.getValueAt(i, 0).toString());

            if(str.equalsIgnoreCase("true"))
            {
               
                try
                {
                    String sql="Select m_contact from SMember where member_id="+memberID;
                    ResultSet set=querySELECT(sql.toUpperCase());
                    //System.out.println(sql);
                    while (set.next())
                    {
                        if(set.getString("m_contact")!=null)
                        {
                            mobileNoCollection.add(set.getString("m_contact"));//phone
                        }
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    //Logger.getLogger(ProjectUtils.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return mobileNoCollection;
    }
	public ArrayList<String> getValueListForColumnName(String tableName,String colmnName)
	{
		try
		{
			ArrayList<String> list = new ArrayList<String>();
			ResultSet set = querySELECT("select "+colmnName+" from "+tableName);
			while(set.next())
			{
				list.add(set.getString(colmnName));
			}
			return list;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void inserRecordsFromFile(File selectedFile)
	{
		/*try {
			String FilePath = selectedFile.getAbsolutePath();
			FileInputStream fs = new FileInputStream(FilePath);
			jxl.Workbook wb = jxl.Workbook.getWorkbook(fs);
			
			Sheet sh = wb.getSheet("Nov 2016");

			// To get the number of rows present in sheet
			int totalNoOfRows = sh.getRows();

			// To get the number of columns present in sheet
			int totalNoOfCols = sh.getColumns();

			for (int row = 0; row < totalNoOfRows; row++)
			{

				for (int col = 0; col < totalNoOfCols; col++)
				{
					System.out.print(sh.getCell(col, row).getContents() + "\t");
				}
				System.out.println();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	public String getFileExtension(File file)
	{
	    String name = file.getName();
	    try
	    {
	        return name.substring(name.lastIndexOf(".") + 1);
	    } catch (Exception e)
	    {
	        return "";
	    }
	}
	
	public Map<String,Map<String,String>> inserRecordsFromXLFile(File selectedFile)
	{
		Map<String,Map<String,String>> records = new HashMap<String,Map<String,String>>();
		Workbook wrk1 = null;
        try 
        {
            wrk1 = Workbook.getWorkbook(selectedFile);
            Sheet sheet1 = wrk1.getSheet(0);
            
           // int width = sheet1.getColumns();
            int height = sheet1.getRows();
            
            Map<String,String> list = null;
            for(int j=0; j<height; j++)
            {
            	list = new HashMap<>();
               //Obtain reference to the Cell using getCell(int col, int row) method of sheet
                Cell numberColumn = sheet1.getCell(0, j);
                Cell familyHeadStatus = sheet1.getCell(1, j);
                Cell marathiName = sheet1.getCell(2, j);
                Cell sex = sheet1.getCell(3, j);
                Cell state = sheet1.getCell(4, j);
                Cell dist = sheet1.getCell(5, j);
                Cell tal = sheet1.getCell(6, j);
                Cell gaon = sheet1.getCell(7, j);
                Cell dob = sheet1.getCell(8, j);
                Cell engName = sheet1.getCell(9, j);
                Cell ward = sheet1.getCell(10, j);

                if(numberColumn.getContents().equalsIgnoreCase(""))
                continue;
                //Read the contents of the Cell using getContents() method, which will return
                //it as a String
                String strKey = numberColumn.getContents();
                list.put(UConstants.MOBILE_NO_ATTR, strKey.isEmpty()?"-NV-":strKey);
                list.put(UConstants.HEAD_STATUS_ATTR, familyHeadStatus.getContents().isEmpty()?"False":familyHeadStatus.getContents());
                list.put(UConstants.MARATHI_NAME_ATTR, marathiName.getContents().isEmpty()?"-NV-":marathiName.getContents());
                list.put(UConstants.SEX_STATUS_ATTR, sex.getContents().isEmpty()?"-NV-":sex.getContents());
                list.put(UConstants.STATE_ATTR, state.getContents().isEmpty()?"-NV-":state.getContents());
                list.put(UConstants.DIST_ATTR, dist.getContents().isEmpty()?"-NV-":dist.getContents());
                list.put(UConstants.TAL_ATTR, tal.getContents().isEmpty()?"-NV-":tal.getContents());
                list.put(UConstants.GAON_ATTR, gaon.getContents().isEmpty()?"-NV-":gaon.getContents());
                list.put(UConstants.DOB_ATTR, dob.getContents().isEmpty()?"-NV-":dob.getContents());
                list.put(UConstants.ENG_NAME_ATTR, engName.getContents().isEmpty()?"-NV-":engName.getContents());
                list.put(UConstants.WARD_ATTR, ward.getContents().isEmpty()?"-NV-":ward.getContents());
               
                records.put(strKey, list);
                //list.clear();
            }
        }
        catch (BiffException | IOException e)
        {
        	System.out.println();
        }
		return records;
	}
	public File generateTextFileToSendSMS(Map<String, String> records)
	{
		File objFile = null;
		try 
        {
            objFile= new File("C:\\temp\\bulkSMSList.txt");
            if(objFile.exists())
                objFile.delete();
            if(!objFile.exists())
                objFile.createNewFile();
            @SuppressWarnings("resource")
			PrintWriter out = new PrintWriter(new FileOutputStream(objFile));
            for (Map.Entry<String, String> entry : records.entrySet())
        	{
            	out.println(entry.getKey());
        	    //System.out.println(entry.getKey() + "/" + entry.getValue());
        	}
            /*for (Object list1 : list)
            {
                //System.out.println(list1);
                out.println(list1);
                
            }*/
            out.flush();
        } catch (IOException ex)
		{
        	ex.printStackTrace();
        }
		return objFile;
	}
	public int insertRecordsFromExcel(Map<String,Map<String,String>> records)
	{
		List<SFamily> familyList = new ArrayList<>();
		int familyId= new VDBSModel().getAllFamiliesUID().length+1;
		int memberId= new VDBSModel().getAllMembersUID().length+1;
		for (Map.Entry<String,Map<String,String>> familyEntry:records.entrySet())
		{
			SFamily family = new SFamily();
			SMember member = new SMember();
			Map<String,String> memberInfo = familyEntry.getValue();
			family.setFamilyId("FID-"+String.valueOf(familyId));
			family.setFamilyHead(memberInfo.get(UConstants.ENG_NAME_ATTR));
			member.setFamily_id("FID-"+String.valueOf(familyId));
			familyId++;
			
			member.setMember_id("MID-"+String.valueOf(memberId));
			
			memberId++;
			
			member.setM_contact(memberInfo.get(UConstants.MOBILE_NO_ATTR));
			member.setFamily_head_status(memberInfo.get(UConstants.HEAD_STATUS_ATTR));
			member.setM_name_m(memberInfo.get(UConstants.MARATHI_NAME_ATTR));
			member.setM_sex(memberInfo.get(UConstants.SEX_STATUS_ATTR));
			member.setM_state(memberInfo.get(UConstants.STATE_ATTR));
			member.setM_dist(memberInfo.get(UConstants.DIST_ATTR));
			member.setM_tal(memberInfo.get(UConstants.TAL_ATTR));
			member.setM_gaon(memberInfo.get(UConstants.GAON_ATTR));
			member.setM_dob(memberInfo.get(UConstants.DOB_ATTR));
			member.setM_name_e(memberInfo.get(UConstants.ENG_NAME_ATTR));
			member.setM_ward(Integer.parseInt(memberInfo.get(UConstants.WARD_ATTR)));
			
			family.addMemberIntoFamily(member);
			familyList.add(family);
		}
		//update datamodel
		new VDBSModel().addFamily(familyList);
		//update db
		//new Dao().updateFamily(familyList);
		return familyList.size();
	}
	public String htmlIfy(String s)
    {
        return HTML.concat(s).concat(HTML_END);
    }
	public String ReadTag(String tagName, String file) {

	    try 
	    {

		File fXmlFile = new File(file);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);

		
		doc.getDocumentElement().normalize();

		//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

		NodeList nList = doc.getElementsByTagName("user");

		//System.out.println("----------------------------");

		for (int temp = 0; temp < nList.getLength(); temp++)
	        {

			Node nNode = nList.item(temp);

			//System.out.println("\nCurrent Element :" + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE)
	        {

				Element eElement = (Element) nNode;
				return eElement.getElementsByTagName(tagName).item(0).getTextContent();
			}
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	        return null;
	  }
}
