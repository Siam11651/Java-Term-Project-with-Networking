package termproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import util.BuyRequest;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TransferPlayerDetailController implements Initializable
{
    @FXML
    Label FX_LABEL_PLAYER_NAME;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }

    public void Action_buy(ActionEvent actionEvent) throws IOException
    {
        BuyRequest buyRequest = new BuyRequest(Main.clubName, FX_LABEL_PLAYER_NAME.getText());

        Main.client.GetNetworkUtil().write(buyRequest);
        ((Button)actionEvent.getSource()).setDisable(true);
    }
}
