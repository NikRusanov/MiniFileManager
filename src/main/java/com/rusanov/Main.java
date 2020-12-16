package com.rusanov;

import com.rusanov.commands.Command;
import com.rusanov.commands.CreateDirCommand;
import com.rusanov.commands.ListDirCommand;
import com.rusanov.input.App;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            new App().start();
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }
}
