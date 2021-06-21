/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.student;

import java.util.List;
import rs.ac.bg.fon.ps.domain.Faculty;
import rs.ac.bg.fon.ps.domain.GenericEntity;
import rs.ac.bg.fon.ps.domain.Student;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author Djina
 */
public class GetStudents extends AbstractGenericOperation{

    private List<GenericEntity> list;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (!(param instanceof Student)) {
            throw new Exception("Object is not valid!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        list = repository.getAll((GenericEntity) param);
        
                
        for (GenericEntity ge : list) {
            Student student = (Student) ge;
            //System.out.println(student.getFaculty().getId());
            //System.out.println(student.getUser().getId());
            student.setFaculty((Faculty) repository.getByID((GenericEntity) student.getFaculty()));
            student.setUser((User) repository.getByID((GenericEntity) student.getUser()));
        }
    }
    
    public List<GenericEntity> getList() {
        return list;
    }
    
}
