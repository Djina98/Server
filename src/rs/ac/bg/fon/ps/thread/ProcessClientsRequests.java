
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.thread;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.communication.Receiver;
import rs.ac.bg.fon.ps.communication.Request;
import rs.ac.bg.fon.ps.communication.Response;
import rs.ac.bg.fon.ps.communication.Sender;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.controller.Coordinator;
import rs.ac.bg.fon.ps.controller.MainController;
import rs.ac.bg.fon.ps.domain.Course;
import rs.ac.bg.fon.ps.domain.GenericEntity;
import rs.ac.bg.fon.ps.domain.Student;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.session.ClientSession;

/**
 *
 * @author Korisnik
 */
public class ProcessClientsRequests extends Thread {

    private Socket socket;
    Sender sender;
    Receiver receiver;

    public ProcessClientsRequests(Socket socket) {
        this.socket = socket;        
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }
    
    

    @Override
    public void run() {
        

        while (!isInterrupted()) {
            try {
                Request request = (Request) receiver.receive();
                Response response = new Response();
                try {
                   switch(request.getOperation()){
                        case LOGIN:
                            
                            try {
                                GenericEntity object = Controller.getInstance().logInUser((GenericEntity) request.getArgument());

                                if (ClientSession.getInstance().getUsers().contains(object)) {
                                    response.setException(new Exception("This user is already logged in!"));
                                } else {
                                    response.setResult(object);
                                    ClientSession.getInstance().addUser(object);
                                    Coordinator.getInstance().getMainContoller().refreshTable();
                                    
                                }
                            } catch (Exception ex) {
                                Logger.getLogger(ProcessClientsRequests.class.getName()).log(Level.SEVERE, null, ex);
                                response.setException(ex);
                            }
                   
                            break;
                        case GET_ALL_FACULTIES:
                            
                            try {
                                List<GenericEntity> faculties = Controller.getInstance().getAllFaculties();
                                response.setResult(faculties);
                            } catch (Exception ex) {
                                Logger.getLogger(ProcessClientsRequests.class.getName()).log(Level.SEVERE, null, ex);
                                response.setException(ex);
                            }
                            break;
                        case GET_ALL_STUDENTS:
                            
                            try {
                                List<GenericEntity> students = Controller.getInstance().getAllStudents();
                                response.setResult(students);
                            } catch (Exception ex) {
                                Logger.getLogger(ProcessClientsRequests.class.getName()).log(Level.SEVERE, null, ex);
                                response.setException(ex);
                            }
                            break;
                        case GET_ALL_LOCATIONS:
                            
                            try {
                                List<GenericEntity> locations = Controller.getInstance().getAllLocations();
                                response.setResult(locations);
                            } catch (Exception ex) {
                                Logger.getLogger(ProcessClientsRequests.class.getName()).log(Level.SEVERE, null, ex);
                                response.setException(ex);
                            }
                            break;
                        case GET_ALL_LECTURERS:
                            
                            try {
                                List<GenericEntity> lecturers = Controller.getInstance().getAllLecturers();
                                response.setResult(lecturers);
                            } catch (Exception ex) {
                                Logger.getLogger(ProcessClientsRequests.class.getName()).log(Level.SEVERE, null, ex);
                                response.setException(ex);
                            }
                            break;
                        case ADD_STUDENT:
                            
                            try {
                                long index = Controller.getInstance().addStudent((GenericEntity) request.getArgument());
                                response.setResult(index);
                            } catch (Exception ex) {
                                Logger.getLogger(ProcessClientsRequests.class.getName()).log(Level.SEVERE, null, ex);
                                response.setException(ex);
                            }
                            break;
                        case ADD_LECTURER:
                            
                            try {
                                long index = Controller.getInstance().addLecturer((GenericEntity) request.getArgument());
                                response.setResult(index);
                            } catch (Exception ex) {
                                Logger.getLogger(ProcessClientsRequests.class.getName()).log(Level.SEVERE, null, ex);
                                response.setException(ex);
                            }
                            break;
                        case EDIT_STUDENT:
                           
                            try {
                                Controller.getInstance().editStudent((GenericEntity) request.getArgument());                   
                            } catch (Exception ex) {
                                Logger.getLogger(ProcessClientsRequests.class.getName()).log(Level.SEVERE, null, ex);
                                response.setException(ex);
                            }
                            break;
                        case DELETE_STUDENT:
                            
                            try {
                                Controller.getInstance().deleteStudent((GenericEntity) request.getArgument());             
                            } catch (MySQLIntegrityConstraintViolationException exp) {
                                System.out.println("SQL EXCEPTION:" + exp.getMessage());
                                response.setException(exp);
                            } catch (Exception ex) {
                                Logger.getLogger(ProcessClientsRequests.class.getName()).log(Level.SEVERE, null, ex);
                                response.setException(ex);
                            }
                            break;
                        case ADD_COURSE:
                            
                            try {
                                long index = Controller.getInstance().addCourse((GenericEntity) request.getArgument());
                                response.setResult(index);
                            } catch (Exception ex) {
                                Logger.getLogger(ProcessClientsRequests.class.getName()).log(Level.SEVERE, null, ex);
                                response.setException(ex);
                            }
                            break;
                        case GET_ALL_COURSES:
                            
                            try {
                                List<GenericEntity> courses = Controller.getInstance().getAllCourses();
                                response.setResult(courses);
                            } catch (Exception ex) {
                                Logger.getLogger(ProcessClientsRequests.class.getName()).log(Level.SEVERE, null, ex);
                                response.setException(ex);
                            }
                            break;
                        case DELETE_COURSE:
                            
                            try {
                                Controller.getInstance().deleteCourse((GenericEntity) request.getArgument());             
                            } catch (MySQLIntegrityConstraintViolationException exp) {
                                System.out.println("SQL EXCEPTION:" + exp.getMessage());
                                response.setException(exp);
                            } catch (Exception ex) {
                                Logger.getLogger(ProcessClientsRequests.class.getName()).log(Level.SEVERE, null, ex);
                                response.setException(ex);
                            }
                            break;
                        case EDIT_COURSE:
                            try {
                                Controller.getInstance().editCourse((GenericEntity) request.getArgument());                   
                            } catch (Exception ex) {
                                Logger.getLogger(ProcessClientsRequests.class.getName()).log(Level.SEVERE, null, ex);
                                response.setException(ex);
                            }
                            break;
                        case LOGOUT:
                            try {
                                Controller.getInstance().logOutUser((GenericEntity) request.getArgument());                               
                                response.setResult(true);
                                Coordinator.getInstance().getMainContoller().refreshTable();

                            } catch (Exception ex) {
                                Logger.getLogger(ProcessClientsRequests.class.getName()).log(Level.SEVERE, null, ex);
                                response.setResult(false);
                                response.setException(ex);
                            }
                            break;
                    } 
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    response.setException(e);
                }
                sender.send(response);
            } catch (Exception ex) {
                Logger.getLogger(ProcessClientsRequests.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Client is no longer connected!");
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

}
