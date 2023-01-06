import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {

    //frame for text editor

    JFrame frame;

    // creating the menu bar for text editor
    JMenuBar menuBar;

    // creating menu on menu bar
    JMenu file ,edit;

    JMenuItem  New , open ,save  , close;
    JMenuItem cut , copy , paste ;

    //Initialising the text Area
    JTextArea textArea;




    // Constructor

    TextEditor(){
        // creating frame
        frame = new JFrame();


        //creating MenuBar
        menuBar = new JMenuBar();

        // Creating Object of the textArea
        textArea = new JTextArea();

        //Creating the Menu of the menuBar and specify the name
        file = new JMenu("File");
        edit = new JMenu("Edit");

        // initialising the menu bar items and specify their names
        New = new JMenuItem("New");
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        close = new JMenuItem("Close");

        //Adding Action Listener to the menuItems
        New.addActionListener(this);
        open.addActionListener(this);
        save.addActionListener(this);
        close.addActionListener(this);


        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);



        // Adding all the Menubar items to their respective Menu.
        file.add(New);
        file.add(open);
        file.add(save);
        file.add(close);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);

        // Adding the Menu's on the MEnuBar
        menuBar.add(file);
        menuBar.add(edit);

        //Setting the Menu Bar in the frame
        frame.setJMenuBar(menuBar);


        //Adding the textArea into the frame
        frame.add(textArea);



        //Setting the occurance and the Dimensions of the frame
        frame.setBounds(100 ,100 ,800 , 500);
        frame.setVisible(true);


    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){

        if(actionEvent.getSource() == cut){
            textArea.cut();
        }else if(actionEvent.getSource() == copy){
            textArea.copy();
        }else if(actionEvent.getSource()==paste){
            textArea.paste();
        }

        if(actionEvent.getSource() == New){
            TextEditor textEditor = new TextEditor();
        }

        if(actionEvent.getSource() == close){
            System.exit(0);
        }

        if(actionEvent.getSource() == open){
            // Opening file chooser

                JFileChooser fileChooser = new JFileChooser("F");

                // get choose option from file choose
                int chooseOption = fileChooser.showOpenDialog(null);
                if(chooseOption == JFileChooser.APPROVE_OPTION){
                    //get selected file
                    File file = fileChooser.getSelectedFile();
                    // getting path for the selected file
                    String path = file.getPath();

                    try{
                        // Creatinng a buffer reader
                        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

                        String Intermediate = "" , output = "" ;
                        while((Intermediate = bufferedReader.readLine()) != null){
                            output += Intermediate + "\n";
                        }
                        textArea.setText(output);
                    }catch (Exception exception){
                        exception.printStackTrace();

                    }
                }

        }

        if(actionEvent.getSource() == save){

            //opening file chooser
            JFileChooser fileChooser = new JFileChooser("F");

            int chooseOption = fileChooser.showOpenDialog(null);

            if(chooseOption == JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");

                try {


                    BufferedWriter outline = new BufferedWriter(new FileWriter(file));

                    textArea.write(outline);
                    outline.close();
                }catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        }


    }
    public static void main(String[] args) {

        TextEditor texteditor = new TextEditor();
    }
}