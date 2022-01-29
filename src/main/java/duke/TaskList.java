package duke;

import duke.task.Task;

import java.io.Serializable;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TaskList implements Serializable {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(TaskList tasks) {
        this.tasks = new ArrayList<>(tasks.tasks);
    }

    public Task mark(int index) {
        Task task = this.tasks.get(index);
        task.mark();
        return task;
    }

    public Task unmark(int index) {
        Task task = this.tasks.get(index);
        task.unmark();
        return task;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public int count() {
        return this.tasks.size();
    }

    public Task remove(int index) {
        Task task = this.tasks.get(index);
        this.tasks.remove(index);
        return task;
    }

    @Override
    public String toString() {
        List<String> taskStrings = IntStream.range(0, tasks.size())
                .mapToObj(i -> (i + 1) + ". " + tasks.get(i).toString())
                .collect(Collectors.toList());
        return String.join("\n", taskStrings);
    }

    public Task deleteTask(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);
        return task;
    }

    public boolean isEmpty() {
        return this.tasks.size() == 0;
    }

    public TaskList findTasks(String keyword) {
        TaskList tl = new TaskList(this);
        tl.tasks.removeIf(task -> !task.descriptionContains(keyword));
        return tl;
    }
}
