import com.rev.utils.models.Response;
import com.rev.utils.models.controllers.WorkerController;
import io.javalin.http.Context;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MockingTests {
    WorkerController wc;
    Context ctx;
    Response response;

    @Test
    public void canMakeWorkerController() throws IOException, SQLException {
        wc = new WorkerController();
        Assert.assertNotNull(wc);
        Assert.assertTrue(wc instanceof  WorkerController);
    }


    public MockingTests() throws IOException, SQLException {
    }

//    @Test
//    public void loginPostTest = ctx ->{
//        when(ctx.formParam("emailAddr")).thenReturn("test");
//        when(ctx.formParam("pw")).thenReturn("test");
//        verify(ctx).redirect("/emp_story_choice");
//
//    }

    @Test
    public void canMakeResponse() {
        response = new Response();
        Assert.assertNotNull(response);
        Assert.assertTrue(response instanceof Response);
    }

    @Test
    public void canFillResponse() {
        response = new Response();
        response.setType("test type");
        response.setMessage("test message");
        response.setBody("test body");
        Assert.assertNotNull(response.getType());
        Assert.assertNotNull(response.getMessage());
        Assert.assertNotNull(response.getBody());
    }
}
