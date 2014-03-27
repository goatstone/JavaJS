package com.goatstone.example.javajs;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by goat on 3/26/14.
 */

public class FormDataServlet extends HttpServlet
{
    private String greeting="Hello World";
    public FormDataServlet(){}
    public FormDataServlet(String greeting)
    {
        this.greeting=greeting;
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<html>");

         response.getWriter().println("<h1>X"+greeting+"</h1>");
        response.getWriter().println("session=" + request.getSession(true).getId());
        response.getWriter().println("</html>");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException    {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<html>");

        response.getWriter().println("<h1>Thanks for the form data</h1>");
//        response.getWriter().println("session=" + request.getSession(true).getId());
        response.getWriter().println("</html>");
    }
}