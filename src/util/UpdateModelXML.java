package util;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import beans.SFamily;
import beans.SMember;

public class UpdateModelXML
{
	private Document doc;
	private String filePath = "resource/VDBSModel.xml";
    private SFamily family;
    public UpdateModelXML(SFamily family)
    {
        this.family = family;
      try
      {
    	  
    	  
    	    File xmlFile = new File(filePath);
    	    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    	    DocumentBuilder dBuilder;
	    	dBuilder = dbFactory.newDocumentBuilder();
	        doc = dBuilder.parse(xmlFile);
	        doc.getDocumentElement().normalize();
            //update attribute value
            //updateAttributeValue(doc);
            
            //update Element value
            //updateElementValue(doc);
            
            //delete element
            //deleteElement(doc);
           /* SFamily family = new SFamily("123");
            SMember member = new SMember();
            member.setFamily_head_status("true");
            member.setM_contact("9922821145");
            member.setM_dist("A.Nagar");
            member.setM_dob("10/06/1991");
            member.setM_gaon("babahulwade");
            member.setM_name_e("Ganpat Nana Jagadale");
            member.setM_name_m("sdsdsdsdsd");
            member.setM_sex("M");
            member.setM_state("MH");
            member.setM_tal("Parner");
            member.setM_ward(1);
            member.setMember_id("01");
            
            family.addMemberIntoFamily(member);
            addElementWithData(doc,family);*/
        	addElementWithData();
					//write the updated document to file or console
					doc.getDocumentElement().normalize();
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(doc);
					StreamResult result = new StreamResult(new File(filePath));
					transformer.setOutputProperty(OutputKeys.INDENT, "yes");
					transformer.transform(source, result);
					System.out.println("XML file updated successfully");
				} catch (TransformerConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerFactoryConfigurationError e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }

    private void addElementWithData()
    {
    	NodeList totalNumberOfFamilysTags = doc.getElementsByTagName("Familys");
        Element familysTag = (Element) totalNumberOfFamilysTags.item(0);
        Element familyElement = doc.createElement("Family");
        familyElement.setAttribute("Fid",family.getFamilyId());
        Node node = familysTag.appendChild(familyElement);
        //add new element
        addDataUnderFamilyTag(node,family);
	}

	private void addDataUnderFamilyTag(Node node, SFamily family)
    {
        Element emp = (Element) node;
        Element familyHeadElement = doc.createElement("FamilyHead");
        familyHeadElement.appendChild(doc.createTextNode("FamilyHead"));
        emp.appendChild(familyHeadElement);
        List<SMember> members = family.getMembers();
            
            for (int i = 0; i < members.size(); i++)
            {
            	SMember mem = members.get(i);
            	Element memberElement = doc.createElement("Member");
                memberElement.setAttribute("id", mem.getMember_id());
                memberElement.setAttribute("m_name_e", mem.getM_name_e());
                memberElement.setAttribute("m_name_m", mem.getM_name_m());
                memberElement.setAttribute("family_head_status", mem.getFamily_head_status());
                memberElement.setAttribute("m_sex", mem.getM_sex());
                memberElement.setAttribute("m_state", mem.getM_state());
                memberElement.setAttribute("m_dist", mem.getM_dist());
                memberElement.setAttribute("m_tal", mem.getM_tal());
                memberElement.setAttribute("m_gaon", mem.getM_gaon());
                memberElement.setAttribute("m_dob", mem.getM_dob());
                memberElement.setAttribute("m_ward", String.valueOf(mem.getM_ward()));
                memberElement.setAttribute("m_contact", mem.getM_contact());
                emp.appendChild(memberElement);
			}
            
    }

    private static void deleteElement(Document doc) {
        NodeList employees = doc.getElementsByTagName("Family");
        Element emp = null;
        //loop for each employee
        for(int i=0; i<employees.getLength();i++){
            emp = (Element) employees.item(i);
            Node genderNode = emp.getElementsByTagName("gender").item(0);
            emp.removeChild(genderNode);
        }
        
    }

    private static void updateElementValue(Document doc) {
        NodeList employees = doc.getElementsByTagName("Family");
        Element emp = null;
        //loop for each employee
        for(int i=0; i<employees.getLength();i++){
            emp = (Element) employees.item(i);
            Node name = emp.getElementsByTagName("name").item(0).getFirstChild();
            name.setNodeValue(name.getNodeValue().toUpperCase());
        }
    }

    private static void updateAttributeValue(Document doc)
    {
        NodeList employees = doc.getElementsByTagName("Family");
        Element emp = null;
        //loop for each employee
        for(int i=0; i<employees.getLength();i++)
        {
            emp = (Element) employees.item(i);
            String gender = emp.getElementsByTagName("gender").item(0).getFirstChild().getNodeValue();
            if(gender.equalsIgnoreCase("male"))
            {
                //prefix id attribute with M
                emp.setAttribute("id", "M"+emp.getAttribute("id"));
            }else
            {
                //prefix id attribute with F
                emp.setAttribute("id", "F"+emp.getAttribute("id"));
            }
        }
    }

}