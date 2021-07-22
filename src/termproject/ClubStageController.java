package termproject;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import util.TransferListRequest;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ClubStageController implements Initializable
{
    @FXML
    ListView<String> FX_LIST_VIEW_CLUB_OPTIONS;

    @FXML
    Label FX_LABEL_CLUB_NAME;

    @FXML
    AnchorPane FX_ANCHOR_PANE_OPTIONS;

    private void AnimateSearchPlayers(VBox searchPlayers)
    {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(300), searchPlayers);

        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setCycleCount(1);
        fadeTransition.setInterpolator(Interpolator.EASE_IN);
        fadeTransition.play();
    }

    private void ShowPlayerSearchStage() throws IOException
    {
        VBox searchPlayers = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("player_search.fxml")));

        FX_ANCHOR_PANE_OPTIONS.getChildren().clear();
        FX_ANCHOR_PANE_OPTIONS.getChildren().add(searchPlayers);
        Main.SetUpRootAnchors(searchPlayers);
        AnimateSearchPlayers(searchPlayers);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        FX_LIST_VIEW_CLUB_OPTIONS.getItems().addAll("Search Players", "Transfer Window");
        FX_LIST_VIEW_CLUB_OPTIONS.getSelectionModel().select(0);

        try
        {
            ShowPlayerSearchStage();
        }
        catch(IOException ioException)
        {
            ioException.printStackTrace();
        }
    }

    public void MouseClicked_option_list(MouseEvent mouseEvent) throws IOException
    {
        int index = FX_LIST_VIEW_CLUB_OPTIONS.getSelectionModel().getSelectedIndex();

        Main.NullifyAllLastSearches();

        if(index == 0)
        {
            ShowPlayerSearchStage();
        }
        else if(index == 1)
        {
            TransferListRequest transferListRequest = new TransferListRequest(Main.clubName);

            Main.ShowLoadingClubOptions(getClass());
            Main.client.GetNetworkUtil().write(transferListRequest);
        }
    }
}
