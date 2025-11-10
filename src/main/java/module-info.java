module ClashCards {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.media;
    requires javafx.swing;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    // requires eu.hansolo.tilesfx;
    // requires eu.hansolo.fx.countries;
    // requires eu.hansolo.fx.heatmap;
    // requires eu.hansolo.toolbox;

    opens UI to javafx.fxml;
    exports UI;
}