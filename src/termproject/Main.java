package termproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import util.CloseRequest;
import util.MaxDataPlayerRequest;
import util.Player;
import util.PlayerSearchRequest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Main extends Application
{
    public static Client client;
    public static String clubName;
    public static Stage mainStage, totalSalaryStage;
    public static PlayerSearchRequest lastPlayerSearchRequest;
    public static MaxDataPlayerRequest lastMaxDataRequest;

    public static void NullifyAllLastSearches()
    {
        Main.lastPlayerSearchRequest = null;
        Main.lastMaxDataRequest = null;
    }

    public static void ShowTransferPlayer(Class<?> thisClass, ArrayList<Player> players) throws IOException
    {
        AnchorPane transferPlayers = FXMLLoader.load(Objects.requireNonNull(thisClass.getResource("transfer_result.fxml")));

        ListView<AnchorPane> listView = (ListView<AnchorPane>)transferPlayers.getChildren().get(1);

        for(Player player : players)
        {
            AnchorPane playerDetail = FXMLLoader.load(Objects.requireNonNull(thisClass.getResource("transfer_player_detail.fxml")));

            Label[] labels = new Label[8];

            for(int j = 0; j < 8; j++)
            {
                labels[j] = (Label) ((HBox) ((VBox) playerDetail.getChildren().get(0)).getChildren().get(j)).getChildren().get(1);
            }

            ImageView playerImageView = (ImageView)playerDetail.lookup("#FX_IMAGE_VIEW_TRANSFER_PLAYER_DETAIL");
            byte[] imageByteArr = player.GetImageByteArr();
            ByteArrayInputStream imageByteArrayInputStream = new ByteArrayInputStream(imageByteArr);

            playerImageView.setImage(new Image(imageByteArrayInputStream));
            labels[0].setText(player.GetName());
            labels[1].setText(player.GetCountry());
            labels[2].setText(player.GetClub());
            labels[3].setText(String.valueOf(player.GetAge()));
            labels[4].setText(String.valueOf(player.GetHeight()));
            labels[5].setText(player.GetPosition());
            labels[6].setText(String.valueOf(player.GetNumber()));
            labels[7].setText(String.valueOf(player.GetWeeklySalary()));

            if(player.GetClub().equalsIgnoreCase(clubName))
            {
                ((AnchorPane)playerDetail.getChildren().get(1)).getChildren().get(1).setDisable(true);
            }
            else
            {
                listView.getItems().add(playerDetail);
            }
        }
        
        if(listView.getItems().size() == 0)
        {
            Label label = new Label("Wow! Such empty");
            VBox vBox = new VBox(label);

            vBox.setAlignment(Pos.CENTER);
            AnchorPane anchorPane = new AnchorPane(vBox);

            AnchorPane.setTopAnchor(vBox, 0.0);
            AnchorPane.setBottomAnchor(vBox, 0.0);
            AnchorPane.setLeftAnchor(vBox, 0.0);
            AnchorPane.setRightAnchor(vBox, 0.0);
            listView.getItems().add(anchorPane);
        }

        ListView<String> optionsList = (ListView<String>) mainStage.getScene().lookup("#FX_LIST_VIEW_CLUB_OPTIONS");

        if(optionsList != null && optionsList.getSelectionModel().getSelectedIndex() == 1)
        {
            AnchorPane options = (AnchorPane) mainStage.getScene().lookup("#FX_ANCHOR_PANE_OPTIONS");

            if(options != null)
            {
                options.getChildren().clear();
                options.getChildren().add(transferPlayers);
                SetUpRootAnchors(transferPlayers);
            }
        }
    }

    public static void ShowPlayerSearchedDetails(Class<?> thisClass, ArrayList<Player> players) throws IOException
    {
        AnchorPane playerSearched = FXMLLoader.load(Objects.requireNonNull(thisClass.getResource("player_search_result.fxml")));

        ListView<AnchorPane> listView = (ListView<AnchorPane>)playerSearched.getChildren().get(1);

        for(Player player : players)
        {
            AnchorPane playerDetail = FXMLLoader.load(Objects.requireNonNull(thisClass.getResource("player_detail.fxml")));

            Label[] labels = new Label[8];

            for(int j = 0; j < 8; j++)
            {
                labels[j] = (Label) ((HBox) ((VBox) playerDetail.getChildren().get(0)).getChildren().get(j)).getChildren().get(1);
            }

            ImageView playerImageView = (ImageView)playerDetail.lookup("#FX_IMAGE_VIEW_SEARCH_PLAYER_DETAIL");
            byte[] imageByteArr = player.GetImageByteArr();
            ByteArrayInputStream imageByteArrayInputStream = new ByteArrayInputStream(imageByteArr);

            playerImageView.setImage(new Image(imageByteArrayInputStream));
            labels[0].setText(player.GetName());
            labels[1].setText(player.GetCountry());
            labels[2].setText(player.GetClub());
            labels[3].setText(String.valueOf(player.GetAge()));
            labels[4].setText(String.valueOf(player.GetHeight()));
            labels[5].setText(player.GetPosition());
            labels[6].setText(String.valueOf(player.GetNumber()));
            labels[7].setText(String.valueOf(player.GetWeeklySalary()));

            if(player.GetTransferListed())
            {
                playerDetail.lookup("#FX_BUTTON_ADD_TO_TRANSFER").setDisable(true);
            }

            listView.getItems().add(playerDetail);
        }

        if(listView.getItems().size() == 0)
        {
            Label label = new Label("Wow! Such empty");
            VBox vBox = new VBox(label);

            vBox.setAlignment(Pos.CENTER);
            AnchorPane anchorPane = new AnchorPane(vBox);

            AnchorPane.setTopAnchor(vBox, 0.0);
            AnchorPane.setBottomAnchor(vBox, 0.0);
            AnchorPane.setLeftAnchor(vBox, 0.0);
            AnchorPane.setRightAnchor(vBox, 0.0);
            listView.getItems().add(anchorPane);
        }

        ListView<String> optionsList = (ListView<String>) mainStage.getScene().lookup("#FX_LIST_VIEW_CLUB_OPTIONS");

        if(optionsList != null && optionsList.getSelectionModel().getSelectedIndex() == 0)
        {
            AnchorPane options = (AnchorPane) mainStage.getScene().lookup("#FX_ANCHOR_PANE_OPTIONS");

            if(options != null)
            {
                options.getChildren().clear();
                options.getChildren().add(playerSearched);
                SetUpRootAnchors(playerSearched);
            }
        }
    }

    public static void ShowPlayerSearch(Class<?> thisClass) throws IOException
    {
        VBox playerSearch = FXMLLoader.load(Objects.requireNonNull(thisClass.getResource("player_search.fxml")));

        ListView<String> optionsList = (ListView<String>) mainStage.getScene().lookup("#FX_LIST_VIEW_CLUB_OPTIONS");

        if(optionsList != null && optionsList.getSelectionModel().getSelectedIndex() == 0)
        {
            AnchorPane options = (AnchorPane) mainStage.getScene().lookup("#FX_ANCHOR_PANE_OPTIONS");

            if(options != null)
            {
                options.getChildren().clear();
                options.getChildren().add(playerSearch);
                SetUpRootAnchors(playerSearch);
            }
        }
    }

    public static void ShowClubStage(Class<?> thisClass)
    {
        AnchorPane clubStage = null;

        try
        {
            clubStage = FXMLLoader.load(Objects.requireNonNull(thisClass.getResource("club_stage.fxml")));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        if(clubStage != null)
        {
            ((Label)clubStage.getChildren().get(0)).setText(Main.clubName);
            ((AnchorPane)Main.mainStage.getScene().getRoot()).getChildren().clear();
            ((AnchorPane)Main.mainStage.getScene().getRoot()).getChildren().add(clubStage);
            Main.SetUpRootAnchors(clubStage);
        }
    }

    public static void ShowLoadingClubOptions(Class<?> thisClass) throws IOException
    {
        VBox loadingScreen = FXMLLoader.load(Objects.requireNonNull(thisClass.getResource("loading.fxml")));

        AnchorPane parent = (AnchorPane)mainStage.getScene().lookup("#FX_ANCHOR_PANE_OPTIONS");

        parent.getChildren().clear();
        parent.getChildren().add(loadingScreen);
        SetUpRootAnchors(loadingScreen);
    }

    public static void ShowLoadingScreenRootAnchor(Class<?> thisClass) throws IOException
    {
        VBox loadingScreen = FXMLLoader.load(Objects.requireNonNull(thisClass.getResource("loading.fxml")));

        ((AnchorPane)mainStage.getScene().getRoot()).getChildren().clear();
        ((AnchorPane)mainStage.getScene().getRoot()).getChildren().add(loadingScreen);
        SetUpRootAnchors(loadingScreen);
    }

    public static void SetUpRootAnchors(Node node)
    {
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        mainStage = primaryStage;
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main_root.fxml")));
        VBox loginVBox = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));

        root.getChildren().add(loginVBox);
        SetUpRootAnchors(loginVBox);

        mainStage.setOnCloseRequest((WindowEvent windowEvent)->
        {
            if(client != null && client.GetNetworkUtil() != null)
            {
                CloseRequest closeRequest = new CloseRequest(clubName);

                try
                {
                    client.GetNetworkUtil().write(closeRequest);
                }
                catch(IOException ioException)
                {
                    ioException.printStackTrace();
                }
            }
        });

        mainStage.setTitle("Football Manager");
        mainStage.setScene(new Scene(root));
        mainStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
