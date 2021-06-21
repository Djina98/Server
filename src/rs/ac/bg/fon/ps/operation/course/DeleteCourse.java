/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.course;

import rs.ac.bg.fon.ps.domain.Course;
import rs.ac.bg.fon.ps.domain.CourseItem;
import rs.ac.bg.fon.ps.domain.GenericEntity;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author Djina
 */
public class DeleteCourse extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        if (!(param instanceof Course)) {
            throw new Exception("Object is not valid!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        
        Course course = (Course) param;
        
        for (CourseItem item : course.getCourseItems()) { 
            repository.delete((GenericEntity) item);
        }
        
        repository.delete((GenericEntity) param);
    }
    
}
