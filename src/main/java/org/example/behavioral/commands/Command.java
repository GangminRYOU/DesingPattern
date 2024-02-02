package org.example.behavioral.commands;

public abstract class Command {
    public Editor editor;
    public String backup;

    Command(Editor editor){
        this.editor = editor;
    }

    void backup(){
        backup = editor.getTextField().getText();
    }

    public void undo(){
        editor.getTextField().setText(backup);
    }

    public abstract boolean execute();
}
