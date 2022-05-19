package util;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class Not {
    public static void notificationsConfirm(String Title, String Text) {
        Notifications NotificationBuilder = Notifications.create()
                .title(Title)
                .text(Text)
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("Saved");

                    }
                });
        NotificationBuilder.darkStyle();
        NotificationBuilder.showInformation();
    }

    public static void notificationError(String Title, String text) {
        Notifications NotificationBuilder = Notifications.create()
                .title(Title)
                .text(text)
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                    }
                });
        NotificationBuilder.darkStyle();
        NotificationBuilder.showWarning();

    }
}
