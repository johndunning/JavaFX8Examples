/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples003;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author johndunning
 */
public class MenuExample extends Application
{

    private MenuItem exitMenuItem()
    {
        MenuItem exitMenuItem = new MenuItem("Exit");

        // 
        // add event handlers
        //
        exitMenuItem.setOnAction(actionEvent -> Platform.exit());

        return exitMenuItem;
    }

    private Menu fileMenu()
    {
        //
        // create menu
        //
        Menu fileMenu = new Menu("File");

        //
        // create menu items
        //
        MenuItem newMenuItem = new MenuItem("New");
        MenuItem saveMenuItem = new MenuItem("Save");

        //
        // add menu items
        //
        fileMenu.getItems().addAll(newMenuItem,
                saveMenuItem,
                new SeparatorMenuItem(),
                exitMenuItem()
        );

        //
        // return the menu
        //
        return fileMenu;
    }

    private Menu cameraMenu()
    {
        //
        // create menu
        //
        Menu cameraMenu = new Menu("Cameras");

        //
        // create menu items
        //
        CheckMenuItem cam1MenuItem = new CheckMenuItem("Show Camera 1");
        cam1MenuItem.setSelected(true);

        CheckMenuItem cam2MenuItem = new CheckMenuItem("Show Camera 2");
        cam2MenuItem.setSelected(true);

        //
        // add menu items
        cameraMenu.getItems().addAll(cam1MenuItem, cam2MenuItem);

        //
        // return the menu
        //
        return cameraMenu;
    }

    private Menu alarmMenu()
    {
        //
        // create menu
        //
        Menu alarmMenu = new Menu("Alarm");

        //
        // create menu items
        //
        RadioMenuItem soundAlarmItem = new RadioMenuItem("Sound Alarm");
        RadioMenuItem stopAlarmItem = new RadioMenuItem("Alarm Off");

        Menu contingencyPlans = new Menu("Contingent Plans");
        contingencyPlans.getItems().addAll(
                new CheckMenuItem("Self Destruct in T minus 50"),
                new CheckMenuItem("Turn off the coffee machine "),
                new CheckMenuItem("Run for your lives! "));
        alarmMenu.getItems().add(contingencyPlans);

        // 
        // set toggle groups
        //
        ToggleGroup tGroup = new ToggleGroup();
        soundAlarmItem.setToggleGroup(tGroup);
        stopAlarmItem.setToggleGroup(tGroup);

        //
        // set toggle defaults
        //
        stopAlarmItem.setSelected(true);

        // 
        // add menu items
        //
        alarmMenu.getItems().addAll(soundAlarmItem,
                stopAlarmItem,
                new SeparatorMenuItem());

        //
        // return the menu
        //
        return alarmMenu;
    }

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Menus Example");
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 300, 250, Color.WHITE);

        //
        // menu bar
        //
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu(), cameraMenu(), alarmMenu());

        //
        // add the menu bar
        //
        root.setTop(menuBar);

        //
        // add context menus
        //
        
        ContextMenu contextFileMenu = new ContextMenu(exitMenuItem());
        
        primaryStage.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent me) ->
        {
            if (me.getButton() == MouseButton.SECONDARY || me.isControlDown())
            {
                contextFileMenu.show(root, me.getScreenX(), me.getScreenY());
            }
            else
            {
                contextFileMenu.hide();
            }
        });

        //
        // set the scene
        //
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

}
