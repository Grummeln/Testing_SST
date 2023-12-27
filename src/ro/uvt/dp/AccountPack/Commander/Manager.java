package ro.uvt.dp.AccountPack.Commander;

import ro.uvt.dp.AccountPack.Operations;

import java.util.Stack;

public class Manager {
    Stack<Operations> UndoStack = new Stack<>();
    Stack<Operations> RedoStack;

    public Manager(){}

    public void executeOp(Operations op) throws Exception {
        RedoStack = new Stack<>();
        op.execute();
        UndoStack.push(op);
    }

    public void undo() throws Exception {
        if(UndoStack.size() <= 0) {return;}
        UndoStack.peek().undo();
        RedoStack.push(UndoStack.peek());
        UndoStack.pop();
    }

    public void redo() throws Exception {
        if(RedoStack.size() <= 0) {return;}
        RedoStack.peek().redo();
        UndoStack.push(RedoStack.peek());
        RedoStack.pop();
    }
}
