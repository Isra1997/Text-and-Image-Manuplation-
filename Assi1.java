import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.PixelGrabber;
import java.awt.image.RGBImageFilter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.xml.stream.events.Characters;

import org.w3c.dom.css.RGBColor;


public class Assi1 {	
	
	public static BufferedImage img = null;
	
	
	//Text manipulation (part a)
	public  BufferedReader LoadFile(String filename){
		BufferedReader br=null;
		try {
			FileReader fr = new FileReader(filename);
			 br = new BufferedReader(fr);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return br;
	}
	
	
	//Text manipulation (part b)
	public String UniformText(BufferedReader br){
		StringBuilder ssb=new StringBuilder();
		try {
			String sCurrentLine;
			while((sCurrentLine =br.readLine()) != null){
				char [] currentString=sCurrentLine.toCharArray();
				for(int i=0;i<currentString.length;i++){
					   if(currentString[i]>='A' && currentString[i]<='Z'){
						   int ascii=(int)currentString[i]+32;
						   currentString[i]=(char)ascii;
					   }
			}
				ssb.append(currentString);
		}
			} catch (IOException e) {
			e.printStackTrace();
		}
	   
		return ssb.toString();
	}
	
	//Text manipulation (part c)
	public int WordCounter(String countThisString){
		int counter=1;
		char [] countingStrings=countThisString.toCharArray();
			for (int i = 0; i < countingStrings.length; i++) {
				int checkedchar=countingStrings[i];
				if(checkedchar== ' ' && countingStrings[0]!=' ' ){
					counter++;
				}
			}

		if(counter==1){
			return 0;
		}
		else{
			return counter;
		}
	}
	
	//Text manipulation (part d)
	public int [] LetterCount(String countMe){
		int [] countOfLetters=new int[26];
		char [] stringtocount=countMe.toCharArray();
		for (int i = 0; i < stringtocount.length; i++) {
			int ascii=stringtocount[i];
			int index=ascii-97;
			if(ascii>=97 && ascii<=122){
				if(index>=0){
					countOfLetters[index]++;
				}
			}
		}
		return countOfLetters;
		
		
	}
	
	public static void PlotHistogramLetters(int [] Xnumbers ,BufferedWriter bw){
		for(int i=0;i<Xnumbers.length;i++){
			int x=i+97;
			char z=(char)x;
			try {
				bw.write(z+" : ");
				System.out.print(z+" : ");
				for(int j=0;j<Xnumbers[i];j++){
					bw.write('*');
				System.out.print('*');

				}
				bw.write("\n");
				System.out.println("");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			}
	}
	
	
	//Text manipulation (part e)
	public int [] CountOfWords(String countme){
		int [] countOfWords=new int[6];
		Scanner sc=new Scanner(countme);
		while(sc.hasNext()){
			String TheWord=sc.next();
			switch (TheWord) {
			case "the":
				countOfWords[0]++;
				break;
			case "a":
				countOfWords[1]++;
				break;
			case "and":
				countOfWords[2]++;
				break;
			case "is":
				countOfWords[3]++;
				break;
			case "are":
				countOfWords[4]++;
				break;
			case "this":
				countOfWords[5]++;
				break;
			default:
				break;
			}				
		}
		return countOfWords;
	}
	
	public static void PlotHistogramWordCount(int [] Xnumbers ,BufferedWriter bw){
		try {
			bw.newLine();
			bw.newLine();
			System.out.println("");;
			System.out.println("");;
			for(int i=0;i<Xnumbers.length;i++){
				switch (i) {
				case 0:
					bw.write("the : ");
					System.out.print("the : ");
					break;
				case 1:
					bw.write("a : ");
					System.out.print("a : ");
					break;
				case 2:
					bw.write("and : ");
					System.out.print("and : ");
					break;
				case 3:
					bw.write("is : ");
					System.out.print("is : ");
					break;
				case 4:
					bw.write("are : ");
					System.out.print("are : ");
					break;
				case 5:
					bw.write("this : ");
					System.out.print("this : ");
					break;

				default:
					break;
				}
				
				for(int j=0;j<Xnumbers[i];j++){
					bw.write('*');
					System.out.print('*');
				}
				bw.newLine();
				System.out.println("");;
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
	}
	
	//Text manipulation (part f)
	public void writeOutput(String outputPath, String unifromCharactersString,  int wordCount){
		FileWriter fw=null;
		BufferedWriter bw=null;
		try {
			File outputfile=new File(outputPath);
		    fw=new FileWriter(outputfile);
		    bw= new BufferedWriter(fw);
			
			bw.write(unifromCharactersString);
			
			bw.write("\n");
			bw.write("\n");
			bw.write("Total number of words : " +WordCounter(unifromCharactersString));
			bw.write("\n");
			bw.write("\n");
			
			PlotHistogramLetters(LetterCount(unifromCharactersString), bw);
			bw.write("\n");
			bw.write("\n");
			
			PlotHistogramWordCount(CountOfWords(unifromCharactersString), bw);
			bw.write("\n");
			bw.write("\n");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		

		try {
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	

	public BufferedImage convertGreyscale(String path){
		BufferedImage img=null;
		try {
//			Image Manipulation(part a)
		    img=ImageIO.read(new File(path));
//			Image Manipulation(part b)
			int width=img.getWidth();
			int height=img.getHeight();
		    for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					int p=img.getRGB(j,i);
					int alpha = (p >> 24) & 0xFF;
					int red = (p >> 16) & 0xFF;
					int green = (p >> 8) & 0xFF;
					int blue = (p ) & 0xFF;
					
					//calculate average
					int average=(red+green+blue)/3;
					
					//set the color components to the average value
					p=(alpha<<24) | (average<<16) | (average<<8) | average;
					img.setRGB(j, i, p);
				}
			}
		   

		} catch (IOException e) {
		}
		
		 return img;

	}
	
	

	
	
	public static void main(String[] args) {
		Assi1 ass=new Assi1();
		String Uniformed=ass.UniformText(ass.LoadFile("/Users/israragheb/Desktop/Assignment 1/input_assign1.txt"));
		ass.writeOutput("/Users/israragheb/Desktop/Assignment 1/Output.txt", Uniformed, ass.WordCounter(Uniformed));
		
		JFrame frame=new JFrame();
		frame.setTitle("Gray Scale image");
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BufferedImage greyImg=ass.convertGreyscale("/Users/israragheb/Desktop/Assignment 1/Image_assign1.jpg");
		ImageIcon icon=new ImageIcon(greyImg);
		JLabel label=new JLabel(icon);
		JPanel jPanel = new JPanel();
        jPanel.add(label);
        frame.add(jPanel);
        frame.setVisible(true);
		
//		LoadImage("/Users/israragheb/Desktop/Assignment 1/Image_assign1.jpg");


		
	}
	
	
}
