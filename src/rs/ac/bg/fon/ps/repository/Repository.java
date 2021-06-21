/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository;

import java.util.List;
import rs.ac.bg.fon.ps.domain.GenericEntity;

/**
 *
 * @author Djina
 */
public interface Repository {
    
    List<GenericEntity> getAll(GenericEntity entity) throws Exception;

    Long add(GenericEntity entity) throws Exception;
    
    void edit(GenericEntity entity) throws Exception;

    void delete(GenericEntity entity) throws Exception;
    
    void deleteItem(GenericEntity entity) throws Exception;
    
    GenericEntity getByID(GenericEntity entity) throws Exception;

    List<GenericEntity> getByCondition(GenericEntity entity) throws Exception;
    
    
    
}
