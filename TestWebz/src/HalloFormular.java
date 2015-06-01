

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HalloFormular
 */
@WebServlet("/HalloFormular")
public class HalloFormular extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HalloFormular() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html/);charset=ISO-8859-1");
		PrintWriter out = response.getWriter();
		out.println("<html><head></head><body>");
		String p1=request.getParameter("param1");
		String p2=request.getParameter("param2");
		String ausgabe="";
		if((p1==null||p1.length()<1))
			ausgabe="<h2>Sie haben nix eingegeben!</h2>";
		else
			ausgabe ="<h2>sie Haben "+p1+" "+p2+" eingegeben!</h2>";
		out.println(ausgabe);
		//out.println("laenge: "+p1.length());
		out.println("<a href='NewFile.html'>zurueck..</a>");
		out.println("</body></html>");
		out.close();
	}

}
