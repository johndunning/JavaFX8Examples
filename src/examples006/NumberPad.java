/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples006;

import java.nio.file.Paths;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 *
 * @author johndunning
 */
public class NumberPad extends Application
{

    @Override
    public void start(Stage primaryStage)
    {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 180, 250);
        scene.getStylesheets().add(getClass().getResource("/examples006/mobile_buttons.css").toExternalForm());
        String[] keys =
        {
            "1", "2", "3",
            "4", "5", "6",
            "7", "8", "9",
            "*", "0", "#"
        };
        
        GridPane numPad = new GridPane();
        numPad.getStyleClass().add("num-pad");
        for (int i = 0; i < 12; i++)
        {
            Button button = new Button(keys[i]);
            button.getStyleClass().add("num-button");
            numPad.add(button, i % 3, (int) Math.ceil(i / 3));
        }
        // Call button
        Button call = new Button("Call");
        call.setId("call-button");
        call.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        numPad.add(call, 0, 4);
        
        GridPane.setColumnSpan(call, 3);
        GridPane.setHgrow(call, Priority.ALWAYS);
        
        root.setCenter(numPad);
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
