

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dataClass.RestaurentData;

/**
 * Servlet implementation class Restaurent
 */
@WebServlet("/Restaurent")
public class Restaurent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Restaurent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.addHeader("Access-Control-Allow-Origin","*");
//		System.out.println(request.getParameter("userName") + ":" + request.getParameter("password"));
		PrintWriter out = response.getWriter();
		Statement statement = null;
		String dbName = "jdbc:mysql://localhost/restaurent?user=root&password=admin";
		Connection con = null;
		ResultSet rs = null;
		String sql = "";
		RestaurentData[] dataArray;
		System.out.println(request.getParameter("searchText"));
		if(request.getParameter("searchText") != null){
			dataArray = new RestaurentData[1];
			sql = "select * from Restaurent  where title=\""+request.getParameter("searchText")+"\"";
		}else{
			dataArray = new RestaurentData[10];
			sql = "select * from Restaurent limit "+request.getParameter("start")+",10";
		}
		String responseStr = "fail";
		Gson gson = new Gson();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dbName);
			statement = con.createStatement();
			rs = statement.executeQuery(sql);
			int i = 0;
			while(rs.next()){
				RestaurentData rest = new RestaurentData();
				rest.id = rs.getInt("restaurentId");
				rest.address = rs.getString("title");
				rest.title = rs.getString("title");
				rest.alias = rs.getString("alias");
				rest.time = rs.getString("time");
				rest.money = rs.getString("money");
				rest.feature = rs.getString("feature");
				rest.phone = rs.getString("phone");
				rest.imgSrc1 = rs.getString("imgSrc1");
				rest.imgSrc2 = rs.getString("imgSrc2");
				rest.imgSrc3 = rs.getString("imgSrc3");
				rest.score1 = rs.getString("score1");
				rest.score2 = rs.getString("score2");
				rest.score3 = rs.getString("score3");
				dataArray[i] = rest;
				i++;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.write(gson.toJson(dataArray));
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
