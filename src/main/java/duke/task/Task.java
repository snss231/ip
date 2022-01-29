package duke.task;

import java.io.Serializable;

public abstract class Task implements Serializable {

    protected String description;
    protected boolean isDone = false;

    Task(String description) {
        this.description = description;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
