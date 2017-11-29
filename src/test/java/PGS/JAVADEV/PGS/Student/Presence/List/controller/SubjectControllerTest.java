package PGS.JAVADEV.PGS.Student.Presence.List.controller;


import PGS.JAVADEV.PGS.Student.Presence.List.dto.Subject;

import PGS.JAVADEV.PGS.Student.Presence.List.model.SubjectEntity;

import org.junit.Test;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class SubjectControllerTest extends RestControllerTest {



    public void setUp(){
            SubjectEntity subjectEntityMath = new SubjectEntity();
            subjectEntityMath.setLecturer("Jan Kowalski");
            subjectEntityMath.setName("matematyka");
            subjectRepository.save(subjectEntityMath);


            SubjectEntity subjectEntityStatistics = new SubjectEntity();
            subjectEntityStatistics.setLecturer("Ewa Kowalska");
            subjectEntityStatistics.setName("statystyka");
            subjectRepository.save(subjectEntityStatistics);

        return;
    }


    public void setDown(){

        subjectRepository.delete(subjectRepository.findByName("matematyka"));
        subjectRepository.delete(subjectRepository.findByName("statystyka"));
    }





    @Test
    public void getAllSubjects() throws Exception {

        ResponseEntity<List<Subject>> response =  restTemplate.exchange("/pgs/subjects/", HttpMethod.GET, null, new TypeReference<List<Subject>>());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(2, response.getBody().size());

    }

    @Test
    public void getSubjectById() throws Exception {

        ResponseEntity<Subject> response =  restTemplate.exchange("/pgs/subjects/id", HttpMethod.GET, null, new TypeReference<Subject>());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals("matematyka", response.getBody().getName());

    }

    @Test
    public void deleteSubject() throws Exception {
        ResponseEntity<Subject> response =  restTemplate.exchange("/pgs/subjects/", HttpMethod.DELETE, null, new TypeReference<Subject>());
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
        assertNull(subjectRepository.findById(1));

    }

    @Test
    public void createSubject() throws Exception {
    }

    @Test
    public void updateSubject() throws Exception {
    }

}

class TypeReference<T> extends ParameterizedTypeReference<T>{

}