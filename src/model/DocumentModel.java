package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

import beans.SDocument;
import util.UConstants;

public class DocumentModel {

	private Document doc;
	private String filePath = UConstants.DOCUMENT_MODEL_FILE_PATH;
	
	public DocumentModel()
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
	public String[] getAllDocumentTypes()
	{
		
		List<String> values = new ArrayList<String>();
	   	 try 
	   	 {
	   		 doc.getDocumentElement().normalize();
	   		 NodeList nList = doc.getElementsByTagName("Doc");
	   		 for (int temp = 0; temp < nList.getLength(); temp++)
	   		 {
	   			 Node nNode = nList.item(temp);

	   		        if (nNode.getNodeType() == Node.ELEMENT_NODE)
	   				{
	   		            Element eElement = (Element) nNode;
	   		            values.add(eElement.getAttribute(UConstants.DOCUMENT_NAME_ATTR));
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
	public void addDocument(List<SDocument> documentList,String dOwner)
	{
		NodeList totalNoOfSecondRootTags = doc.getElementsByTagName("Documents");
        Element eElement = (Element) totalNoOfSecondRootTags.item(0);
    	
        Element element = doc.createElement("MName");
        element.setAttribute(UConstants.DOCUMENT_OWNER_ATTR,dOwner );
    
        for (int docNo = 0;docNo<documentList.size();docNo++)
        {
	        	SDocument document = documentList.get(docNo);
	        	Element childElement = doc.createElement("Doc");
	            childElement.setAttribute(UConstants.DOCUMENT_ID_ATTR,document.getDocumentID());
	            childElement.setAttribute(UConstants.DOCUMENT_PATH_ATTR, document.getdPath());
	            childElement.setAttribute(UConstants.DOCUMENT_NAME_ATTR, document.getdName());
	            childElement.setAttribute(UConstants.DOCUMENT_OWNER_ATTR,document.getdOwnerName());
	           
	            element.appendChild(childElement);
		}
	     eElement.appendChild(element);
		 finallyMethod();
		
	}
	private void finallyMethod()
	{
		try {
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
		}
	}
	public List<String> getDocumentNames(String text)
	{
		List<String> values = new ArrayList<String>();
	   	 try 
	   	 {
	   		 doc.getDocumentElement().normalize();
	   		 NodeList nList = doc.getElementsByTagName("Doc");
	   		 for (int temp = 0; temp < nList.getLength(); temp++)
	   		 {
	   			 Node nNode = nList.item(temp);

	   		        if (nNode.getNodeType() == Node.ELEMENT_NODE)
	   				{
	   		            Element eElement = (Element) nNode;
	   		            if(eElement.getAttribute(UConstants.DOCUMENT_OWNER_ATTR).equalsIgnoreCase(text))
	   		            	values.add(eElement.getAttribute(UConstants.DOCUMENT_NAME_ATTR));
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
