import com.rev.utils.DAO;

import com.rev.utils.models.controllers.WorkerController;
import io.javalin.Javalin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;


public class JavalinMain {
    public static final Logger logger = LogManager.getLogger(JavalinMain.class.getName());
    public static final Logger rootLogger = LogManager.getRootLogger();

    public static void main(String[] args) {

        try {
            logger.always().log("Program Starting up...");
            DAO dao = new DAO();

            Javalin app = Javalin
                    .create(config -> {
                        config.addStaticFiles("/public/"); // get javalin to serve our static files from the public folder.
                        // which I added to the resources directory
                        config.contextPath = "/login";   // set the application context path will result in
                        // http://localhost:8000/login
                    })
                    .start(8000);
            logger.always().log("Javalin app started...");

           app.post("login", WorkerController.login);
           // app.post("localhost:8000/login", LoginController.handleLoginPost);
            //
           // app.post("localhost:8000/emp_story_choice", WorkerController.)


        } catch (SQLException | FileNotFoundException throwables) {
            System.out.println("Error with connection or File not found");
            logger.error("IO / sql exception");
        } catch (IOException e) {
            System.out.println("IO exception");
            logger.error("IO error");
        }
    }
}
