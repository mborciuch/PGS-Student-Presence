package students.presence.list.controller;

import students.presence.list.PgsStudentPresenceListApplication;
import students.presence.list.repositories.CourseRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = PgsStudentPresenceListApplication.class)
public abstract class RestControllerTest {

    @Autowired
    TestRestTemplate restTemplate;
    @Autowired
    CourseRepository courseRepository;


}
