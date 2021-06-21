/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.student;

import java.util.ArrayList;
import rs.ac.bg.fon.ps.domain.CourseItem;
import rs.ac.bg.fon.ps.domain.GenericEntity;
import rs.ac.bg.fon.ps.domain.Student;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author Djina
 */
public class DeleteStudent extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        if (!(param instanceof Student)) {
            throw new Exception("Object is not valid!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Student student = (Student) param;
        ArrayList<CourseItem> items = student.getItems();
        
        for (CourseItem item : items) {
            repository.deleteItem((GenericEntity) item);
        }
        
        repository.delete((GenericEntity) param);
    }
    
}
