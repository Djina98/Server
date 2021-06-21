/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import rs.ac.bg.fon.ps.controller.Coordinator;
import rs.ac.bg.fon.ps.form.FrmSettings;
import rs.ac.bg.fon.ps.util.Constants;
import rs.ac.bg.fon.ps.util.PropertiesLoader;

/**
 *
 * @author Djina
 */
public class SettingsController {
    
    FrmSettings frmSettings;
   
    
    SettingsController(FrmSettings frmSettings) {
        this.frmSettings = frmSettings;
        addActionListeners();
    }

    private void addActionListeners() {
        frmSettings.addBtnSaveActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newUrl = frmSettings.getTxtURL().getText().trim();
                String newUsername = frmSettings.getTxtUsername().getText().trim();
                String newPassword = String.valueOf(frmSettings.getTxtPassword().getPassword());
                String newPort = frmSettings.getTxtPort().getText().trim();
                
                if(newUrl.isEmpty() || newUsername.isEmpty() || newPort.isEmpty()){
                    JOptionPane.showMessageDialog(frmSettings, "You need to input all data." , "Warning" , JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                
                try {
                    Integer.parseInt(newPort);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frmSettings, "Port must be a number.", "Warnings", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                
                
                PropertiesLoader.getInstance().setProperty(Constants.URL, newUrl);
                PropertiesLoader.getInstance().setProperty(Constants.USERNAME, newUsername);
                PropertiesLoader.getInstance().setProperty(Constants.PASSWORD, newPassword);
                PropertiesLoader.getInstance().setProperty(Constants.PORT, newPort);
                
                try {
                    PropertiesLoader.getInstance().saveProperties();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmSettings, "Unable to save properties data", "Warning", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                JOptionPane.showMessageDialog(frmSettings, "Properties data is successfully saved", "Success" ,JOptionPane.INFORMATION_MESSAGE);
                frmSettings.dispose();
            }

            

        });
    }

    void openForm() {
        loadDataFromProperties();
        frmSettings.setLocationRelativeTo(Coordinator.getInstance().getMainContoller().getFrmMain());
        frmSettings.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frmSettings.setVisible(true);
    }
    
    

    private void loadDataFromProperties() {
        String username = PropertiesLoader.getInstance().getProperty(Constants.USERNAME);
        String password = PropertiesLoader.getInstance().getProperty(Constants.PASSWORD);
        String port = PropertiesLoader.getInstance().getProperty(Constants.PORT);
        String url = PropertiesLoader.getInstance().getProperty(Constants.URL);
        
        frmSettings.getTxtURL().setText(url);
        frmSettings.getTxtUsername().setText(username);
        frmSettings.getTxtPassword().setText(password);
        frmSettings.getTxtPort().setText(port);
    }
    
}
