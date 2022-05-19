/**
 * Main class for password generator UI
 * @author Robert McClintick
 */
package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main extends Application
{

	private int length = 12;
	private int maxIndex = 2;
	private Insets insets = new Insets(10);

	private TextField output;

	@Override
	public void start(Stage primaryStage)
	{
		try {
			VBox root = new VBox();
			root.setPadding(insets);

			// make length slider pane
			root.getChildren().add(makeLengthSlider());

			// make Radio and generate buttons
			HBox hb = new HBox(makeRadioGroup(), makeButtons());

			// make radio buttons
			root.getChildren().add(hb);
			// make generate button

			// make output box
			root.getChildren().add(makeOutputBox());

			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Password Generator");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private VBox makeRadioGroup()
	{
		ToggleGroup radioToggleGrp = new ToggleGroup();
		VBox radioGrp = new VBox();
		radioGrp.setPadding(insets);
		RadioButton abcBtn = new RadioButton("Alphabet only");
		RadioButton alphaNumBtn = new RadioButton("Alphanumeric");
		RadioButton alphaNumSymBtn = new RadioButton("Alphanumeric + Symbols");

		// alphanumeric with symbols by default
		alphaNumSymBtn.setSelected(true);

		abcBtn.setToggleGroup(radioToggleGrp);
		alphaNumBtn.setToggleGroup(radioToggleGrp);
		alphaNumSymBtn.setToggleGroup(radioToggleGrp);
		
		radioGrp.getChildren().addAll(abcBtn, alphaNumBtn, alphaNumSymBtn);

		//radio button listener
		radioToggleGrp.selectedToggleProperty().addListener(e -> {
			RadioButton sel = (RadioButton) radioToggleGrp.getSelectedToggle();
			switch (sel.getText())
			{
			case "Alphabet only":
				maxIndex = 0;
				break;
			case "Alphanumeric":
				maxIndex = 1;
				break;
			case "Alphanumeric + Symbols":
				maxIndex = 2;
				break;
			}
		});

		return radioGrp;
	}

	private VBox makeButtons()
	{
		VBox vb = new VBox();
		
		//generate button
		Button genBtn = new Button("Generate Password");
		genBtn.setOnAction(e -> {
			Generator gen = new Generator(maxIndex);
			output.setText(gen.randomString(length));
		});

		//copy button
		Button copyBtn = new Button("Copy Password");
		copyBtn.setOnAction(e -> {
			ClipboardContent content = new ClipboardContent();
			String op = output.getText();
			if (!op.isBlank()) {
				content.putString(output.getText());
				Clipboard.getSystemClipboard().setContent(content);
			}
		});

		vb.setSpacing(5);
		vb.setPadding(insets);
		vb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(genBtn, copyBtn);
		return vb;
	}

	private HBox makeLengthSlider()
	{
		HBox hb = new HBox();
		Label lengthLabel = new Label("Password Length:");

		Slider lengthSlider = new Slider(1, 30, 12);
		lengthSlider.setMaxWidth(Double.MAX_VALUE);

		Label selectedLengthLbl = new Label(Integer.toString((int) Math.round(lengthSlider.getValue())));

		lengthSlider.valueProperty().addListener(e -> {
			length = (int) Math.round(lengthSlider.getValue());
			selectedLengthLbl.setText(Integer.toString(length));
		});
		hb.setAlignment(Pos.CENTER);
		hb.getChildren().addAll(lengthLabel, lengthSlider, selectedLengthLbl);

		return hb;
	}

	private VBox makeOutputBox()
	{
		VBox vb = new VBox();
		output = new TextField();
		output.setPadding(insets);
		output.setPromptText("Output goes here");
		output.setEditable(false);
		output.setAlignment(Pos.CENTER);

		vb.getChildren().add(output);
		vb.setAlignment(Pos.CENTER);
		vb.setPadding(insets);
		return vb;
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
