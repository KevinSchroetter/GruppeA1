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
 * Servlet implementation class wuerfelnServlet
 */
@WebServlet("/wuerfelnServlet")
public class wuerfelnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public wuerfelnServlet() {
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
		SpielBean game= null;
		HttpSession sess=request.getSession(true);
		if(this.getServletContext().getAttribute("game")!=null){
			game = (SpielBean)this.getServletContext().getAttribute("game");
			int erg = game.rollTheDice();
			this.getServletContext().setAttribute("wuerfel", erg);
			sess.getServletContext().removeAttribute("amZug");
			sess.getServletContext().setAttribute("amZug", game.getIstAmZug().getFarbe().toString());		
		}
		response.sendRedirect("Spiel.jsp");
	}
}
