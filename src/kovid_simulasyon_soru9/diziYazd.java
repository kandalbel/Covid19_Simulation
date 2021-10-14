package kovid_simulasyon_soru9;

import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class diziYazd extends panel{
	
	private static final long serialVersionUID = 1L;

	static ForkJoinPool f = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
	
	static recursiveCiz r;

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		r = new recursiveCiz(Runtime.getRuntime().availableProcessors(),MainKovid.dz, g,
				MainKovid.d.mavi, MainKovid.d.kirmizi,MainKovid.d.yesil);
		f.invoke(r);
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.BLUE);
		g2d.setStroke(new BasicStroke(80));
		g2d.drawLine(900, 795, 900, 800-MainKovid.infekteDegil);
		
		
		g2d.setColor(Color.RED);
		g2d.setStroke(new BasicStroke(80));
		g2d.drawLine(1050, 795, 1050, 800-MainKovid.infekte);
		
		g2d.setColor(Color.GREEN);
		g2d.setStroke(new BasicStroke(80));
		g2d.drawLine(1200, 795, 1200, 800-MainKovid.iyilesen);
	

		
		hastaSayac.setText("Infekte Hastaları : "+MainKovid.infekte);
		hastaSayac.setBackground(Color.BLACK);
		hastaSayac.setBounds(850, 50, (int) (Math.log10(MainKovid.infekte+1)+1)*30 + 300, 30);
		this.add(hastaSayac);
		
		sagalikliSayac.setText("Infekte olmayanlar: "+MainKovid.infekteDegil);
		sagalikliSayac.setBackground(Color.BLACK);
		sagalikliSayac.setBounds(850, 100, (int) (Math.log10(MainKovid.infekteDegil+1)+1)*30 + 300, 30);
		this.add(sagalikliSayac);
		
		
		iyilesenSayac.setText("Iyileşen Hastaları: "+MainKovid.iyilesen);
		iyilesenSayac.setBackground(Color.BLACK);
		iyilesenSayac.setBounds(850, 150, (int) (Math.log10(MainKovid.iyilesen+1)+1)*30 + 300, 30);
		this.add(iyilesenSayac);

		
	}
	
}

class recursiveCiz extends RecursiveAction{
	
	private static final long serialVersionUID = 1L;
	int threads;
	int bsla;
	int fin;
	static Graphics g;
	dizi[] diz;
	static BufferedImage mavi;
	static BufferedImage kirmizi;
	static BufferedImage yesil;
	static boolean lock = true;
	public recursiveCiz(int t, dizi[] p){
		this.threads = t;
		this.diz = p;
	}
	public recursiveCiz(int t, dizi[] p, Graphics g, BufferedImage mv,BufferedImage krmz,BufferedImage ysl){
		this.diz = p;
		this.threads = t;
		recursiveCiz.g = g;
		recursiveCiz.mavi = mv;
		recursiveCiz.kirmizi = krmz;
		recursiveCiz.yesil = ysl;
	}
	@Override
	protected void compute() {
		// TODO Auto-generated method stub
		if(threads>1){
			dizi[] p = Arrays.copyOfRange(diz, 0, diz.length/2);
			dizi[] p1 = Arrays.copyOfRange(diz, diz.length/2, (diz.length));
			recursiveCiz r1 = new recursiveCiz(threads/2, p);
			recursiveCiz r2 = new recursiveCiz(threads/2, p1);
			invokeAll(r1,r2);
		}else{
			for(dizi p : diz){
			
					if(p.infekte==true){
						g.drawImage(kirmizi, p.x-5, p.y-5, 10, 10,null);
					}else if(p.iyilesen==true){
						g.drawImage(yesil, p.x-5, p.y-5, 10, 10,null);
					}else{
						g.drawImage(mavi, p.x-5, p.y-5, 10, 10,null);
					}
			}
		}
	}
	
}