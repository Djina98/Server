/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.controller;

import java.util.List;
import rs.ac.bg.fon.ps.domain.Course;
import rs.ac.bg.fon.ps.domain.Faculty;
import rs.ac.bg.fon.ps.domain.GenericEntity;
import rs.ac.bg.fon.ps.domain.Lecturer;
import rs.ac.bg.fon.ps.domain.Location;
import rs.ac.bg.fon.ps.domain.Student;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ps.operation.course.AddCourse;
import rs.ac.bg.fon.ps.operation.course.AddLecturer;
import rs.ac.bg.fon.ps.operation.course.DeleteCourse;
import rs.ac.bg.fon.ps.operation.course.EditCourse;
import rs.ac.bg.fon.ps.operation.course.GetCourses;
import rs.ac.bg.fon.ps.operation.faculty.GetFaculties;
import rs.ac.bg.fon.ps.operation.lecturer.GetLecturers;
import rs.ac.bg.fon.ps.operation.location.GetLocations;
import rs.ac.bg.fon.ps.operation.student.AddStudent;
import rs.ac.bg.fon.ps.operation.student.DeleteStudent;
import rs.ac.bg.fon.ps.operation.student.EditStudent;
import rs.ac.bg.fon.ps.operation.student.GetStudents;
import rs.ac.bg.fon.ps.operation.user.LogIn;
import rs.ac.bg.fon.ps.operation.user.LogOut;
import rs.ac.bg.fon.ps.repository.Repository;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDBGeneric;

/**
 *
 * @author Djina
 */
public class Controller {
    private static Controller instance;
    private Repository repository;
    
    private Controller() {
        repository = new RepositoryDBGeneric();
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }
    
    public GenericEntity logInUser(GenericEntity entity) throws Exception {
        AbstractGenericOperation so = new LogIn();
        so.execute(entity);
        return ((LogIn) so).getObject();
    }
    
    public List<GenericEntity> getAllFaculties() throws Exception {
        AbstractGenericOperation so = new GetFaculties();
        so.execute(new Faculty());
        return ((GetFaculties) so).getList();
    }
    
    public List<GenericEntity> getAllLocations() throws Exception {
        AbstractGenericOperation so = new GetLocations();
        so.execute(new Location());
        return ((GetLocations) so).getList();
    }
    
    public List<GenericEntity> getAllLecturers() throws Exception {
        AbstractGenericOperation so = new GetLecturers();
        so.execute(new Lecturer());
        return ((GetLecturers) so).getList();
    }
    
    public List<GenericEntity> getAllStudents() throws Exception {
        AbstractGenericOperation so = new GetStudents();
        so.execute(new Student());
        return ((GetStudents) so).getList();
    }
    
    public List<GenericEntity> getAllCourses() throws Exception {
        AbstractGenericOperation so = new GetCourses();
        so.execute(new Course());
        return ((GetCourses) so).getList();
    }
    
    public Long addStudent(GenericEntity entity) throws Exception {
        AbstractGenericOperation so = new AddStudent();
        so.execute(entity);
        return ((AddStudent) so).getIndex();
    }
    
    public Long addCourse(GenericEntity entity) throws Exception {
        AbstractGenericOperation so = new AddCourse();
        so.execute(entity);
        return ((AddCourse) so).getIndex();
    }
    
    public void deleteStudent(GenericEntity entity) throws Exception {
        AbstractGenericOperation so = new DeleteStudent();
        so.execute(entity);
    }
    
    public void deleteCourse(GenericEntity entity) throws Exception {
        AbstractGenericOperation so = new DeleteCourse();
        so.execute(entity);
    }
    
    public void editStudent(GenericEntity entity) throws Exception {
        AbstractGenericOperation so = new EditStudent();
        so.execute(entity);
    }
    
    public void editCourse(GenericEntity entity) throws Exception {
        AbstractGenericOperation so = new EditCourse();
        so.execute(entity);
    }
    
    public void logOutUser(GenericEntity entity) throws Exception {
        AbstractGenericOperation so = new LogOut();
        so.execute(entity);

    }

    public long addLecturer(GenericEntity entity) throws Exception {
        AbstractGenericOperation so = new AddLecturer();
        so.execute(entity);
        return ((AddLecturer) so).getIndex();
    }

    
}
