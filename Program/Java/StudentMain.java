import java.io.*;
import java.util.*;

class Student {
	int rollNo;
	String name;
	long mobileNo;
	String address;
	int m1;
	int m2;
	int m3;
	int m4;
	int m5;
	int total;
	Student(int rollNo,String name,long mobileNo,String address,int m1,int m2,int m3,int m4,int m5,int total)
	{
		this.rollNo = rollNo;
		this.name = name;
		this.mobileNo = mobileNo;
		this.address =address;
		this.m1 = m1;
		this.m2 =m2;
		this.m3 =m3;
		this.m4=m4;
		this.m5=m5;
		this.total = total;
	}
}
class StudentMain 
{	
	public static int n;
	public static int[] arr = new int[20];
   	static List<Student> s =new ArrayList<Student>();
	public static void print_details()
	{
		int i=0,j=0,temp,k=0;
		try
		{
			for(i=0;i<n;i++)
			{
				Student data=s.get(i);
				arr[i]=data.total;
			}
			for (i=0;i<n;++i)
			{
				for (j=i+1;j<n;++j)
				{
					if (arr[i] < arr[j])
					{
						temp =  arr[i];
						arr[i] = arr[j];
						arr[j] = temp;
					}
				}
			}	
			for(j=0;j<n;j++)
			{
				for(i=0;i<n;i++)
				{	
					Student data=s.get(i);
					if (arr[j]==data.total)
					{
						System.out.println(data.rollNo + "\t" + data.name+ "\t" +data.mobileNo+ "\t" +data.address+ "\t" +data.m1+ "\t" +data.m2+ "\t" +data.m3+ "\t" +data.m4+ "\t" +data.m5+ "\t" +data.total+"\t"+(j+1));
						break;
					}
				}
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	public static void input_details()
	{	
		int rollNo,m1,m2,m3,m4,m5,total;
		String name,address;
		Long mobileNo;
		InputStreamReader input = new InputStreamReader(System.in);  
		BufferedReader reader = new BufferedReader(input);
		try
		{ 
			System.out.print("Enter Roll No : ");
			rollNo=Integer.parseInt(reader.readLine());
			System.out.print("Enter Name :");
			name=reader.readLine();
			System.out.print("Mobile No:");
			mobileNo=Long.parseLong(reader.readLine());
			System.out.print("Address:");
			address=reader.readLine();;
			System.out.print("Mark 1:");
			m1=Integer.parseInt(reader.readLine());
			System.out.print("Mark 2:");
			m2=Integer.parseInt(reader.readLine());
			System.out.print("Mark 3:");
			m3=Integer.parseInt(reader.readLine());
			System.out.print("Mark 4:");
			m4=Integer.parseInt(reader.readLine());
			System.out.print("Mark 5:");
			m5=Integer.parseInt(reader.readLine());
			total =m1+m2+m3+m4+m5;
			s.add(new Student(rollNo,name,mobileNo,address,m1,m2,m3,m4,m5,total));
			n+=1;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}



	public static void admin_login()
	{	
		int rollNo,m1,m2,m3,m4,m5,total;
		String name,address;
		Long mobileNo;
		String username,password;
		int i,nos,typeAdminLogin;
		System.out.println("Admin Login \n");
		System.out.print("\n1.Add Students\n2.Display All student mark details\n");
		InputStreamReader input = new InputStreamReader(System.in);  
		BufferedReader reader = new BufferedReader(input); 
		try
		{
			typeAdminLogin=Integer.parseInt(reader.readLine());
				if(typeAdminLogin==1)
				{
					
					System.out.print("Enter the number of students :");
					nos=Integer.parseInt(reader.readLine());
					for(i=0;i<nos;i++)
					{
						System.out.print("\nEnter Student "+(i+1)+" Details :\n\n");
						input_details();
					}
					//data_to_file();
				}

				else if (typeAdminLogin == 2)
				{
					System.out.print("RollNo\tName\tMobileNo Address Mark1\tMark2\tMark3\tMark4\tMark5\tTotal\tRank\n");
					print_details();
				}
		}
		catch(Exception e){
			e.printStackTrace();
		}	
	}
	public static void type_login(){
		char c;
		int i,findrollNo,j,temp;
		int[] array=new int[20];
		long findmobileNo;
		InputStreamReader input = new InputStreamReader(System.in);  
		BufferedReader reader = new BufferedReader(input);
		//print_details(1);              //1 to break the loop before printing
		try 
		{	
			System.out.print("Student Login \n\n");
			System.out.print("Rollno  :");
			findrollNo=Integer.parseInt(reader.readLine());
			System.out.print("Mobileno  : ");
			findmobileNo=Long.parseLong(reader.readLine());
			for(i=0;i<n;i++)
			{
				Student data=s.get(i);
				array[i]=data.total;
			}
			for (i=0;i<n;++i)
			{
				for (j=i+1;j<n;++j)
				{
					if (array[i] < array[j])
					{
						temp =  array[i];
						array[i] = array[j];
						array[j] = temp;
					}
				}
			}	
			
			for(i=0;i<=n;i++)
			{
				Student data=s.get(i);	
				if (findrollNo==data.rollNo)
				{
					if (findmobileNo==data.mobileNo)
					{
						System.out.print("\nRoll No :"+data.rollNo+"\nName    :"+data.name+"\nPhone No:"+data.mobileNo+"\nAddress :"+data.address+"\nMark 1  :"+data.m1+"\nMark 2  :"+data.m2+"\nMark 3  :"+data.m3+"\nMark 4  :"+data.m4+"\nMark 5  :"+data.m5+"\nTotal   :"+data.total+"\n");
						for(j=0;j<n;j++)
						{
							if (array[j]==data.total)
								System.out.print("Rank   :"+(j+1)+"\n");
						}
						break;
					}
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args) //throws IOException 
	{
		int typeLogin,decision=1;
		InputStreamReader input = new InputStreamReader(System.in);  
		BufferedReader reader = new BufferedReader(input); 	
	  	try{
			System.out.println("Main  hi");
			while(decision==1)
			{
				System.out.println("1.Admin Login \n2.Student Login");
				typeLogin=Integer.parseInt(reader.readLine());
				if (typeLogin == 1)
					admin_login();
				else if(typeLogin == 2 )
					type_login();
					//System.out.println("Student");
				System.out.println("\nDo you want to continue (1) or exit(0) :");
				decision=Integer.parseInt(reader.readLine());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}