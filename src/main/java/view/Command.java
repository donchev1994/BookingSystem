package view;

import exception.InvalidEntityDataException;
import exception.NonexistentEntityException;

import java.sql.SQLException;

public interface Command {
    String execute() throws InvalidEntityDataException, SQLException, NonexistentEntityException;
}

