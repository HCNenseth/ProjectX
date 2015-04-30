package main.view.table;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by alex on 4/24/15.
 */
class Column
{
    private Column() {}

    public static <T> TableColumn<T, String> generate(String label, String key)
    {
        TableColumn<T, String> col = new TableColumn<>(label);
        col.setCellValueFactory(new PropertyValueFactory<>(key));

        return col;
    }
}
