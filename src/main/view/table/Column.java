package main.view.table;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Column.java
 */
class Column
{
    private Column()
    {
    }

    /**
     * Generate table column.
     *
     * @param <T>  the type parameter
     * @param label the label
     * @param key the key
     * @return the table column
     */
    public static <T> TableColumn<T, String> generate(String label, String key)
    {
        TableColumn<T, String> col = new TableColumn<>(label);
        col.setCellValueFactory(new PropertyValueFactory<>(key));

        return col;
    }
}
