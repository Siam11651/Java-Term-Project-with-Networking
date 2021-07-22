package termproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import util.TransferEnlistRequest;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayerDetailController implements Initializable
{
    @FXML
    AnchorPane FX_ANCHOR_PANE_DETAILS;

    @FXML
    Label FX_LABEL_PLAYER_NAME;

    @FXML
    ImageView FX_IMAGE_VIEW_SEARCH_PLAYER_DETAIL;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }

    public void Action_add_to_transfer(ActionEvent actionEvent) throws IOException
    {
        TransferEnlistRequest transferEnlistRequest = new TransferEnlistRequest(Main.clubName, FX_LABEL_PLAYER_NAME.getText());

        Main.client.GetNetworkUtil().write(transferEnlistRequest);
        ((Node)actionEvent.getSource()).setDisable(true);
    }
}
