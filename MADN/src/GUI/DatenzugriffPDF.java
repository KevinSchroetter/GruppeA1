package GUI;

import Spiel.iDatenzugriff;

import java.awt.Graphics2D;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;








import javax.swing.JPanel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;


public class DatenzugriffPDF implements iDatenzugriff {

	@Override
	public Object openFile(String path, int mode) {
		//Modus obsolet - PDF speichert nur
		if(path == null){
			System.out.println("Fehler beim Speichern: Pfad ungültig!");
		}
		try {
			FileOutputStream fos = new FileOutputStream(path);
			return fos;
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void spielSpeichern(Object saveme, Object stream) {

		
		if(!(saveme instanceof JPanel)) throw new IllegalArgumentException("Panel fehlerhaft");
		if(!(stream instanceof FileOutputStream)) throw new IllegalArgumentException("Datenstrom ungültig");
		
		FileOutputStream fos = (FileOutputStream) stream;
		Document doc = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(doc,fos);
			doc.open();
			doc.add(new Paragraph("MADN - Gore Edition - pdf Save"));
			PdfContentByte contentByte = writer.getDirectContent();
			PdfTemplate template = contentByte.createTemplate(750,750);
			Graphics2D g2 = template.createGraphics(750,750);
			JPanel buf = (JPanel) saveme;
			buf.print(g2);
			g2.dispose();
			contentByte.addTemplate(template,0,300);
			
			
		} catch (DocumentException e) {

			e.printStackTrace();
		}
		
		
		finally{
			if(doc.isOpen()){
				doc.close();
			}
		}
		

		

	}

	@Override
	public Object spielLaden(Object stream) {

		//Obsolet bei .pdf!
		return null;
	}

	@Override
	public void closeFile(Object o) {
		if(!(o instanceof FileOutputStream)) throw new IllegalArgumentException("Strom ungültig");
		else try{
			FileOutputStream fos = (FileOutputStream)o;
			fos.close();
		} catch(Exception e){
			e.printStackTrace();
		}


	}

}
