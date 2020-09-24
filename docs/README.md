# User Guide
Duke is a desktop application to help you in the management of tasks.

## Before you begin:
1. Ensure that you have **Java 11** installed on your computer.
2. Ensure that you have the latest version of `Duke.jar`. You can get it from [here](https://github.com/yuen-sihao/ip/realeases).
3. Transfer the `Duke.jar` file to a suitable local directory.
4. Launch your command line terminal. For *Windows command line*, check that your font is set to **NSimSun**.
5. Navigate to the directory that stores your `Duke.jar` file.
6. Run the ```java -Dfile.encoding=UTF-8 -jar Duke.jar``` to launch the Duke application.

## Features 

### Task Management
* Allows you to
  * Create your own list by adding and deleting different type of tasks.
  * Mark tasks as done.
  * Search for tasks based on a specific keyword.

### Data Management
Modifications to the tasks in the list are automatically saved to a file and loaded on start up.

## Usage

### Listing the tasks `list`
This allows you to list out all the tasks in the list.

Command: `list`

Example: `list`

Expected outcome:
```
Here are the tasks in your list:
1.[T][✓] read book
2.[D][✘] return book (by: June 6th)
3.[E][✘] project meeting (at: Aug 6th 2-4pm)
4.[T][✓] join sports club
_____________________________________________
```

### Adding a ToDo task `todo`
This allows you to create a Todo task in the list.

Command: `todo TASK_DESCRIPTION`

Example: `todo buy food`

Expected outcome:
```
One more thing you got to do. Press on!
[T][✘] buy food
Now you have 5 tasks in the list.
_____________________________________________
```

### Adding a Deadline task `deadline`
This allows you to create a Deadline task in the list.

Command:
1. `deadline TASK_DESCRIPTION /by DEADLINE_DETAILS`
2. `deadline TASK_DESCRIPTION /by <YYY-MM-DD>T<hh:mm>`

Example:
1. `deadline assignment /by today`
2. `deadline tutorial /by 2020-09-25T23:59`

Expected outcome 1:
```
A friendly reminder: 
If your task has both a date and time,
please specify <YYYY-MM-DD>T<hh:mm>

One more thing you got to do. Press on!
[D][✘] assignment 1 (by: today)
Now you have 6 tasks in the list.
_____________________________________________
```

Expected outcome 2:
```
One more thing you got to do. Press on!
[D][✘] tutorial (by: 25 Sep 2020 11:59 PM)
Now you have 7 tasks in the list.
_____________________________________________
```

### Adding a Event task `event`
This allows you to create a Event task in the list.

Command:
1. `event TASK_DESCRIPTION /at EVENT_DETAILS`
2. `event TASK_DESCRIPTION /at <YYYY-MM-DD>T<hh:mm>`

Example:
1. `event group meeting /at next friday`
2. `event lecture /at 2020-10-02T16:00`

Expected outcome 1:
```
A friendly reminder: 
If your task has both a date and time,
please specify <YYYY-MM-DD>T<hh:mm>

One more thing you got to do. Press on!
[E][✘] group meeting (at: next friday)
Now you have 8 tasks in the list.
_____________________________________________
```

Expected outcome 2:
```
One more thing you got to do. Press on!
[E][✘] lecture (at: 02 Oct 2020 4:00 PM)
Now you have 9 tasks in the list.
_____________________________________________
```

### Deleting a task `delete`
This allows you to delete a task from the list.

Command: `delete TASK_NUMBER`

Example: `delete 6`

Expected outcome:
```
A non-essential task ya? Ok removed!
[D][✘] assignment 1 (by: today)
Now you have 8 tasks in the list.
_____________________________________________
```

### Marking a task as done `done`
This allows you to mark a task in the list as done.

Command: `done TASK_NUMBER`

Example: `done 5`

Expected outcome:
```
Nice. One more down!
[T][✓] buy food
_____________________________________________
```

### Finding a task `find`
This allows you to find tasks in the list related to the specified keyword.

Command: `find KEYWORD`

Example: `find meeting`

Expected outcome:
```
Here are some matching tasks I found you:
1.[E][✘] project meeting (at: Aug 6th 2-4pm)
2.[E][✘] group meeting (at: next friday)
_____________________________________________
```

Exiting this Duke application `bye`
This allows you to terminate this Duke application smoothly.

Command: `bye`

Example: `bye`

Expected Outcome:
```
Bye. See you soon!
_____________________________________________
```
