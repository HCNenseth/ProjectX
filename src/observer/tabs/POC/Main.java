package observer.tabs.POC;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created by alex on 4/10/15.
 */
public class Main extends Application
{
    /********************
     * HERE BE BAD CODE *
     ********************/

    private BorderPane bp = new BorderPane();
    private MenuBar mb = new MenuBar();
    private ObserverTabPane otp = new ObserverTabPane();

    @Override
    public void start(Stage primaryStage) throws Exception{

        /* test menu */
        Menu test = new Menu("Test");
        MenuItem a = new MenuItem("A");
        MenuItem b = new MenuItem("B");
        MenuItem c = new MenuItem("C");

        a.setOnAction(e -> otp.injectObservableTab(a.getText(), true));
        b.setOnAction(e -> otp.injectObservableTab(b.getText(), true));
        c.setOnAction(e -> otp.injectObservableTab(c.getText(), true));

        test.getItems().addAll(a, b ,c);
        mb.getMenus().addAll(test);

        /* add menu bar to borderpane */
        bp.setTop(mb);

        /* tab pane */
        otp.injectObservableTab("Default", false);
        otp.injectOfflineTab("Offline", false);

        bp.setCenter(otp);

        Scene scene = new Scene(bp, 600, 600);

        primaryStage.setTitle("Observer Tabs POC");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
