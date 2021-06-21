/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.controller;


import rs.ac.bg.fon.ps.form.FrmMain;
import rs.ac.bg.fon.ps.form.FrmSettings;
import rs.ac.bg.fon.ps.form.FrmSoftware;




/**
 *
 * @author Djina
 */
public class Coordinator {
    private static Coordinator instance;
    private final MainController mainController;
    
    private Coordinator() {
        mainController = new MainController(new FrmMain()) ;
    }
    
    public static Coordinator getInstance(){
        if(instance == null){
            instance = new Coordinator();
        }
        
        return instance;
    }
    
    public void openMainForm(){
        mainController.openForm();
    }
    
    public MainController getMainContoller() {
        return mainController;
    }
    
    public void openSettingsForm() {   
        FrmSettings frmSettings = new FrmSettings(mainController.getFrmMain(), true);
        SettingsController settingsController = new SettingsController(frmSettings);
        settingsController.openForm();
    }
    
    public void openSoftwareForm() {   
        FrmSoftware frmSoftware = new FrmSoftware(mainController.getFrmMain(), true);
        SoftwareController softwareController = new SoftwareController(frmSoftware);
        softwareController.openForm();
    }
    
    
    
}
