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
			this.getServletContext().removeAttribute("GRUENS1");
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
			this.getServletContext().removeAttribute("GRUENE1");
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
		System.out.println("NEUES SPIEL GESTARTET!");
		response.sendRedirect("Login.jsp");
	}
}
