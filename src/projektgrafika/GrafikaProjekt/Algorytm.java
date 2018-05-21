package projektgrafika.GrafikaProjekt;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Algorytm {

	private BufferedImage obraz, nowyObraz;
	private final int rozmiarMaski = 5;
	private int margines = (rozmiarMaski-1)/2;
	private int[] maska;
	private Rozdzka rozdzka;
	
	
	
	//--------------------- Konstruktory--------------------------

	public Algorytm(){

	}

	public Algorytm(BufferedImage obraz) {
		this.obraz = obraz;
		this.nowyObraz = new BufferedImage(obraz.getWidth(), obraz.getHeight(), obraz.getType());
		this.maska = new Maska().pobierzMaske(Typ.LOSOWY);
		this.rozdzka = new Rozdzka();
	}
	
	public Algorytm(BufferedImage obraz, Typ maska) {
		this.obraz = obraz;
		this.nowyObraz = new BufferedImage(obraz.getWidth(), obraz.getHeight(), obraz.getType());
		this.maska = new Maska().pobierzMaske(maska);
		this.rozdzka = new Rozdzka();
	}
	
	public Algorytm(String sciezka) throws IOException {
		File plik = new File(sciezka);
		
		this.obraz = ImageIO.read(plik);
		this.nowyObraz = new BufferedImage(obraz.getWidth(), obraz.getHeight(), obraz.getType());
		this.maska = new Maska().pobierzMaske(Typ.LOSOWY);
		this.rozdzka = new Rozdzka();
	}
	
	public Algorytm(String sciezka, Typ maska) throws IOException {
		File plik = new File(sciezka);
		
		this.obraz = ImageIO.read(plik);
		this.nowyObraz = new BufferedImage(obraz.getWidth(), obraz.getHeight(), obraz.getType());
		this.maska = new Maska().pobierzMaske(maska);
		this.rozdzka = new Rozdzka();
	}
	
	//-----------------------------------------------------------------------------
	
	public void wybierzObraz(BufferedImage obraz) {
		this.obraz = obraz;
	}
	
	public void wybierzObraz(String sciezka) throws IOException {
		File plik = new File(sciezka);
		this.obraz = ImageIO.read(plik);
	}
	
	public void wybierzMaske(Typ maska) {
		this.maska = new Maska().pobierzMaske(maska);
	}
	
	public void ustawMaskeUzytkownika(int[] maska) throws Exception {
		if(maska.length != 25)
			throw new Exception("Maska nie jest zgodna z przyjêt¹ d³ugoœci¹ maski wynosz¹c¹ 5x5 (rozmiar = 25)");
		else
			this.maska = maska;
	}
	
	public void wlaczRozdzke() {
		this.rozdzka.setCzyAktywna(true);
	}
	
	public void wylaczRozdzke() {
		this.rozdzka.setCzyAktywna(false);
	}
	
	public void ustawPozycjeRozdzki(int x, int y) {
		this.rozdzka.setX(x);
		this.rozdzka.setY(y);
	}
	
	public void zwiekszMocRozdzki() {
		this.rozdzka.zmnieszMoc();
	}
	
	public void zmniejszMocRozdzki() {
		this.zmniejszMocRozdzki();
	}
	
	public void ustawMocRozdzki(int moc) throws Exception {
		if(moc >= 0 && moc <=255)
			this.rozdzka.setMoc(moc);
		else
			throw new Exception("Ustawiona moc rozdzki jest mniejsza od 0 lub wieksza od 255. Zakres <0, 255>");
	}
		
	public BufferedImage pobierzObrazPoFiltracji() {
		return nowyObraz;
	}
	
	// Prosta funkcja sprawdzaj¹ca czy obraz jest kolorowy
	boolean czyKolorowy(BufferedImage obraz) {
		WritableRaster raster = obraz.getRaster();
		int[] pixele = new int[3];
		
		raster.getPixel(0, 0, pixele);
				
		if((pixele[0] != pixele[1]) && (pixele[1] != pixele[2]))
			return true;
		else
			return false;
	}

	// Filtruje obrazek, który zosta³ za³adowany do klasy
	public BufferedImage filtruj() {
		this.nowyObraz = algorytmFiltracji(this.obraz);
		return this.nowyObraz;		
	}
	
	// Filtruje obrazek, który zosta³ podany w argumencie
	public BufferedImage filtruj(BufferedImage obraz) {
		this.nowyObraz = algorytmFiltracji(obraz);
		return this.nowyObraz;
	}
	
	// Filtruje obraz, który zosta³ za³adowany do klasy wybran¹ metod¹ filtruj¹c¹
	public BufferedImage filtruj(Typ maska) {
		this.maska = new Maska().pobierzMaske(maska);
		
		this.nowyObraz = algorytmFiltracji(this.obraz);
		return this.nowyObraz;		
	}
	
	// Filtruje obrazek, który zosta³ podany w argumencie wybran¹ metod¹ filtruj¹c¹
	public BufferedImage filtruj(BufferedImage obraz, Typ maska) {
		this.maska = new Maska().pobierzMaske(maska);
		
		this.nowyObraz = algorytmFiltracji(obraz);
		return this.nowyObraz;
	}
	
	// Funkcja, która sprawdza czy obraz jest w odcieniach szaroœci i przekazuje go do odpowiedniej ju¿ funkcji filtruj¹cej
	private BufferedImage algorytmFiltracji(BufferedImage obraz) {
		if(this.czyKolorowy(obraz)){
			return this.algorytmFiltracjiKolorowe(obraz);
		}
		else
			return this.algorytmFiltracjiOdcienieSzarosci(obraz);
	}
	
	// Algorytm filtruj¹cy obrazy w odcieniach szarosci
	private BufferedImage algorytmFiltracjiOdcienieSzarosci(BufferedImage obraz) {
		BufferedImage poEdycji = new BufferedImage(obraz.getWidth(), obraz.getHeight(), obraz.getType());
		WritableRaster raster = obraz.getRaster();
		WritableRaster newImageRaster = poEdycji.getRaster();
		
		int height = raster.getHeight();
		int width = raster.getWidth();
		
		
		int sumaFiltru = 0;
		for (int i=0; i<maska.length; i++)
			sumaFiltru += maska[i];
		if (sumaFiltru == 0) sumaFiltru = 1;
		
		int pixele[] = new int[3];
		
		for(int i=margines; i<width-margines; i++){
			for(int j=margines; j<height-margines; j++){
				
				int suma = 0;
				
				int pixeleRozdzka[] = new int[3];
				raster.getPixel(rozdzka.getX(), rozdzka.getY(), pixeleRozdzka);
				
				raster.getPixel(i, j, pixele);
				
				for(int k=0; k<rozmiarMaski; k++){
					for(int l=0; l<rozmiarMaski; l++){
						raster.getPixel(i+k-margines, j+l-margines, pixele);
						
						suma += maska[k*rozmiarMaski+l] * pixele[0];
					}
				}
				
				if(rozdzka.isCzyAktywna()) {
					if (pixele[0] > pixeleRozdzka[0] - rozdzka.getMoc()	&& pixele[0] < pixeleRozdzka[0] + rozdzka.getMoc()) {
						suma /= sumaFiltru;
				
						if (suma > 255) 
							suma = 255;
						else if (suma < 0) 
							suma = 0;
						
						pixele[0] = suma;
						pixele[1] = suma;
						pixele[2] = suma;
					}
				} else {
					suma /= sumaFiltru;
					
					if (suma > 255) 
						suma = 255;
					else if (suma < 0) 
						suma = 0;
					
					pixele[0] = suma;
					pixele[1] = suma;
					pixele[2] = suma;
				}
					
				newImageRaster.setPixel(i, j, pixele);
			}
		}
		poEdycji.setData(newImageRaster);
		
		return poEdycji;
	}	
	
	// Algorytm filtruj¹cy obrazy kolorowe
	private BufferedImage algorytmFiltracjiKolorowe(BufferedImage obraz) {
		BufferedImage poEdycji = new BufferedImage(obraz.getWidth(), obraz.getHeight(), obraz.getType());
		WritableRaster raster = obraz.getRaster();
		WritableRaster newImageRaster = poEdycji.getRaster();
		
		int height = raster.getHeight();
		int width = raster.getWidth();
		
		
		int sumaFiltru = 0;
		for (int i=0; i<maska.length; i++)
			sumaFiltru += maska[i];
		if (sumaFiltru == 0) sumaFiltru = 1;
		
		int pixele[] = new int[3];
		
		for(int i=margines; i<width-margines; i++){
			for(int j=margines; j<height-margines; j++){
				
				int sumaR = 0;
				int sumaG = 0;
				int sumaB = 0;
				
				int pixeleRozdzka[] = new int[3];
				raster.getPixel(rozdzka.getX(), rozdzka.getY(), pixeleRozdzka);
				
				raster.getPixel(i, j, pixele);
				
				
				for(int k=0; k<rozmiarMaski; k++){
					for(int l=0; l<rozmiarMaski; l++){
						raster.getPixel(i+k-margines, j+l-margines, pixele);
						
						sumaR += maska[k*rozmiarMaski+l] * pixele[0];
						sumaG += maska[k*rozmiarMaski+l] * pixele[1];
						sumaB += maska[k*rozmiarMaski+l] * pixele[2];
					}
				}
				
				if(rozdzka.isCzyAktywna()) {
					if (pixele[0] > pixeleRozdzka[0] - rozdzka.getMoc()	&& pixele[0] < pixeleRozdzka[0] + rozdzka.getMoc()) {
						sumaR /= sumaFiltru;
						sumaG /= sumaFiltru;
						sumaB /= sumaFiltru;
						
						if (sumaR > 255) 
							sumaR = 255;
						else if (sumaR < 0) 
							sumaR = 0;
						if (sumaG > 255) 
							sumaG = 255;
						else if (sumaG < 0) 
							sumaG = 0;
						if (sumaB > 255) 
							sumaB = 255;
						else if (sumaB < 0) 
							sumaB = 0;
						
						pixele[0] = sumaR;
						pixele[1] = sumaG;
						pixele[2] = sumaB;
						
					}
				} else {
					sumaR /= sumaFiltru;
					sumaG /= sumaFiltru;
					sumaB /= sumaFiltru;
					
					if (sumaR > 255) 
						sumaR = 255;
					else if (sumaR < 0) 
						sumaR = 0;
					if (sumaG > 255) 
						sumaG = 255;
					else if (sumaG < 0) 
						sumaG = 0;
					if (sumaB > 255) 
						sumaB = 255;
					else if (sumaB < 0) 
						sumaB = 0;
					
					pixele[0] = sumaR;
					pixele[1] = sumaG;
					pixele[2] = sumaB;
					
				}
				
				
				newImageRaster.setPixel(i, j, pixele);
			}
		}
		poEdycji.setData(newImageRaster);
		
		return poEdycji;
	}
	
	// Konwertuje obraz kolorowy na obraz w ocieniach szaroœci
	public BufferedImage odcienieSzarosci(BufferedImage obraz){
		WritableRaster raster = obraz.getRaster();
		
		int pixele[] = new int[3];
		
		double odcienie[] = new double[3];
		int h = raster.getHeight();
		int w = raster.getWidth();
		
		
		for(int i=0; i<w; i++){
			for(int j=0; j<h; j++){
	            raster.getPixel(i, j, pixele);
	            
	            odcienie[0] = 0.299 * pixele[0] + 0.587 * pixele[1] + 0.114 * pixele[2];
	            odcienie[1] = 0.299 * pixele[0] + 0.587 * pixele[1] + 0.114 * pixele[2];
	            odcienie[2] = 0.299 * pixele[0] + 0.587 * pixele[1] + 0.114 * pixele[2];
	            
	            raster.setPixel(i, j, odcienie);
			}
		}
		obraz.setData(raster);
        
		return obraz;
	}
	
}
