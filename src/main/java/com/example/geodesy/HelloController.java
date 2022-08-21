package com.example.geodesy;

import com.example.geodesy.count.Manager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
      Manager manager = new Manager();
        manager.addFileNames("Файлы для рассчета");
      if (manager.getFileList().isEmpty()) {
          manager.createFileSample();
          welcomeText.setText("Внесите данные в файл Образец");
      } else {
          manager.startReading();
          welcomeText.setText("Расчеты выполнены. Результаты в папке Результаты рассчета");
      }
    }
}