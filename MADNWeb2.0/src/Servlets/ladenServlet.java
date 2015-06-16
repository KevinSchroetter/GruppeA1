package Servlets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Basisklassen.Spielbrett;
import Basisklassen.Spieler;
import Basisklassen.Spielfigur;
import Hilfsklassen.FigurenWrapper;
import Hilfsklassen.SpielXMLWrapper;
import Hilfsklassen.SpielerWrapper;
import Spiel.DatenzugriffCSV;
import Spiel.DatenzugriffSerialisiert;
import Spiel.DatenzugriffXML;
import Spiel.SpielBean;
import Spiel.iDatenzugriff;

/**
 * Servlet implementation class ladenServlet
 */
@WebServlet("/ladenServlet")
public class ladenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ladenServlet() {
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

		iDatenzugriff iD = null;

		HttpSession sess = request.getSession(true);
		SpielBean loadme = null;
		String zugriffsTyp = request.getParameter("ladenTyp");
		// String dateiName = request.getParameter("dateiname");

		if (zugriffsTyp.equals("SER")) {

			FileInputStream fis = null;
			iD = new DatenzugriffSerialisiert();
			fis = (FileInputStream) iD.openFile("C:\\"+ request.getParameter("path")+".ser", 1);

			try {
				loadme = (SpielBean) iD.spielLaden(fis);
				sess.getServletContext().setAttribute("game", loadme);
				fis.close();
				if (loadme != null) {
					this.getServletContext().setAttribute("game", loadme);
					if(sess.getAttribute("zug1")!=null)
						sess.removeAttribute("zug1");
					if(sess.getAttribute("zug2")!=null)
						sess.removeAttribute("zug2");
					if(sess.getAttribute("zug3")!=null)
						sess.removeAttribute("zug3");
					if(sess.getAttribute("zug4")!=null)
						sess.removeAttribute("zug4");
					if (this.getServletContext().getAttribute("ROTS1")!=null){
						this.getServletContext().removeAttribute("ROTS1");
					}
					if (this.getServletContext().getAttribute("ROTS2")!=null){
						this.getServletContext().removeAttribute("ROTS2");
					}
					if (this.getServletContext().getAttribute("ROTS3")!=null){
						this.getServletContext().removeAttribute("ROTS3");
					}
					if (this.getServletContext().getAttribute("ROTS4")!=null){
						this.getServletContext().removeAttribute("ROTS4");
					}
					if (this.getServletContext().getAttribute("ROTE1")!=null){
						this.getServletContext().removeAttribute("ROTE1");
					}
					if (this.getServletContext().getAttribute("ROTE2")!=null){
						this.getServletContext().removeAttribute("ROTE2");
					}
					if (this.getServletContext().getAttribute("ROTE3")!=null){
						this.getServletContext().removeAttribute("ROTE3");
					}
					if (this.getServletContext().getAttribute("ROTE4")!=null){
						this.getServletContext().removeAttribute("ROTE4");
					}
					if (this.getServletContext().getAttribute("BLAUS1")!=null){
						this.getServletContext().removeAttribute("BLAUS1");
					}
					if (this.getServletContext().getAttribute("BLAUS2")!=null){
						this.getServletContext().removeAttribute("BLAUS2");
					}
					if (this.getServletContext().getAttribute("BLAUS3")!=null){
						this.getServletContext().removeAttribute("BLAUS3");
					}
					if (this.getServletContext().getAttribute("BLAUS4")!=null){
						this.getServletContext().removeAttribute("BLAUS4");
					}
					if (this.getServletContext().getAttribute("BLAUE1")!=null){
						this.getServletContext().removeAttribute("BLAUE1");
					}
					if (this.getServletContext().getAttribute("BLAUE2")!=null){
						this.getServletContext().removeAttribute("BLAUE2");
					}
					if (this.getServletContext().getAttribute("BLAUE3")!=null){
						this.getServletContext().removeAttribute("BLAUE3");
					}
					if (this.getServletContext().getAttribute("BLAUE4")!=null){
						this.getServletContext().removeAttribute("BLAUE4");
					}
					if (this.getServletContext().getAttribute("GRUENS1")!=null){
						this.getServletContext().removeAttribute("GRUENS1");
					}
					if (this.getServletContext().getAttribute("GRUENS2")!=null){
						this.getServletContext().removeAttribute("GRUENS2");
					}
					if (this.getServletContext().getAttribute("GRUENS3")!=null){
						this.getServletContext().removeAttribute("GRUENS3");
					}
					if (this.getServletContext().getAttribute("GRUENS4")!=null){
						this.getServletContext().removeAttribute("GRUENS4");
					}
					if (this.getServletContext().getAttribute("GRUENE1")!=null){
						this.getServletContext().removeAttribute("GRUENE1");
					}
					if (this.getServletContext().getAttribute("GRUENE2")!=null){
						this.getServletContext().removeAttribute("GRUENE2");
					}
					if (this.getServletContext().getAttribute("GRUENE3")!=null){
						this.getServletContext().removeAttribute("GRUENE3");
					}
					if (this.getServletContext().getAttribute("GRUENE4")!=null){
						this.getServletContext().removeAttribute("GRUENE4");
					}
					if (this.getServletContext().getAttribute("GELBS1")!=null){
						this.getServletContext().removeAttribute("GELBS1");
					}
					if (this.getServletContext().getAttribute("GELBS2")!=null){
						this.getServletContext().removeAttribute("GELBS2");
					}
					if (this.getServletContext().getAttribute("GELBS3")!=null){
						this.getServletContext().removeAttribute("GELBS3");
					}
					if (this.getServletContext().getAttribute("GELBS4")!=null){
						this.getServletContext().removeAttribute("GELBS4");
					}
					if (this.getServletContext().getAttribute("GELBE1")!=null){
						this.getServletContext().removeAttribute("GELBE1");
					}
					if (this.getServletContext().getAttribute("GELBE2")!=null){
						this.getServletContext().removeAttribute("GELBE2");
					}
					if (this.getServletContext().getAttribute("GELBE3")!=null){
						this.getServletContext().removeAttribute("GELBE3");
					}
					if (this.getServletContext().getAttribute("GELBE4")!=null){
						this.getServletContext().removeAttribute("GELBE4");
					}
					for (int i = 1;i<=40;i++){
						if(this.getServletContext().getAttribute(""+i+"")!=null){
							this.getServletContext().removeAttribute(""+i+"");
						}
					}
					ArrayList<String> figuren = loadme.alleFigs();
					figuren = loadme.alleFigs();
					String[] split = new String[2];
					for(String s:figuren){
						split=s.split("-");
						sess.getServletContext().setAttribute(split[0], split[1].substring(0, 2)+split[2]);
					}
					if (loadme.getIstAmZug().getBedienung()==null){
						sess.getServletContext().setAttribute("amZug", loadme.getIstAmZug().getFarbe().toString());	
					}
					else
						sess.getServletContext().setAttribute("amZug", loadme.getIstAmZug().getFarbe().toString()+"-KI");
					response.sendRedirect("ladenWin.jsp");
				} else
					response.sendRedirect("LadenFail.jsp");
			} catch (Exception e) {
				System.out.println("Fehler beim laden (SER)");
				e.printStackTrace();

			}

		} else if (zugriffsTyp.equals("CSV")) {

			try {
				iD = new DatenzugriffCSV();
				BufferedReader bR = (BufferedReader) iD.openFile(
						"C:\\" + request.getParameter("path")+".csv", 1);
				loadme = (SpielBean) iD.spielLaden(bR);
				sess.getServletContext()
						.setAttribute("game", loadme);
				bR.close();
				if(loadme == null){
					response.sendRedirect("LadenFail.jsp");
				} else{
					this.getServletContext().setAttribute("game", loadme);
					if(sess.getAttribute("zug1")!=null)
						sess.removeAttribute("zug1");
					if(sess.getAttribute("zug2")!=null)
						sess.removeAttribute("zug2");
					if(sess.getAttribute("zug3")!=null)
						sess.removeAttribute("zug3");
					if(sess.getAttribute("zug4")!=null)
						sess.removeAttribute("zug4");
					if (this.getServletContext().getAttribute("ROTS1")!=null){
						this.getServletContext().removeAttribute("ROTS1");
					}
					if (this.getServletContext().getAttribute("ROTS2")!=null){
						this.getServletContext().removeAttribute("ROTS2");
					}
					if (this.getServletContext().getAttribute("ROTS3")!=null){
						this.getServletContext().removeAttribute("ROTS3");
					}
					if (this.getServletContext().getAttribute("ROTS4")!=null){
						this.getServletContext().removeAttribute("ROTS4");
					}
					if (this.getServletContext().getAttribute("ROTE1")!=null){
						this.getServletContext().removeAttribute("ROTE1");
					}
					if (this.getServletContext().getAttribute("ROTE2")!=null){
						this.getServletContext().removeAttribute("ROTE2");
					}
					if (this.getServletContext().getAttribute("ROTE3")!=null){
						this.getServletContext().removeAttribute("ROTE3");
					}
					if (this.getServletContext().getAttribute("ROTE4")!=null){
						this.getServletContext().removeAttribute("ROTE4");
					}
					if (this.getServletContext().getAttribute("BLAUS1")!=null){
						this.getServletContext().removeAttribute("BLAUS1");
					}
					if (this.getServletContext().getAttribute("BLAUS2")!=null){
						this.getServletContext().removeAttribute("BLAUS2");
					}
					if (this.getServletContext().getAttribute("BLAUS3")!=null){
						this.getServletContext().removeAttribute("BLAUS3");
					}
					if (this.getServletContext().getAttribute("BLAUS4")!=null){
						this.getServletContext().removeAttribute("BLAUS4");
					}
					if (this.getServletContext().getAttribute("BLAUE1")!=null){
						this.getServletContext().removeAttribute("BLAUE1");
					}
					if (this.getServletContext().getAttribute("BLAUE2")!=null){
						this.getServletContext().removeAttribute("BLAUE2");
					}
					if (this.getServletContext().getAttribute("BLAUE3")!=null){
						this.getServletContext().removeAttribute("BLAUE3");
					}
					if (this.getServletContext().getAttribute("BLAUE4")!=null){
						this.getServletContext().removeAttribute("BLAUE4");
					}
					if (this.getServletContext().getAttribute("GRUENS1")!=null){
						this.getServletContext().removeAttribute("GRUENS1");
					}
					if (this.getServletContext().getAttribute("GRUENS2")!=null){
						this.getServletContext().removeAttribute("GRUENS2");
					}
					if (this.getServletContext().getAttribute("GRUENS3")!=null){
						this.getServletContext().removeAttribute("GRUENS3");
					}
					if (this.getServletContext().getAttribute("GRUENS4")!=null){
						this.getServletContext().removeAttribute("GRUENS4");
					}
					if (this.getServletContext().getAttribute("GRUENE1")!=null){
						this.getServletContext().removeAttribute("GRUENE1");
					}
					if (this.getServletContext().getAttribute("GRUENE2")!=null){
						this.getServletContext().removeAttribute("GRUENE2");
					}
					if (this.getServletContext().getAttribute("GRUENE3")!=null){
						this.getServletContext().removeAttribute("GRUENE3");
					}
					if (this.getServletContext().getAttribute("GRUENE4")!=null){
						this.getServletContext().removeAttribute("GRUENE4");
					}
					if (this.getServletContext().getAttribute("GELBS1")!=null){
						this.getServletContext().removeAttribute("GELBS1");
					}
					if (this.getServletContext().getAttribute("GELBS2")!=null){
						this.getServletContext().removeAttribute("GELBS2");
					}
					if (this.getServletContext().getAttribute("GELBS3")!=null){
						this.getServletContext().removeAttribute("GELBS3");
					}
					if (this.getServletContext().getAttribute("GELBS4")!=null){
						this.getServletContext().removeAttribute("GELBS4");
					}
					if (this.getServletContext().getAttribute("GELBE1")!=null){
						this.getServletContext().removeAttribute("GELBE1");
					}
					if (this.getServletContext().getAttribute("GELBE2")!=null){
						this.getServletContext().removeAttribute("GELBE2");
					}
					if (this.getServletContext().getAttribute("GELBE3")!=null){
						this.getServletContext().removeAttribute("GELBE3");
					}
					if (this.getServletContext().getAttribute("GELBE4")!=null){
						this.getServletContext().removeAttribute("GELBE4");
					}
					for (int i = 1;i<=40;i++){
						if(this.getServletContext().getAttribute(""+i+"")!=null){
							this.getServletContext().removeAttribute(""+i+"");
						}
					}
					ArrayList<String> figuren = loadme.alleFigs();
					figuren = loadme.alleFigs();
					String[] split = new String[2];
					for(String s:figuren){
						split=s.split("-");
						sess.getServletContext().setAttribute(split[0], split[1].substring(0, 2)+split[2]);
					}
					if (loadme.getIstAmZug().getBedienung()==null){
						sess.getServletContext().setAttribute("amZug", loadme.getIstAmZug().getFarbe().toString());	
					}
					else
						sess.getServletContext().setAttribute("amZug", loadme.getIstAmZug().getFarbe().toString()+"-KI");
					response.sendRedirect("ladenWin.jsp");
				}
				
			} catch (Exception e) {
				response.sendRedirect("LadenFail.jsp");
				e.printStackTrace();
			}

		}

