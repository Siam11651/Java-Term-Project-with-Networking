package termproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import util.PlayerSearchRequest;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayerSearchController implements Initializable
{
    @FXML
    TextField FX_TEXT_FIELD_NAME,
            FX_TEXT_FIELD_COUNTRY,
            FX_TEXT_FIELD_AGE,
            FX_TEXT_FIELD_HEIGHT,
            FX_TEXT_FIELD_POSITION,
            FX_TEXT_FIELD_NUMBER,
            FX_TEXT_FIELD_SALARY;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }

    public void Action_search(ActionEvent actionEvent) throws IOException
    {
        Main.lastPlayerSearchRequest = new PlayerSearchRequest
                (
                        Main.clubName,
                        FX_TEXT_FIELD_NAME.getText(),
                        FX_TEXT_FIELD_COUNTRY.getText(),
                        FX_TEXT_FIELD_AGE.getText(),
                        FX_TEXT_FIELD_HEIGHT.getText(),
                        FX_TEXT_FIELD_POSITION.getText(),
                        FX_TEXT_FIELD_NUMBER.getText(),
                        FX_TEXT_FIELD_SALARY.getText()
                );

        Main.ShowLoadingClubOptions(getClass());
        Main.client.GetNetworkUtil().write(Main.lastPlayerSearchRequest);
    }
}
