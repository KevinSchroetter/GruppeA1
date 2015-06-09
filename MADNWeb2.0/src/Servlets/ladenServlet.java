package Servlets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Spiel.DatenzugriffCSV;
import Spiel.DatenzugriffSerialisiert;
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
			fis = (FileInputStream) iD.openFile("C:\\ame.ser", 1);

			try {
				loadme = (SpielBean) iD.spielLaden(fis);
				sess.getServletContext().setAttribute("game", loadme);
				iD.closeFile(fis);
				if (loadme != null) {
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
						"D:\\Spiel.csv", 1);
				loadme = (SpielBean) iD.spielLaden(bR);
				sess.getServletContext()
						.setAttribute("game", loadme);
				
				if(loadme == null){
					response.sendRedirect("LadenFail.jsp");
				} else response.sendRedirect("ladenWin.jsp");
			} catch (Exception e) {
				response.sendRedirect("LadenFail.jsp");
				e.printStackTrace();
			}

		}

		else if (zugriffsTyp.equals("XML")) {

		} else if (zugriffsTyp.equals("PDF")) {

		}
	}

}
