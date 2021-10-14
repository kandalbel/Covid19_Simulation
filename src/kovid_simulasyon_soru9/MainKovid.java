package kovid_simulasyon_soru9;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainKovid {
	static dizi[] dz;
	static menu m;
	static int infekte;
	static int infekteDegil;
	static int iyilesen;
	static long zaman;
	static frame f;
	static int sure;
	static Thread t;
	static int pop;
	static int hsay;
	static int yaymk;
	static int frek;
	static diziYazd d;

	
	public static void main(String[] args) {
		
		
		
		f = new frame();
		f.setBounds(0, 0,1400, 800);
		f.setVisible(true);
		f.setTitle("Kovid-19 Simulasyon");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		zaman = 0;
		m = new menu();
		m.setLayout(null);
		f.add(m);
		m.revalidate();
		t = new Thread();
		t.run();

		
	}
	public static void baslatma(int i, int j, int k,int h, int sr){	
		
		f.setBounds(0, 0,1400, 800);
		hstaOlusturma(i,h);
		d = new diziYazd();
		d.setLayout(null);
        f.remove(m);
		f.add(d);
		d.revalidate();
		d.setBackground(Color.BLACK);
		f.createBufferStrategy(3);
		t.baslazamani = System.currentTimeMillis();
		t.basl=true;
		frek = j;
		yaymk = k;
		pop = i;
		hsay =h;
		sure = sr;
	
	}
	
	static void hstaOlusturma(int byklk, int l){
		dz = new dizi[byklk];
		Random random = new Random();
		for(int i=0;i<byklk;i++){
			
			dz[i] = new dizi((int)(random.nextDouble()*800), 
					(int)(random.nextDouble()*1200),(long) 0,false);
			if(i==0){
				dz[i].infekte=true;
				dz[i].infSuresi = System.currentTimeMillis();
				infekte = l;
			}
		}
	}
}

class frame extends JFrame{

	private static final long serialVersionUID = 1L;
	
}

class panel1 extends JPanel{
	
	private static final long serialVersionUID = 1L;
}

class panel extends JPanel{

	private static final long serialVersionUID = 1L;
	BufferedImage mavi;
	BufferedImage kirmizi;
	BufferedImage yesil;
	JLabel hastaSayac = new JLabel();
	JLabel iyilesenSayac = new JLabel();
	JLabel sagalikliSayac = new JLabel();

	
	public panel(){
		try {
			mavi = ImageIO.read(new File("mavi.png"));
			kirmizi = ImageIO.read(new File("kirmizi.png"));
			yesil = ImageIO.read(new File("yesil.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		
		}
		
		hastaSayac.setFont(new Font("Ariel", Font.PLAIN, 30));
		hastaSayac.setForeground(Color.RED);
		hastaSayac.setOpaque(true);
		
		sagalikliSayac.setFont(new Font("Ariel", Font.PLAIN, 30));
		sagalikliSayac.setForeground(Color.BLUE);
		sagalikliSayac.setOpaque(true);
		
		iyilesenSayac.setFont(new Font("Ariel", Font.PLAIN, 30));
		iyilesenSayac.setForeground(Color.GREEN);
		iyilesenSayac.setOpaque(true);
	
	}
	
}

class dizi{
	int x;
	int y;
	long zamanlayici;
	long infSuresi;
	boolean infekte;
	boolean iyilesen;
	public dizi(int x, int y, long zmnlyc, boolean inf){
		this.x = x;
		this.y = y;
		this.zamanlayici = zmnlyc;
		this.infekte = inf;
	}
}

