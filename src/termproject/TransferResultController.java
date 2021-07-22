package termproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.TransferListRequest;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TransferResultController implements Initializable
{
    @FXML
    ListView<AnchorPane> FX_LIST_VIEW_TRANSFER_PLAYER_DETAILS;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        FX_LIST_VIEW_TRANSFER_PLAYER_DETAILS.getSelectionModel().clearSelection();

        FX_LIST_VIEW_TRANSFER_PLAYER_DETAILS.setOnMousePressed((MouseEvent mouseEvent)->
        {
            FX_LIST_VIEW_TRANSFER_PLAYER_DETAILS.getSelectionModel().clearSelection();
        });
    }

    public void Action_refresh(ActionEvent actionEvent) throws IOException
    {
        TransferListRequest transferListRequest = new TransferListRequest(Main.clubName);

        Main.ShowLoadingClubOptions(getClass());
        Main.client.GetNetworkUtil().write(transferListRequest);
    }
}
