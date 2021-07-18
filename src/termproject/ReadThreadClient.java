package termproject;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import util.*;

import java.io.IOException;
import java.util.Objects;

public class ReadThreadClient implements Runnable
{
    private Thread thread;
    private NetworkUtil networkUtil;

    public ReadThreadClient(NetworkUtil networkUtil)
    {
        this.networkUtil = networkUtil;
        this.thread = new Thread(this, "Client Read Thread");
        thread.start();
    }

    public void run()
    {
        try
        {
            while (true)
            {
                Object o = networkUtil.read();

                if (o instanceof Message)
                {
                    Message obj = (Message) o;
                    System.out.println(obj.getFrom() + ", " + obj.getTo() + ", " + obj.getText());
                }
                else if(o instanceof LoginSuccess)
                {
                    Platform.runLater(()->
                    {
                        LoginSuccess loginSuccess = (LoginSuccess)o;
                        Main.clubName = loginSuccess.GetName();
                        Main.ShowClubStage(getClass());
                    });
                }
                else if(o instanceof LoginFail)
                {
                    LoginFail loginFail = (LoginFail)o;

                    Platform.runLater(()->
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);

                        alert.setTitle("Loggin Failed");
                        alert.setHeaderText(loginFail.GetMessage());
                        alert.showAndWait();

                        VBox loginStage = null;

                        try
                        {
                            loginStage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
                        }
                        catch(IOException e)
                        {
                            e.printStackTrace();
                        }

                        ((AnchorPane)Main.mainStage.getScene().getRoot()).getChildren().clear();
                        ((AnchorPane)Main.mainStage.getScene().getRoot()).getChildren().add(loginStage);
                        Main.SetUpRootAnchors(loginStage);
                    });
                }
                else if(o instanceof CloseRequest)
                {
                    break;
                }
                else if(o instanceof PlayerSearchResult)
                {
                    PlayerSearchResult playerSearchResult = (PlayerSearchResult)o;

                    Platform.runLater(()->
                    {
                        try
                        {
                            Main.ShowPlayerSearchedDetails(getClass(), playerSearchResult.GetPlayers());
                        }
                        catch(IOException ioException)
                        {
                            ioException.printStackTrace();
                        }
                    });
                }
                else if(o instanceof TransferListResult)
                {
                    TransferListResult transferListResult = (TransferListResult)o;

                    Platform.runLater(()->
                    {
                        try
                        {
                            Main.ShowTransferPlayer(getClass(), transferListResult.GetPlayers());
                        }
                        catch(IOException ioException)
                        {
                            ioException.printStackTrace();
                        }
                    });
                }
                else if(o instanceof TransferUpdate)
                {
                    TransferUpdate transferUpdate = (TransferUpdate)o;

                    Platform.runLater(()->
                    {
                        Label transferUpdateNotification = (Label)Main.mainStage.getScene().lookup("#FX_LABEL_TRANFER_UPDATE_NOTIFICATION");
                        Label playerSoldNotification = (Label)Main.mainStage.getScene().lookup("#FX_LABEL_PLAYER_SEARCH_UPDATE_NOTIFICATION");

                        if(transferUpdateNotification != null)
                        {
                            transferUpdateNotification.setVisible(true);
                        }

                        String to = transferUpdate.GetTo();
                        String from = transferUpdate.GetFrom();

                        if(playerSoldNotification != null && !to.isEmpty() && from.equalsIgnoreCase(Main.clubName))
                        {
                            playerSoldNotification.setVisible(true);
                        }
                    });
                }
            }
        }
        catch (Exception e)
        {
            Platform.runLater(()->
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Connection Error");
                alert.setHeaderText("Cannot connect to server");
                alert.showAndWait();

                VBox loginStage = null;

                try
                {
                    loginStage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
                }
                catch(IOException ioException)
                {
                    ioException.printStackTrace();
                }

                ((AnchorPane)Main.mainStage.getScene().getRoot()).getChildren().clear();
                ((AnchorPane)Main.mainStage.getScene().getRoot()).getChildren().add(loginStage);
                Main.SetUpRootAnchors(loginStage);
            });
        }
        finally
        {
            try
            {
                networkUtil.closeConnection();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}



