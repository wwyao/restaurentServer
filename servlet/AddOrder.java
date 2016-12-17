

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

import dataClass.OrderData;

/**
 * Servlet implementation class Login
 */
@WebServlet("/AddOrder")
public class AddOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.addHeader("Access-Control-Allow-Origin","*");
		response.addHeader("Access-Control-Allow-Headers","Content-Type");
		response.addHeader("Access-Control-Allow-Methods"," GET, POST, OPTIONS");
//		System.out.println(request.getParameter("userName") + ":" + request.getParameter("password"));
		
		OrderData orderData = new OrderData();
		orderData.time = request.getParameter("time");
		orderData.peopleNum = request.getParameter("peopleNum");
		orderData.name = request.getParameter("name");
		orderData.sex = request.getParameter("sex");
		orderData.phone = request.getParameter("phone");
		orderData.userName = request.getParameter("userName");
		orderData.statu = request.getParameter("statu");
		
		PrintWriter out = response.getWriter();
		Statement statement = null;
		String dbName = "jdbc:mysql://localhost/restaurent?user=root&password=admin&useUnicode=true&characterEncoding=utf-8&useSSL=false";
		Connection con = null;
		ResultSet rs = null;
		String sql = "insert into Orders(statu,time,peopleNum,phone,sex,userName) values(\""+orderData.statu+"\",\""+orderData.time+"\",\""+orderData.peopleNum+"\",\""+orderData.phone+"\",\""+orderData.sex+"\",\""+orderData.userName+"\")";
		String responseStr = "success"; 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dbName);
			statement = con.createStatement();
//			rs = statement.executeQuery(sql1);
//			if(!rs.next()){
			statement.executeUpdate(sql);
//			}
		} catch (ClassNotFoundException | SQLException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.write(responseStr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
