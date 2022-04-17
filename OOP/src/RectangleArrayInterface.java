import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Scanner;


interface IFile{
	void save();
	void load();
}
public class RectangleArrayInterface implements IFile {
	private ColorRectangle[] aColl=new ColorRectangle[10];
	private String inputFile = "defaultIn.txt";
	private String outputFile = "defaultOut.txt";
	public RectangleArrayInterface(String fileNameIn, String fileNameOut ) {
		inputFile = fileNameIn;
		outputFile = fileNameOut;
		load();
//		try {
//			RandomAccessFile myFile = new RandomAccessFile(fileName,"r");
//			String strLine;
//			String[] res;
//			int ind=0;
//			while( (strLine=myFile.readLine()) != null){
//				res=strLine.split(" ");	
////				Integer oIntX1 = Integer.valueOf(res[0]);	// "1" => 1
////				Long oColor = Long.valueOf(res[4]);
//				ColorRectangle oRectangle = new ColorRectangle(
//						Integer.valueOf(res[0]).intValue(),
//						Integer.valueOf(res[1]).intValue(),
//						Integer.valueOf(res[2]).intValue(),
//						Integer.valueOf(res[3]).intValue(),
//						Long.valueOf(res[4]).longValue()
//						);
//				aColl[ind++] = oRectangle;				
//			}
//			myFile.close();
//		}catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	public void printColl() {
		System.out.println(Arrays.toString(aColl));
	}

// public int inside(int rec_to_compare) {
//    	int result = 0;
//   	int index = 0;
//		for(ColorRectangle current : aColl) { 
//			if (!current.equals(aColl[rec_to_compare])) {
//				if (current.getix1() >= aColl[index].getix1() && 
//					current.getix1() <= aColl[index].getix2() && 
//					current.getix2() <= aColl[index].getix2() && 
//					current.getix2() >= aColl[index].getix1()) {
//					result++;
//					index++;
//				}
//			}
//			else {
//				index++;
//			}
//		}
//		return result;
//   }

	public static void main(String[] args) {
//		III. Тестова главна функция:
//			III.1. Създава обект RectangleArrayInterface от файлове (например: ColorRect.txt и ColorRectOut.txt). Извежда.
		RectangleArrayInterface obj = new RectangleArrayInterface("ColorRect.txt", "ColorRectOut.txt");
	    obj.printColl();
//			III.2. Променя елемент на последен индекс с 2, 2, 400, 400, 255 .Записва чрез интерфейсния метод
	    obj.changeRectangle(9, new ColorRectangle(2, 2, 400, 400, 255));
	    obj.save();	//Записва чрез интерфейсния метод
//			III.3.Изчисляване на сумарна площ използва -double calcSumArea()
	    System.out.println("Sum Area : "+obj.calcSumArea());
//			III.4. Изчисляване на сумарен периметър използва double calcSumPerimeter()
	    System.out.println("Sum Perimeter : "+obj.calcSumPerimeter());


	}
	//II.3. Имплементация на интерфейсните методи
	@Override
	public void save() {
		try {
			RandomAccessFile fout = new RandomAccessFile(outputFile,"rw");
			for(ColorRectangle current : aColl) {
				//Формат:<x1>SP<y1>SP<x2>SP<y2><SP><color>CR
				fout.writeBytes(current.getiX1()+" ");
				fout.writeBytes(current.getiY1()+" ");
				fout.writeBytes(current.getiX2()+" ");
				fout.writeBytes(current.getiY2()+" ");
				fout.writeBytes(current.getColor()+"\r\n");
			}
			fout.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void load() {
		try {
			Scanner iStream = new Scanner(new File(inputFile));
			int index = 0;
			while(iStream.hasNext()) {
				//Формат:<x1>SP<y1>SP<x2>SP<y2><SP><color>CR
				int x1=iStream.nextInt();
                                int y1 = iStream.nextInt();
				int x2 = iStream.nextInt();
				int y2 = iStream.nextInt();
				long clr = iStream.nextLong();
				aColl[index++]=new ColorRectangle(iStream.nextInt(),iStream.nextInt(), iStream.nextInt(),iStream.nextInt(),iStream.nextLong());
			}
			iStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	//II.4. Изтриване на данните;
	public void clearColl() {
		aColl = null;
	}
	//II.5. Промяна на данните в масива по указан индекс;
	public void changeRectangle(int index, ColorRectangle oRectangle) {
		aColl[index]=oRectangle;
	}
//	III.3.Изчисляване на сумарна площ използва -double calcSumArea()
	public double calcSumArea() {
		double result = 0.0;
		for(ColorRectangle current : aColl) {
			result += current.calcArea();
		}
		
		return result;
	}
////			III.4. Изчисляване на сумарен периметър използва double calcSumPerimeter()
	public double calcSumPerimeter() {
		double result = 0.0;
		for(ColorRectangle current : aColl) {
			result += current.calcPerimeter();
		}		
		return result;
	}
}
