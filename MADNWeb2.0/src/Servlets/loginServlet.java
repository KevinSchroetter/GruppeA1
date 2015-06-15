package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Spiel.SpielBean;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess=request.getSession(true);
		sess.getServletContext().setAttribute("check","checked");
		SpielBean game = null;
		if(request.getParameter("maxSpielerAnzahl")!=null){
			String maxSpieler = request.getParameter("maxSpielerAnzahl");
			sess.getServletContext().setAttribute("maxSpieler",maxSpieler);
		}
		if(sess.getServletContext().getAttribute("game")==null){
			sess.getServletContext().setAttribute("game", new SpielBean());
			game = (SpielBean) sess.getServletContext().getAttribute("game");	
		}
		else{
			game = (SpielBean) sess.getServletContext().getAttribute("game");
		}
		int checkSpieler = Integer.parseInt(sess.getServletContext().getAttribute("maxSpieler").toString());
		String name = request.getParameter("name");
		String farbe = request.getParameter("farbe");
		String verhalten = request.getParameter("verhalten");
		int verhaltenID = 0;
		if(verhalten.equals("ki_aggressiv")){
			verhaltenID = 1;
		}
		else if(verhalten.equals("ki_defensiv")){
			verhaltenID = 2;
		}		
		int farbID = 0;
		if (farbe.equals("ROT")){
			farbID = 1;
		}
		else if(farbe.equals("BLAU")){
			farbID = 2;
		}
		else if(farbe.equals("GRUEN")){
			farbID = 3;
		}
		else if (farbe.equals("GELB")){
			farbID = 4;
		}
		try{
			int anzahl = (int) sess.getServletContext().getAttribute("anzahlSpieler");
			if(verhaltenID == 1 || verhaltenID==2){
				game.neuerSpieler(name, farbID, verhaltenID);
				sess.getServletContext().setAttribute("anzahlSpieler", ++anzahl);
				sess.getServletContext().setAttribute("s"+(anzahl-1)+"Farbe", farbe);
				sess.getServletContext().setAttribute("exist", "ja");
				sess.getServletContext().setAttribute("lastKI", "ja");
				System.out.println("Spieler "+(anzahl-1) +" angelegt KI");
				System.out.println("");
			}
			else{
				game.neuerSpieler(name, farbID, verhaltenID);
				sess.setAttribute("name", name);
				sess.setAttribute("farbe", farbe);
				sess.setAttribute("spielerNummer", anzahl);
				sess.getServletContext().setAttribute("anzahlSpieler", ++anzahl);
				sess.getServletContext().setAttribute("s"+(anzahl-1)+"Farbe", farbe);
				sess.getServletContext().setAttribute("exist", "ja");
				sess.getServletContext().setAttribute("s"+(anzahl-1)+"Session",sess.getId());
				sess.getServletContext().removeAttribute("sessID");
				sess.getServletContext().removeAttribute("lastKI");
				System.out.println("Spieler "+(anzahl-1) +" angelegt");
				System.out.println("");
			}
			if(anzahl > checkSpieler){
				response.sendRedirect("LoginDone.jsp");
				sess.getServletContext().setAttribute("sessID", "DONE");
				game.starteSpiel();
				sess.getServletContext().setAttribute("gestartet","ja");
				sess.getServletContext().setAttribute("amZug", game.getIstAmZug().getFarbe());	
			}
			else{		
				sess.getServletContext().setAttribute("sessId", sess.getId());
				response.sendRedirect("Login.jsp");
			}
		}
		catch(Exception e){
			sess.getServletContext().setAttribute("error", e);
			response.sendRedirect("addPlayerError.jsp");
		}
	}
}
