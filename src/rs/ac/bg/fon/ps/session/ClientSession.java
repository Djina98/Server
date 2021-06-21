/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.session;

import java.util.ArrayList;
import rs.ac.bg.fon.ps.domain.GenericEntity;
import rs.ac.bg.fon.ps.domain.User;

/**
 *
 * @author Djina
 */
public class ClientSession {
    private static ClientSession instance;
    ArrayList<User> users;

    public ClientSession() {
        users = new ArrayList<>();
    }

    public static ClientSession getInstance() {
        if (instance == null) {
            instance = new ClientSession();
        }
        return instance;
    }

    public void addUser(GenericEntity object) {
        if (object instanceof User) {
            if (!users.contains(object)) {
                users.add((User) object);
            }
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void removeUser(Object object) {
        try {
            if (object instanceof User) {
                for (User user : users) {
                    if (users.contains(object)) {
                        users.remove(object);
                    }
                }
            }
        } catch (Exception e) {
        }

    }

    public void logoutAll() {
        users = new ArrayList<>();
    }
}
