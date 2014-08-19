/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples008;

import javafx.event.EventHandler;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author johndunning
 */
public class EditingCell extends TableCell<Object, Object>
{
    private TextField textField;

    public EditingCell()
    {
    }

    @Override
    public void startEdit()
    {

        super.startEdit();

        if (textField == null)
        {
            createTextField();
        }

        setGraphic(textField);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        textField.selectAll();
    }

    @Override
    public void cancelEdit()
    {
        super.cancelEdit();

        setText(String.valueOf(getItem()));
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }

    public void updateItem(Object item, boolean empty)
    {
        super.updateItem(item, empty);

        if (empty)
        {
            setText(null);
            setGraphic(null);
        }
        else
        {
            if (isEditing())
            {
                if (textField != null)
                {
                    textField.setText(getString());
                }
                setGraphic(textField);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }
            else
            {
                setText(getString());
                setContentDisplay(ContentDisplay.TEXT_ONLY);
            }
        }
    }

    private void createTextField()
    {
        textField = new TextField(getString());
        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        textField.setOnKeyPressed(new EventHandler<KeyEvent>()
        {

            @Override
            public void handle(KeyEvent t)
            {
                if (t.getCode() == KeyCode.ENTER)
                {
                    commitEdit(textField.getText());
                }
                else if (t.getCode() == KeyCode.ESCAPE)
                {
                    cancelEdit();
                }
            }
        });
    }

    private String getString()
    {
        return getItem() == null ? "" : getItem().toString();
    }
}
