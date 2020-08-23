import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("___________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("___________________________________");
        String command;
        Task[] tasks = new Task[100];
        int numberOfCommands = 0;
        boolean isFinished = false;
        while (!isFinished) {
            Scanner input = new Scanner(System.in);
            command = input.nextLine();
            if (command.equals("bye")) {
                isFinished = true;
            }else if (command.startsWith("done")) {
                String[] splitCommand = command.split(" ");
                int taskNumber = Integer.parseInt(splitCommand[1]);
                System.out.println("___________________________________");
                tasks[(taskNumber - 1)].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + tasks[(taskNumber - 1)].getStatusIcon() + "] "
                        + tasks[(taskNumber - 1)].description);
                System.out.println("___________________________________");
            } else if (command.equals("list")) {
                System.out.println("___________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < numberOfCommands; i++) {
                    System.out.println((i + 1) + ".[" + tasks[i].getStatusIcon() + "] "
                            + tasks[i].description);
                }
                System.out.println("___________________________________");
            } else {
                Task newTask = new Task(command);
                tasks[numberOfCommands] = newTask;
                numberOfCommands++;
                System.out.println("___________________________________");
                System.out.println("added: " + command);
                System.out.println("___________________________________");
            }
        }
        System.out.println("___________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("___________________________________");
    }
}
