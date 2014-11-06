package br.ufrj.cos.expline.scicumulus.convertion;

import org.w3c.dom.Element;

public interface IWriter 
{
	public void insertActivity( String tag,String type );
	public void insertInputRelation(String name, String dependency, String activityTag);
	public void insertOutputRelation(String name, String activityTag);
	public void insertField( String name, String type, String input, String output, String activityTag);
		
}
