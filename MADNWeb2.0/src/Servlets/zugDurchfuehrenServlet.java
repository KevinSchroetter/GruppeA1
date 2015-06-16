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

import Spiel.SpielBean;

/**
 * Servlet implementation class zugDurchfuehrenServlet
 */
@WebServlet("/zugDurchfuehrenServlet")
public class zugDurchfuehrenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public zugDurchfuehrenServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		HttpSession sess=request.getSession(true);
		SpielBean game = null;
		if(this.getServletContext().getAttribute("game")!=null){
			game = (SpielBean)this.getServletContext().getAttribute("game");
		}
		PrintWriter out = response.getWriter();
		String zuZiehen = (String) request.getParameter("zuZiehen");
		if(zuZiehen.contains(sess.getAttribute("farbe").toString())){
			int chars = 0;
			chars = sess.getAttribute("farbe").toString().length();
			zuZiehen = zuZiehen.substring(chars);
		}
		out.println(zuZiehen);
		game.zugDurchfuehren(zuZiehen);
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
		ArrayList<String> figuren = game.alleFigs();
		figuren = game.alleFigs();
		String[] split = new String[2];
		for(String s:figuren){
			split=s.split("-");
			sess.getServletContext().setAttribute(split[0], split[1].substring(0, 2)+split[2]);
			System.out.println("Feld: "+split[0]+" Farbe: "+split[1]+" ID: "+split[2]);
		}
		sess.getServletContext().setAttribute("amZug",game.getIstAmZug().getFarbe().toString());
		
		response.sendRedirect("Spiel.jsp");
		
	}

}
