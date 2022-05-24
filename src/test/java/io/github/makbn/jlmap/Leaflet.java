package io.github.makbn.jlmap;

import io.github.makbn.jlmap.listener.OnJLMapViewListener;
import io.github.makbn.jlmap.listener.OnJLObjectActionListener;
import io.github.makbn.jlmap.model.*;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class Leaflet extends Application {
	
	public static void main(String[] args){
		Leaflet.launch(args);
	}
        private final String ACCESS_TOKEN = "pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw";
    @Override
    public void start(Stage stage) {

        //stage.initStyle(StageStyle.TRANSPARENT);
        System.out.println("hi");
        //building a new map view
        final JLMapView map = JLMapView
                .builder()
                .mapType(JLProperties.MapType.LIGHT)
                .accessToken(ACCESS_TOKEN)
                .startCoordinate(JLLatLng.builder()
                        .lat(43.54)
                        .lng(22.54)
                        .build())
                .build();

        //creating a window
        //AnchorPane inside = createBasePane();
        AnchorPane root = new AnchorPane(map);
        root.setBackground(Background.EMPTY);
        root.setMinHeight(JLProperties.INIT_MIN_HEIGHT_STAGE);
        root.setMinWidth(JLProperties.INIT_MIN_WIDTH_STAGE);
        Scene scene = new Scene(root);

        //adding map to window
        //inside.getChildren().add(map);

        stage.setMinHeight(JLProperties.INIT_MIN_HEIGHT_STAGE);
        stage.setMinWidth(JLProperties.INIT_MIN_WIDTH_STAGE);
        scene.setFill(Color.TRANSPARENT);
        stage.setTitle("Java-Leaflet Test");
        stage.setScene(scene);
        stage.show();

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY(100);

        //set listener fo map events
        map.setMapListener(new OnJLMapViewListener() {
            @Override
            public void mapLoadedSuccessfully(JLMapView mapView) {
                addMultiPolyline(map);
               // addPolyline(map);
               // addPolygon(map);

                map.setView(JLLatLng.builder()
                        .lng(10)
                        .lat(10)
                        .build());
                

            
                map.getUiLayer()
                .addMarker(JLLatLng.builder()
                		.lat( 47.164221)
                		.lng(27.590581 )
                        .build(), "Tg Cucu", true)
                .setOnActionListener(getListener());
                            		
            }
            @Override
            public void mapFailed() {
                log.error("map failed!");
            }

            @Override
            public void onMove(Action action, JLLatLng center, JLBounds bounds, int zoomLevel) {
                super.onMove(action, center, bounds, zoomLevel);

                System.out.println("map on move: " + action + " c:" + center + " \t bounds:" + bounds + "\t z:" + zoomLevel);

            }
        });
    }

    private OnJLObjectActionListener<JLMarker> getListener() {
        return new OnJLObjectActionListener<JLMarker>() {
            @Override
            public void click(JLMarker object, Action action) {
                System.out.println("object click listener for marker:" + object);
            }

            @Override
            public void move(JLMarker object, Action action) {
                System.out.println("object move listener for marker:" + object);
            }
        };
    }

    private void addMultiPolyline(JLMapView map) {
        JLLatLng[][] verticesT = new JLLatLng[2][];

        verticesT[0] = new JLLatLng[]{
                new JLLatLng(41.509, 20.08),
                new JLLatLng(31.503, -10.06),
                new JLLatLng(21.51, -0.047)
        };

        verticesT[1] = new JLLatLng[]{
                new JLLatLng(51.509, 10.08),
                new JLLatLng(55.503, 15.06),
                new JLLatLng(42.51, 20.047)
        };

      //  map.getVectorLayer().addMultiPolyline(verticesT);
    }
  

    private AnchorPane createBasePane() {
        AnchorPane inside = new AnchorPane();
        inside.setStyle("-fx-background-color: #555555");
        AnchorPane.setLeftAnchor(inside, (double) JLProperties.NORMAL_MARGIN);
        AnchorPane.setRightAnchor(inside, (double) JLProperties.NORMAL_MARGIN);
        AnchorPane.setTopAnchor(inside, (double) JLProperties.NORMAL_MARGIN);
        AnchorPane.setBottomAnchor(inside, (double) JLProperties.NORMAL_MARGIN);

        return inside;

    }
}