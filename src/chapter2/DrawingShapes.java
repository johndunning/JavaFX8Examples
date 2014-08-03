/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chapter2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;

/**
 *
 * @author johndunning
 */
public class DrawingShapes extends Application
{
    
    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Drawing Shapes");
        
        //
        
        Group root = new Group();        
        Scene scene = new Scene(root, 500, 260);
        
        //
        
        Rectangle roundRect = new Rectangle(50,50,400,130);
        
        roundRect.setArcWidth(30);
        roundRect.setArcHeight(60);
        
        roundRect.setFill(null);
        roundRect.setStroke(Color.DARKORANGE);
        roundRect.setStrokeWidth(2);
        roundRect.setStrokeLineCap(StrokeLineCap.BUTT);
        
        root.getChildren().add(roundRect);
        
        //
                
        Slider slider = new Slider(30,150,0);
        slider.setLayoutX(250-slider.getWidth()/2);
        slider.setLayoutY(115-slider.getHeight()/2);
        
        slider.widthProperty().addListener(
                (ov, curVal, newVal) -> { slider.setLayoutX(250-slider.getWidth()/2); }
        );
        
        slider.heightProperty().addListener(
                (ov, curVal, newVal) -> slider.setLayoutY(115-slider.getHeight()/2)
        );
        
        //
                
        roundRect.arcWidthProperty().bind(slider.valueProperty());
        
        root.getChildren().add(slider);
        
        //
        
        Slider slider2 = new Slider(10,120,0);
        slider2.setValue(50);
        slider2.setLayoutX(50);
        slider2.setLayoutY(230);
        
        slider2.widthProperty().addListener(
                (ov, curVal, newVal) -> { slider2.setLayoutX(250-slider2.getWidth()/2); }
        );
        
        roundRect.yProperty().bind(slider2.valueProperty());
        root.getChildren().add(slider2);
        
        slider2.valueProperty().addListener(
                (ov, curVal, newVal) -> slider.setLayoutY(slider.getLayoutY()+newVal.doubleValue()-curVal.doubleValue())
        );
        
        
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
