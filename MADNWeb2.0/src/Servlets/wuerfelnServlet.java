package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Basisklassen.Spielfigur;
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
			if(game.getZugFiguren()!=null){
				ArrayList<Spielfigur> figs =game.getZugFiguren();
				int size = figs.size();
				if (size>0){
					for (int i = 0; i< size; i++){
						sess.setAttribute("zug"+i,(figs.get(i).getMeinFeld().toString()));
						sess.getServletContext().setAttribute(sess.getAttribute("zug"+i).toString(),"<a href='zugDurchfuehrenServlet?zuZiehen="+sess.getAttribute("zug"+i).toString()+"'>"+sess.getServletContext().getAttribute(sess.getAttribute("zug"+i).toString())+"</a>");
					}
				}
			}
			else
				System.out.println("ist null");
			this.getServletContext().setAttribute("wuerfel", erg);
			sess.getServletContext().removeAttribute("amZug");
			if (game.getIstAmZug().getBedienung()==null){
				sess.getServletContext().setAttribute("amZug", game.getIstAmZug().getFarbe().toString());	
			}
			else
				sess.getServletContext().setAttribute("amZug", game.getIstAmZug().getFarbe().toString()+"-KI");	
		}
		response.sendRedirect("Spiel.jsp");
	}
}
