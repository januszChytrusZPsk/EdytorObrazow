package projektgrafika;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

import javafx.stage.Window;
import projektgrafika.GrafikaProjekt.*;

public  class MaskaControler implements Initializable {
    @FXML private Spinner<Integer> f00;
    @FXML private Spinner<Integer> f01;
    @FXML private Spinner<Integer> f02;
    @FXML private Spinner<Integer> f03;
    @FXML private Spinner<Integer> f04;
    @FXML private Spinner<Integer> f10;
    @FXML private Spinner<Integer> f11;
    @FXML private Spinner<Integer> f12;
    @FXML private Spinner<Integer> f13;
    @FXML private Spinner<Integer> f14;
    @FXML private Spinner<Integer> f20;
    @FXML private Spinner<Integer> f21;
    @FXML private Spinner<Integer> f22;
    @FXML private Spinner<Integer> f23;
    @FXML private Spinner<Integer> f24;
    @FXML private Spinner<Integer> f30;
    @FXML private Spinner<Integer> f31;
    @FXML private Spinner<Integer> f32;
    @FXML private Spinner<Integer> f41;
    @FXML private Spinner<Integer> f40;
    @FXML private Spinner<Integer> f33;
    @FXML private Spinner<Integer> f42;
    @FXML private Spinner<Integer> f43;
    @FXML private Spinner<Integer> f34;
    @FXML private Spinner<Integer> f44;
    @FXML private Button maskaButton;
    
    private int[] maska=new int[25];
    Maska mask ;
  
