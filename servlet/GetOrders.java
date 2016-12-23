

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

import dataClass.OrderData;
import dataClass.RestaurentData;

/**
 * Servlet implementation class Restaurent
 */
@WebServlet("/GetOrders")
public class GetOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOrders() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	request.setCharacterEncoding("utf-8");
    	response.addHeader("Access-Control-Allow-Origin","*");
		response.addHeader("Access-Control-Allow-Headers","Content-Type");
		response.addHeader("Access-Control-Allow-Methods"," GET, POST, OPTIONS");
		response.setCharacterEncoding("utf-8");
//		System.out.println(request.getParameter("userName") + ":" + request.getParameter("password"));
		PrintWriter out = response.getWriter();
		Statement statement = null;
		String dbName = "jdbc:mysql://127.0.0.1/restaurent?user=root&password=root";
		Connection con = null;
		ResultSet rs = null;
		String sql = "select * from orders where statu=\"Î´¸¶¿î\" and userName = \"admin\"";
		OrderData[] resultArray;
		Gson gson = new Gson();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dbName);
			statement = con.createStatement();
			rs = statement.executeQuery(sql);
			int i = 0;
			while(rs.next()){
				i++;
			}
			resultArray = new OrderData[i];
			rs = statement.executeQuery(sql);
			int j = 0;
			while(rs.next()){
				OrderData od = new OrderData(); 
				od.orderId = rs.getInt("orderId");
				od.statu = rs.getString("statu");
				od.time = rs.getString("time");
				od.peopleNum = rs.getString("peopleNum");
				od.phone = rs.getString("phone");
				od.sex = rs.getString("sex");
				od.userName = rs.getString("userName");
				od.restaurentName = rs.getString("restaurentName");
				od.restaurentId = rs.getString("restaurentId");
				od.name = rs.getString("name");
				od.helpOther = rs.getString("helpOther");
				od.bookMoney = rs.getString("bookMoney");
				od.imgSrc = rs.getString("imgSrc");
				resultArray[j] = od;
				j++;
			}
			out.write(gson.toJson(resultArray));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
