/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.course;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Course;
import rs.ac.bg.fon.ps.domain.CourseItem;
import rs.ac.bg.fon.ps.domain.GenericEntity;
import rs.ac.bg.fon.ps.domain.Lecturer;
import rs.ac.bg.fon.ps.domain.Location;
import rs.ac.bg.fon.ps.domain.Student;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author Djina
 */
public class GetCourses extends AbstractGenericOperation{

    private List<GenericEntity> list;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (!(param instanceof Course)) {
            throw new Exception("Object is not valid!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        list = repository.getAll((GenericEntity) new Course());
                
        for (GenericEntity entityCourse : list) {
            Course course = (Course) entityCourse;
            
            course.setLecturer((Lecturer) repository.getByID((GenericEntity) course.getLecturer()));
            course.setLocation((Location) repository.getByID((GenericEntity) course.getLocation()));
            course.setUser((User) repository.getByID((GenericEntity) course.getUser()));
            
            CourseItem courseItem = new CourseItem();
            courseItem.setCourse(course);
            
            List<GenericEntity> items = repository.getByCondition((GenericEntity) courseItem);

            ArrayList<CourseItem> courseItems = new ArrayList<>();
            for (GenericEntity entityCourseItem : items) {
                CourseItem ci = (CourseItem) entityCourseItem;
                ci.setCourse(course);
                ci.setStudent((Student) repository.getByID((GenericEntity) ci.getStudent()));   
                courseItems.add(ci);
            }
            course.setCourseItems(courseItems);
        }
    }
    
    public List<GenericEntity> getList() {
        return list;
    }
    
}
