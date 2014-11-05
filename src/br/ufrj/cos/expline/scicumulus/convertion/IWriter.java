package br.ufrj.cos.expline.scicumulus.convertion;

import org.w3c.dom.Element;

public interface IWriter 
{
	public void insertActivity( Element parent, Element node );
	public void insertRelation( Element parent, Element node );
	public void insertField( Element parent, Element node );
		
}