		else if (zugriffsTyp.equals("XML")) {
			try{
				
				iD = new DatenzugriffXML();
				
				SpielBean xmlFinal = null;
				SpielXMLWrapper sXML = null;

				iDatenzugriff dXML = new DatenzugriffXML();
				try {
					sXML = (SpielXMLWrapper) dXML.spielLaden(dXML.openFile(
							"C:\\test.xml", 0));

					// for (Object o : sXML.getSpieler().toArray()) {
					// SpielerWrapper sW = (SpielerWrapper) o;
					// System.out.println(sW);
					// }
					//
					// for (Object o : sXML.getFiguren().toArray()) {
					// FigurenWrapper fW = (FigurenWrapper) o;
					// System.out.println(fW);
					// }

					Spielfigur figuren[][] = new Spielfigur[4][4];
					Spieler spieler[] = new Spieler[4];
					Spielbrett spielbrett = new Spielbrett();

					for (int i = 0; i < sXML.getSpieler().toArray().length; i++) {
						SpielerWrapper sW = (SpielerWrapper) sXML.getSpieler()
								.toArray()[i];
						if (sW == null)
							continue;
						else {

							if (sW.getBedienung() == null) {
								spieler[i] = new Spieler(sW.getName(), sW.getFarbe(),
										spielbrett.getAlleStartFelderEinerFarbe(sW
												.getFarbe()),
										spielbrett.getAlleEndFelderEinerFarbe(sW
												.getFarbe()), xmlFinal);
							} else {
								spieler[i] = new Spieler(sW.getName(), sW.getFarbe(),
										spielbrett.getAlleStartFelderEinerFarbe(sW
												.getFarbe()),
										spielbrett.getAlleEndFelderEinerFarbe(sW
												.getFarbe()), sW.getBedienung(),
										xmlFinal);
							}

						}
					}

					FigurenWrapper fW = null;
					Spielfigur.deleteAnzahlFiguren();
					for (int i = 0; i < figuren.length; i++) {
						if (spieler[i] == null)
							continue;
						for (int j = 0; j < figuren[i].length; j++) {
							if (sXML.getFiguren().get(j) == null)
								continue;

							int farbID = 0;
							switch (spieler[i].getFarbe()) {
							case ROT:
								farbID = 0;
								break;
							case BLAU:
								farbID = 1;
								break;
							case GRUEN:
								farbID = 2;
								break;
							case GELB:
								farbID = 3;
								break;
							}
							fW = sXML.getFiguren().get(j);
							figuren[i][j] = new Spielfigur(farbID, spieler[i]
									.getFarbe().toString() + " " + j);
							figuren[i][j].setMeinFeld(spielbrett.getFeld(
									fW.getFeldID(), figuren[i][j].getFarbe()));

							System.out.println(figuren[i][j]);
						}
						spieler[i].figurenLaden(figuren[i]);
					}
					xmlFinal = new SpielBean();
					xmlFinal.setSpielbrett(spielbrett);
					Spieler amZug = null;
					for (int i = 0; i < spieler.length; i++) {
						if (spieler[i] == null)
							continue;
						xmlFinal.spielerLaden(spieler[i]);
						if(sXML.getSpieler().get(i).getIstAmZug()){
							amZug = spieler[i];
							spieler[i].setAmZug(true);
						}
					}
					
					
						xmlFinal.setIstBeendet(sXML.isBeendet());
						xmlFinal.setHatBegonnen(sXML.isGestartet());

					xmlFinal.setIstAmZug(amZug);
					xmlFinal.starteSpiel();
					xmlFinal.ausgabeFiguren();
					

					sess.getServletContext()
							.setAttribute("game", xmlFinal);
					
					if(xmlFinal == null){
						response.sendRedirect("LadenFail.jsp");
					} else {
						this.getServletContext().setAttribute("game", loadme);
						if(sess.getAttribute("zug1")!=null)
							sess.removeAttribute("zug1");
						if(sess.getAttribute("zug2")!=null)
							sess.removeAttribute("zug2");
						if(sess.getAttribute("zug3")!=null)
							sess.removeAttribute("zug3");
						if(sess.getAttribute("zug4")!=null)
							sess.removeAttribute("zug4");
						if (this.getServletContext().getAttribute("ROTS1")!=null){
							this.getServletContext().removeAttribute("ROTS1");
						}
						if (this.getServletContext().getAttribute("ROTS2")!=null){
							this.getServletContext().removeAttribute("ROTS2");
						}
						if (this.getServletContext().getAttribute("ROTS3")!=null){
							this.getServletContext().removeAttribute("ROTS3");
						}
						if (this.getServletContext().getAttribute("ROTS4")!=null){
							this.getServletContext().removeAttribute("ROTS4");
						}
						if (this.getServletContext().getAttribute("ROTE1")!=null){
							this.getServletContext().removeAttribute("ROTE1");
						}
						if (this.getServletContext().getAttribute("ROTE2")!=null){
							this.getServletContext().removeAttribute("ROTE2");
						}
						if (this.getServletContext().getAttribute("ROTE3")!=null){
							this.getServletContext().removeAttribute("ROTE3");
						}
						if (this.getServletContext().getAttribute("ROTE4")!=null){
							this.getServletContext().removeAttribute("ROTE4");
						}
						if (this.getServletContext().getAttribute("BLAUS1")!=null){
							this.getServletContext().removeAttribute("BLAUS1");
						}
						if (this.getServletContext().getAttribute("BLAUS2")!=null){
							this.getServletContext().removeAttribute("BLAUS2");
						}
						if (this.getServletContext().getAttribute("BLAUS3")!=null){
							this.getServletContext().removeAttribute("BLAUS3");
						}
						if (this.getServletContext().getAttribute("BLAUS4")!=null){
							this.getServletContext().removeAttribute("BLAUS4");
						}
						if (this.getServletContext().getAttribute("BLAUE1")!=null){
							this.getServletContext().removeAttribute("BLAUE1");
						}
						if (this.getServletContext().getAttribute("BLAUE2")!=null){
							this.getServletContext().removeAttribute("BLAUE2");
						}
						if (this.getServletContext().getAttribute("BLAUE3")!=null){
							this.getServletContext().removeAttribute("BLAUE3");
						}
						if (this.getServletContext().getAttribute("BLAUE4")!=null){
							this.getServletContext().removeAttribute("BLAUE4");
						}
						if (this.getServletContext().getAttribute("GRUENS1")!=null){
							this.getServletContext().removeAttribute("GRUENS1");
						}
						if (this.getServletContext().getAttribute("GRUENS2")!=null){
							this.getServletContext().removeAttribute("GRUENS2");
						}
						if (this.getServletContext().getAttribute("GRUENS3")!=null){
							this.getServletContext().removeAttribute("GRUENS3");
						}
						if (this.getServletContext().getAttribute("GRUENS4")!=null){
							this.getServletContext().removeAttribute("GRUENS4");
						}
						if (this.getServletContext().getAttribute("GRUENE1")!=null){
							this.getServletContext().removeAttribute("GRUENE1");
						}
						if (this.getServletContext().getAttribute("GRUENE2")!=null){
							this.getServletContext().removeAttribute("GRUENE2");
						}
						if (this.getServletContext().getAttribute("GRUENE3")!=null){
							this.getServletContext().removeAttribute("GRUENE3");
						}
						if (this.getServletContext().getAttribute("GRUENE4")!=null){
							this.getServletContext().removeAttribute("GRUENE4");
						}
						if (this.getServletContext().getAttribute("GELBS1")!=null){
							this.getServletContext().removeAttribute("GELBS1");
						}
						if (this.getServletContext().getAttribute("GELBS2")!=null){
							this.getServletContext().removeAttribute("GELBS2");
						}
						if (this.getServletContext().getAttribute("GELBS3")!=null){
							this.getServletContext().removeAttribute("GELBS3");
						}
						if (this.getServletContext().getAttribute("GELBS4")!=null){
							this.getServletContext().removeAttribute("GELBS4");
						}
						if (this.getServletContext().getAttribute("GELBE1")!=null){
							this.getServletContext().removeAttribute("GELBE1");
						}
						if (this.getServletContext().getAttribute("GELBE2")!=null){
							this.getServletContext().removeAttribute("GELBE2");
						}
						if (this.getServletContext().getAttribute("GELBE3")!=null){
							this.getServletContext().removeAttribute("GELBE3");
						}
						if (this.getServletContext().getAttribute("GELBE4")!=null){
							this.getServletContext().removeAttribute("GELBE4");
						}
						for (int i = 1;i<=40;i++){
							if(this.getServletContext().getAttribute(""+i+"")!=null){
								this.getServletContext().removeAttribute(""+i+"");
							}
						}
						ArrayList<String> figuren1 = loadme.alleFigs();
						figuren1 = loadme.alleFigs();
						String[] split = new String[2];
						for(String s:figuren1){
							split=s.split("-");
							sess.getServletContext().setAttribute(split[0], split[1].substring(0, 2)+split[2]);
						}
						if (loadme.getIstAmZug().getBedienung()==null){
							sess.getServletContext().setAttribute("amZug", loadme.getIstAmZug().getFarbe().toString());	
						}
						else
							sess.getServletContext().setAttribute("amZug", loadme.getIstAmZug().getFarbe().toString()+"-KI");
						response.sendRedirect("ladenWin.jsp");
					
					}


				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			catch(Exception e){
				
			}

		} else if (zugriffsTyp.equals("PDF")) {

		}
	}


}
