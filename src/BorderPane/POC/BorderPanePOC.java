package BorderPane.POC;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Created by Hans Christian on 12.04.2015.
 */
public class BorderPanePOC extends Application
{

    private BorderPane borderPane = new BorderPane();
    private MenuBar menuBar = new MenuBar();
    private Accordion accordion = new Accordion();
    private Pane pane = new Pane();

    public void start(Stage primaryStage) throws Exception
    {

        // TopMenu
        Menu test = new Menu("Test");
        Menu test2 = new Menu("About");
        MenuItem a = new MenuItem("Test Insert");
        MenuItem b = new MenuItem("Test Delete");
        MenuItem c = new MenuItem("Help");

        test.getItems().addAll(a, b);
        test2.getItems().addAll(c);
        menuBar.getMenus().addAll(test, test2);

        // Dropdown SideMenu
        TitledPane t1 = new TitledPane("DropDown", new Button("OrUP"));
        TitledPane t2 = new TitledPane("T2", new Button("B2"));
        accordion.getPanes().addAll(t1, t2);


        borderPane.setCenter(pane);
        borderPane.setTop(menuBar);
        borderPane.setLeft(accordion);

        Scene scene = new Scene(borderPane, 800, 800);

        primaryStage.setTitle("Stacking");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
