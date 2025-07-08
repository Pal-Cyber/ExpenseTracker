import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String DB_URL = "jdbc:derby://localhost:1527/Tracker";
        String DB_USER = "admin12345";
        String DB_PASSWORD = "12345678";

        Connection conn = null;
        StringBuilder expenseData = new StringBuilder();

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            HttpSession session = request.getSession();
            Integer userId = (Integer) session.getAttribute("userId");

            if (userId == null) {
                response.sendRedirect("login.html");
                return;
            }

            // SQL query to retrieve all expenses
            String sql = "SELECT * FROM expenses WHERE user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                expenseData.append("<p>")
                           .append("Title: ").append(rs.getString("title")).append(", ")
                           .append("Amount: ").append(rs.getDouble("amount")).append(", ")
                           .append("Category: ").append(rs.getString("category")).append(", ")
                           .append("Date: ").append(rs.getDate("date")).append("</p>");
            }

            if (expenseData.length() == 0) {
                expenseData.append("<p>No expenses added yet.</p>");
            }

            // Set attributes for the dashboard page
            request.setAttribute("expenseList", expenseData.toString());
            request.setAttribute("showReports", true);  // Enables the Reports section
            request.setAttribute("showSettings", true); // Enables the Settings section
            request.setAttribute("showLogout", true);   // Enables the Logout button

            // Forward to JSP to display the dashboard
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
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
