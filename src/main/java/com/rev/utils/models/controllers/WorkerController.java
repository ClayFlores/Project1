package com.rev.utils.models.controllers;

import com.rev.utils.DAO;
import com.rev.utils.models.Response;
import com.rev.utils.models.User;
import io.javalin.http.Handler;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WorkerController {
    final static List<String> workers =  new ArrayList<String>();
    static DAO dao;

    static {
        try {
            dao = new DAO();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * a begin handler that uses a context to
     */
    public static Handler begin = ctx ->{
        Response response =  new Response();

        try {
            if(ctx.body() != null && ctx.method().equalsIgnoreCase("PUT")) {
                ;
                ctx.status(200);
                response.setType("Access Granted");
                response.setMessage("so far so good...");

            } else {
                    ctx.status(403);
                    response.setType("Access Denied");
                    response.setMessage("Invalid account / password");
            }
            ctx.json(response);

        } catch (Exception ex) {
            ex.printStackTrace();
            ctx.status(500);
            response.setType("Error");
            response.setMessage(ex.getMessage());
            ctx.json(response);
        }
    };

    /**
     * Attempts to handle a post request by checking the entered email address and password against
     * the database.
     * Should redirect to the next page i think? i dont even know anymore
     *
     */
    public static Handler login = ctx -> {
        Response response = new Response();
        User emp =  new User();
      if (dao.login(ctx.formParam("emailAddr"), ctx.formParam("pw"))) {
            emp.setEmail( ctx.formParam("emailAddr"));
            emp.setPassword(ctx.formParam("pw"));

            ctx.redirect("/emp_story_choice");
      }
    };

    public WorkerController() throws IOException, SQLException {
    }
}
