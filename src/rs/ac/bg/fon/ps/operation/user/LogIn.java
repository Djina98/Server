/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.user;

import rs.ac.bg.fon.ps.domain.GenericEntity;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author Djina
 */
public class LogIn extends AbstractGenericOperation{

    GenericEntity object = null;
    
    @Override
    protected void preconditions(Object param) throws Exception {
         if (!(param instanceof User)) {
            throw new Exception("Object is not valid!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        object = repository.getByID((GenericEntity) param);
    }
    
    public GenericEntity getObject() {
        return object;
    }
    
}
