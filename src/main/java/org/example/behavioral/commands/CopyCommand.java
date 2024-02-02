package org.example.behavioral.commands;

public class CopyCommand extends Command{
    public CopyCommand(Editor editor){
        super(editor);
    }
    @Override
    public boolean execute() {
        editor.saveOnClipBoard(editor.getTextField().getSelectedText());
        return false;
    }
}
