package com.sebas.filechooser;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
 
public class FileChooser extends JFrame {
 
    private JPanel contentPane;
    private JTextField textField;
    private JTextArea textArea;
    private JFileChooser fc;
   
    public JFileChooser getFc() {
		return fc;
	}

	/**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	FileChooser frame = new FileChooser();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
 
    /**
     * Create the frame.
     */
    public FileChooser() {
 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
 
        textField = new JTextField();
        textField.setToolTipText("Insert XML file");
        textField.setBounds(52, 26, 209, 20);
        contentPane.add(textField);
        textField.setColumns(10);
 
        JButton btnSeleccionar = new JButton("Choose XML file...");
        btnSeleccionar.setBounds(288, 25, 109, 23);
        contentPane.add(btnSeleccionar);
 
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBounds(52, 76, 360, 156);
 
        JScrollPane scroll=new JScrollPane(textArea);
        scroll.setBounds(52, 76, 360, 156);
        contentPane.add(scroll);
 
        btnSeleccionar.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){

            	fc = new JFileChooser();
            	FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.XML", "xml");
            	fc.setFileFilter(filtro);
            	int seleccion=fc.showOpenDialog(contentPane);
            	 
            	if(seleccion==JFileChooser.APPROVE_OPTION){
            	 
            	    File fichero=fc.getSelectedFile();

            	    textField.setText(fichero.getAbsolutePath());
            	 
            	    try(FileReader fr=new FileReader(fichero)){
            	        String cadena="";
            	        int valor=fr.read();
            	        while(valor!=-1){
            	            cadena=cadena+(char)valor;
            	            valor=fr.read();
            	        }
            	        textArea.setText(cadena);
            	    } catch (IOException e1) {
            	        e1.printStackTrace();
            	    }
            	}
            }
        });
 
    }
}