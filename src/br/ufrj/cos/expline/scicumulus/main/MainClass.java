package br.ufrj.cos.expline.scicumulus.main;

import br.ufrj.cos.expline.scicumulus.convertion.Convertion_writer;

public class MainClass {
	
	public static void main(String[] args) {
		
		Convertion_writer cw = new Convertion_writer();
		
		cw.scicumulusMainNodeCreation();
		cw.scicumulusConceptualWorkflowCreation();
		
		cw.insertActivity("act1", "MAP");
		cw.insertActivity("act2", "MAP");
		
		cw.insertInputRelation("IMod1Act1", null, "act1");
		cw.insertOutputRelation("OMod1Act1", "act1");
		
		cw.insertField("ID", "float", "IMod1Act1" , "OMod1Act1", "act1");
		cw.insertField("T1", "float", "IMod1Act1" , null, "act1");
		cw.insertField("T2", "float", "IMod1Act1" , "OMod1Act1", "act1");
		
		cw.insertInputRelation("IMod1Act2", "act1", "act2");
		cw.insertOutputRelation("OMod1Act2", "act2");
		
		cw.insertField("ID", "float", "IMod1Act2", "OMod1Act2", "act2");
		cw.insertField("T2", "float", "IMod1Act2", "OMod1Act2", "act2");
		
		
		cw.saveDocumentToDisk();
	}
	

}
