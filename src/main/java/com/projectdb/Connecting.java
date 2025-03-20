//class for establishing sql connectivity->
package com.projectdb;

import java.sql.*;
import javax.swing.*;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.event.*;

//class for submission of values:
class Response implements ActionListener {
    public FormWindow form;
    String name;
    String email;
    String roll;
    String grp;
    Vector<StringBuffer> cboxes;
    StringBuffer subs;

    public Response(){
        form = new FormWindow();
        form.jb.addActionListener(this);
    }
    
    static String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();){
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()){
                return button.getText();
            }
        }
        return null;
    }

    static Vector<StringBuffer> getCheckboxText(JCheckBox[] checkGroup){
        Vector<StringBuffer> retval = new Vector<StringBuffer>(1);
        for (JCheckBox i : checkGroup){
            if (i.isSelected()){
                retval.add(new StringBuffer(i.getText()));
            }
        }
        if (retval.size()==0)
            return null;
        else
            return retval;
    } 

    @Override
    public void actionPerformed(ActionEvent ae){
        name = form.tname.getText();
        email = form.temail.getText();
        roll = form.troll.getText();
        grp = getSelectedButtonText(form.group);
        cboxes = getCheckboxText(form.cbarr);
        subs = new StringBuffer();
        for (StringBuffer i : cboxes){
            if (i != cboxes.lastElement()){
                subs.append(i);
                subs.append(", ");
            }
            else
                subs.append(i);
        }
        form.fm.dispose();
        //System.out.println(name+email+roll+grp+cboxes);
    }
}

class Values extends Thread {
    Response res;
    Values(){
        res = new Response();
    }

    @Override
    public void run(){
        synchronized(res){}
    }
}

public class Connecting{
    //important constants for connection
    private static final String username = "system";
    private static final String password = "abc123";
    private static final String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
    
    public Connecting(){
        //creating connection
        try(Connection cnt = DriverManager.getConnection(url, username, password);){
            // checking if table exists
            boolean exists = cnt.getMetaData().getTables(null, null, "EXAM", null).next();
            PreparedStatement stm = null;
            if (exists == false){
                String relation = "CREATE TABLE EXAM " + "(Enroll INTEGER PRIMARY KEY, " + "Name VARCHAR2(20), " + "Email VARCHAR2(30), " + "Course VARCHAR2(5), " + "Subjects VARCHAR2(100))";
                stm = cnt.prepareStatement(relation);
                stm.executeUpdate(relation);
                System.out.println("Table Created.");
            }
            //inserting values
            Values th = new Values();
            th.start();
            try{
                th.join();
                String insert = "INSERT INTO EXAM(Enroll, Name, Email, Course, Subjects) VALUES(?, '?', '?', '?', '?')";
                stm = cnt.prepareStatement(insert);
                stm.setInt(1, Integer.parseInt(th.res.roll));
                stm.setString(2, th.res.name);
                stm.setString(3, th.res.email);
                    stm.setString(4, th.res.grp);
                stm.setString(5, th.res.subs.toString());
                stm.executeUpdate(insert);
            } catch(InterruptedException e){System.out.println(e);}
        } 
        
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
