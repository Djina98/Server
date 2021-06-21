/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.components.table;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.User;

/**
 *
 * @author Djina
 */
public class UsersTableModel extends AbstractTableModel{
    ArrayList<User> users = new ArrayList<>();
    String columnNames[]={"Firstname","Lastname","Username"};
    
    public UsersTableModel(ArrayList<User> users){
        this.users = users;
    }

    @Override
    public int getRowCount() {
        if(users == null){
            return 0;
        }
        
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public String getColumnName(int column) {
         if(column > columnNames.length){
            return "n/a";
        }
        
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = users.get(rowIndex);
        switch(columnIndex){
            case 0: return user.getFirstname();
            case 1: return user.getLastname();
            case 2: return user.getUsername();
            default: return "n/a";
        }
    }
    
    public void setUsers(ArrayList<User> users) {
        this.users = users;
        fireTableDataChanged();
    }
    
}
