package projektgrafika.GrafikaProjekt;

public class Rozdzka {
	private boolean czyAktywna;
	private int moc;
	private int x, y;
	
	public Rozdzka() {
		this.moc = 100;
		this.czyAktywna = false;
	}
	
	public void zwiekszMoc() {
		if(this.moc < 255)
			this.moc += 5;
	}
	
	public void zmnieszMoc() {
		if(this.moc > 0)
			this.moc -= 5;
	}
	
	public int getMoc() {
		return moc;
	}
	public void setMoc(int moc) {
		this.moc = moc;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	public boolean isCzyAktywna() {
		return czyAktywna;
	}

	public void setCzyAktywna(boolean czyAktywna) {
		this.czyAktywna = czyAktywna;
	}
	
}
