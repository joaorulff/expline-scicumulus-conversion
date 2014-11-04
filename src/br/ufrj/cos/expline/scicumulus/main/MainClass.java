package br.ufrj.cos.expline.scicumulus.main;

import br.ufrj.cos.expline.scicumulus.convertion.Convertion_writer;

public class MainClass {
	
	public static void main(String[] args) {
		Convertion_writer cw = new Convertion_writer();
		cw.scicumulusMainNodeCreation();
		//cw.scicumulusConceptualWorkflowCreation();
		cw.saveDocumentToDisk();
	}
	

}
