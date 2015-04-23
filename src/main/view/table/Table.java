package main.view.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.List;

/**
 * Created by alex on 4/23/15.
 */
public class Table<T>
{
    private List<T> data;
    private TableView<T> table;
    private ObservableList<T> observableList;

    public Table(List<T> data)
    {
        this.data = data;

        table = new TableView<>();
        observableList = FXCollections.observableArrayList();

        insertData();
    }

    private void insertData()
    {
        data.stream().forEach(observableList::add);
        table.setItems(observableList);
    }

    public TableView<T> getTable() { return table; }

}
