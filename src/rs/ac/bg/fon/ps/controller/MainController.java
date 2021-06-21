/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.controller;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.components.table.UsersTableModel;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.form.FrmMain;
import rs.ac.bg.fon.ps.repository.db.DBConnectionFactory;
import rs.ac.bg.fon.ps.session.ClientSession;
import rs.ac.bg.fon.ps.thread.Server;
import rs.ac.bg.fon.ps.util.Constants;
import rs.ac.bg.fon.ps.util.PropertiesLoader;

/**
 *
 * @author Djina
 */
public class MainController {
    FrmMain frmMain;
    private Server server;
    
    
    public MainController(FrmMain frmMain) {
        this.frmMain = frmMain;
        UsersTableModel utm = new UsersTableModel(ClientSession.getInstance().getUsers());
        frmMain.getTblUsers().setModel(utm);
        addActionListeners();
    }
    
    
    public void refreshTable() {
        UsersTableModel utm = (UsersTableModel) frmMain.getTblUsers().getModel();
        utm.setUsers(ClientSession.getInstance().getUsers());
    }

    private void addActionListeners() {
        frmMain.jmiSettingsAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinator.getInstance().openSettingsForm();
            }
        });
        
        frmMain.btnStartServerAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (server == null || !server.isAlive()) {
                    try {
                        server = new Server(Integer.parseInt(PropertiesLoader.getInstance().getProperty(Constants.PORT)));
                        server.start();

                        DBConnectionFactory.getInstance().getConnection();

                        frmMain.getTxtStatus().setText("Server is listening");
                        frmMain.getTxtStatus().setForeground(Color.green);
                        frmMain.getBtnStartServer().setEnabled(false);
                        frmMain.getBtnStopServer().setEnabled(true);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frmMain, "Port " + PropertiesLoader.getInstance().getProperty(Constants.PORT) + " is already taken.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(frmMain, "Invalid data for database connection.", "Error", JOptionPane.INFORMATION_MESSAGE);

                        try {
                            server.stopServer();
                            server = null;
                        } catch (IOException ex1) {
                            JOptionPane.showMessageDialog(frmMain, "Unable to stop server!", "Error", JOptionPane.INFORMATION_MESSAGE);
                            System.exit(0);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(frmMain, "Server is already listening on this port.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        frmMain.btnStopServerAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    server.stopServer();
                    server = null;
                    DBConnectionFactory.getInstance().closeConnection();
                    
                    frmMain.getTxtStatus().setText("Server is stopped");
                    frmMain.getTxtStatus().setForeground(Color.red);
                    frmMain.getBtnStartServer().setEnabled(true);
                    frmMain.getBtnStopServer().setEnabled(false);

                    ClientSession.getInstance().logoutAll();                  
                    refreshTable();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frmMain, "Unable to stop server.", "Error", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frmMain, "Unable to close database", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }

            
        });
        
        frmMain.jmiAboutAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinator.getInstance().openSoftwareForm();
            }
        });
        
    }

    void openForm() {
        frmMain.setVisible(true);
        frmMain.setLocationRelativeTo(null);
    }

    public FrmMain getFrmMain() {
        return frmMain;
    }
    

}
