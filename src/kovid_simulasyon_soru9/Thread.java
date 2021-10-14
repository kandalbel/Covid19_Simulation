package kovid_simulasyon_soru9;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;


public class Thread implements Runnable{
	Random random = new Random();
	ForkJoinPool fpool;
	ForkJoinPool fpol;
	static boolean devam = true;
	boolean basl = false;
	long baslazamani;
	
	
	public Thread(){
		basl = false;
	}
	@Override
	public void run() {
		fpool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		fpol = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		
		long start = 0;
		while(devam){
			long now = System.currentTimeMillis();
				if(start>100/6){
					start = 0;
					if(basl==true){
						hareket();
						infeksyonlarKayVehesplma();
						MainKovid.d.repaint();
						if(MainKovid.infekte==0 || MainKovid.infekte+MainKovid.iyilesen==MainKovid.pop){
							devam = false;
						}
						MainKovid.zaman =System.currentTimeMillis()-baslazamani;
					}
				
				}
			if(System.currentTimeMillis()-now>1000/100){
				MainKovid.f.setTitle("Kovid-19 Simulasyon");
			}
			start += System.currentTimeMillis()-now;
		}
	}
	 void infeksyonlarKayVehesplma() {
		
		recursiveSayac r = new recursiveSayac(Runtime.getRuntime().availableProcessors(), MainKovid.dz);
		fpol.invoke(r);
		MainKovid.infekte = r.count;
		MainKovid.iyilesen = r.rcount;
		MainKovid.infekteDegil=r.ncount;
		
	}
	void hareket() {
		// TODO Auto-generated method stub
		
		parcaYinelemeli r = new parcaYinelemeli(Runtime.getRuntime().availableProcessors(), random
				,MainKovid.dz);
		fpool.invoke(r);
	}
}

class parcaYinelemeli extends RecursiveAction{
	
	private static final long serialVersionUID = 1L;
	int threads;
	Random random;
	parcaYinelemeli r1;
	parcaYinelemeli r2;
	dizi[] array;
	public parcaYinelemeli(int ap, Random rand, dizi[] dzi) {
		// TODO Auto-generated constructor stub
		this.threads = ap;
		this.random = rand;
		this.array = dzi;
	}

	@Override
	protected void compute() {
		// TODO Auto-generated method stub
		if(threads>1){
			
			dizi[] p = Arrays.copyOfRange(array, 0, array.length/2);
			dizi[] p1 = Arrays.copyOfRange(array, array.length/2, (array.length));
			
			r1 = new parcaYinelemeli(threads>>1, random, p);
			r2 = new parcaYinelemeli(threads>>1, random, p1);
			
			
			invokeAll(r1,r2);
		}else{
			double dist = 0;
			for(dizi p : array){
				p.x +=(int)((random.nextDouble()*10)-5);
				p.y +=(int)((random.nextDouble()*10)-5);
				if(p.x>800){
					p.x=800;
				}else if(p.x<0){
					p.x=0;
				}
				if(p.y>(800)){
					p.y=(800);
				}else if(p.y<0){
					p.y=0;
				}
				if(p.infekte==true){
					if(p.zamanlayici>1000/MainKovid.frek){
						p.zamanlayici=0;
						for(int j=0;j<MainKovid.dz.length;j++){
								if(MainKovid.dz[j].infekte==false && MainKovid.dz[j].iyilesen==false){
									 dist = 
										Math.sqrt(
										 Math.pow(p.x-MainKovid.dz[j].x,2)
										+Math.pow(p.y-MainKovid.dz[j].y,2));
									if(dist<=MainKovid.yaymk){
										MainKovid.dz[j].infekte = true;
										MainKovid.dz[j].infSuresi = System.currentTimeMillis();
									}
								}
						}
					}
					if(System.currentTimeMillis()-p.infSuresi>MainKovid.sure){
						p.infekte = false;
						p.iyilesen = true;
					}
					p.zamanlayici+=1000/60;
				}
			}
		}
	}
	
}

class recursiveSayac extends RecursiveAction{
	
	private static final long serialVersionUID = 1L;
	int threads;
	int start;
	int fin;
	int count;
	int rcount;
	int ncount;
	dizi[] array;
	
	public recursiveSayac(int t, dizi[] array){
		this.threads = t;
		count=0;
		rcount=0;
		ncount=0;
		this.array = array;
	}
	@Override
	protected void compute() {
		
		if(threads>1){
			
			dizi[] p = Arrays.copyOfRange(array, 0, array.length/2);
			dizi[] p1 = Arrays.copyOfRange(array, array.length/2, (array.length));
			recursiveSayac r1 = new recursiveSayac(threads/2, p);
			recursiveSayac r2 = new recursiveSayac(threads/2, p1);
			invokeAll(r1,r2);
			count=r1.count+r2.count;
			rcount=r1.rcount+r2.rcount;
			ncount= MainKovid.pop-(count+rcount);
		}else{
			for(dizi p : array){
				if(p.iyilesen){
					rcount++;
				}else if(p.infekte){
					count++;
				}
			}
			
		}
	}
	
}
