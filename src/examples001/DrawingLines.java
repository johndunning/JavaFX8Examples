/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package examples001;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author johndunning
 */
public class DrawingLines extends Application
{
    
    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Drawing Lines");
        
        Group root = new Group();
        
        Scene scene = new Scene(root, 300, 150, Color.GRAY);
        
        Line redLine = new Line(10, 10, 200, 10);
        
        redLine.setStroke(Color.RED);
        redLine.setStrokeWidth(10);
        redLine.setStrokeLineCap(StrokeLineCap.BUTT);
        
        redLine.getStrokeDashArray().addAll(10d,5d,15d,5d,20d);
        redLine.setStrokeDashOffset(0);
        
        root.getChildren().add(redLine);
        
        //
        
        Text offsetText = new Text("Stroke Dash Offset: 0.0");
        offsetText.setX(10);
        offsetText.setY(80);
        offsetText.setStroke(Color.WHITE);
        
        root.getChildren().add(offsetText);
        
        //
        
        Slider slider = new Slider(0,100,0);
        slider.setLayoutX(10);
        slider.setLayoutY(95);
        
        //
        
        redLine.strokeDashOffsetProperty().bind(slider.valueProperty());
        
        slider.valueProperty()
                .addListener(
                        (ov,curVal,newVal) -> offsetText.setText("Stroke Dash Offset: "+slider.getValue())
                );
        
        root.getChildren().add(slider);
        
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
