package termproject;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import util.NetworkUtil;

import java.io.IOException;
import java.util.Objects;

public class Client
{
    private NetworkUtil networkUtil;

    public Client(String clientName, String serverAddress, int serverPort)
    {
        try
        {
            networkUtil = new NetworkUtil(serverAddress, serverPort);

            networkUtil.write(clientName);
            new ReadThreadClient(networkUtil);
        }
        catch (Exception e)
        {
            Platform.runLater(()->
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Login Error");
                alert.setHeaderText("Cannot connect to server");
                alert.showAndWait();

                AnchorPane loginStage = null;

                try
                {
                    loginStage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
                }
                catch(IOException ioException)
                {
                    ioException.printStackTrace();
                }

                if(loginStage != null)
                {
                    ((AnchorPane)Main.mainStage.getScene().getRoot()).getChildren().clear();
                    ((AnchorPane)Main.mainStage.getScene().getRoot()).getChildren().add(loginStage);
                    Main.SetUpRootAnchors(loginStage);
                    Main.AnimateLoginRoot(loginStage);
                }
            });
        }
    }

    public NetworkUtil GetNetworkUtil()
    {
        if(networkUtil == null)
        {
            return null;
        }

        return networkUtil;
    }
}


