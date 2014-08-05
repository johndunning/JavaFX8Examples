/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples004;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author johndunning
 */
public class PersonTable extends Application
{

    private ObservableList<Person> getPeople()
    {
        ObservableList<Person> people = FXCollections.<Person>observableArrayList();
        
        // boss with employees
        Person docX = new Person("Professor X", "Charles", "Xavier");
        docX.employeesProperty().add(new Person("Wolverine", "James", "Howlett"));
        docX.employeesProperty().add(new Person("Cyclops", "Scott", "Summers"));
        docX.employeesProperty().add(new Person("Storm", "Ororo", "Munroe"));
        
        // boss
        Person magneto = new Person("Magneto", "Max", "Eisenhardt");

        // boss
        Person biker = new Person("Mountain Biker", "Jonathan", "Gennick");

        // add all the bosses to the observable list
        people.addAll(docX, magneto, biker);
        
        return people;
    }
    
    private ListView<Person> createLeaderListView(ObservableList<Person> leaders)
    {
        final ListView<Person> leaderListView = new ListView<>(leaders);
        leaderListView.setPrefWidth(150);
        leaderListView.setMaxWidth(Double.MAX_VALUE);
        leaderListView.setPrefHeight(150);
        
        // display first and last name with tooltip using alias
        leaderListView.setCellFactory(new Callback<ListView<Person>, ListCell<Person>>()
        {
            @Override
            public ListCell<Person> call(ListView<Person> param)
            {
                Label leadLbl = new Label();
                Tooltip tooltip = new Tooltip();
                
                ListCell<Person> cell = new ListCell<Person>()
                {
                    @Override
                    public void updateItem(Person item, boolean empty)
                    {
                        super.updateItem(item, empty);
                        if (item != null)
                        {
                            //leadLbl.setText(item.getAliasName());
                            setText(item.getFirstName() + " " + item.getLastName());
                            tooltip.setText(item.getAliasName());
                            setTooltip(tooltip);
                        }
                    }
                }; 
                
                return cell;
            }
        }); 
        
        return leaderListView;
    }
    
    private TableView<Person> createEmployeeTableView(ObservableList<Person> teamMembers)
    {
        final TableView<Person> employeeTableView = new TableView<>();
        employeeTableView.setPrefWidth(300);
        
        employeeTableView.setItems(teamMembers);
        
        TableColumn<Person, String> aliasNameCol = new TableColumn<>("Alias");
        TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
        TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
        
        aliasNameCol.setEditable(true);
        
        aliasNameCol.setPrefWidth(employeeTableView.getPrefWidth() / 3);
        firstNameCol.setPrefWidth(employeeTableView.getPrefWidth() / 3);
        lastNameCol.setPrefWidth(employeeTableView.getPrefWidth() / 3);
        
        aliasNameCol.setCellValueFactory(new PropertyValueFactory("aliasName"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory("lastName"));
        
        employeeTableView.getColumns().setAll(aliasNameCol, firstNameCol, lastNameCol);
        
        return employeeTableView;
    }

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Bosses and Employees: Chapter 4 Working with Tables");
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 500, 250, Color.WHITE);
        
        // create a grid pane
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(10);
        gridpane.setVgap(10);
        root.setCenter(gridpane);

        // candidates label
        

        // List of bosses and teams
        ObservableList<Person> leaders = getPeople();
        final ObservableList<Person> teamMembers = FXCollections.observableArrayList();
        
        // list View and Table View
        ListView<Person> leaderListView = createLeaderListView(leaders);
        TableView<Person> employeeTableView = createEmployeeTableView(teamMembers);

        //
        
        Label bossesLbl = new Label("Boss");
        GridPane.setHalignment(bossesLbl, HPos.CENTER);
        gridpane.add(bossesLbl, 0, 0);
        gridpane.add(leaderListView, 0, 1);
        
        //

        Label emplLbl = new Label("Employees");
        GridPane.setHalignment(emplLbl, HPos.CENTER);
        gridpane.add(emplLbl, 2, 0);
        gridpane.add(employeeTableView, 2, 1);
        
        // selection listening
        leaderListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Person> observable, Person oldValue, Person newValue) ->
        {
            if (observable != null && observable.getValue() != null)
            {
                teamMembers.clear();
                teamMembers.addAll(observable.getValue().employeesProperty());
            }
        });
        
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
