package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import beans.SFamily;
import beans.SMember;
import util.UConstants;

public class VDBSModel 
{


	private Document doc;
	private String filePath = UConstants.VDBS_DATA_MODEL_FILE_PATH;
	
	public VDBSModel()
	{
		
	      try
	      {
	    	    File xmlFile = new File(filePath);
	    	    if(!xmlFile.exists())
	    	    	xmlFile.createNewFile();
	    	    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	    DocumentBuilder dBuilder;
		    	dBuilder = dbFactory.newDocumentBuilder();
		        doc = dBuilder.parse(xmlFile);
		        doc.getDocumentElement().normalize();
	      }
	      catch (IOException | ParserConfigurationException | SAXException e)
	      {
	    	  // TODO Auto-generated catch block
	    	  e.printStackTrace();
	      } 
	}
    public int addFamily(List<SFamily> families)
    {
    	
    	NodeList totalNoOfSecondRootTags = doc.getElementsByTagName("Familys");
        Element eElement = (Element) totalNoOfSecondRootTags.item(0);
        
        for(int familyNo=0;familyNo<families.size();familyNo++)
        {
        	SFamily family = families.get(familyNo);
        	Element element = doc.createElement("Family");
        	element.setAttribute(UConstants.FAMILY_UID_ATTR,String.valueOf(getAllFamiliesUID().length+1) );
       
        	int muid= getAllMembersUID().length+1;
	        for (int memberNo = 0;memberNo<family.getMembers().size();memberNo++)
	        {
	        	SMember member = family.getMembers().get(memberNo);
	        	Element childElement = doc.createElement("Member");
	            childElement.setAttribute(UConstants.MEMBER_UID_ATTR,String.valueOf(muid++));
	            childElement.setAttribute(UConstants.MOBILE_NO_ATTR, member.getM_contact());
	            childElement.setAttribute(UConstants.HEAD_STATUS_ATTR, member.getFamily_head_status());
	            childElement.setAttribute(UConstants.MARATHI_NAME_ATTR,member.getM_name_m());
	            childElement.setAttribute(UConstants.SEX_STATUS_ATTR, member.getM_sex());
	            childElement.setAttribute(UConstants.STATE_ATTR, member.getM_state());
	            childElement.setAttribute(UConstants.DIST_ATTR, member.getM_dist());
	            childElement.setAttribute(UConstants.TAL_ATTR, member.getM_tal());
	            childElement.setAttribute(UConstants.GAON_ATTR, member.getM_gaon());
	            childElement.setAttribute(UConstants.DOB_ATTR, member.getM_dob());
	            childElement.setAttribute(UConstants.ENG_NAME_ATTR, member.getM_name_e());
	            childElement.setAttribute(UConstants.WARD_ATTR, String.valueOf(member.getM_ward()));
	            element.appendChild(childElement);
			}
	        
	        eElement.appendChild(element);
        }
        
        try
        {
			doc.getDocumentElement().normalize();
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filePath));
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);
			System.out.println("XML file updated successfully");
			
		} catch (TransformerException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
    public String[] getAllFamiliesUID()
    {
    	List<String> values = new ArrayList<String>();
   	 try 
   	 {
   		 doc.getDocumentElement().normalize();
   		 NodeList nList = doc.getElementsByTagName("Family");
   		 for (int temp = 0; temp < nList.getLength(); temp++)
   		 {
   			 Node nNode = nList.item(temp);

   		        if (nNode.getNodeType() == Node.ELEMENT_NODE)
   				{
   		            Element eElement = (Element) nNode;
   		            values.add(eElement.getAttribute(UConstants.FAMILY_UID_ATTR));
   		            //System.out.println("Staff id : " + eElement.getAttribute("id"));
   		        }
   		    }
   		    }
   			catch (Exception e)
   			{
   				e.printStackTrace();
   		    }
		return values.toArray(new String[values.size()]);
	}
	public String[] getAllMembersUID()
    {
    	List<String> values = new ArrayList<String>();
    	 try 
    	 {
    		 doc.getDocumentElement().normalize();
    		 NodeList nList = doc.getElementsByTagName("Member");
    		 for (int temp = 0; temp < nList.getLength(); temp++)
    		 {
    			 Node nNode = nList.item(temp);

    		        if (nNode.getNodeType() == Node.ELEMENT_NODE)
    				{
    		            Element eElement = (Element) nNode;
    		            values.add(eElement.getAttribute(UConstants.MEMBER_UID_ATTR));
    		            //System.out.println("Staff id : " + eElement.getAttribute("id"));
    		        }
    		    }
    		    }
    			catch (Exception e)
    			{
    				e.printStackTrace();
    		    }
		return values.toArray(new String[values.size()]);
    }
    public int getNoOfTagsUnderTag(String tagName)
    {
    	NodeList list = doc.getElementsByTagName(tagName);
    	return list.getLength();
    }
	public ArrayList<String> getAllFamilyHeadNames()
	{
		ArrayList<String> values = new ArrayList<String>();
	   	 try 
	   	 {
	   		 doc.getDocumentElement().normalize();
	   		 NodeList nList = doc.getElementsByTagName("Member");
	   		 for (int temp = 0; temp < nList.getLength(); temp++)
	   		 {
	   			 Node nNode = nList.item(temp);
	
	   		        if (nNode.getNodeType() == Node.ELEMENT_NODE)
	   				{
	   		            Element eElement = (Element) nNode;
	   		            values.add(eElement.getAttribute(UConstants.ENG_NAME_ATTR));
	   		            //System.out.println("Staff id : " + eElement.getAttribute("id"));
	   		        }
	   		    }
	   		    }
	   			catch (Exception e)
	   			{
	   				e.printStackTrace();
	   		    }
	   	 return values;
	}
	public SMember getMemberByName(String name)
	{
		SMember member = null; 
		doc.getDocumentElement().normalize();
  		 NodeList nList = doc.getElementsByTagName("Member");
  		 for (int temp = 0; temp < nList.getLength(); temp++)
  		 {
  			 Node nNode = nList.item(temp);

  		        if (nNode.getNodeType() == Node.ELEMENT_NODE)
  				{
  		            Element eElement = (Element) nNode;
  		            if(name.equalsIgnoreCase(eElement.getAttribute(UConstants.ENG_NAME_ATTR)))
  		            {
  		            	member = new SMember();
  		            	member.setMember_id(eElement.getAttribute(UConstants.MEMBER_UID_ATTR));
  		            	member.setFamily_head_status(eElement.getAttribute(UConstants.HEAD_STATUS_ATTR));
  		            	member.setFamily_id(eElement.getAttribute(UConstants.FAMILY_UID_ATTR));
  		            	member.setM_contact(eElement.getAttribute(UConstants.MOBILE_NO_ATTR));
  		            	member.setM_dist(eElement.getAttribute(UConstants.DIST_ATTR));
  		            	member.setM_dob(eElement.getAttribute(UConstants.DOB_ATTR));
  		            	member.setM_gaon(eElement.getAttribute(UConstants.GAON_ATTR));
  		            	member.setM_name_e(eElement.getAttribute(UConstants.ENG_NAME_ATTR));
  		            	member.setM_name_m(eElement.getAttribute(UConstants.MARATHI_NAME_ATTR));
  		            	member.setM_sex(eElement.getAttribute(UConstants.SEX_STATUS_ATTR));
  		            	member.setM_state(eElement.getAttribute(UConstants.STATE_ATTR));
  		            	member.setM_tal(eElement.getAttribute(UConstants.TAL_ATTR));
  		            	member.setM_ward(Integer.parseInt(eElement.getAttribute(UConstants.WARD_ATTR)));
  		            }
  		        }
  		  }
		return member;
	}
	public ArrayList<String> getAllStrings(String attr)
	{
		ArrayList<String> values = new ArrayList<String>();
	   	 try 
	   	 {
	   		 doc.getDocumentElement().normalize();
	   		 NodeList nList = doc.getElementsByTagName("Member");
	   		 for (int temp = 0; temp < nList.getLength(); temp++)
	   		 {
	   			 Node nNode = nList.item(temp);
	
	   		        if (nNode.getNodeType() == Node.ELEMENT_NODE)
	   				{
	   		            Element eElement = (Element) nNode;
	   		            values.add(eElement.getAttribute(attr));
	   		            //System.out.println("Staff id : " + eElement.getAttribute("id"));
	   		        }
	   		    }
	   		    }
	   			catch (Exception e)
	   			{
	   				e.printStackTrace();
	   		    }
	   	 return values;
	}
	public Map<String, String> getSMSMapFor(String ward, String surname,int sexIndex, int familyHeadIndex) 
	{
		String sex= "";
		if (sexIndex == 0)
			sex="Male";
		else 
			sex = "Female";
		
		String head= "";
		if (familyHeadIndex == 0)
			head="Yes";
		else 
			head = "No";
		
		Map<String, String> smsMap = new HashMap<String, String>();
		doc.getDocumentElement().normalize();
  		 NodeList nList = doc.getElementsByTagName("Member");
  		 for (int temp = 0; temp < nList.getLength(); temp++)
  		 {
  			 Node nNode = nList.item(temp);

  		        if (nNode.getNodeType() == Node.ELEMENT_NODE)
  				{
  		            Element eElement = (Element) nNode;
  		            if(
  		            		eElement.getAttribute(UConstants.WARD_ATTR).equalsIgnoreCase(ward.trim()) &&
  		            		eElement.getAttribute(UConstants.SEX_STATUS_ATTR).equalsIgnoreCase(sex) &&
  		            		eElement.getAttribute(UConstants.HEAD_STATUS_ATTR).equalsIgnoreCase(head)
  		            		//eElement.getAttribute(UConstants.WARD_ATTR).equalsIgnoreCase(ward)
  		            		)
  		            	smsMap.put(eElement.getAttribute(UConstants.ENG_NAME_ATTR),eElement.getAttribute(UConstants.MOBILE_NO_ATTR));
  		            //System.out.println("Staff id : " + eElement.getAttribute("id"));
  		        }
  		    }
		return smsMap;
	}
	public ArrayList<String> getAllMemberNames()
	{
		ArrayList<String> values = new ArrayList<String>();
	   	 try 
	   	 {
	   		 doc.getDocumentElement().normalize();
	   		 NodeList nList = doc.getElementsByTagName("Member");
	   		 for (int temp = 0; temp < nList.getLength(); temp++)
	   		 {
	   			 Node nNode = nList.item(temp);
	
	   		        if (nNode.getNodeType() == Node.ELEMENT_NODE)
	   				{
	   		            Element eElement = (Element) nNode;
	   		            values.add(eElement.getAttribute(UConstants.ENG_NAME_ATTR));
	   		            //System.out.println("Staff id : " + eElement.getAttribute("id"));
	   		        }
	   		    }
	   		    }
	   			catch (Exception e)
	   			{
	   				e.printStackTrace();
	   		    }
	   	 return values;
	}

}