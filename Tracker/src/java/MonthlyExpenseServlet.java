import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/MonthlyExpenseServlet")
public class MonthlyExpenseServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String DB_URL = "jdbc:derby://localhost:1527/Tracker"; 
        String DB_USER = "admin12345"; 
        String DB_PASSWORD = "12345678"; 

        Connection conn = null;

        try {
            // Retrieve user ID from session
            HttpSession session = request.getSession();
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId == null) {
                response.sendRedirect("index.html");
                return;
            }

            // Get selected month and year from request
            String month = request.getParameter("month");
            String year = request.getParameter("year");

            // Establish DB connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // SQL query to fetch monthly expenses
            String sql = "SELECT title, amount, category, date FROM expenses WHERE user_id = ? AND MONTH(date) = ? AND YEAR(date) = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(2, Integer.parseInt(month));
            stmt.setInt(3, Integer.parseInt(year));
            ResultSet rs = stmt.executeQuery();

            // Generate HTML table for expenses
            StringBuilder expenseTable = new StringBuilder("<table border='1'>");
            expenseTable.append("<tr><th>Title</th><th>Amount</th><th>Category</th><th>Date</th></tr>");

            boolean hasExpenses = false;
            while (rs.next()) {
                hasExpenses = true;
                expenseTable.append("<tr>")
                            .append("<td>").append(rs.getString("title")).append("</td>")
                            .append("<td>").append(rs.getDouble("amount")).append("</td>")
                            .append("<td>").append(rs.getString("category")).append("</td>")
                            .append("<td>").append(rs.getDate("date")).append("</td>")
                            .append("</tr>");
            }
            expenseTable.append("</table>");

            // Set attribute and forward to dashboard
            request.setAttribute("expenseTable", hasExpenses ? expenseTable.toString() : "<p>No expenses found for this month.</p>");
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);

        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while fetching expenses.");
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
