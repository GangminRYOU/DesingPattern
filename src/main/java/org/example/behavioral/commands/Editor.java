package org.example.behavioral.commands;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Editor {
    @Getter
    private JTextArea textField;
    @Getter
    private String clipboard;
    private CommandHistory history = new CommandHistory();

    public void init(){
        JFrame jFrame = new JFrame("Text editor (type & use buttons, Luke!)");
        JPanel content = new JPanel();
        jFrame.setContentPane(content);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        textField = new JTextArea();
        textField.setLineWrap(true);
        content.add(textField);
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton ctrlC = new JButton("Ctrl+C");
        JButton ctrlX = new JButton("Ctrl+X");
        JButton ctrlV = new JButton("Ctrl+V");
        JButton ctrlZ = new JButton("Ctrl+Z");
        Editor editor = this;
        ctrlC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeCommand(new CopyCommand(editor));
            }
        });

        ctrlX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeCommand(new CutCommand(editor));
            }
        });
        ctrlV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeCommand(new PasteCommand(editor));
            }
        });
        ctrlZ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                undo();
            }
        });
    }

    public void saveOnClipBoard(String text){
        clipboard = text;
    }

    public void executeCommand(Command command){
        if(command.execute()) {
            history.push(command);
        }
    }

    private void undo(){
        if(history.isEmpty()) return;
        Command command = history.pop();
        if(command != null){
            command.undo();
        }
    }
}
