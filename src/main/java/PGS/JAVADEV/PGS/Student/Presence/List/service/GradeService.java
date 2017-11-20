package PGS.JAVADEV.PGS.Student.Presence.List.service;

import PGS.JAVADEV.PGS.Student.Presence.List.dto.Grade;
import PGS.JAVADEV.PGS.Student.Presence.List.model.GradeEntity;
import PGS.JAVADEV.PGS.Student.Presence.List.repositories.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GradeService {

    @Autowired
    GradeRepository gradeRepository;

    public List<Grade> findAllGrades(){
        Iterable<GradeEntity> gradeEntities = gradeRepository.findAll();
        List<Grade> grades = new ArrayList<>();
        gradeEntities.forEach(grade -> grades.add(map(grade)));
        return grades;
    }
    public Grade findById(long id){
       GradeEntity gradeEntity = gradeRepository.findById(id);
        if(gradeEntity == null) {
            return null;
        }
        return map(gradeRepository.findById(id));
    }
    public void save(Grade grade){
        gradeRepository.save(map(grade));
    }
    public void delete(long id){
        gradeRepository.deleteById(id)
        ;
    }

    public GradeEntity map(Grade grade){
        GradeEntity gradeEntity = new GradeEntity();
        gradeEntity.setId(grade.getId());
        gradeEntity.setGrade(grade.getGrade());

        return  gradeEntity;
    }
    public Grade map(GradeEntity gradeEntity){
        Grade grade = new Grade();
        grade.setId(gradeEntity.getId());
        grade.setGrade(gradeEntity.getGrade());

        return grade;
    }
}
