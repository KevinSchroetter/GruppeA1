package Servlets;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Basisklassen.KI_Aggressiv;
import Basisklassen.KI_Defensiv;
import Basisklassen.Spieler;
import Hilfsklassen.FigurenWrapper;
import Hilfsklassen.SpielXMLWrapper;
import Hilfsklassen.SpielerWrapper;
import Spiel.DatenzugriffCSV;
import Spiel.DatenzugriffSerialisiert;
import Spiel.DatenzugriffXML;
import Spiel.SpielBean;
import Spiel.iDatenzugriff;

/**
 * Servlet implementation class speichernServlet
 */
@WebServlet("/speichernServlet")
public class speichernServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public speichernServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// PrintWriter out = response.getWriter();
		// out.println("<html><head></head><body>");
		// out.println("<h1>Ich bin das speichernServlet</h1>");
		// out.println("</body></html>");
		// out.close();

		iDatenzugriff iD = null;
		HttpSession sess = request.getSession(true);
		SpielBean saveme = null;
		String zugriffsTyp = request.getParameter("Zugriffstyp");
		// String dateiName = request.getParameter("dateiname");

		saveme = (SpielBean) sess.getServletContext().getAttribute("game");
		if (zugriffsTyp.equals("SER")) {
			try {
				iD = new DatenzugriffSerialisiert();
				FileOutputStream fos = (FileOutputStream) iD.openFile("D:\\"
						+ request.getParameter("path") + ".ser", 2);
				iD.spielSpeichern(saveme, fos);
				this.getServletContext().setAttribute("erfolg", "ja");
				response.sendRedirect("speichernErfolg.jsp");
			} catch (Exception e) {
				this.getServletContext().setAttribute("erfolg", null);
				response.sendRedirect("speichernFail.jsp");
			} finally {
				System.out.println("Speichern ser beendet");
			}

		} else if (zugriffsTyp.equals("CSV")) {
			try {
				System.out.println("Beginne Speichern mit CSV");
				iD = new DatenzugriffCSV();
				PrintWriter bW = (PrintWriter) iD.openFile("C:\\"
						+ request.getParameter("path") + ".csv", 2);
				iD.spielSpeichern(saveme, bW);
				this.getServletContext().setAttribute("erfolg", "ja");
				System.out.println("Speichern erfolgreich!");
				response.sendRedirect("speichernErfolg.jsp");
			} catch (Exception e) {
				System.out.println("Fehler beim Speichern!");
				e.printStackTrace();
				this.getServletContext().setAttribute("erfolg", null);
				response.sendRedirect("speichernFail.jsp");
			} finally {
				iD.closeFile("C:\\Spiel.csv");
				System.out.println("Speichern CSV beendet.");
			}
		}

		else if (zugriffsTyp.equals("XML")) {
			try {

				iDatenzugriff dXML = new DatenzugriffXML();
				FileWriter fW = (FileWriter) dXML.openFile(
						"D:\\" + request.getParameter("path") + ".xml", 1);

				SpielXMLWrapper sXML = new SpielXMLWrapper();
				ArrayList<SpielerWrapper> wrapperListe = new ArrayList<SpielerWrapper>();
				ArrayList<FigurenWrapper> figurenListe = new ArrayList<FigurenWrapper>();

				SpielBean speicherMich = (SpielBean) sess.getServletContext()
						.getAttribute("game");

				for (int i = 0; i < 4; i++) {
					if (speicherMich.getSpieler()[i] != null) {
						SpielerWrapper sW = new SpielerWrapper();
						Spieler buf = speicherMich.getSpieler()[i];
						sW.setName(buf.getName());
						sW.setFarbe(buf.getFarbe());
						sW.setIstAmZug(buf.getAmZug());
						sW.setNummer(i + 1);
						if (buf.getBedienung() == null) {
							sW.setBedienung(null);
						} else if (buf.getBedienung() instanceof KI_Aggressiv) {
							sW.setBedienung("aggressiv");
						} else if (buf.getBedienung() instanceof KI_Defensiv) {
							sW.setBedienung("defensiv");
						}
						wrapperListe.add(sW);
						sW = null;
					}
				}

				for (int i = 0; i < 4; i++) {
					if (speicherMich.getSpieler()[i] == null)
						continue;
					System.out.println(speicherMich.getSpieler()[i]);
					for (int j = 0; j < 4; j++) {
						if (speicherMich.getSpieler()[i].alleFiguren()[j] != null) {
							FigurenWrapper fw = new FigurenWrapper();
							fw.setFarbe(speicherMich.getSpieler()[i].getFarbe());
							fw.setFeldID(speicherMich.getSpieler()[i]
									.alleFiguren()[j].getMeinFeld().getID());
							fw.setSchritteGelaufen(speicherMich.getSpieler()[i]
									.alleFiguren()[j].getFelderGelaufen());
							fw.setName(fw.getFarbe().toString() + " " + j);
							fw.setGespawnt(speicherMich.getSpieler()[i]
									.alleFiguren()[j].getIstGespawnt());
							fw.setEndposition(speicherMich.getSpieler()[i]
									.alleFiguren()[j].getBinIchAufEndpostion());
							figurenListe.add(fw);
						}

					}
				}

				for (int i = 0; i < figurenListe.size(); i++) {
					System.out.println(figurenListe.get(i));
				}

				for (Object o : wrapperListe.toArray()) {
					SpielerWrapper printme = (SpielerWrapper) o;
					System.out.println(printme);
				}

				sXML.setFiguren(figurenListe);
				sXML.setSpieler(wrapperListe);

				dXML.spielSpeichern(sXML, fW);
				dXML.closeFile(fW);
				
				response.sendRedirect("speichernErfolg.jsp");
			} catch (Exception e) {
				e.printStackTrace();
				this.getServletContext().setAttribute("erfolg", null);
				response.sendRedirect("speichernFail.jsp");
			}

		} else if (zugriffsTyp.equals("PDF")) {

		}

	}
}
