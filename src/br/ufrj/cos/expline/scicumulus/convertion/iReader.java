package br.ufrj.cos.expline.scicumulus.convertion;

import javax.xml.soap.Node;

public interface iReader 
{
	public void insertActivity( Node parent, Node node );
	public void insertRelation( Node parent, Node node );
	public void insertField( Node parent, Node node );
		
}
