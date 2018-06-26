package projektgrafika;


import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

import projektgrafika.GrafikaProjekt.*;



public class FXMLDocumentController implements Initializable {
	
	@FXML private ImageView oriImage, endImage;
	@FXML private ComboBox<String> mocRozdzki;
	@FXML private ToggleButton czyRozdzka;
	@FXML private RadioMenuItem czyCB;
	
	private boolean czyCzarnoBiale;
	private File plik;
    private BufferedImage wczytanyObraz;
    private Image wczytanyIMG;
    private Image otrzymanyIMG;
    private BufferedImage otrzymanyObraz;
    private boolean czyObrazek;
    private boolean czyOtrzymano;
    private boolean czyWybranaMaska;
    private boolean blad;
    private Typ wybranaMaska;
    private int moc;

    private Maska maskaObiekt;
	private Algorytm filtrowanie;
	private Rozdzka rozdzka;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	czyObrazek = false;
    	blad = false;
    	czyOtrzymano = false;
    	czyWybranaMaska = false;
    	czyCzarnoBiale = false; 
    	wybranaMaska = Typ.LOSOWY;
    	maskaObiekt = new Maska();
    	filtrowanie = new Algorytm();
    	rozdzka = new Rozdzka();
    	rozdzka.setCzyAktywna(false);
    	moc = 0;
    	mocRozdzki.getItems().addAll("0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
    								"10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
    								"20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
    								"30", "31", "32", "33", "34", "35", "36", "37", "38", "39",
    								"40", "41", "42", "43", "44", "45", "46", "47", "48", "49",
    								"50", "51");

