/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.start;

import rs.ac.bg.fon.ps.controller.Coordinator;
import rs.ac.bg.fon.ps.controller.MainController;

/**
 *
 * @author Djina
 */
public class Start {
    public static void main(String[] args) {
        Coordinator.getInstance().openMainForm();
    }
}
