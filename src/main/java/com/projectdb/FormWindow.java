//Class used to create the form ->
package com.projectdb;

import java.awt.*;
import javax.swing.*;

public class FormWindow {
    //initialising elements
    public JFrame fm;
    public JPanel con;
    public JTextField tname , temail , troll;
    public JLabel form , name , email , roll , course , subjects;
    public String[] grp = {"CSE" , "IT" , "ECE" , "EEE"};
    public String[] subs = {"DBMS" , "Java" , "CandS" , "PSLP" , "TOC" , "EMFT" , "NAandS" , "Microprocessors" , "EM-2" , "DComms" , "PS-1" , "AE-2" , "Electronics-2"};
    public JRadioButton[] rbarr = new JRadioButton[grp.length];
    public ButtonGroup group;
    public JCheckBox[] cbarr = new JCheckBox[subs.length];
    public JButton jb;

    //constructor
    public FormWindow(){
        //setting frame
        fm = new JFrame();
        fm.setTitle("Examination Form");
        fm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fm.setResizable(true);
        fm.setBounds(new Rectangle(300,300));

        //initialising JPanel
        con = new JPanel();
        con.setLayout(null);
        con.setPreferredSize(new Dimension(500,500));

        //creating labels
        form = new JLabel("Examination Form for 4th Semester Students");
        form.setFont(new Font("Arial", Font.BOLD, 50));

        name = new JLabel("Name:");
        name.setFont(new Font("Arial", Font.PLAIN, 25));

        email = new JLabel("E-mail:");
        email.setFont(new Font("Arial", Font.PLAIN, 25));

        roll = new JLabel("Enrollment no.:");
        roll.setFont(new Font("Arial", Font.PLAIN, 25));

        course = new JLabel("Course:");
        course.setFont(new Font("Arial", Font.PLAIN, 25));

        subjects = new JLabel("Subjects:");
        subjects.setFont(new Font("Arial", Font.PLAIN, 25));

        //creating textfields
        tname = new JTextField();
        temail = new JTextField();
        troll = new JTextField();

        //creating list of courses for 4th sem
        group = new ButtonGroup();
        for (int i=0 ; i<grp.length ; i++){
            rbarr[i] = new JRadioButton(grp[i]);
            group.add(rbarr[i]);
        }

        //creating subject list for 4th sem
        for (int i=0 ; i<subs.length ; i++){
            cbarr[i] = new JCheckBox(subs[i]);
        }

        //adding elements
        con.add(form);
        con.add(name); con.add(tname);
        con.add(email); con.add(temail);
        con.add(roll); con.add(troll);
        con.add(course);
        for (int i=0 ; i<grp.length ; i++){
            con.add(rbarr[i]);
        }
        con.add(subjects);
        for (int i=0 ; i<subs.length ; i++){
            con.add(cbarr[i]);
        }

        //setting locations and sizes
        form.setSize(2000,50); form.setLocation(235,30);
        name.setSize(100,25); name.setLocation(5,150); 
        tname.setFont(new Font("Arial", Font.PLAIN, 20)); tname.setSize(300,30); tname.setLocation(100,150);
        email.setSize(100,25); email.setLocation(5,250);
        temail.setFont(new Font("Arial", Font.PLAIN, 20)); temail.setSize(300,30); temail.setLocation(100,250);
        roll.setSize(200,25); roll.setLocation(5,350);
        troll.setFont(new Font("Arial", Font.PLAIN, 20)); troll.setSize(300,30); troll.setLocation(200,350);
        course.setSize(100,25); course.setLocation(5,450);
        for (int i=0 ; i<rbarr.length ; i++){
            rbarr[i].setFont(new Font("Arial", Font.PLAIN, 15));
            rbarr[i].setSize(60,15);
            rbarr[i].setLocation(5+i*65, 480);
        }
        subjects.setSize(170,25); subjects.setLocation(5,550);
        for (int i=0 ; i<cbarr.length-5 ; i++){
            cbarr[i].setFont(new Font("Arial", Font.PLAIN, 15));
            cbarr[i].setSize(170,15);
            cbarr[i].setLocation(5+i*170, 580);
        }
        for (int i=1 ; i<=5 ; i++){
            cbarr[cbarr.length-i].setFont(new Font("Arial", Font.PLAIN, 15));
            cbarr[cbarr.length-i].setSize(170,15);
            cbarr[cbarr.length-i].setLocation(5+(i-1)*170, 600);
        }

        //creating submit button
        jb = new JButton("Submit");
        con.add(jb);
        jb.setSize(300,35);
        jb.setFont(new Font("Arial", Font.PLAIN, 30));
        jb.setLocation(600,700);

        fm.add(con);
        fm.pack();
        fm.setVisible(true);
    }
}
