# 💰 Expense Tracker (JSP + Servlets in VS Code)

A lightweight web-based Expense Tracker built using JSP, Servlets, HTML/CSS, and Java DB (Derby). It lets users track their daily expenses and generate insightful monthly reports with clean MVC design.

---

## 🚀 Features

- 👤 User Registration & Login with Session Tracking
- ➕ Add, ✏️ Update, 🗑️ Delete Expenses
- 🔐 Secure Access — Expenses visible only after login
- 📊 Monthly Expense Report with Charts
- ⚙️ Settings for Categories and Preferences
- 🧹 Clean, Modular Code (MVC Pattern)

---

## 🛠️ Tech Stack

- Java (JDK 8+)
- JSP + Servlets
- HTML5 + CSS3
- Java DB (Apache Derby)
- Apache Tomcat (v9 or v10)
- VS Code with Extensions:
  - Java Extension Pack
  - Tomcat for Java
  - Language Support for Java(TM) by Red Hat

---

## 📁 Project Structure

```
Tracker/
├── Web Pages/
│ ├── index.html
│ ├── SignUp.html
│ ├── dashboard.jsp
│ └── styles.css
├── WEB-INF/
│ └── web.xml
├── Source Packages/
│ ├── Expense.java
│ ├── DashboardServlet.java
│ ├── LoginServlet.java
│ ├── LogoutServlet.java
│ ├── SignupServlet.java
│ ├── ExpenseServlet.java
│ ├── ReportServlet.java
│ └── MonthlyExpenseServlet.java
```

---

## 🔧 How to Run

1. Open project in **NetBeans 8.1**
2. Right-click on the project → `Clean and Build`
3. Set server as **GlassFish** or **Tomcat**
4. Run the project
5. App opens in browser at:  
   `http://localhost:8080/Expense_Tracker/`

---

## 👤 Author

- **Pritam Pal**
- GitHub: [@Pal-Cyber](https://github.com/Pal-Cyber)

---

## 📜 License

This project is for learning purposes only.
