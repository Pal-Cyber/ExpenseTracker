import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String DB_URL = "jdbc:derby://localhost:1527/Tracker";
        String DB_USER = "admin12345";
        String DB_PASSWORD = "12345678";

        Connection conn = null;
        StringBuilder jsonOutput = new StringBuilder();

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            HttpSession session = request.getSession();
            Integer userId = (Integer) session.getAttribute("userId");

            String sql = "SELECT category, SUM(amount) as totalAmount FROM expenses WHERE user_id = ? GROUP BY category";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            // Construct JSON manually
            jsonOutput.append("[");
            boolean first = true;

            while (rs.next()) {
                if (!first) {
                    jsonOutput.append(",");
                }
                jsonOutput.append("{")
                          .append("\"category\":\"").append(rs.getString("category")).append("\",")
                          .append("\"totalAmount\":").append(rs.getDouble("totalAmount"))
                          .append("}");
                first = false;
            }
            jsonOutput.append("]");

            // Set response type to JSON
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonOutput.toString());
            out.flush();

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
