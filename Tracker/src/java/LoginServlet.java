import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
   private static final String DB_URL = "jdbc:derby://localhost:1527/Tracker";
    private static final String DB_USER = "admin12345";
    private static final String DB_PASSWORD = "12345678";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT id FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // User found, create a session
                int userId = rs.getInt("id");
                HttpSession session = request.getSession();
                session.setAttribute("userId", userId);
              response.sendRedirect("DashboardServlet"); // Redirect to the dashboard page
 // Redirect to expense tracker
            } else {
                // User not found, send error
                response.sendRedirect("index.html?error=Invalid credentials");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("<p>Error logging in: " + e.getMessage() + "</p>");
        }
    }
}
