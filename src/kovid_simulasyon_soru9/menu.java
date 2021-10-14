package kovid_simulasyon_soru9;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class menu extends JPanel{
	
	private static final long serialVersionUID = 1L;
	JLabel populasyon = new JLabel();
	JTextField pop = new JTextField();
	
	JLabel iyOr = new JLabel();
	JTextField io = new JTextField();
	
	JLabel ds = new JLabel();
	JTextField dsek = new JTextField();
	
	JLabel df = new JLabel();
	JSlider dfe = new JSlider();
	
	JLabel dr = new JLabel();
	JTextField dre = new JTextField();
	
	JLabel dd = new JLabel();
	JTextField d = new JTextField();
	
	JPanel op = new JPanel();
	JPanel op1 = new JPanel();
	
	
	JButton button = new JButton("SIMULASYON  BASLAT");

	
	
	
	public menu(){
		this.add(button);
		populasyon.setFont(new Font("Ariel", Font.PLAIN, 12));
		populasyon.setOpaque(true);	
		populasyon.setText("Populasyon");
		populasyon.setBounds(50, 20, 200, 30);
		pop.setOpaque(true);
		pop.setBounds(150, 20, 50, 30);
		
		ds.setFont(new Font("Ariel", Font.PLAIN, 12));
		ds.setOpaque(true);	
		ds.setText("Hasta Sayisi");
		ds.setBounds(250, 20, 200, 30);
		dsek.setOpaque(true);
		dsek.setBounds(350, 20, 50, 30);
		
		iyOr.setFont(new Font("Ariel", Font.PLAIN, 12));
		iyOr.setOpaque(true);	
		iyOr.setText("Iyilesme Orani");
		iyOr.setBounds(450, 20, 200, 30);
		io.setOpaque(true);
		io.setBounds(550, 20, 150, 30);
		
		df.setFont(new Font("Ariel", Font.PLAIN, 12));
		df.setOpaque(true);	
		df.setBounds(50, 60, 200, 50);
		df.setText("Simulasyon hizi");
		dfe.setMinimum(0);
		dfe.setForeground(new Color(0,0,0));
		dfe.setMajorTickSpacing(10);
		dfe.setMaximum(100);
		dfe.setOpaque(true);
		dfe.setBounds(250, 60, 500, 50);
		
		
		dr.setFont(new Font("Ariel", Font.PLAIN, 12));
		dr.setOpaque(true);	
		dr.setText("Bulasma Katsayisi");
		dr.setBounds(50, 150, 100, 30);
		dre.setOpaque(true);
		dre.setBounds(200, 150, 50, 30);
		
		dd.setFont(new Font("Ariel", Font.PLAIN, 12));
		dd.setOpaque(true);	
		dd.setBounds(300, 150, 100, 30);
		dd.setText("Iyilesme Suresi");
		d.setForeground(new Color(0,0,0));
		d.setOpaque(true);
		d.setBounds(400, 150, 100, 30);
		
		op.setBounds(755, 5, 10, 5);
		op.setSize(645,790);
		op.setOpaque(true);
		op.setBackground(Color.BLACK);
		op.setBorder(getBorder());
		
		op1.setBounds(5, 200, 0, 5);
		op1.setSize(745, 650);
		op1.setBackground(Color.BLACK);
		op1.setOpaque(true);
		
		
		button.setFont(new Font("Ariel", Font.BOLD, 14));
		button.setBounds(550,130, 200, 50);
		button.addActionListener(new ActionListener(){
			

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int kant = Integer.parseInt(pop.getText());
				int frekan = dfe.getValue();
				int rg = Integer.parseInt(dre.getText());
				int s = Integer.parseInt(d.getText())*1000;
				int hs = Integer.parseInt(dsek.getText());
				
				if(frekan==0){
					frekan=1;
				}
				MainKovid.baslatma(kant,frekan,rg, hs,s);
			}

		});	
		
	}
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			this.add(pop);
			this.add(populasyon);
			this.add(dsek);
			this.add(ds);
			this.add(df);
			this.add(dfe);
			this.add(dr);
			this.add(dre);
			this.add(d);
			this.add(dd);	
			this.add(iyOr);
			this.add(io);
			this.add(op);
			this.add(op1);
	}
}
