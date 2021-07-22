package termproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import util.BuyRequest;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TransferPlayerDetailController implements Initializable
{
    @FXML
    Label FX_LABEL_PLAYER_NAME;

    @FXML
    AnchorPane FX_ANCHOR_PANE_TRANSFER_DETAILS;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        BorderStroke borderStroke = new BorderStroke(Color.color(0, 0, 0, 0.25), BorderStrokeStyle.SOLID,
                new CornerRadii(10), new BorderWidths(1));
        Border border = new Border(borderStroke);

        FX_ANCHOR_PANE_TRANSFER_DETAILS.setBorder(border);
    }

    public void Action_buy(ActionEvent actionEvent) throws IOException
    {
        BuyRequest buyRequest = new BuyRequest(Main.clubName, FX_LABEL_PLAYER_NAME.getText());

        Main.client.GetNetworkUtil().write(buyRequest);
        ((Button)actionEvent.getSource()).setDisable(true);
    }
}
