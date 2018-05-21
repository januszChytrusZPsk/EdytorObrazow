package projektgrafika;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public  class RozdzkaControler implements Initializable, Runnable {
    @FXML private ImageView view;
    private Image img;
    private double[] pos = new double[2];
    
    public void getImage(Images images) {
    	this.img = images.getImg();
    	view.setFitHeight(images.getImg().getHeight());
    	view.setFitWidth(images.getImg().getWidth());
    	view.setImage(img);
    	view.setOnMouseClicked(new EventHandler<MouseEvent>(){
							            @Override
							            public void handle(MouseEvent event) {
							            	pos[0] = event.getX();
							            	pos[1] = event.getY();
							            	images.setX(pos[0]);
							            	images.setY(pos[1]);
							            	
							            }
						    		}
						    	);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

	@Override
	public void run() {

	}
}