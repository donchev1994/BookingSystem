package view;

import java.sql.SQLException;

public interface EntityDialog<E> {
    E input() throws SQLException;
}

