package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class neuesSpielServlet
 */
@WebServlet("/neuesSpielServlet")
public class neuesSpielServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public neuesSpielServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().removeAttribute("game");
		if (this.getServletContext().getAttribute("refresh")!=null){
			this.getServletContext().removeAttribute("refresh");
		}
		if (this.getServletContext().getAttribute("s1Farbe")!=null){
			this.getServletContext().removeAttribute("s1Farbe");
		}
		if (this.getServletContext().getAttribute("s2Farbe")!=null){
			this.getServletContext().removeAttribute("s2Farbe");
		}
		if (this.getServletContext().getAttribute("s3Farbe")!=null){
			this.getServletContext().removeAttribute("s3Farbe");
		}
		if (this.getServletContext().getAttribute("s4Farbe")!=null){
			this.getServletContext().removeAttribute("s4Farbe");
		}
		if (this.getServletContext().getAttribute("maxSpieler")!=null){
			this.getServletContext().removeAttribute("maxSpieler");
		}
		if (this.getServletContext().getAttribute("anzahlSpieler")!=null){
			this.getServletContext().removeAttribute("anzahlSpieler");
		}
		if (this.getServletContext().getAttribute("error")!=null){
			this.getServletContext().removeAttribute("error");
		}
		if (this.getServletContext().getAttribute("beendet")!=null){
			this.getServletContext().removeAttribute("beendet");
		}
		if (this.getServletContext().getAttribute("sessID")!=null){
			this.getServletContext().removeAttribute("sessID");
		}
		if (this.getServletContext().getAttribute("check")!=null){
			this.getServletContext().removeAttribute("check");
		}
		if (this.getServletContext().getAttribute("exist")!=null){
			this.getServletContext().removeAttribute("exist");
		}
		if (this.getServletContext().getAttribute("s1Session")!=null){
			this.getServletContext().removeAttribute("s1Session");
		}
		if (this.getServletContext().getAttribute("s2Session")!=null){
			this.getServletContext().removeAttribute("s2Session");
		}
		if (this.getServletContext().getAttribute("s3Session")!=null){
			this.getServletContext().removeAttribute("s3Session");
		}
		if (this.getServletContext().getAttribute("s4Session")!=null){
			this.getServletContext().removeAttribute("s4Session");
		}
		if (this.getServletContext().getAttribute("lastKI")!=null){
			this.getServletContext().removeAttribute("lastKI");
		}
		if (this.getServletContext().getAttribute("gestartet")!=null){
			this.getServletContext().removeAttribute("gestartet");
		}
		if (this.getServletContext().getAttribute("amZug")!=null){
			this.getServletContext().removeAttribute("amZug");
		}
		if (this.getServletContext().getAttribute("wuerfel")!=null){
			this.getServletContext().removeAttribute("wuerfel");
		}
		System.out.println("NEUES SPIEL GESTARTET!");
		response.sendRedirect("Login.jsp");
	}
}
