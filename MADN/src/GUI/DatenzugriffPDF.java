package GUI;

import Spiel.iDatenzugriff;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;












import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Image;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDocument;
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

		
		if(!(saveme instanceof GUI)) throw new IllegalArgumentException("Frame fehlerhaft");
		if(!(stream instanceof FileOutputStream)) throw new IllegalArgumentException("Datenstrom ungültig");
		
		FileOutputStream fos = (FileOutputStream) stream;
		GUI g = (GUI) saveme;
		JFrame buf = g.getFrame();
		
		
		try {
			BufferedImage img = new BufferedImage(1280,1080,BufferedImage.TYPE_INT_RGB);
			buf.paint(img.getGraphics());
			Document doc = new Document();
			PdfWriter pdfW = PdfWriter.getInstance(doc,fos);
			doc.open();
			PdfContentByte cb = pdfW.getDirectContent();
			PdfTemplate pdfT =cb.createTemplate(PageSize.A4.getWidth(), PageSize.A4.getHeight());
			Image imgPDF = Image.getInstance(cb,img,1);
			imgPDF.scaleToFit(new Rectangle(PageSize.A4.getWidth(),PageSize.A4.getHeight()));
			doc.add(new Paragraph("Spielstand MADN Gore Edition"));		
			imgPDF.setAbsolutePosition((float)0.0, (float)3.0);
			pdfT.addImage(imgPDF, false);
			cb.addTemplate(pdfT,0,3);
			doc.close();
			
			
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
