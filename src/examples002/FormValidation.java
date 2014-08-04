/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples002;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
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

    // The methods only required because I have broken code into methods
    private Stage primaryStage;
    private Group root;
    private User user;

    private Rectangle drawRectangleBackground()
    {
        Rectangle background = new Rectangle(320, 112);
        background.setX(0);
        background.setY(0);
        background.setArcHeight(15);
        background.setArcWidth(15);
        background.setFill(Color.rgb(0, 0, 0, 0.55));
        background.setStrokeWidth(1.5);
        background.setStroke(foregroundColor);

        return background;
    }

    private HBox drawRow1()
    {
        Text userNameField = new Text();
        userNameField.setFont(Font.font("SanSerif", FontWeight.BOLD, 30));
        userNameField.setFill(foregroundColor);
        userNameField.setSmooth(true);

        // bind the field to the user.username
        userNameField.textProperty().bind(user.userNameProperty());

        // wrap the text node
        HBox userNameCell = new HBox();
        userNameCell.prefWidthProperty().bind(primaryStage.widthProperty().subtract(45));
        userNameCell.getChildren().add(userNameField);

        // padlock
        SVGPath padLock = new SVGPath();
        padLock.setFill(foregroundColor);
        padLock.setContent("M24.875,15.334v-4.876c0-4.894-3.981-8.875-8.875-8.875s-8.875,"
                + "3.981-8.875,8.875v4.876H5.042v15.083h21.916V15.334H24.875zM10.625,10."
                + "458c0-2.964,2.411-5.375,5.375-5.375s5.375,2.411,5.375,5.375v4.876h-10"
                + ".75V10.458zM18.272,26.956h-4.545l1.222-3.667c-0.782-0.389-1.324-1.188"
                + "-1.324-2.119c0-1.312,1.063-2.375,2.375-2.375s2.375,1.062,2.375,2.375c0"
                + ",0.932-0.542,1.73-1.324,2.119L18.272,26.956z");
        // first row
        HBox row1 = new HBox();
        row1.getChildren().addAll(userNameCell, padLock);

        return row1;
    }

    private HBox drawRow2()
    {
        // password text field
        PasswordField passwordField = new PasswordField();
        passwordField.setFont(Font.font("SanSerif", 20));
        passwordField.setPromptText("Password");
        passwordField.setStyle("-fx-text-fill:black; "
                + "-fx-prompt-text-fill:gray; "
                + "-fx-highlight-text-fill:black; "
                + "-fx-highlight-fill: gray; "
                + "-fx-background-color: rgba(255, 255, 255, .80); ");

        passwordField.prefWidthProperty().bind(primaryStage.widthProperty().subtract(55));
        user.passwordProperty().bind(passwordField.textProperty());

        // error icon
        SVGPath deniedIcon = new SVGPath();
        deniedIcon.setFill(Color.rgb(255, 0, 0, .9));
        deniedIcon.setStroke(Color.WHITE);//
        deniedIcon.setContent("M24.778,21.419 19.276,15.917 24.777,10.415 21.949,7.585 "
                + "16.447,13.08710.945,7.585 8.117,10.415 13.618,15.917 8.116,21.419 10"
                + ".946,24.248 16.447,18.746 21.948,24.248z");
        deniedIcon.setVisible(false);

        SVGPath grantedIcon = new SVGPath();
        grantedIcon.setFill(Color.rgb(0, 255, 0, .9));
        grantedIcon.setStroke(Color.WHITE);//
        grantedIcon.setContent("M2.379,14.729 5.208,11.899 12.958,19.648 25.877,"
                + "6.733 28.707,9.56112.958,25.308z");
        grantedIcon.setVisible(false);

        //
        StackPane accessIndicator = new StackPane();
        accessIndicator.getChildren().addAll(deniedIcon, grantedIcon);
        accessIndicator.setAlignment(Pos.CENTER_RIGHT);

        grantedIcon.visibleProperty().bind(GRANTED_ACCESS);

        // user hits the enter key
        passwordField.setOnAction(actionEvent ->
        {
            if (GRANTED_ACCESS.get())
            {
                System.out.printf("User %s is granted access.\n", user.getUserName());
                System.out.printf("User %s entered the password: %s\n", 
                        user.getUserName(), user.getPassword());
                Platform.exit();
            }
            else
            {
                deniedIcon.setVisible(true);
            }

            ATTEMPTS.set(ATTEMPTS.add(1).get());
            System.out.println("Attempts: " + ATTEMPTS.get());
        });

        // listener when the user types into the password field
        passwordField.textProperty().addListener((obs, ov, nv) ->
        {
            boolean granted = passwordField.getText().equals(MY_PASS);
            GRANTED_ACCESS.set(granted);
            if (granted)
            {
                deniedIcon.setVisible(false);
            }
        });

        // listener on number of attempts
        ATTEMPTS.addListener((obs, ov, nv) ->
        {
            if (MAX_ATTEMPTS == nv.intValue())
            {
                // failed attempts
                System.out.printf("User %s is denied access.\n",user.getUserName());
                Platform.exit();
            }
        });

        // second row
        HBox row2 = new HBox(3);
        row2.getChildren().addAll(passwordField, accessIndicator);

        return row2;
    }

    @Override
    public void start(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        user = new User();

        primaryStage.initStyle(StageStyle.TRANSPARENT);

        root = new Group();
        Scene scene = new Scene(root, 320, 112, Color.rgb(0, 0, 0, 0));

        primaryStage.setScene(scene);

        Rectangle background = this.drawRectangleBackground();
        HBox row1 = this.drawRow1();
        HBox row2 = this.drawRow2();

        // 
        VBox formLayout = new VBox(4); // spacing
        formLayout.setLayoutX(12);
        formLayout.setLayoutY(12);

        formLayout.getChildren().addAll(row1, row2);

        //
        root.getChildren().addAll(background, formLayout);

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
