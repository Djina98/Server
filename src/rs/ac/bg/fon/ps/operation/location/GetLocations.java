/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.location;

import java.util.List;
import rs.ac.bg.fon.ps.domain.GenericEntity;
import rs.ac.bg.fon.ps.domain.Location;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author Djina
 */
public class GetLocations extends AbstractGenericOperation{

    private List<GenericEntity> list;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (!(param instanceof Location)) {
            throw new Exception("Object is not valid!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        list = repository.getAll((GenericEntity) new Location());
    }
    
    public List<GenericEntity> getList() {
        return list;
    }
    
}
