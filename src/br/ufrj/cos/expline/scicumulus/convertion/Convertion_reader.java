package br.ufrj.cos.expline.scicumulus.convertion;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class Convertion_reader {
	private final IWriter writer;
	private Document document;
	public Convertion_reader()
	{
		writer = null;
	}
	
	public Convertion_reader(IWriter writer)
	{
		this.writer = writer;
		initConvertion();
	}
	
	private void initConvertion()
	{
		try{
			File explineFile = new File("src/othersource/AbstractWorkflow-ScicumulusExample.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			document = dBuilder.parse(explineFile);
			startReading(document);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	private void startReading(Document document)
	{
		Element root = document.getDocumentElement();
		NodeList childrenList = root.getChildNodes();
		
		for(int i = 0;i<childrenList.getLength();i++)
		{
			if(!childrenList.item(i).getNodeName().equals("#text"))
			{
				System.out.println(childrenList.item(i).getNodeName());
			}
		}
		
		/*
		 * Catching Activities 
		 */
		NodeList rootChildrenActivity = root.getElementsByTagName("Activity");
				
		for(int i = 0 ; i < rootChildrenActivity.getLength();i++)
		{
			Element auxElem = (Element) rootChildrenActivity.item(i);
			auxElem.getElementsByTagName("Ports");
		}
		/*
		 * Catching Edges
		 */
		NodeList rootChildrenEdge = root.getElementsByTagName("Edge");
		
		for(int i = 0; i < rootChildrenEdge.getLength();i++){
			Element auxElem = (Element)rootChildrenEdge.item(i);
		}
		
	}
	

}
