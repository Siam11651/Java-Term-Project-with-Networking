package termproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayerSearchResultController implements Initializable
{
    @FXML
    ListView<AnchorPane> FX_LIST_VIEW_PLAYER_DETAILS;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        FX_LIST_VIEW_PLAYER_DETAILS.getSelectionModel().clearSelection();

        FX_LIST_VIEW_PLAYER_DETAILS.setOnMousePressed((MouseEvent mouseEvent)->
        {
            FX_LIST_VIEW_PLAYER_DETAILS.getSelectionModel().clearSelection();
        });
    }

    public void Action_back(ActionEvent actionEvent) throws IOException
    {
        Main.ShowPlayerSearch(getClass());
    }

    public void Action_refresh(ActionEvent actionEvent) throws IOException
    {
        Main.ShowLoadingClubOptions(getClass());
        Main.client.GetNetworkUtil().write(Main.lastPlayerSearchRequest);
    }
}
