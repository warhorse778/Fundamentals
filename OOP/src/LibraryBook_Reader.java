import java.util.Arrays;
import java.util.GregorianCalendar;


 *I. Да се състави интерфейс ILibraryBook за обслужване на библиотечна книга:
Методи:
Книга се заема от читателя аргумент:	public void get(Reader rdr);
Книга се връща:	public void ret();

interface ILibraryBook {
	void get(Reader rdr);
	void ret();
}
 *II. Да се състави интерфейс IReader за обслужване на читател: Читателя
 заема/освобождава книгата(аргумент)	
interface IReader{
	void get(LibraryBook lb);
	void ret(LibraryBook lb);
}
 * III. Да се състави клас пакетен достъп Applicant скрити членове: 1. Скрити
 * (Защитени) членове име, дата на постъпване в университет, успех: име : String
 * дата на раждане : GregorianCalendar успех : double Конструктор: public
 * Applicant(GregorianCalendar birth, String name, double uspeh) Методи :
 * get/set toString

class Applicant{
	protected String name;
	protected GregorianCalendar date_app;
	protected double uspeh;
	protected Applicant() {
		date_app=new GregorianCalendar();
	}
	protected Applicant(GregorianCalendar date_var, String name_var, double uspeh_var){
		name=name_var;
		date_app=date_var;
		uspeh=uspeh_var;
	}
	public String getName() {
		return name;
	}
        public  getGregorianCalendar() {
		return date_app;
	}
        public  getUspeh() {
		return uspeh;
	}
	public void setName(String name_var) {
	        this.name_var=name_var;
	}
        public void setGregorianCalendar() {
	        this.date_var=date_var;
	}
        public void setuspeh(double uspeh_var) {
	        this.uspeh_var=uspeh_var;
	}
	public String toString() {
		return ("Student name: "+name+" , Uspeh: "+uspeh+" Date : "+date_app.get(GregorianCalendar.DATE)+"/"+date_app.get(GregorianCalendar.MONTH)+"/"+date_app.get(GregorianCalendar.YEAR)+"\n");
	}
}

 * IV. Да се състави клас пакетен достъп Student, наследник на Applicant
 * имплементиращ Comparable скрити членове: фак. номер : String Конструктори:
 * public Student(String name, String f_nom) public Student(GregorianCalendar
 * date, String name, double uspeh, String f_nom) { Методи : get/set toString
 * equals
 
class Student extends Applicant implements Comparable{
	protected String str_fnum;
	public Student(String name, String f_nom) {
		super(new GregorianCalendar(), name, 0.0);
		this.str_fnum = f_nom;
	}
	public Student(GregorianCalendar date, String name, double uspeh, String f_nom) {
		super(date,name,uspeh);
		str_fnum = f_nom;
	}
	public int compareTo(Object o) {
		return str_fnum.compareTo(((Student)o).str_fnum);
	}
	
	public boolean equals(Student o) {
		return (str_fnum.compareTo(((Student)o).str_fnum)==0);
	}
	public String toString() {
		return ("Student FN : "+str_fnum+" "+super.toString());
	}

}

 * V. Да се състави клас пакетен достъп Reader, наследник на Student,
 * имплементиращ интерфейс IReader,Comparable: 
 * Частно поле LibraryBook
 * 1. Експлицитни конструктори:
 * public Reader(String nm,String fn) public Reader(GregorianCalendar date,
 * String name, double uspeh, String f_nom) 2.Методи четене/запис еквивалентност
 * и стрингова интерпретация: get/set,toString()
 
class Reader extends Student implements IReader,Comparable{
	private LibraryBook readerBook;
	public Reader(GregorianCalendar date, String name, double uspeh, String f_nom){
		super(date, new String(name),uspeh, new String(f_nom));
	}
	public Reader(String nm,String fn){
		super(new String(nm),new String(fn));
	}
	@Override
	public void get(LibraryBook lb) {
		readerBook=lb;
		
	}
	@Override
	public void ret(LibraryBook lb) {
		lb.ret();
		readerBook=null;	
	}
	public int compareTo(Object ob) {
		Reader rd=(Reader)ob;
		return str_fnum.compareTo(rd.str_fnum);
	}
	public boolean equals(Object ob) {
		if(ob.getClass().getName().compareTo(this.getClass().getName())!=0){
			return false;
		}else{
			Reader rd=(Reader)ob;
			if(this.str_fnum.compareTo(rd.str_fnum)==0){
				return true;
			}else{
				return false;
			}
		}
	}
	public String toString(){
		return "Reader:"+super.toString()+" Book "+ readerBook!=null?readerBook.toString():" no book" ;
	}

}

 * VI. Да се състави клас пакетен достъп LibraryBook, имплиментиращ
 * ILibraryBook,Comparable:  class LibraryBook implements
 * ILibraryBook,Comparable 1.Частни членове: Читател : Reader Име на книга:
 * String Статус: boolean
 * 2.Методи еквивалентност и стрингова интерпретация:

class LibraryBook implements ILibraryBook,Comparable{
	private Reader bookReader; 
	private String str_bookName;
	private boolean b_status;
	public LibraryBook(String bn, boolean st){
		bookReader = new Reader("N/A","N/A");
		str_bookName=new String(bn);
		b_status=st;
	}

	@Override
	public void get(Reader rdr) {
		bookReader.name=rdr.name;
		bookReader.str_fnum=rdr.str_fnum;
		b_status=true;
	}
	@Override
	public void ret() {
		bookReader.name="N/A";
		bookReader.str_fnum="N/A";
		b_status=false;
	}
	public String toString(){
		return "Book:"+str_bookName+"; Status: "+b_status+";";
	}
	public int compareTo(Object obj){
		LibraryBook lb=(LibraryBook)obj;
		return this.toString().compareTo(lb.toString());
	}
	public boolean equals(Object obj){
		if(obj.getClass().getName().compareTo(this.getClass().getName())!=0){
			return false;
		}else{
			LibraryBook lb=(LibraryBook)obj;
			if(this.toString().compareTo(lb.toString())==0){
				return true;
			}else{
				return false;
			}
		}
	}
}

 * VII.Да се състави клас публичен достъп Main с главна функция: 
 * 4.1. Създава  масив от обекти от VI с данни за книги 
 * 4.2. Симулира заемане1 от библиотека, Извежда; 
 * 4.3. Симулира заемане2 от библиотека, Извежда; 
 * 4.4. Сортира, извежда
 * 4.5. Проверява за еквивалентност, извежда

public class Main {
	public static void main(String[] args) {
		LibraryBook[] lib = new LibraryBook[2];
		lib[0]=new LibraryBook("book1",false);
		lib[1]=new LibraryBook("book0",false);
		lib[1].get((Reader) new Reader("John","123456"));
		lib[0].get((Reader) new Reader("Merry","654321"));

		System.out.println(" ALL "+Arrays.toString(lib));
		Arrays.sort(lib);
		System.out.println(" ALL "+Arrays.toString(lib));
		lib[0].ret();
		System.out.println(lib[0]);
		if(lib[0].compareTo(lib[1])==0){
			System.out.println(lib[0]+" EQUALS "+lib[1]);
		}else{
			System.out.println(lib[0]+" NOT EQUAL TO "+lib[1]);
		}
	}

}
