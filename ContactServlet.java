import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class ContactServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {

        String firstname = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String message = request.getParameter("message");

        response.setContentType("text/html");

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/portfolio_db",
                "root",
                ""
            );

            String query =
            "INSERT INTO contact_form(firstname, lastname, email, phone, message) VALUES(?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setString(5, message);

            ps.executeUpdate();

            response.getWriter().println("<h1>Message Sent Successfully</h1>");

            con.close();

        } catch(Exception e) {

            response.getWriter().println(e);

        }
    }
}