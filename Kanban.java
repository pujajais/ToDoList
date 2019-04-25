package HomeWork2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lab5.State;


@WebServlet("/Kanban")
public class Kanban extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<ToDo> todoList = new ArrayList<ToDo>();
	List<ToDo> inProgress = new ArrayList<ToDo>();
	List<ToDo> done = new ArrayList<ToDo>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Kanban() {
		super();
		// TODO Auto-generated constructor stub
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String textTitle = request.getParameter("title");
		String textdescription = request.getParameter("description");
		if (textTitle != null && !textTitle.isEmpty()) {
			if (textdescription == null || textdescription.isEmpty()) {
				textdescription = "";
			}
			todo(textTitle, textdescription);
		}
		HttpSession session = request.getSession();
		session.setAttribute("todoList", todoList);
		session.setAttribute("inProgress", inProgress);
		session.setAttribute("done", done);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");
		out.println("<head>");
		out.println("<title>ToDoList</title>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		out.println(
				"<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\" >");
		out.println(
				"<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.7.2/css/all.css\" integrity=\"sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr\" crossorigin=\"anonymous\">");

		out.println("<style>" + ".card-header > a:link, .card-header > a:visited, .card-header > a:hover"
				+ " { color: white; }	" + ".card-footer  a:link, .card-footer a:visited" + " { color: gray;} "
				+ "a:hover.move { color: blue;}	" + "a:hover.delete { color: red; }" + "</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container-fluid\">");// div1 container
		out.println("<div class=\"jumbotron my-4\">");// div2 jumbotron
		out.println("<h1 class=\"display-4\">" + "Kanban" + "</h1>");
		out.println("</div>");// div2 jumbotron close
		out.println("<div class=\"row\">");// div3 row
		out.println("<div class=\"col-md\">");// div4 col-md
		out.println("<h3>To-Do</h3>");
		out.println("<hr class=\"my-4\">");
		for (int i = 0; i < todoList.size(); i++) {
			String name = todoList.get(i).item;
			String description = todoList.get(i).description;
			if (todoList.get(i).state == State.INCOMPLETE) {

				out.println("<div class=\"card mb-4\">");// div5 card-mb
				out.println("<h5 class=\"card-header text-white bg-warning\">");
				out.println("<a class=\"white-icon\" href=\"priority?id=" + i + "&action=decrease&list=todoList\">"
						+ "<i class=\"fas fa-caret-down float-right\">" + "</i>" + "</a>");
				out.println("<a class=\"white-icon\" href=\"priority?id=" + i + "&action=increase&list=todoList\">"
						+ "<i class=\"fas fa-caret-up float-right\">" + "</i>" + "</a>" + name);
				out.println("</h5>");
				out.println("<div class=\"card-body\">");// div6 card-body
				out.println("<p class=\"card-text\">" + description + "</p>");
				out.println("</div>");// div6 card-body close
				out.println("<div class=\"card-footer\">");// div7 card-footer
				out.println("<span class=\"float-left\">");
				out.println("<a class=\"delete\" href=\"delete?id=" + i + "&list=todoList\">"
						+ "<i class=\"fas fa-trash\">" + "</i>" + "</a>");
				out.println("</span>");
				out.println("<span class=\"float-right\">");
				out.println("<a class=\"move\" href=\"Move?id=" + i + "&current=incomplete" + "\">"
						+ "Move to In-Progress" + "<i class=\"fas fa-caret-right\">" + "</i>" + "</a>");
				out.println("</span>");
				out.println("</div>");// div7 card-footer close
				out.println("</div>");// div5 card-body close
			}

		}

		out.println("</div>");// div4 col-md close
		out.println("<div class=\"col-md\">");// div8 col-md
		out.println("<h3 class=\"\">" + "In-Progress" + "</h3>");
		out.println("<hr class=\"my-4\">");
		for (int i = 0; i < inProgress.size(); i++) {
			String name = inProgress.get(i).item;
			String description = inProgress.get(i).description;
			if (inProgress.get(i).state == State.INPROGRESS) {
				// Date date = listOfTodo.get(i).timeStamp;
				out.println("<div class=\"card mb-4\">");// div9 card mb-4
				out.println("<h5 class=\"card-header text-white bg-primary\">");
				out.println("<a class=\"white-icon\" href=\"priority?id=" + i + "&action=decrease&list=inProgress\">"
						+ "<i class=\"fas fa-caret-down float-right\">" + "</i></a>");
				out.println("<a class=\"white-icon\" href=\"priority?id=" + i
						+ "&action=increase&list=inProgress\"><i class=\"fas fa-caret-up float-right\">" + "</i></a>"
						+ name);
				out.println("</h5>");
				out.println("<div class=\"card-body\">");// div10 card-body
				out.println("<p class=\"card-text\">" + description + "</p>");
				out.println("</div>");// div10 card-body close
				out.println("<div class=\"card-footer\">");// div11 card-footer
				out.println("<span class=\"float-left\">");
				out.println("<a class=\"delete\" href=\"delete?id=" + i + "&list=inProgress\">"
						+ "<i class=\"fas fa-trash\">" + "</i></a>");
				out.println("</span>");
				out.println("<span class=\"float-right\">");
				out.println("<a class=\"move\" href=\"Move?id=" + i + "&current=inProgress\">" + "Move to Done"
						+ "<i class=\"fas fa-caret-right\">" + "</i></a>");
				out.println("</span>");
				out.println("</div>");// div11 card-footer close
				out.println("</div>");// div9 card-mb4 close
			}
		}
		out.println("</div>");// div8 col-md close
		out.println("<div class=\"col-md\">");// div12 col-md
		out.println("<h3 class='\'>" + "Done" + "</h3>");

		out.println("<hr class=\"my-4\">");
		for (int i = 0; i < done.size(); i++) {
			String name = done.get(i).item;
			String description = done.get(i).description;
			if (done.get(i).state == State.COMPLETE) {
				// Date date = listOfTodo.get(i).timeStamp;
				out.println("<div class=\"card mb-4\">");// div13 card mb-4
				out.println("<h5 class=\"card-header text-white bg-success\">" + name);
				out.println("</h5>");
				out.println("<div class=\"card-body\">	");// div14 cord-body
				out.println("<p class=\"card-text\">" + description + "</p>");
				out.println("</div>");// div14 cord-body close
				out.println("</div>");// div13 card mb-4 close
			}
		}

		out.println("</div>");// div12 col-md close
		out.println("</div>");// div3 row close
		out.println("<hr class=\"my-4\">");
		out.println("<div class=\"row\">");// div15 row
		out.println("<div class=\"col\">");// div16 col
		out.println("<h3 class=\"text-center\">Add a New Card</h3>");
		out.println("<form action=\"Kanban\" method=\"post\">");
		out.println("<div class=\"form-group\">");// div17 rform-group
		out.println("<label for=\"title\" class=\"\">Title</label>");
		out.println(
				"<input type=\"text\" class=\"form-control\" id=\"title\" name=\"title\" placeholder=\"Enter a Title\">");
		out.println("</div>");// div17 rform-group close
		out.println("<div class=\"form-group\">");// div18 rform-group
		out.println("<label for=\"description\" class=\"\">Description</label>");
		out.println(
				"<textarea class=\"form-control\" rows=\"5\" id=\"description\" name=\"description\" placeholder=\"Enter a description\"></textarea>");
		out.println("</div>");// div18 rform-group close
		out.println("<input type=\"submit\" class=\"btn btn-primary\" value=\"Add Card\">");
		out.println("</form>");
		out.println("</div>");// div16 col close
		out.println("</div>");// div15 row close
		out.println("</div>");// div1 ccontainer

		out.println("</body>");
		out.println("</html>");
	}

	public void todo(String text, String textdescription) {
		String itemText = text;
		String itemDescription = textdescription;
		Date dNow = new Date();
		ToDo tdo = new ToDo(itemText, itemDescription, State.INCOMPLETE, dNow);
		todoList.add(tdo);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
