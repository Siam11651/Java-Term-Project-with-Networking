package termproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.MaxDataPlayerRequest;
import util.MaxDataType;
import util.PlayerSearchRequest;
import util.TotalSalaryRequest;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
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
        Main.NullifyAllLastSearches();

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

    public void Action_max_salary(ActionEvent actionEvent) throws IOException
    {
        Main.NullifyAllLastSearches();

        Main.lastMaxDataRequest = new MaxDataPlayerRequest(Main.clubName, MaxDataType.SALARY);

        Main.ShowLoadingClubOptions(getClass());
        Main.client.GetNetworkUtil().write(Main.lastMaxDataRequest);
    }

    public void Action_max_height(ActionEvent actionEvent) throws IOException
    {
        Main.NullifyAllLastSearches();

        Main.lastMaxDataRequest = new MaxDataPlayerRequest(Main.clubName, MaxDataType.HEIGHT);

        Main.ShowLoadingClubOptions(getClass());
        Main.client.GetNetworkUtil().write(Main.lastMaxDataRequest);
    }

    public void Action_max_age(ActionEvent actionEvent) throws IOException
    {
        Main.NullifyAllLastSearches();

        Main.lastMaxDataRequest = new MaxDataPlayerRequest(Main.clubName, MaxDataType.AGE);

        Main.ShowLoadingClubOptions(getClass());
        Main.client.GetNetworkUtil().write(Main.lastMaxDataRequest);
    }

    public static void RequestTotalSalary(ActionEvent actionEvent, Class<?> thisClass) throws IOException
    {
        TotalSalaryRequest totalSalaryRequest = new TotalSalaryRequest(Main.clubName);

        VBox vBox = FXMLLoader.load(Objects.requireNonNull(thisClass.getResource("loading.fxml")));
        AnchorPane anchorPane = new AnchorPane(vBox);

        anchorPane.setPrefSize(300, 100);
        AnchorPane.setTopAnchor(vBox, 0.0);
        AnchorPane.setBottomAnchor(vBox, 0.0);
        AnchorPane.setLeftAnchor(vBox, 0.0);
        AnchorPane.setRightAnchor(vBox, 0.0);

        Main.totalSalaryStage = new Stage();
        Main.totalSalaryStage.setScene(new Scene(anchorPane));
        Main.totalSalaryStage.setResizable(false);
        Main.totalSalaryStage.setTitle("Totol Salary");
        Main.totalSalaryStage.initModality(Modality.APPLICATION_MODAL);
        Main.totalSalaryStage.show();

        Main.client.GetNetworkUtil().write(totalSalaryRequest);
    }

    public void Action_total_salary(ActionEvent actionEvent) throws IOException
    {
        RequestTotalSalary(actionEvent, getClass());
    }
}
