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
        String[] commands = new String[100];
        int numberOfCommands = 0;
        while (true) {
            Scanner input = new Scanner(System.in);
            command = input.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                for (int i = 0; i < numberOfCommands; i++) {
                    System.out.println((i + 1) + ". " + commands[i]);
                }
                System.out.println("___________________________________");
            } else {
                commands[numberOfCommands] = command;
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
