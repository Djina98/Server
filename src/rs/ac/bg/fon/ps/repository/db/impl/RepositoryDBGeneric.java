/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import rs.ac.bg.fon.ps.domain.GenericEntity;
import rs.ac.bg.fon.ps.repository.db.DBConnectionFactory;
import rs.ac.bg.fon.ps.repository.db.DBRepository;

/**
 *
 * @author Djina
 */
public class RepositoryDBGeneric implements DBRepository{

    @Override
    public List<GenericEntity> getAll(GenericEntity entity) throws Exception {
        
        List<GenericEntity> list = null;
        
        try {
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            String query = "SELECT * FROM " + entity.getTableName();
            System.out.println(query);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            list = entity.getList(resultSet);
            resultSet.close();
            statement.close();
            return list;
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public Long add(GenericEntity entity) throws Exception {
       try {
            String query;
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            query = "INSERT INTO " + entity.getTableName() + "(" + entity.getColumnNamesForInsert() + ")" + " VALUES(" + entity.getInsertValues() + ")";
            System.out.println(query);
            PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs != null && rs.next()) {
                return rs.getLong(1);
            } else {
                return 0L;
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void edit(GenericEntity entity) throws Exception {
        try {
            String query;
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            query = "UPDATE " + entity.getTableName() + " SET " + entity.setAttributes() + " WHERE " + entity.getUpdateCondition();
            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.commit();
            statement.close();
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void delete(GenericEntity entity) throws Exception {
        try {
            String query;
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            query = "DELETE FROM " + entity.getTableName() + " WHERE " + entity.getDeleteContidion();
            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    @Override
    public void deleteItem(GenericEntity entity) throws Exception {
        try {
            String query;
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            query = "DELETE FROM " + entity.getTableName() + " WHERE " + entity.getDeleteContidionForItem();
            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public GenericEntity getByID(GenericEntity entity) throws Exception {
        List<GenericEntity> list = null;
        try {
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            String query = "SELECT * FROM " + entity.getTableName() + " WHERE " + entity.getSelectContidion();
            System.out.println(query);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            list = entity.getList(resultSet);

            statement.close();
            return list.get(0);
        } catch (Exception ex) {
            throw new Exception("Doesn't exist!");
        }
    }

    @Override
    public List<GenericEntity> getByCondition(GenericEntity entity) throws Exception {
        List<GenericEntity> list = null;
        try {
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            String query = "SELECT * FROM " + entity.getTableName() + " WHERE " + entity.getSelectContidion();
            System.out.println(query);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            list = entity.getList(resultSet);
            resultSet.close();
            statement.close();
            return list;
        } catch (Exception ex) {
            throw ex;
        }
    }
    
}