    	mocRozdzki.setValue("0");
    }  

    
    @FXML private void openPicOption(ActionEvent event) throws MalformedURLException, IOException {
    	blad = false;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Otwórz Plik");
        fileChooser.getExtensionFilters().add( new ExtensionFilter("Obrazy","*.jpg", "*.jpeg","*.bmp"));
        plik = fileChooser.showOpenDialog(new Stage());
        if (plik != null) { 
        	try {
            wczytajObrazek(plik);
        	}
        	catch(NullPointerException e) {
        		alert("Błąd odczytu pliku !!");
        		blad = true;
        	}
        	if(!blad) {
        	wyswietlObrazek(oriImage, wczytanyIMG);
            czyObrazek = true;
        	}
        }
        else {
            alert("Brak wybranego obrazu !");
            czyObrazek = false;
        }
    }    
    
    @FXML private void saveOption(ActionEvent event) {
    	if(czyOtrzymano) {
	    	FileChooser saveFileChooser = new FileChooser();
	        saveFileChooser.setTitle("Zapisz otrzymany obraz");
	        saveFileChooser.getExtensionFilters().addAll(   new ExtensionFilter("plik PNG",  "*.png"),
                                                            new ExtensionFilter("plik JPG", "*.jpg"),
                                                            new ExtensionFilter("plik JPEG","*.jpeg")
                                                        );
	        File selectedFile = saveFileChooser.showSaveDialog(new Stage());

	        if(selectedFile!= null) {
	            try {
                        ImageIO.write(SwingFXUtils.fromFXImage(otrzymanyIMG, null), "png", selectedFile);
	            } catch (IOException e) {
	                    alert("Błąd zapisu !");
	                    e.printStackTrace();
	            }
		    }
    	}
    	else {
    		alert("Brak pliku do zapisu !");
    	}
    }
    
    static BufferedImage deepCopy(BufferedImage image) {
    	 ColorModel colorModel = image.getColorModel();
    	 boolean isAlphaPremultiplied = colorModel.isAlphaPremultiplied();
    	 WritableRaster raster = image.copyData(null);
    	 return new BufferedImage(colorModel, raster, isAlphaPremultiplied, null);
    	}


    @FXML private void startButton(ActionEvent event) throws Exception {
        rozpocznij();
    }

    private void rozpocznij(){
        if (czyObrazek && czyWybranaMaska) {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(new start());
        }
        if (!czyObrazek && !czyWybranaMaska) {
            this.alert("Brak obrazu i wybranego filtru!\nWczytaj obraz i wybierz filtr!");
        } else {
            if (!czyObrazek) {
                this.alert("Brak obrazu !\nWczytaj najpierw obraz!");
            }
            if (!czyWybranaMaska) {
                this.alert("Brak wybranego filtru!\nWybierz najpierw filtr!");
            }
        }
    }


    class start implements Runnable {
        @Override
        public void run() {
                filtrowanie.wybierzObraz(wczytanyObraz);
                filtrowanie.wybierzMaske(wybranaMaska);
                if (czyCzarnoBiale) {
                    BufferedImage bc = deepCopy(otrzymanyObraz);
                    bc = filtrowanie.odcienieSzarosci(bc);
                    filtrowanie.wybierzObraz(bc);
                }
                filtrowanie.filtruj();
                otrzymanyObraz = filtrowanie.pobierzObrazPoFiltracji();

                otrzymanyIMG = SwingFXUtils.toFXImage(otrzymanyObraz, null);
                wyswietlObrazek(endImage, otrzymanyIMG);
                czyOtrzymano = true;
        }

        private void alert(String text){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Filtrowanie Obrazów");
            alert.setHeaderText(null);
            alert.setContentText(text);
            Stage st = (Stage) alert.getDialogPane().getScene().getWindow();
            st.getIcons().add(new Image(this.getClass().getResource("ico.jpg").toString()));
            alert.showAndWait();
        }

    }
    
    @FXML private void czyRozdzkaButton(ActionEvent event) {
    	
    	if(rozdzka.isCzyAktywna()) {
    		rozdzka.setCzyAktywna(false);
    	}
    	else {
    		rozdzka.setCzyAktywna(true);
    	}
    }

    @FXML private void setMoc(ActionEvent event) throws Exception {
    	int tmpMoc = Integer.parseInt(mocRozdzki.getValue());
    	this.moc = 5*tmpMoc;
        filtrowanie.ustawMocRozdzki(this.moc);
    	
    }
    
    @FXML private void usrOption(ActionEvent event) {
        wybranaMaska = Typ.USREDNIAJACY;
        czyWybranaMaska = true;
        filtrowanie.wybierzMaske(Typ.UZYTKOWNIKA);
    }
    
    @FXML private void hp3Option(ActionEvent event) {
        wybranaMaska = Typ.HP3;
        czyWybranaMaska = true;
        filtrowanie.wybierzMaske(Typ.HP3);
    }
    
    @FXML private void sobOption(ActionEvent event) {
        wybranaMaska = Typ.PIONOWY_SOBELA;
        czyWybranaMaska = true;
        filtrowanie.wybierzMaske(Typ.PIONOWY_SOBELA);
    }
    
    @FXML private void rndOption(ActionEvent event) {
        wybranaMaska = Typ.LOSOWY;
        czyWybranaMaska = true;
        filtrowanie.wybierzMaske(Typ.LOSOWY);
    }
    
    @FXML private void userOption(ActionEvent event) {
        wybranaMaska = Typ.UZYTKOWNIKA;
        czyWybranaMaska = true;
        filtrowanie.wybierzMaske(Typ.UZYTKOWNIKA);
    }
    @FXML private void cbOption(ActionEvent event) {
        if (!czyCzarnoBiale) {
            czyCzarnoBiale = true;
        }
      	else
        	czyCzarnoBiale = false;              	
    }
    
    @FXML private void aboutOption(ActionEvent event) {
        alert("Autor: \nTomasz Szyma�ski");
    }
    
    @FXML private void exitOption(ActionEvent event) {
        Platform.exit();
    }
    

    
    
    private void wczytajObrazek(File plik){
        try {
            wczytanyObraz = ImageIO.read(plik);
            wczytanyIMG = SwingFXUtils.toFXImage(wczytanyObraz, null);
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }


    @FXML
    private void ustawPozycjeRozdzki() {
        oriImage.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (rozdzka.isCzyAktywna()) {
                    int x = (int) event.getX();
                    int y = (int) event.getY();


                    rozdzka.setX(x);
                    rozdzka.setY(y);
                    System.out.println(rozdzka.getX() + " "+ rozdzka.getY());
                    try {
                        rozpocznij();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    alert("Różdzka nie jest aktywna !!");
                }

            }
        });
    }



    private void wyswietlObrazek(ImageView view, Image image){
        view.setImage(image);
    }

    @FXML private void rozdzkaWindow(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("layout/MaskaView.fxml"));
            Parent maska = loader.load();

            Scene scene = new Scene(maska);

            MaskaControler controler = loader.<MaskaControler>getController();
            controler.getMask(this.maskaObiekt);

            Stage window = new Stage();
            window.setResizable(false);
            window.setScene(scene);
            window.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void alert(String text){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Filtrowanie Obrazów");
        alert.setHeaderText(null);
        alert.setContentText(text);
        Stage st = (Stage) alert.getDialogPane().getScene().getWindow();
        st.setResizable(false);
        st.getIcons().add(new Image(this.getClass().getResource("ico.jpg").toString()));
        alert.showAndWait();
    }
}
