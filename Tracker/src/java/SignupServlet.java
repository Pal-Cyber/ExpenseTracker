import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:derby://localhost:1527/Tracker";
    private static final String DB_USER = "admin12345";
    private static final String DB_PASSWORD = "12345678";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Set the content type to HTML
        response.setContentType("text/html");

        try {
            // Connect to the database
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Generate a new ID by finding the maximum ID and adding 1
            String idQuery = "SELECT MAX(id) AS max_id FROM users";
            PreparedStatement idStmt = conn.prepareStatement(idQuery);
            ResultSet idRs = idStmt.executeQuery();
            int newId = 1;
            if (idRs.next()) {
                newId = idRs.getInt("max_id") + 1;
            }

            // Insert the new user
            String sql = "INSERT INTO users (id, username, password) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, newId);
            stmt.setString(2, username);
            stmt.setString(3, password);

            int rowsInserted = stmt.executeUpdate();

            // Check if the insert was successful
            if (rowsInserted > 0) {
                // Redirect to the login page after a successful signup
                response.sendRedirect("index.html");
            } else {
                PrintWriter out = response.getWriter();
                out.println("<h1>Signup failed. Please try again.</h1>");
                out.close();
            }

            // Clean up
            idRs.close();
            idStmt.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            PrintWriter out = response.getWriter();
            out.println("<h1>Database connection failed: " + e.getMessage() + "</h1>");
            out.close();
        }
    }
}
