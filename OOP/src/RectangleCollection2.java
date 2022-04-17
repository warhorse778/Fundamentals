import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.ArrayList;

interface iFileT {
	boolean remove();
	void save();
	void load();
}

public class RectangleCollection implements iFileT {
	private TreeSet box = new TreeSet();; // = new TreeSet();	
    private String inputfile = "defaultIn.txt";
    private String outputfile = "defaultOut.txt";
	
	public RectangleCollection() {
		box = new TreeSet();
	}
	
	public RectangleCollection(String file) {
    	try {
			Scanner iStream = new Scanner(new File(file));
			while(iStream.hasNext()) {
				ColorRectangle rec = new ColorRectangle(iStream.nextInt(),iStream.nextInt(),
					      			  iStream.nextInt(),iStream.nextInt(),iStream.nextLong());
				box.add(rec);
			}
			iStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public RectangleCollection(String inputf, String outputf) {
		inputfile = inputf;
		outputfile = outputf;
		load();
	}
	
	@Override
	public boolean remove(){
    	try {
    		new FileWriter(inputfile, false).close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
    @Override
    public void save() {
    	try {
			RandomAccessFile fout = new RandomAccessFile(outputfile, "rw");
			for(Iterator It = box.iterator(); It.hasNext();) {
				ColorRectangle current = (ColorRectangle)It.next();
				fout.writeBytes(current.getix1()+" ");
				fout.writeBytes(current.getiy1()+" ");
				fout.writeBytes(current.getix2()+" ");
				fout.writeBytes(current.getiy2()+" ");
				fout.writeBytes(current.getColor()+"\r\n");
			}
			fout.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    @Override
    public void load() {
    	try {
			Scanner iStream = new Scanner(new File(inputfile));
			while(iStream.hasNext()) {
				ColorRectangle rec = new ColorRectangle(iStream.nextInt(),iStream.nextInt(),
					      			  iStream.nextInt(),iStream.nextInt(),iStream.nextLong());
				box.add(rec);
			}
			iStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
	
	public void addRectangle(ColorRectangle r1) {
		box.add(r1);
	}
	
	public ColorRectangle calcUnionColl() {
		ColorRectangle result = new ColorRectangle(0,0,0,0,0);
		for(Iterator It = box.iterator(); It.hasNext();){
			ColorRectangle current = (ColorRectangle)It.next();
			result = result.unionRect(current);
		}
		return result;
	}
	
	public ColorRectangle calcMin() {
		boolean first = true;
		ColorRectangle result = new ColorRectangle(0,0,0,0,0);
		
		for(Iterator It = box.iterator(); It.hasNext();){
			ColorRectangle current = (ColorRectangle)It.next();
			if (first) {
				result = current;
				first = false;
			}
			else {
				if (result.calcArea() > current.calcArea()) {
					result = current;
				}
			}
		
		}
		return result;
	}
	
	public ColorRectangle calcMax() {
		boolean first = true;
		ColorRectangle result = new ColorRectangle(0,0,0,0,0);
		
		for(Iterator It = box.iterator(); It.hasNext();){
			ColorRectangle current = (ColorRectangle)It.next();
			if (first) {
				result = current;
				first = false;
			}
			else {
				if (result.calcArea() < current.calcArea()) {
					result = current;
				}
			}
		
		}
		return result;
	}
	
	public int calcNested() {
		int result = 0;
		
		for(Iterator It = box.iterator(); It.hasNext();){
		ColorRectangle current = (ColorRectangle)It.next();
		
			for(Iterator It2 = box.iterator(); It2.hasNext();){
			ColorRectangle second = (ColorRectangle)It2.next();
				if(!current.equals(second)) {
					if (second.isInside(current.getix1(), current.getiy1()) && 
						second.isInside(current.getix2(), current.getiy2())) {
						result++;
						break;
					}
				}
			}
			
		}
		return result;
	}
	
	public int calcSize() {
		return box.size();
	}
	
	public void printColl() {
		System.out.println(box.toString()); 
	}
	
	public double calcSumArea() {
		double result = 0;
		for(Iterator It = box.iterator(); It.hasNext();){
			ColorRectangle current = (ColorRectangle)It.next();
			result += current.calcArea();
		}
		return result;
	}
	
	public double calcSumPerimeter() {
		double result = 0;
		for(Iterator It = box.iterator(); It.hasNext();){
			ColorRectangle current = (ColorRectangle)It.next();
			result += current.calcPerimeter();
		}
		return result;
	}
	
	public ArrayList reverseList() {
		ArrayList<ColorRectangle> lst = new ArrayList<ColorRectangle>(box);
		return lst;
	}
	
	public boolean findRect(ColorRectangle toFind) {
		for(Iterator It = box.iterator(); It.hasNext();){
			ColorRectangle current = (ColorRectangle)It.next();
			if (current.calcPerimeter() == toFind.calcPerimeter()) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		RectangleCollection test = new RectangleCollection("recs.txt","recsOut.txt");
		ColorRectangle r1 = new ColorRectangle(2,2,400,400,255);
		test.addRectangle(r1);
		System.out.println("Union Rectangle\n"+test.calcUnionColl());
		System.out.println("Minimum Rectangle\n"+test.calcMin());
		System.out.println("Maximum Rectangle\n"+test.calcMax());
		System.out.println("Nested Rectangle\n"+test.calcNested());
		System.out.println("Reverse Rectangle\n"+test.reverseList().toString());
		System.out.println("Sum Rectangle Area\n"+test.calcSumArea());
		System.out.println("Sum Rectangle Parameter\n"+test.calcSumPerimeter());
		System.out.println("Size of collection\n"+test.calcSize());
		System.out.println("Find Rectangle\n"+test.findRect(r1));
		test.save();
		test.remove();
	}
}












