package projektgrafika.GrafikaProjekt;

import java.util.Arrays;
import java.util.Random;

public class Maska {
	//Predefiniowana maska
	private int[] filtrUsredniajacy = {
			1, 1, 1, 1, 1,
			1, 1, 1, 1, 1,
			1, 1, 1, 1, 1,
			1, 1, 1, 1, 1,
			1, 1, 1, 1, 1
		};
	
	//Predefiniowana maska
	private int[] filtrHP3 = {
				-1, 0, 0, 0, 0,
				0, -1, 0, 0, 0,
				0, 0, 1, 0, 0,
				0, 0, 0, 0, 0,
				0, 0, 0, 0, 0
			};
	
	//Predefiniowana maska
	private int[] pionowyFiltrSobela = {
			2, 1, 0, -1, -2,
			3, 2, 0, -2, -3,
			4, 3, 0, -3, -4,
			3, 2, 0, -2, -3,
			2, 1, 0, -1, -2
		};
	
	//maska uzytkownika
	private int[] userMask = {
			1, 1, 1, 1, 1,
			1, 1, 1, 1, 1,
			1, 1, 1, 1, 1,
			1, 1, 1, 1, 1,
			1, 1, 1, 1, 1
		};
        
	private int[] wylosujMaske() {
		Random rand = new Random();
		final int rozmiarMaski = 5*5;
		
		int[] losowaMaska = new int[rozmiarMaski];
		
		for(int i=0; i<rozmiarMaski; i++) {
			losowaMaska[i] = rand.nextInt(11)-5;
		}
		
		return losowaMaska;
	}
	
	public void setUserMask(int[] dane) {
            
			userMask = Arrays.copyOf(dane, 25);
	}
	
	public int[] pobierzMaske(Typ typ) {
		if(typ == Typ.USREDNIAJACY)
			return this.filtrUsredniajacy;
		if(typ == Typ.HP3)
			return this.filtrHP3;
		if(typ == Typ.PIONOWY_SOBELA)
			return this.pionowyFiltrSobela;
		if(typ == Typ.LOSOWY)
			return this.wylosujMaske();
                if(typ == Typ.UZYTKOWNIKA)
			return this.userMask;
		
		return null;
	}

}
