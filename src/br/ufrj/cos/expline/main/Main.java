package br.ufrj.cos.expline.main;

import br.ufrj.cos.expline.scicumulus.convertion.Convertion_reader;
import br.ufrj.cos.expline.scicumulus.convertion.Convertion_writer;
import br.ufrj.cos.expline.scicumulus.convertion.IWriter;

public class Main 
{
	public static void main(String [] args)
	{
		IWriter writer = new Convertion_writer();
		//new Convertion_reader(writer);
	}
}
