package com.rusanov.commands;

import java.util.List;

public interface CommandWithArgs {
    String execute(String path, List<String> linesToWrite);
}
