package com.goatstone.example.javajs;

import javax.script.ScriptException;
import javax.script.*;
import java.io.FileNotFoundException;
import java.io.FileReader;


import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Created by goat on 3/25/14.
 */
public class JavaJS {

    ScriptEngineManager m = new ScriptEngineManager();
    ScriptEngine nashorn;

    public JavaJS() {

//        runNashorn();

        Server server = new Server();
        HandlerList handlers = new HandlerList();

        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(8111);
        server.addConnector(connector);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        // form_data : process data posted from forms
        context.addServlet(new ServletHolder(new FormDataServlet("hello")), "/form_data");
        handlers.addHandler(context);

        WebAppContext root = new WebAppContext();

        root.setContextPath("/");
        root.setDescriptor("war/WEB-INF/web.xml");
        root.setResourceBase("war/");
        root.setParentLoaderPriority(true);
        handlers.addHandler(root);

        server.setHandler(handlers);

        try {
            server.start();
            server.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private boolean runNashorn() {
        nashorn = m.getEngineByName("Nashorn");
        try {
            nashorn.eval("print('Hello, world')");
        } catch (ScriptException e) {
        }

        try {
            String possibleEmail = "val@Email";
            Invocable invocable = (Invocable) nashorn;

            Object validatorScript = nashorn.eval(new FileReader("war/js/goatstone/forms/Validator.js"));
            Object val = nashorn.eval("val");

            Object valEmail = invocable.invokeMethod(val, "isEmail", possibleEmail);
            Object isUnderMax = invocable.invokeMethod(val, "isUnderMax", 5);
            Object isOverMin = invocable.invokeMethod(val, "isOverMin", 1);

//            Object controllerScript = nashorn.eval(new FileReader("js/goatstone/hello8/main.js"));


            System.out.println("- - - - ");
            System.out.println(valEmail);
            System.out.println(isUnderMax);
            System.out.println(isOverMin);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("hello 8 1");
        new JavaJS();
    }
}
