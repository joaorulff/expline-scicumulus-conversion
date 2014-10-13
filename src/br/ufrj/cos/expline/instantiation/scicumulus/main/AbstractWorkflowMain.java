package br.ufrj.cos.expline.instantiation.scicumulus.main;

import br.ufrj.cos.expline.instantiation.scicumulus.AbstractWorkflowModification;

public class AbstractWorkflowMain {
	
	public static void main (String [] args){
		
		/*AbstractWorkflow aw = new AbstractWorkflow();
		aw.createDocument();*/
		
		AbstractWorkflowModification awm = new AbstractWorkflowModification();
		awm.xmlHandle();
	}

}
