package Servlets;

import java.io.BufferedWriter;
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
 * Servlet implementation class speichernServlet
 */
@WebServlet("/speichernServlet")
public class speichernServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public speichernServlet() {
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
		// PrintWriter out = response.getWriter();
		// out.println("<html><head></head><body>");
		// out.println("<h1>Ich bin das speichernServlet</h1>");
		// out.println("</body></html>");
		// out.close();


			iDatenzugriff iD = null;
			HttpSession sess = request.getSession(true);
			SpielBean saveme = null;
			String zugriffsTyp = request.getParameter("Zugriffstyp");

			saveme = (SpielBean) sess.getServletContext().getAttribute("game");
			if (zugriffsTyp.equals("SER")) {
				try{	
					iD = new DatenzugriffSerialisiert();
					FileOutputStream fos = (FileOutputStream) iD.openFile("D:\\Spiel.ser", 2);
					iD.spielSpeichern(saveme, fos);
					this.getServletContext().setAttribute("erfolg", "ja");
				}
				catch(Exception e){
					this.getServletContext().setAttribute("erfolg", null);
				}
				finally{
					response.sendRedirect("spielSpeichern.jsp");
					System.out.println("Speichern ser beendet");
				}


			} else if (zugriffsTyp.equals("CSV")) {
				try {
					System.out.println("Beginne Speichern mit CSV");
					iD = new DatenzugriffCSV();
					BufferedWriter bW = (BufferedWriter) iD.openFile(
							"D:\\Spiel.csv", 2);
					iD.spielSpeichern(saveme,
							bW);
					this.getServletContext().setAttribute("erfolg", "ja");
					System.out.println("Speichern erfolgreich!");
				} catch (Exception e) {
					System.out.println("Fehler beim Speichern!");
					e.printStackTrace();
					this.getServletContext().setAttribute("erfolg", null);
				} finally {
					iD.closeFile("D:\\Spiel.csv");
					response.sendRedirect("spielSpeichern.jsp");
					System.out.println("Speichern CSV beendet.");
				}
			}

			else if (zugriffsTyp.equals("XML")) {

			} else if (zugriffsTyp.equals("PDF")) {

			}


		}
	}


