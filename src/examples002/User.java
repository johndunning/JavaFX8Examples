/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package examples002;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author johndunning
 */
public class User
{
    private final static String USERNAME_PROP_NAME = "userName";
    private final static String PASSWORD_PROP_NAME = "password";
    
    private final ReadOnlyStringWrapper userName;
    private StringProperty password;
    
    public User()
    {
        // wrappers and properties expecting Object bean, String name, <T> initialValue
        userName = new ReadOnlyStringWrapper(this, USERNAME_PROP_NAME, System.getProperty("user.name"));
        password = new SimpleStringProperty(this, PASSWORD_PROP_NAME, "");
    }
    
    //
    // -- username is immutable
    //
    
    public final String getUserName()
    {
        return userName.get();
    }
    
    //
    // -- password
    //
    
    public final String getPassword()
    {
        return password.get();
    }
    
    public final void setPassword(String password)
    {
        this.password.set(password);
    }
    
    //
    // -- Properties for binding
    //
    
    public ReadOnlyStringProperty userNameProperty()
    {
        return userName.getReadOnlyProperty();
    }
    
    public StringProperty passwordProperty()
    {
        return password;
    }
    
    //
    
    public String toString()
    {
        return String.format("User[username=%s, password=%s]",getUserName(),getPassword());
    }
}
