package br.ufrj.cos.expline.scicumulus.convertion;

import java.io.File;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class Convertion_writer implements IWriter{

	
	
	Document scicumulusXML;
	Element root;
	
	
	public Convertion_writer(){
		
		try{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		this.scicumulusXML = builder.newDocument();
		}catch(Exception e){
			System.out.println("Error while creating the XML document");
		}
	}
	
	
	public void scicumulusMainNodeCreation(){
		Element root = this.scicumulusXML.createElement("SciCumulus");
		this.scicumulusXML.appendChild(root);
		this.root = root;
	}
	
	public void scicumulusConceptualWorkflowCreation(){
		
		Element conceptualWorkflow = this.scicumulusXML.createElement("conceptualWorkflow");
		
		Attr tag = this.scicumulusXML.createAttribute("tag");
		tag.setValue("workflow-1");
		conceptualWorkflow.setAttributeNode(tag);
		
		Attr description = this.scicumulusXML.createAttribute("description");
		description.setValue("");
		conceptualWorkflow.setAttributeNode(description);
		
		
		root.appendChild(conceptualWorkflow);
	}
	
	
	public void saveDocumentToDisk(){
		
		try{
		TransformerFactory transformerfactory = TransformerFactory.newInstance();
		Transformer transformer = transformerfactory.newTransformer();
		DOMSource documentSource = new DOMSource(this.scicumulusXML);
		StreamResult result = new StreamResult(new File("SciCumulus-wp.xml"));
		transformer.transform(documentSource, result);
		System.out.println("File Saved");
		}catch(Exception e){
			System.out.println("Error while saving the XML file");
		}
	}



	public void insertActivity(String tag, String type) {
		
		Scanner entry = new Scanner(System.in);
		
		
		Element activity = this.scicumulusXML.createElement("activity");
		
		Attr sciCumulusTag = this.scicumulusXML.createAttribute("tag");
		sciCumulusTag.setValue(tag);
		
		Attr sciCumulusDescription = this.scicumulusXML.createAttribute("description");
		sciCumulusDescription.setValue("");
		
		Attr sciCumulusType = this.scicumulusXML.createAttribute("type");
		sciCumulusType.setValue(type);
		
		Attr sciCumulusActivation = this.scicumulusXML.createAttribute("activation");
		System.out.println("Activation:");
		sciCumulusActivation.setValue(entry.nextLine());
		
		
		activity.setAttributeNode(sciCumulusTag);
		activity.setAttributeNode(sciCumulusType);
		activity.setAttributeNode(sciCumulusDescription);
		activity.setAttributeNode(sciCumulusActivation);
		
		appendActivity(activity);
		
	}
	
	private void appendActivity(Element activity){
		
		NodeList sciCumulusChildren = this.root.getChildNodes();
		Node currentChild = null;
		
		for (int i = 0; i < sciCumulusChildren.getLength(); i++) {
			currentChild = sciCumulusChildren.item(i);
			
			if (currentChild.getNodeName().equals("conceptualWorkflow")) {
				break;
			}
		}
		
		currentChild.appendChild(activity);
	}


	@Override
	public void insertRelation(String reltype, String name) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void insertField(String name, String type, String input,
			String output) {
		// TODO Auto-generated method stub
		
	}

}
	
