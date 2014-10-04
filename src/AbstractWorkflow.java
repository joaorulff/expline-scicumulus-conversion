import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class AbstractWorkflow {
	
public void createDocument (){
		
		try{
		
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
		
		Document newDocument = documentBuilder.newDocument();
		
		//root
		Element root = newDocument.createElement("AbstractWorkflow");
		newDocument.appendChild(root);
		
		Element activities = newDocument.createElement("Activities");
		root.appendChild(activities);
		
		Element atvA = newDocument.createElement("atvA");
		
		//Create attributes of node atvA
		Attr idAtva = newDocument.createAttribute("ID");
		idAtva.setValue("1");
		atvA.setAttributeNode(idAtva);
		
		Attr nameAtvA = newDocument.createAttribute("Name");
		nameAtvA.setValue("atvA");
		atvA.setAttributeNode(nameAtvA);
		
		activities.appendChild(atvA);
		
		
		Element atvB = newDocument.createElement("atvB");
		
		//Create attributes of node atvB
		Attr idAtvB = newDocument.createAttribute("ID");
		idAtvB.setValue("2");
		atvB.setAttributeNode(idAtvB);
		
		Attr nameAtvB = newDocument.createAttribute("Name");
		nameAtvB.setValue("atvB");
		atvB.setAttributeNode(nameAtvB);
		
		activities.appendChild(atvB);
		
		
		Element edges = newDocument.createElement("Edges");
		
		Element edge = newDocument.createElement("Edge");
		
		//Create attributes of edge
		Attr id = newDocument.createAttribute("ID");
		id.setNodeValue("3");
		edge.setAttributeNode(id);
		
		Attr source = newDocument.createAttribute("Source");
		source.setValue("1");
		edge.setAttributeNode(source);
		
		Attr target = newDocument.createAttribute("Target");
		target.setValue("2");
		edge.setAttributeNode(target);
		
		root.appendChild(edges);
		
		edges.appendChild(edge);
		
		
		
		
		
		
		TransformerFactory transformerfactory = TransformerFactory.newInstance();
		Transformer transformer = transformerfactory.newTransformer();
		DOMSource documentSource = new DOMSource(newDocument);
		StreamResult result = new StreamResult(new File("newDocument.xml"));
		transformer.transform(documentSource, result);
		System.out.println("File Saved");
		
		
		
		
			
		
		}catch (Exception e){
			System.out.println("Erro");
		}
	}


}
