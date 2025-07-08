<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        * { box-sizing: border-box; }
        body { font-family: 'Arial', sans-serif; margin: 0; background-color: #f4f4f4; }
        .sidebar { background: #2c3e50; color: white; width: 250px; position: fixed; height: 100%; padding: 15px; }
        .sidebar h2 { text-align: center; }
        .sidebar ul { list-style: none; padding: 0; }
        .sidebar ul li { padding: 15px; text-align: center; }
        .sidebar ul li a { color: white; text-decoration: none; }
        .sidebar ul li a:hover { background: #34495e; }
        .main-content { margin-left: 260px; padding: 20px; }
        header { background: #3498db; color: white; padding: 15px; text-align: center; }
        section { background: white; margin: 15px 0; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }
        h2 { border-bottom: 2px solid #3498db; padding-bottom: 10px; }
        form { display: flex; flex-direction: column; }
        form input, form select { margin: 10px 0; padding: 10px; border: 1px solid #ddd; border-radius: 4px; }
        form button { padding: 10px; background: #3498db; color: white; border: none; border-radius: 4px; cursor: pointer; }
        form button:hover { background: #2980b9; }
        .expense-list { margin-top: 10px; }
        #reportCanvas { width: 100%; height: 400px; }
    </style>
</head>
<body>
    <div class="sidebar">
        <h2>Expense Tracker</h2>
        <ul>
            <li><a href="#addExpense"><i class="fas fa-plus"></i> Add Expense</a></li>
            <li><a href="#viewExpenses"><i class="fas fa-eye"></i> View Expenses</a></li>
            <li><a href="#report"><i class="fas fa-chart-pie"></i> Reports</a></li>
            <li><a href="#settings"><i class="fas fa-cog"></i> Settings</a></li>
            <li><a href="#logout" onclick="openLogoutModal()"><i class="fas fa-sign-out-alt"></i> Logout</a></li>
        </ul>
    </div>
    <div class="main-content">
        <header>
            <h1>Dashboard</h1>
        </header>
        <!-- Sections that require login -->
        <section id="addExpense">
            <h2>Add Expense</h2>
            <form action="ExpenseServlet" method="POST">
                <input type="text" name="title" placeholder="Expense Title" required>
                <input type="number" step="0.01" name="amount" placeholder="Amount" required>
                <select name="category" required>
                    <option value="" disabled selected>Select Category</option>
                    <option value="Food">Food</option>
                    <option value="Transport">Transport</option>
                    <option value="Utilities">Utilities</option>
                    <option value="Entertainment">Entertainment</option>
                    <option value="Healthcare">Healthcare</option>
                    <option value="Other">Other</option>
                </select>
                <input type="date" name="date" required>
                <button type="submit">Add Expense</button>
            </form>
        </section>
       <section id="viewExpenses">
    <h2>View Expenses</h2>
    <div class="expense-list">
        <form action="MonthlyExpenseServlet" method="GET">
            <label for="monthSelect">Month:</label>
            <select name="month" id="monthSelect" required>
                <option value="" disabled selected>Select Month</option>
                <option value="1">January</option>
                <option value="2">February</option>
                <option value="3">March</option>
                <option value="4">April</option>
                <option value="5">May</option>
                <option value="6">June</option>
                <option value="7">July</option>
                <option value="8">August</option>
                <option value="9">September</option>
                <option value="10">October</option>
                <option value="11">November</option>
                <option value="12">December</option>
            </select>

            <label for="yearSelect">Year:</label>
            <select name="year" id="yearSelect" required>
                <option value="" disabled selected>Select Year</option>
                <option value="2024">2024</option>
                <option value="2023">2023</option>
                <option value="2025">2025</option>

            </select>
            <button type="submit">Show Expenses</button>
        </form>
        <div id="expenseData">
            <p>No expenses available for the selected period.</p>
            <%= request.getAttribute("expenseTable") != null ? request.getAttribute("expenseTable") : "" %>
        </div>
    </div>
</section>
        <section id="report">
            <h2>Reports</h2>
            <canvas id="reportCanvas"></canvas>
            <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
            <script>
                // Fetching report data from the server for pie chart
                fetch('ReportServlet')
                    .then(response => response.json())
                    .then(data => {
                        const categories = data.map(item => item.category);
                        const amounts = data.map(item => item.totalAmount);

                        const ctx = document.getElementById('reportCanvas').getContext('2d');
                        new Chart(ctx, {
                            type: 'pie',
                            data: {
                                labels: categories,
                                datasets: [{
                                    data: amounts,
                                    backgroundColor: [
                                        '#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0', '#9966FF', '#FF9F40'
                                    ]
                                }]
                            },
                            options: {
                                responsive: true,
                                plugins: {
                                    legend: {
                                        position: 'top',
                                    },
                                    tooltip: {
                                        callbacks: {
                                            label: function(tooltipItem) {
                                                return categories[tooltipItem.dataIndex] + ': $' + amounts[tooltipItem.dataIndex];
                                            }
                                        }
                                    }
                                }
                            }
                        });
                    })
                    .catch(error => console.error('Error fetching report data:', error));
            </script>
        </section>
        <section id="settings">
            <h2>Settings</h2>
            <p>Manage your account settings here.</p>
            <!-- Add settings options here -->
            <ul>
               
                <li><a href="#wait">Update Profile</a></li>
                
            </ul>
        </section>
        <!-- Logout Modal -->
        <div id="logoutModal" class="modal" style="display:none;">
            <div class="modal-content">
                <p>Are you sure you want to log out?</p>
                <button class="cancel-btn" onclick="closeLogoutModal()">Cancel</button>
                <button class="confirm-btn" onclick="confirmLogout()">Logout</button>
            </div>
        </div>
    </div>
    <script>
        // Modal handling for logout confirmation
        function openLogoutModal() {
            document.getElementById("logoutModal").style.display = "block";
        }

        function closeLogoutModal() {
            document.getElementById("logoutModal").style.display = "none";
        }

        function confirmLogout() {
            window.location.href = "LogoutServlet"; // Redirect to your logout servlet
        }
    </script>
</body>
</html>
