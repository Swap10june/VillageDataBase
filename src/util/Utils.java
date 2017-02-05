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
import java.util.Map;
import java.util.Vector;
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

















import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;













//import jxl.Sheet;
//import jxl.read.biff.BiffException;
import beans.SFamily;
import beans.SMSContacts;
import beans.SMember;

public class Utils 
{
	private static Utils s_utils=null;
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
        Statement statement = DBConnection.getConnectionInstance().createStatement();
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
			
			//PreparedStatement pStmtFamily = DBConnection.getConnectionInstance().prepareStatement("UPDATE EMPLOYEES SET SALARY = ? WHERE ID = ?");	
			if(table instanceof SFamily)
			{
				String familyId= ((SFamily) table).getFamilyId().split("-")[1];
				
				PreparedStatement pStmtFamily = DBConnection.getConnectionInstance().prepareStatement("insert into SFAMILY values(?,?)");
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
				
				PreparedStatement pStmtMember = DBConnection.getConnectionInstance().prepareStatement
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
	        	    PreparedStatement pStmtMember = DBConnection.getConnectionInstance().prepareStatement
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
				statement = DBConnection.getConnectionInstance().createStatement();
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
	public Vector<?> inserRecordsFromFilePOI(File selectedFile) throws EncryptedDocumentException, InvalidFormatException
	{
		 /*Vector cellVectorHolder = new Vector();
		try
		{
		    Workbook wb = WorkbookFactory.create(selectedFile);
		    Sheet mySheet = wb.getSheetAt(0);
		    Iterator<Row> rowIter = mySheet.rowIterator();
		    System.out.println(mySheet.getRow(1).getCell(0));
		    
		    System.out.println(mySheet.getRow(1).getCell(0));
	        while(rowIter.hasNext())
	        {
	            HSSFRow myRow = (HSSFRow) rowIter.next();
	            Iterator cellIter = myRow.cellIterator();
	            Vector cellStoreVector=new Vector();
	            while(cellIter.hasNext())
	            {
	                HSSFCell myCell = (HSSFCell) cellIter.next();
	                cellStoreVector.addElement(myCell);
	            }
	            cellVectorHolder.addElement(cellStoreVector);
	        }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return cellVectorHolder;*/
		return null;
	}
	public Map<String,String> inserRecordsFromFileJXL(File selectedFile)
	{
		Map<String,String> records = new HashMap<String, String>();
		Workbook wrk1 = null;
        try 
        {
                wrk1 = Workbook.getWorkbook(selectedFile);
                Sheet sheet1 = wrk1.getSheet(0);
                
               // int width = sheet1.getColumns();
                int height = sheet1.getRows();
                
              //List<Cell> cells = new ArrayList<Cell>();
                for(int j=0; j<height; j++)
                {
                   //Obtain reference to the Cell using getCell(int col, int row) method of sheet
	                Cell nameColumn = sheet1.getCell(0, j);
	                Cell numberColumn = sheet1.getCell(1, j);
	                //Cell colArow3 = sheet1.getCell(2, j);
	
	                if(nameColumn.getContents().equalsIgnoreCase(""))
	                continue;
	                //Read the contents of the Cell using getContents() method, which will return
	                //it as a String
	                String strKey = numberColumn.getContents();
	                String strValue = nameColumn.getContents();
	                //System.out.println(row1);
	                records.put(strKey, strValue);
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
	public int insertContactWithNumbersIntoDB(Map<String, String> records)
	{
		try
		{
			SMSContacts smsContacts = new SMSContacts(records);
			return queryINSERT(smsContacts);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
