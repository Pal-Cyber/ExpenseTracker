import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ExpenseServlet")
public class ExpenseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String DB_URL = "jdbc:derby://localhost:1527/Tracker"; 
        String DB_USER = "admin12345"; 
        String DB_PASSWORD = "12345678"; 

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Retrieve user ID from session
            HttpSession session = request.getSession();
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId == null) {
                response.sendRedirect("index.html"); // Redirect to login if user ID is not found
                return;
            }

            // Retrieve expense details from the request
            String title = request.getParameter("title");
            double amount = Double.parseDouble(request.getParameter("amount"));
            String category = request.getParameter("category");
            String date = request.getParameter("date");

            // SQL query to insert a new expense
            String sql = "INSERT INTO expenses (user_id, title, amount, category, date) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setString(2, title);
            stmt.setDouble(3, amount);
            stmt.setString(4, category);
            stmt.setDate(5, java.sql.Date.valueOf(date));

            // Execute the insert statement
            stmt.executeUpdate();

            // Redirect to the dashboard after adding the expense
            response.sendRedirect("DashboardServlet");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred while adding the expense.");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input for amount.");
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
