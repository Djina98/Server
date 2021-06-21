/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.student;

import rs.ac.bg.fon.ps.domain.GenericEntity;
import rs.ac.bg.fon.ps.domain.Student;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author Djina
 */
public class AddStudent extends AbstractGenericOperation{

    long index = -1;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (!(param instanceof Student)) {
            throw new Exception("Object is not valid!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        index = repository.add((GenericEntity) param);
    }
    
    public Long getIndex() {
        return index;
    }
    
}
