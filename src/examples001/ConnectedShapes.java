/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples001;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;

/**
 *
 * @author johndunning
 */
public class ConnectedShapes extends Application
{

    double orgSceneX, orgSceneY;
    
    private EventHandler<MouseEvent> mousePressedEventHandler = (t) ->
    {
        orgSceneX = t.getSceneX();
        orgSceneY = t.getSceneY();
        
        // bring the clicked circle to the front
        
        Circle c = (Circle) (t.getSource());      
        c.toFront();
    };

    private EventHandler<MouseEvent> mouseDraggedEventHandler = (t) ->
    {
        double offsetX = t.getSceneX() - orgSceneX;
        double offsetY = t.getSceneY() - orgSceneY;

        Circle c = (Circle) (t.getSource());

        c.setCenterX(c.getCenterX() + offsetX);
        c.setCenterY(c.getCenterY() + offsetY);

        orgSceneX = t.getSceneX();
        orgSceneY = t.getSceneY();
    };

    private Circle createCircle(double x, double y, double r, Color color)
    {
        Circle circle = new Circle(x, y, r, color);

        circle.setCursor(Cursor.CROSSHAIR);

        circle.setOnMousePressed(mousePressedEventHandler);
        circle.setOnMouseDragged(mouseDraggedEventHandler);

        return circle;
    }

    private Line connect(Circle c1, Circle c2)
    {
        Line line = new Line();

        line.startXProperty().bind(c1.centerXProperty());
        line.startYProperty().bind(c1.centerYProperty());

        line.endXProperty().bind(c2.centerXProperty());
        line.endYProperty().bind(c2.centerYProperty());

        line.setStrokeWidth(1);
        line.setStrokeLineCap(StrokeLineCap.BUTT);
        line.getStrokeDashArray().setAll(1.0, 4.0);

        return line;
    }

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Connected Shapes");

        //
        Group root = new Group();
        Scene scene = new Scene(root, 500, 260);

        // circles
        Circle redCircle = createCircle(100, 50, 30, Color.RED);
        Circle blueCircle = createCircle(200, 150, 20, Color.BLUE);
        Circle greenCircle = createCircle(400, 100, 40, Color.GREEN);

        Line line1 = connect(redCircle, blueCircle);
        Line line2 = connect(redCircle, greenCircle);
        Line line3 = connect(greenCircle, blueCircle);

        // add the circles 
        root.getChildren().add(redCircle);
        root.getChildren().add(blueCircle);
        root.getChildren().add(greenCircle);

        // add the lines 
        root.getChildren().add(line1);
        root.getChildren().add(line2);
        root.getChildren().add(line3);
        
        // bring the circles to the front of the lines
        redCircle.toFront();
        blueCircle.toFront();
        greenCircle.toFront();

        // set the scene
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
