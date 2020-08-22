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
        while (true) {
            Scanner input = new Scanner(System.in);
            command = input.nextLine();
            if (command.equals("bye")) {
                break;
            }
            System.out.println("___________________________________");
            System.out.println(command);
            System.out.println("___________________________________");
        }
        System.out.println("___________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("___________________________________");
    }
}
