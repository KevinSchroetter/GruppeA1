package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		if(request.getParameter("maxSpielerAnzahl")!=null){
		String maxSpieler = request.getParameter("maxSpielerAnzahl");
		sess.getServletContext().setAttribute("maxSpieler",maxSpieler);
		}
		int checkSpieler = Integer.parseInt(sess.getServletContext().getAttribute("maxSpieler").toString());
		String name = request.getParameter("name");
		String farbe = request.getParameter("farbe");
		String spielerNummer = request.getParameter("anzahlSpieler");
		sess.setAttribute("name", name);
		sess.setAttribute("farbe", farbe);
		sess.setAttribute("spielerNummer", spielerNummer);


		
		
		int anzahl = (int) sess.getServletContext().getAttribute("anzahlSpieler");
		sess.getServletContext().setAttribute("anzahlSpieler", ++anzahl);
		sess.getServletContext().setAttribute("s"+(anzahl-1)+"Farbe", farbe);

		if(anzahl > checkSpieler)
			response.sendRedirect("LoginDone.jsp");
		else		
			response.sendRedirect("Login.jsp");
	}

}
