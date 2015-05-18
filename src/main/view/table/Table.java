package main.view.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.localization.Loc;

import java.util.function.Consumer;


/**
 * Table.java
 *
 * @param <T>  the type parameter
 */
public abstract class Table<T> extends TableView<T>
{
    private ObservableList<T> observableList;
    private ContextMenu contextMenu;
    private MenuItem edit;
    private MenuItem view;

    /**
     * Instantiates a new Table.
     */
    public Table()
    {
        contextMenu = new ContextMenu();
        edit = new MenuItem(Loc.c("edit"));
        view = new MenuItem(Loc.c("view"));

        contextMenu.getItems().add(edit);
        contextMenu.getItems().add(view);

        observableList = FXCollections.observableArrayList();

        setEditable(false);
        setTableMenuButtonVisible(true);
        setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        setContextMenu(contextMenu);
    }

    private T getSelected()
    {
        return getSelectionModel().getSelectedItem();
    }

    /**
     * Sets on edit action.
     *
     * @param c the c
     */
    public void setOnEditAction(Consumer<T> c)
    {
        edit.setOnAction(e -> c.accept(getSelected()));
    }

    /**
     * Sets on view action.
     *
     * @param c the c
     */
    public void setOnViewAction(Consumer<T> c)
    {
        view.setOnAction(e -> c.accept(getSelected()));
    }

    /**
     * Sets on double click action.
     *
     * @param c the c
     */
    public void setOnDoubleClickAction(Consumer<T> c)
    {
        onMouseClickedProperty().set(e -> {
            if (e.getClickCount() == 2) c.accept(getSelected());
        });
    }

    /**
     * Insert data.
     *
     * @param t the t
     */
    public void insertData(T t)
    {
        observableList.add(t);
    }

    /**
     * Inject column.
     *
     * @param column the column
     */
    protected void injectColumn(TableColumn<T, String> column)
    {
        getColumns().add(column);
    }

    /**
     * Gets table.
     *
     * @return the table
     */
    public Table<T> getTable()
    {
        setItems(observableList);
        return this;
    }

}