    public void getMask(Maska mask) {
        this.mask = mask;
    	maska = Arrays.copyOf(this.mask.pobierzMaske(Typ.UZYTKOWNIKA),25);
    	SpinnerValueFactory<Integer> valueFactoryf00 = new SpinnerValueFactory.IntegerSpinnerValueFactory(-5, 5, maska[0]);
        SpinnerValueFactory<Integer> valueFactoryf01 = new SpinnerValueFactory.IntegerSpinnerValueFactory(-5, 5, maska[1]);
        SpinnerValueFactory<Integer> valueFactoryf02 = new SpinnerValueFactory.IntegerSpinnerValueFactory(-5, 5, maska[2]);
        SpinnerValueFactory<Integer> valueFactoryf03 = new SpinnerValueFactory.IntegerSpinnerValueFactory(-5, 5, maska[3]);
        SpinnerValueFactory<Integer> valueFactoryf04 = new SpinnerValueFactory.IntegerSpinnerValueFactory(-5, 5, maska[4]);
        
        SpinnerValueFactory<Integer> valueFactoryf10 = new SpinnerValueFactory.IntegerSpinnerValueFactory(-5, 5, maska[5]);
        SpinnerValueFactory<Integer> valueFactoryf11 = new SpinnerValueFactory.IntegerSpinnerValueFactory(-5, 5, maska[6]);
        SpinnerValueFactory<Integer> valueFactoryf12 = new SpinnerValueFactory.IntegerSpinnerValueFactory(-5, 5, maska[7]);
        SpinnerValueFactory<Integer> valueFactoryf13 = new SpinnerValueFactory.IntegerSpinnerValueFactory(-5, 5, maska[8]);
        SpinnerValueFactory<Integer> valueFactoryf14 = new SpinnerValueFactory.IntegerSpinnerValueFactory(-5, 5, maska[9]);
        
        SpinnerValueFactory<Integer> valueFactoryf20 = new SpinnerValueFactory.IntegerSpinnerValueFactory(-5, 5, maska[10]);
        SpinnerValueFactory<Integer> valueFactoryf21 = new SpinnerValueFactory.IntegerSpinnerValueFactory(-5, 5, maska[11]);
        SpinnerValueFactory<Integer> valueFactoryf22 = new SpinnerValueFactory.IntegerSpinnerValueFactory(-5, 5, maska[12]);
        SpinnerValueFactory<Integer> valueFactoryf23 = new SpinnerValueFactory.IntegerSpinnerValueFactory(-5, 5, maska[13]);
        SpinnerValueFactory<Integer> valueFactoryf24 = new SpinnerValueFactory.IntegerSpinnerValueFactory(-5, 5, maska[14]);
        
        SpinnerValueFactory<Integer> valueFactoryf30 = new SpinnerValueFactory.IntegerSpinnerValueFactory(-5, 5, maska[15]);
        SpinnerValueFactory<Integer> valueFactoryf31 = new SpinnerValueFactory.IntegerSpinnerValueFactory(-5, 5, maska[16]);
        SpinnerValueFactory<Integer> valueFactoryf32 = new SpinnerValueFactory.IntegerSpinnerValueFactory(-5, 5, maska[17]);
        SpinnerValueFactory<Integer> valueFactoryf33 = new SpinnerValueFactory.IntegerSpinnerValueFactory(-5, 5, maska[18]);
        SpinnerValueFactory<Integer> valueFactoryf34 = new SpinnerValueFactory.IntegerSpinnerValueFactory(-5, 5, maska[19]);
        
        SpinnerValueFactory<Integer> valueFactoryf40 = new SpinnerValueFactory.IntegerSpinnerValueFactory(-5, 5, maska[20]);
        SpinnerValueFactory<Integer> valueFactoryf41 = new SpinnerValueFactory.IntegerSpinnerValueFactory(-5, 5, maska[21]);
        SpinnerValueFactory<Integer> valueFactoryf42 = new SpinnerValueFactory.IntegerSpinnerValueFactory(-5, 5, maska[22]);
        SpinnerValueFactory<Integer> valueFactoryf43 = new SpinnerValueFactory.IntegerSpinnerValueFactory(-5, 5, maska[23]);
        SpinnerValueFactory<Integer> valueFactoryf44 = new SpinnerValueFactory.IntegerSpinnerValueFactory(-5, 5, maska[24]);
        
        this.f00.setValueFactory(valueFactoryf00);
        this.f01.setValueFactory(valueFactoryf01);
        this.f02.setValueFactory(valueFactoryf02);
        this.f03.setValueFactory(valueFactoryf03);
        this.f04.setValueFactory(valueFactoryf04);
        
        this.f10.setValueFactory(valueFactoryf10);
        this.f11.setValueFactory(valueFactoryf11);
        this.f12.setValueFactory(valueFactoryf12);
        this.f13.setValueFactory(valueFactoryf13);
        this.f14.setValueFactory(valueFactoryf14);
        
        this.f20.setValueFactory(valueFactoryf20);
        this.f21.setValueFactory(valueFactoryf21);
        this.f22.setValueFactory(valueFactoryf22);
        this.f23.setValueFactory(valueFactoryf23);
        this.f24.setValueFactory(valueFactoryf24);
        
        this.f30.setValueFactory(valueFactoryf30);
        this.f31.setValueFactory(valueFactoryf31);
        this.f32.setValueFactory(valueFactoryf32);
        this.f33.setValueFactory(valueFactoryf33);
        this.f34.setValueFactory(valueFactoryf34);
        
        this.f40.setValueFactory(valueFactoryf40);
        this.f41.setValueFactory(valueFactoryf41);
        this.f42.setValueFactory(valueFactoryf42);
        this.f43.setValueFactory(valueFactoryf43);
        this.f44.setValueFactory(valueFactoryf44);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    @FXML    private void maskaButtonPush(ActionEvent event) {

        maska[0]=Integer.parseInt(f00.getValue().toString());
        maska[1]=Integer.parseInt(f01.getValue().toString());
        maska[2]=Integer.parseInt(f02.getValue().toString());
        maska[3]=Integer.parseInt(f03.getValue().toString());
        maska[4]=Integer.parseInt(f04.getValue().toString());
        
        maska[5]=Integer.parseInt(f10.getValue().toString());
        maska[6]=Integer.parseInt(f11.getValue().toString());
        maska[7]=Integer.parseInt(f12.getValue().toString());
        maska[8]=Integer.parseInt(f13.getValue().toString());
        maska[9]=Integer.parseInt(f14.getValue().toString());
        
        maska[10]=Integer.parseInt(f20.getValue().toString());
        maska[11]=Integer.parseInt(f21.getValue().toString());
        maska[12]=Integer.parseInt(f22.getValue().toString());
        maska[13]=Integer.parseInt(f23.getValue().toString());
        maska[14]=Integer.parseInt(f24.getValue().toString());
        
        maska[15]=Integer.parseInt(f30.getValue().toString());
        maska[16]=Integer.parseInt(f31.getValue().toString());
        maska[17]=Integer.parseInt(f32.getValue().toString());
        maska[18]=Integer.parseInt(f33.getValue().toString());
        maska[19]=Integer.parseInt(f34.getValue().toString());
        
        maska[20]=Integer.parseInt(f40.getValue().toString());
        maska[21]=Integer.parseInt(f41.getValue().toString());
        maska[22]=Integer.parseInt(f42.getValue().toString());
        maska[23]=Integer.parseInt(f43.getValue().toString());
        maska[24]=Integer.parseInt(f44.getValue().toString());

        this.mask.setUserMask(maska);

        Stage stage = (Stage) maskaButton.getScene().getWindow();
        stage.close();
    }
    
    
}
