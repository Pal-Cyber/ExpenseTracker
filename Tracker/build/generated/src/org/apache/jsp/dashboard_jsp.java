package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

public final class dashboard_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("    <title>Dashboard</title>\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css\">\n");
      out.write("    <style>\n");
      out.write("        * { box-sizing: border-box; }\n");
      out.write("        body { font-family: 'Arial', sans-serif; margin: 0; background-color: #f4f4f4; }\n");
      out.write("        .sidebar { background: #2c3e50; color: white; width: 250px; position: fixed; height: 100%; padding: 15px; }\n");
      out.write("        .sidebar h2 { text-align: center; }\n");
      out.write("        .sidebar ul { list-style: none; padding: 0; }\n");
      out.write("        .sidebar ul li { padding: 15px; text-align: center; }\n");
      out.write("        .sidebar ul li a { color: white; text-decoration: none; }\n");
      out.write("        .sidebar ul li a:hover { background: #34495e; }\n");
      out.write("        .main-content { margin-left: 260px; padding: 20px; }\n");
      out.write("        header { background: #3498db; color: white; padding: 15px; text-align: center; }\n");
      out.write("        section { background: white; margin: 15px 0; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }\n");
      out.write("        h2 { border-bottom: 2px solid #3498db; padding-bottom: 10px; }\n");
      out.write("        form { display: flex; flex-direction: column; }\n");
      out.write("        form input, form select { margin: 10px 0; padding: 10px; border: 1px solid #ddd; border-radius: 4px; }\n");
      out.write("        form button { padding: 10px; background: #3498db; color: white; border: none; border-radius: 4px; cursor: pointer; }\n");
      out.write("        form button:hover { background: #2980b9; }\n");
      out.write("        .expense-list { margin-top: 10px; }\n");
      out.write("        #reportCanvas { width: 100%; height: 400px; }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <div class=\"sidebar\">\n");
      out.write("        <h2>Expense Tracker</h2>\n");
      out.write("        <ul>\n");
      out.write("            <li><a href=\"#addExpense\"><i class=\"fas fa-plus\"></i> Add Expense</a></li>\n");
      out.write("            <li><a href=\"#viewExpenses\"><i class=\"fas fa-eye\"></i> View Expenses</a></li>\n");
      out.write("            <li><a href=\"#report\"><i class=\"fas fa-chart-pie\"></i> Reports</a></li>\n");
      out.write("            <li><a href=\"#settings\"><i class=\"fas fa-cog\"></i> Settings</a></li>\n");
      out.write("            <li><a href=\"#logout\" onclick=\"openLogoutModal()\"><i class=\"fas fa-sign-out-alt\"></i> Logout</a></li>\n");
      out.write("        </ul>\n");
      out.write("    </div>\n");
      out.write("    <div class=\"main-content\">\n");
      out.write("        <header>\n");
      out.write("            <h1>Dashboard</h1>\n");
      out.write("        </header>\n");
      out.write("        <!-- Sections that require login -->\n");
      out.write("        <section id=\"addExpense\">\n");
      out.write("            <h2>Add Expense</h2>\n");
      out.write("            <form action=\"ExpenseServlet\" method=\"POST\">\n");
      out.write("                <input type=\"text\" name=\"title\" placeholder=\"Expense Title\" required>\n");
      out.write("                <input type=\"number\" step=\"0.01\" name=\"amount\" placeholder=\"Amount\" required>\n");
      out.write("                <select name=\"category\" required>\n");
      out.write("                    <option value=\"\" disabled selected>Select Category</option>\n");
      out.write("                    <option value=\"Food\">Food</option>\n");
      out.write("                    <option value=\"Transport\">Transport</option>\n");
      out.write("                    <option value=\"Utilities\">Utilities</option>\n");
      out.write("                    <option value=\"Entertainment\">Entertainment</option>\n");
      out.write("                    <option value=\"Healthcare\">Healthcare</option>\n");
      out.write("                    <option value=\"Other\">Other</option>\n");
      out.write("                </select>\n");
      out.write("                <input type=\"date\" name=\"date\" required>\n");
      out.write("                <button type=\"submit\">Add Expense</button>\n");
      out.write("            </form>\n");
      out.write("        </section>\n");
      out.write("        <section id=\"viewExpenses\">\n");
      out.write("            <h2>View Expenses</h2>\n");
      out.write("            <div class=\"expense-list\">\n");
      out.write("                <p>No expenses added yet.</p>\n");
      out.write("                <!-- This will be populated by the servlet -->\n");
      out.write("                ");
      out.print( request.getAttribute("expenseList") != null ? request.getAttribute("expenseList") : "" );
      out.write("\n");
      out.write("            </div>\n");
      out.write("        </section>\n");
      out.write("      <section id=\"report\">\n");
      out.write("    <h2>Reports</h2>\n");
      out.write("    <form id=\"reportFilterForm\">\n");
      out.write("        <label for=\"timeFilter\">View Report:</label>\n");
      out.write("        <select id=\"timeFilter\" onchange=\"fetchReport()\">\n");
      out.write("            <option value=\"\" disabled selected>Select Time Period</option>\n");
      out.write("            <option value=\"daily\">Daily</option>\n");
      out.write("            <option value=\"weekly\">Weekly</option>\n");
      out.write("            <option value=\"monthly\">Monthly</option>\n");
      out.write("        </select>\n");
      out.write("    </form>\n");
      out.write("    <canvas id=\"reportCanvas\"></canvas>\n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/chart.js\"></script>\n");
      out.write("    <script>\n");
      out.write("        function fetchReport() {\n");
      out.write("            const timeFilter = document.getElementById('timeFilter').value;\n");
      out.write("            if (!timeFilter) return;\n");
      out.write("\n");
      out.write("            fetch(`ReportServlet?filter=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${timeFilter}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("`)\n");
      out.write("                .then(response => response.json())\n");
      out.write("                .then(data => {\n");
      out.write("                    const categories = data.map(item => item.category);\n");
      out.write("                    const amounts = data.map(item => item.totalAmount);\n");
      out.write("\n");
      out.write("                    // Update chart\n");
      out.write("                    const ctx = document.getElementById('reportCanvas').getContext('2d');\n");
      out.write("                    new Chart(ctx, {\n");
      out.write("                        type: 'pie',\n");
      out.write("                        data: {\n");
      out.write("                            labels: categories,\n");
      out.write("                            datasets: [{\n");
      out.write("                                data: amounts,\n");
      out.write("                                backgroundColor: [\n");
      out.write("                                    '#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0', '#9966FF', '#FF9F40'\n");
      out.write("                                ]\n");
      out.write("                            }]\n");
      out.write("                        },\n");
      out.write("                        options: {\n");
      out.write("                            responsive: true,\n");
      out.write("                            plugins: {\n");
      out.write("                                legend: {\n");
      out.write("                                    position: 'top',\n");
      out.write("                                },\n");
      out.write("                                tooltip: {\n");
      out.write("                                    callbacks: {\n");
      out.write("                                        label: function (tooltipItem) {\n");
      out.write("                                            return categories[tooltipItem.dataIndex] + ': $' + amounts[tooltipItem.dataIndex];\n");
      out.write("                                        }\n");
      out.write("                                    }\n");
      out.write("                                }\n");
      out.write("                            }\n");
      out.write("                        }\n");
      out.write("                    });\n");
      out.write("                })\n");
      out.write("                .catch(error => console.error('Error fetching report data:', error));\n");
      out.write("        }\n");
      out.write("    </script>\n");
      out.write("</section>\n");
      out.write("\n");
      out.write("        <section id=\"settings\">\n");
      out.write("            <h2>Settings</h2>\n");
      out.write("            <p>Manage your account settings here.</p>\n");
      out.write("            <!-- Add settings options here -->\n");
      out.write("            <ul>\n");
      out.write("               \n");
      out.write("                <li><a href=\"#wait\">Update Profile</a></li>\n");
      out.write("                \n");
      out.write("            </ul>\n");
      out.write("        </section>\n");
      out.write("        <!-- Logout Modal -->\n");
      out.write("        <div id=\"logoutModal\" class=\"modal\" style=\"display:none;\">\n");
      out.write("            <div class=\"modal-content\">\n");
      out.write("                <p>Are you sure you want to log out?</p>\n");
      out.write("                <button class=\"cancel-btn\" onclick=\"closeLogoutModal()\">Cancel</button>\n");
      out.write("                <button class=\"confirm-btn\" onclick=\"confirmLogout()\">Logout</button>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    <script>\n");
      out.write("        // Modal handling for logout confirmation\n");
      out.write("        function openLogoutModal() {\n");
      out.write("            document.getElementById(\"logoutModal\").style.display = \"block\";\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        function closeLogoutModal() {\n");
      out.write("            document.getElementById(\"logoutModal\").style.display = \"none\";\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        function confirmLogout() {\n");
      out.write("            window.location.href = \"LogoutServlet\"; // Redirect to your logout servlet\n");
      out.write("        }\n");
      out.write("    </script>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
