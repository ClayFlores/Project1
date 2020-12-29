import com.rev.utils.models.User;
import io.javalin.Javalin;
import org.junit.Assert;
import org.junit.Test;

public class JavalinTests {
    private Javalin app;

    @Test
    public void canCreateJavalinObj() {
        app = Javalin.create().start(7000);
        Assert.assertNotNull(app);
        Assert.assertTrue(app instanceof Javalin);
    }
    @Test
    public void canCreateUser() {
        User user = new User();
        user.setPassword("test");
        user.setEmail("test");
        user.setfName("test");
        user.setlName("test");
        Assert.assertNotNull(user);
        Assert.assertTrue(user instanceof User);
    }
}
