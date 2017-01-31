package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ui.AddDocuments;
import ui.AddFamily;

public class AddDocumentHandler implements ActionListener
{

	
	private AddDocuments addDocument;
	public AddDocumentHandler(AddFamily addFamily, AddDocuments addDocument)
	{
		this.setAddDocument(addDocument);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		
	}
	public AddDocuments getAddDocument() {
		return addDocument;
	}
	public void setAddDocument(AddDocuments addDocument) {
		this.addDocument = addDocument;
	}
	
	
}
