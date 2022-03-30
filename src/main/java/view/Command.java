package view;

import exception.InvalidEntityDataException;

public interface Command {
    String execute() throws InvalidEntityDataException;
}
