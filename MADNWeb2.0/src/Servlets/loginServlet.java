package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

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
        // TODO Auto-generated constructor stub
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
		SpielBean game = null;
		if(request.getParameter("maxSpielerAnzahl")!=null){
			String maxSpieler = request.getParameter("maxSpielerAnzahl");
			sess.getServletContext().setAttribute("maxSpieler",maxSpieler);
		}
		if(sess.getServletContext().getAttribute("game")==null){
			sess.getServletContext().setAttribute("game", new SpielBean());
			game = (SpielBean) sess.getServletContext().getAttribute("game");
		}
		else
			game = (SpielBean) sess.getServletContext().getAttribute("game");
		int checkSpieler = Integer.parseInt(sess.getServletContext().getAttribute("maxSpieler").toString());
		String name = request.getParameter("name");
		String farbe = request.getParameter("farbe");
		String verhalten = request.getParameter("verhalten");
		int verhaltenID = 0;
		if(verhalten.equals("ki_aggressiv"))
			verhaltenID = 1;
		else if(verhalten.equals("ki_defensiv"))
			verhaltenID = 2;
		
		int farbID = 0;
		if (farbe.equals("rot"))
			farbID = 1;
		else if(farbe.equals("blau"))
			farbID = 2;
		else if(farbe.equals("gruen"))
			farbID = 3;
		else if (farbe.equals("gelb"))
			farbID = 4;
		try{
		game.neuerSpieler(name, farbID, verhaltenID);
		String spielerNummer = request.getParameter("anzahlSpieler");
		sess.setAttribute("name", name);
		sess.setAttribute("farbe", farbe);
		sess.setAttribute("spielerNummer", spielerNummer);


		
		
		int anzahl = (int) sess.getServletContext().getAttribute("anzahlSpieler");
		sess.getServletContext().setAttribute("anzahlSpieler", ++anzahl);
		sess.getServletContext().setAttribute("s"+(anzahl-1)+"Farbe", farbe);
		System.out.println("Spieler angelegt");
		if(anzahl > checkSpieler){
			response.sendRedirect("LoginDone.jsp");
			game.starteSpiel();
		}
		else		
			response.sendRedirect("Login.jsp");
		}
		catch(Exception e){
			sess.getServletContext().setAttribute("error", e);
			response.sendRedirect("addPlayerError.jsp");
		}
		
	}

}
