package students.presence.list;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes = PgsStudentPresenceListApplication.class)
public class PgsStudentPresenceListApplicationTests {

	@Test
	public void contextLoads() {
	}

}
