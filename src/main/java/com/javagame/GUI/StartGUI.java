package com.javagame.GUI;

import org.springframework.stereotype.Component;

import com.javagame.SpringContext;

import controls.MiddleControl;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Màn hình start — giữ menu gốc (Play / Continue / Help / About / Exit).
 * Play -> chia bài và vào bàn chơi; Exit -> hỏi xác nhận rồi thoát.
 */
@Component
public class StartGUI implements SpringContext {
	public void start(Stage primaryStage) {
		BackgroundFill backgroundFill = new BackgroundFill(Color.GRAY, null, null);
		Background background = new Background(backgroundFill);
		BorderPane root = new BorderPane();
		root.setBackground(background);

		// tiêu đề NA88 ở trên cùng, như tên cửa sổ gốc
		Text title = new Text("NA88");
		title.setFont(Font.font(64));
		title.setFill(Color.WHITE);
		title.setEffect(new DropShadow(8, Color.DARKGRAY));
		BorderPane titlePane = new BorderPane(title);
		titlePane.setPadding(new javafx.geometry.Insets(40, 0, 0, 0));
		BorderPane.setAlignment(title, javafx.geometry.Pos.CENTER);
		root.setTop(titlePane);

		Button play = new Button("Play");
		play.setPrefWidth(180);
		play.setOnAction(event -> {
			MiddleControl control = new MiddleControl();
			// nút Menu trong bàn chơi -> dừng ván, quay về màn hình start
			control.showTablePlayer(primaryStage, () -> start(primaryStage));
		});

		Button _continue = new Button("Continue");
		_continue.setPrefWidth(180);
		_continue.setOnAction(event -> new Alert(Alert.AlertType.INFORMATION,
				"Chưa có ván nào được lưu.").showAndWait());

		Button help = new Button("Help");
		help.setPrefWidth(180);
		help.setOnAction(event -> new Alert(Alert.AlertType.INFORMATION,
				"Tiến Lên miền Nam — 4 người, 13 lá/người.\n"
						+ "3 bích đi trước. Đánh bộ nhỏ hơn thì mất lượt.\n"
						+ "Hết bài trước là thắng.").showAndWait());

		Button about = new Button("About");
		about.setPrefWidth(180);
		about.setOnAction(event -> new Alert(Alert.AlertType.INFORMATION,
				"NA88 — Tiến Lên\nDự án Java/JavaFX cũ được nối lại luật chơi và AI.")
						.showAndWait());

		Button exit = new Button("Exit");
		exit.setPrefWidth(180);
		exit.setOnAction(event -> exitApplication());

		VBox vBox = new VBox(10, play, _continue, help, about, exit);
		vBox.setAlignment(javafx.geometry.Pos.CENTER);
		Group group = new Group();
		group.getChildren().add(vBox);
		root.setCenter(group);

		Scene scene = new Scene(root, 800, 600, Color.GRAY);
		primaryStage.setTitle("NA88");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/** Giữ nguyên logic xác nhận thoát từ StartDesign gốc. */
	private void exitApplication() {
		Alert confirmExit = new Alert(Alert.AlertType.CONFIRMATION);
		confirmExit.setTitle("Exit");
		confirmExit.setHeaderText("Do you want exit app?");
		confirmExit.getButtonTypes().setAll(javafx.scene.control.ButtonType.OK,
				javafx.scene.control.ButtonType.CANCEL);
		java.util.Optional<javafx.scene.control.ButtonType> result = confirmExit.showAndWait();
		if (result.isPresent() && result.get() == javafx.scene.control.ButtonType.OK)
			javafx.application.Platform.exit();
	}
}
