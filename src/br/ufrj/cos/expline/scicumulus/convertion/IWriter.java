package br.ufrj.cos.expline.scicumulus.convertion;

import org.w3c.dom.Element;

public interface IWriter 
{
	public void insertActivity( String tag,String type );
	public void insertRelation( String reltype, String name );
	public void insertField( String name, String type, String input, String output );
		
}
