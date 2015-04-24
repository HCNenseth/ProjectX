package main.view.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Created by alex on 4/23/15.
 */
public abstract class Table<T> extends TableView<T>
{
    private ObservableList<T> observableList;

    public Table()
    {
        observableList = FXCollections.observableArrayList();
    }

    public void insertData(T t)
    {
        observableList.add(t);
    }

    public void injectColumn(TableColumn<T, String> column)
    {
        getColumns().add(column);
    }

    public Table<T> getTable()
    {
        setItems(observableList);
        return this;
    }

}
