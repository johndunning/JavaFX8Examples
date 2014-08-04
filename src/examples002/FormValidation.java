/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples002;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author johndunning
 */
public class FormValidation extends Application
{

    private final static String MY_PASS = "password1";
    private final static BooleanProperty GRANTED_ACCESS = new SimpleBooleanProperty(false);
    private final static int MAX_ATTEMPTS = 3;
    private final static IntegerProperty ATTEMPTS = new SimpleIntegerProperty(0);
    
    private static final Color foregroundColor = Color.rgb(255, 255, 255, 0.9);
    
    private Group root;

    
    private void drawRectangleBackground()
    {
        Rectangle background = new Rectangle(320,112);
        background.setX(0);
        background.setY(0);
        background.setArcHeight(15);
        background.setArcWidth(15);
        background.setFill(Color.rgb(0, 0, 0, 0.55));
        background.setStrokeWidth(1.5);
        background.setStroke(foregroundColor);
        
        root.getChildren().add(background);
    }
    
    private void drawUsernameText()
    {
        Text userName = new Text();
        userName.setFont(Font.font("SanSerif", FontWeight.BOLD, 30));
        userName.setFill(foregroundColor);
        userName.setSmooth(true);
        
        userName.textProperty().bind(user.usernameProperty());
    }

    @Override
    public void start(Stage primaryStage)
    {
        User user = new User();
        
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        
        root = new Group();
        Scene scene = new Scene(root, 320, 112, Color.rgb(0, 0, 0, 0));
        
        primaryStage.setScene(scene);
        
        this.drawRectangleBackground();
        
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

class User
{
    private String password;

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
    
    

}
