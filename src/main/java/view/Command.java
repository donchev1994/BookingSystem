package view;

import exception.InvalidEntityDataException;

import java.sql.SQLException;

public interface Command {
    String execute() throws InvalidEntityDataException, SQLException;
}
