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
import org.w3c.dom.NamedNodeMap;
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
		
		Attr sciCumulusActivityTag = this.scicumulusXML.createAttribute("tag");
		sciCumulusActivityTag.setValue(tag);
		
		Attr sciCumulusActivityDescription = this.scicumulusXML.createAttribute("description");
		sciCumulusActivityDescription.setValue("");
		
		Attr sciCumulusActivityType = this.scicumulusXML.createAttribute("type");
		sciCumulusActivityType.setValue(type);
		
		Attr sciCumulusActivityActivation = this.scicumulusXML.createAttribute("activation");
		System.out.println("Activation:");
		sciCumulusActivityActivation.setValue(entry.nextLine());
		
		
		activity.setAttributeNode(sciCumulusActivityTag);
		activity.setAttributeNode(sciCumulusActivityType);
		activity.setAttributeNode(sciCumulusActivityDescription);
		activity.setAttributeNode(sciCumulusActivityActivation);
		
		appendActivity(activity);
		
	}
	
	private void appendActivity(Element activity){
		
		NodeList sciCumulusChildren = this.root.getElementsByTagName("conceptualWorkflow");
		Node currentChild = sciCumulusChildren.item(0);
		
		currentChild.appendChild(activity);
	}


	@Override
	public void insertInputRelation(String name, String dependency, String activityTag) {
		Element sciCumulusRelation = this.scicumulusXML.createElement("relation");
		
		Attr sciCumulusRelationReltype = this.scicumulusXML.createAttribute("reltype");
		sciCumulusRelationReltype.setNodeValue("Input");
		
		Attr sciCumulusRelationName = this.scicumulusXML.createAttribute("name");
		sciCumulusRelationName.setNodeValue(name);
		
		if (dependency != null) {
			Attr sciCumulusRelationDependency = this.scicumulusXML.createAttribute("dependency");
			sciCumulusRelationDependency.setNodeValue(dependency);
		}
		
		appendRelation(sciCumulusRelation, activityTag);
		
	}
	
	private void appendRelation (Element relation, String activityTag){
		
		System.out.println("Entered in AppendRelation");
		
		NodeList sciCumulusChildren = this.root.getElementsByTagName("conceptualWorkflow");
		
		Element currentChild = (Element)sciCumulusChildren.item(0);
		
		NodeList conceptualWorkflowChildren = currentChild.getElementsByTagName("activity");
		
		for (int i = 0; i < conceptualWorkflowChildren.getLength(); i++) {
			
			Element currentActivity = (Element)conceptualWorkflowChildren.item(i);
			String currentActivityTag = currentActivity.getAttribute("tag");
			
			if (currentActivityTag.equals(activityTag)) {
				currentActivity.appendChild(relation);
			}

		}
		
		
		
		
	}
	
	public void insertOutputRelation(String name) {
		//TODO
	}


	@Override
	public void insertField(String name, String type, String input,
			String output) {
		// TODO Auto-generated method stub
		
	}

}
	
