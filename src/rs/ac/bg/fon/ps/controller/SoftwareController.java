/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.controller;

import rs.ac.bg.fon.ps.form.FrmMain;
import rs.ac.bg.fon.ps.form.FrmSoftware;

/**
 *
 * @author Djina
 */
public class SoftwareController {

    FrmSoftware frmSoftware;
    
    SoftwareController(FrmSoftware frmSoftware) {
        this.frmSoftware = frmSoftware;
    }

    void openForm() {
        frmSoftware.setLocationRelativeTo(Coordinator.getInstance().getMainContoller().getFrmMain());
        frmSoftware.setVisible(true);
    }
    
}
