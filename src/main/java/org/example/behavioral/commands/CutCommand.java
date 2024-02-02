package org.example.behavioral.commands;

public class CutCommand extends Command{ 
    public CutCommand(Editor editor) {
        super(editor);
    }

    @Override
    public boolean execute() {
        if(editor.getTextField().getSelectedText().isEmpty()) return false;
        backup();
        String source = editor.getTextField().getText();
        editor.saveOnClipBoard(editor.getTextField().getSelectedText());
        editor.getTextField().setText(cutString(source));
        return false;
    }
    
    private String cutString(String source){
        String start = source.substring(0, editor.getTextField().getSelectionStart());
        String end = source.substring(editor.getTextField().getSelectionEnd());
        return start + end;
    }
}
