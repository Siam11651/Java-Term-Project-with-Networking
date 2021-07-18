package termproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable
{
    @FXML
    TextField FX_TEXT_FIELD_CLUB_NAME;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }

    public void Action_login(ActionEvent actionEvent) throws IOException
    {
        if(!FX_TEXT_FIELD_CLUB_NAME.getText().isEmpty())
        {
            System.out.println(FX_TEXT_FIELD_CLUB_NAME.getText());
            Main.ShowLoadingScreenRootAnchor(getClass());
            String clubName = FX_TEXT_FIELD_CLUB_NAME.getText();
            String serverAddress = "127.0.0.1";
            int serverPort = 33333;

            new Thread(()->
            {
                Main.client = new Client(clubName, serverAddress, serverPort);
            }, "Client Connect Thread").start();
        }
    }
}