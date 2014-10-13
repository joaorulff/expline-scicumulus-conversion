package br.ufrj.cos.expline.instantiation.scicumulus;


import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

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

public class AbstractWorkflowModification {

	public void xmlHandle (){

		try{


			File xmlFile = new File("newDocument.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			Document newXmlDocument = dBuilder.parse(xmlFile);
			
			newXmlDocument = copyActivitiesToAbstractWorkflow(newXmlDocument);
			
			TransformerFactory transformerfactory = TransformerFactory.newInstance();
			Transformer transformer = transformerfactory.newTransformer();
			DOMSource documentSource = new DOMSource(newXmlDocument);
			StreamResult result = new StreamResult(new File("newXmlDocument.xml"));
			transformer.transform(documentSource, result);
			System.out.println("File Saved");

			System.out.println();
			System.out.println();
			readTree();
			
		}catch (Exception E){
			System.err.println("Erro");
		}
	}
	
	private void readTree() throws Exception
	{
		File xmlFile = new File("newXmlDocument.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		Document originalXML = dBuilder.parse(xmlFile);	
		
		Element root = originalXML.getDocumentElement();
		
		NodeList children = root.getChildNodes();
		
		for(int i = 0 ; i < children.getLength(); i++)
		{
			Node aux = children.item(i);
			System.out.println(aux.getNodeName());
			if(aux.hasChildNodes())
			{
				NodeList children1 = aux.getChildNodes();
				for(int k = 0; k < children1.getLength();k++)
				{
					Node temp = children1.item(k);
					System.out.println("\t"+temp.getNodeName());
					NamedNodeMap nnm = temp.getAttributes();
					for(int j = 0 ; j < nnm.getLength(); j++)
					{
						System.out.println("\t\t"+nnm.item(j).getNodeName() + " => " +nnm.item(j).getNodeValue() );
					}
				}
			}
			else{
				NamedNodeMap nnm = aux.getAttributes();
				for(int j = 0 ; j < nnm.getLength(); j++)
				{
					String value = (nnm.item(j).getNodeValue().length() == 0)?"Empty":nnm.item(j).getNodeValue();
					System.out.println("\t"+nnm.item(j).getNodeName() + " => " + value );
				}
			}
			System.out.println();
		}
	}
	
public Document copyActivitiesToAbstractWorkflow(Document originalXML){
		
		Element root = originalXML.getDocumentElement();
		
		ArrayList<Node> array = new ArrayList<>();
		ArrayList<Node> removedList = new ArrayList<>();
		
		NodeList activities = root.getElementsByTagName("Activities");
		
		for(int i = 0; i < activities.getLength();i++)
		{
			Node aux = activities.item(i);
			for(int j = 0;j < aux.getChildNodes().getLength();j++)
			{
				array.add(aux.getChildNodes().item(j));
			}
			removedList.add(aux);
		}
		
		Iterator<Node> it = removedList.iterator(); 
		while(it.hasNext())
		{
			Node aux = it.next();
			root.removeChild(aux);
		}
		
		it = array.iterator();
		System.out.println(array.size());
		
		while(it.hasNext())
		{
			Node aux = it.next();
			Attr novo = originalXML.createAttribute("Description");
			novo.setValue("");
			Element elem = (Element)aux;
			elem.setAttributeNode(novo);
			root.appendChild(elem);
		}
		
		return originalXML;
		
	}
}
