/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.lecturer;

import java.util.List;
import rs.ac.bg.fon.ps.domain.GenericEntity;
import rs.ac.bg.fon.ps.domain.Lecturer;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author Djina
 */
public class GetLecturers extends AbstractGenericOperation{
    private List<GenericEntity> list;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (!(param instanceof Lecturer)) {
            throw new Exception("Object is not valid!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        list = repository.getAll((GenericEntity) new Lecturer());
    }
    
    public List<GenericEntity> getList() {
        return list;
    }
}
