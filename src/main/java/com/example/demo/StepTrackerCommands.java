package com.example.demo;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.HashMap;
import java.util.Map;

@ShellComponent
public class StepTrackerCommands {

    private final Map<String, Integer> stepsData = new HashMap<>();

    // Command to log steps for a given day
    @ShellMethod("Log the number of steps for a given day")
    public String logSteps(
            @ShellOption(help = "Date (e.g., '2024-09-13')") String date, 
            @ShellOption(help = "Number of steps") int steps) {

        stepsData.put(date, steps);
        return "Logged " + steps + " steps for " + date;
    }

    // Command to view steps for a specific day
    @ShellMethod("View the number of steps logged for a given day")
    public String viewSteps(@ShellOption(help = "Date (e.g., '2024-09-13')") String date) {

        if (stepsData.containsKey(date)) {
            return "You walked " + stepsData.get(date) + " steps on " + date;
        } else {
            return "No steps logged for " + date;
        }
    }

    // Command to view total steps logged across all days
    @ShellMethod("View the total number of steps logged across all days")
    public String totalSteps() {
        return "Total steps: " + stepsData.values().stream().mapToInt(Integer::intValue).sum();
    }
}
