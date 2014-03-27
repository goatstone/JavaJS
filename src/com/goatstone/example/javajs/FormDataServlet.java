package com.goatstone.example.javajs;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by goat on 3/26/14.
 */

public class FormDataServlet extends HttpServlet {
    private String greeting = "Hello World";

    public FormDataServlet() {
    }

    public FormDataServlet(String greeting) {
        this.greeting = greeting;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<html>");

        response.getWriter().println("<h1>X" + greeting + "</h1>");
        response.getWriter().println("session=" + request.getSession(true).getId());
        response.getWriter().println("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        String firstName = request.getParameter("first_name");
        String email = request.getParameter("email");


        System.out.println(request);
        System.out.println(firstName);
//        response.getWriter().println(" Thanks for the form data ");

        // If there is an erro string returned the send it
        if (checkData(firstName, email) != null) {
            response.getWriter().println(" There are errors in the form. ");
        } else {
            response.getWriter().println(" Thanks for the form data ");
        }
    }

    private String checkData(String name, String possibleEmail) {
        String retStr = null;
        ScriptEngineManager m = new ScriptEngineManager();
        ScriptEngine nashorn;
        nashorn = m.getEngineByName("Nashorn");

        try {
            Invocable invocable = (Invocable) nashorn;
            Object validatorScript = nashorn.eval(new FileReader("war/js/goatstone/forms/Validator.js"));
            Object val = nashorn.eval("val");

            Object valEmail = invocable.invokeMethod(val, "isEmail", possibleEmail);
//            Object isUnderMax = invocable.invokeMethod(val, "isUnderMax", 5);
//            Object isOverMin = invocable.invokeMethod(val, "isOverMin", 1);

            if (valEmail.toString().equals("false")) {
                System.out.println("check bool ");
                retStr = "Email is not valid";
            }
            System.out.println("- - - - ");
            System.out.println(possibleEmail);
            System.out.println(valEmail);
//            System.out.println(isUnderMax);
//            System.out.println(isOverMin);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return retStr;
    }
}