package gui;

import duke.Duke;
import duke.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    private Image userImage = new Image(this.getClass().getResourceAsStream("/Images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/Images/DaDuke.png"));

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    /**
     * Sets the UI settings.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets Duke.
     *
     * @param d Duke.
     */
    public void setDuke(Duke d) {
        duke = d;
        String greetings = Ui.getWelcomeText();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(greetings, dukeImage)
        );
    }

    /**
     * Handles user input.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (!duke.isRunning()) {
            System.exit(0);
        }
    }
}
