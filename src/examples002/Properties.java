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
public class Properties
{
    public static void main(String[] args)
    {
        StringProperty password = new SimpleStringProperty("password1");
        System.out.format("password: %s\n",password.get());
        
        password.set("1234");
        System.out.format("modified password: %s\n",password.get());
        
        // GOOD PRACTISE
        // note that Referencing a StringProperty exposes fewer methods of the implementation class
        // SimpleStringProperty
        
        ReadOnlyStringWrapper userName = new ReadOnlyStringWrapper("jondoe");
        ReadOnlyStringProperty readOnlyUsername = userName.getReadOnlyProperty();
        System.out.format("username: %s\n",userName.get());
        
        userName.set("janedoe");
        System.out.format("username: %s\n",userName.get());
        
        //
        
        ReadOnlyStringProperty username2 = new ReadOnlyStringWrapper("markdoe");
        System.out.format("username2: %s\n",username2.get());
        
        username2
    }
}
