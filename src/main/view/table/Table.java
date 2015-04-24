package main.view.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Created by alex on 4/23/15.
 */
abstract class Table<T>
{
    private TableView<T> table;
    private ObservableList<T> observableList;

    public Table()
    {
        table = new TableView<>();
        observableList = FXCollections.observableArrayList();
    }

    public void insertData(T t)
    {
        observableList.add(t);
    }

    public void injectColumn(TableColumn<T, String> column)
    {
        table.getColumns().add(column);
    }

    public TableView<T> getTable()
    {
        table.setItems(observableList);
        return table;
    }

}
