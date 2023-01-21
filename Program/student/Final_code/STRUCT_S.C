#include<stdio.h>
#include<conio.h>
#include<string.h>
int n=0,arr[20];     				//n= no_of_records
struct student{
	int rollNo;
	char name[20];
	long int mobileNo;
	char address[40];
	int m1;
	int m2;
	int m3;
	int m4;
	int m5;
	int total;
}s[20];
int print_details(int flag)                  // FLAG for printing RANK
{
	int i=0,j=0,temp;
	for(i=0;i<n;i++){
	      arr[i]=s[i].total;
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
	if (flag==1)
	return arr;
	for(j=0;j<n;j++)
	{
	    for(i=0;i<n;i++)
	    {
		if (arr[j]==s[i].total)
		{
			printf("%d\t%s\t%ld\t%s\t%d\t%d\t%d\t%d\t%d\t%d\t%d\n",s[i].rollNo,s[i].name,s[i].mobileNo,s[i].address,s[i].m1,s[i].m2,s[i].m3,s[i].m4,s[i].m5,s[i].total,j+1);
			break;
		}
	    }
	}
	return 0;
}
void input_details()
{       printf("N  :%d\n",n);
	printf("Enter Roll No : ");
	scanf("%d",&s[n].rollNo);
	printf("Enter Name :");
	scanf("	%[^\n]%*c",&s[n].name);
	printf("Mobile No:");
	scanf("%ld",&s[n].mobileNo);
	printf("Address:");
	scanf("	%[^\n]%*c",&s[n].address);
	printf("Mark 1:");
	scanf("%d",&s[n].m1);
	printf("Mark 2:");
	scanf("%d",&s[n].m2);
	printf("Mark 3:");
	scanf("%d",&s[n].m3);
	printf("Mark 4:");
	scanf("%d",&s[n].m4);
	printf("Mark 5:");
	scanf("%d",&s[n].m5);
	s[n].total =s[n].m1+s[n].m2+s[n].m3+s[n].m4+s[n].m5;
	n+=1;
}
void data_to_file()                            // Inserting datas to file
{
	FILE *fp;
	int i;
	fp = fopen("student.dat", "w");
	for(i=0;i<n;i++)
	fwrite(&s[i], sizeof(s[i]),1,fp);
	fclose(fp);
}
void admin_login(){
	FILE *fp;
	char username[20],file_username[20],password[20],file_password[20];
	int i,nos,typeAdminLogin;
	printf("Admin Login \n\n");
	printf("Admin username: ");
	scanf("	%[^\n]%*c",username);
	printf("Admin password: ");
	scanf("	%[^\n]%*c",password);
	fp = fopen("details.txt", "r");
	fscanf(fp," %s  %s",file_username,file_password);
	fclose(fp);
	if (strcmp(username, file_username) == 0)
	{
		if (strcmp(password, file_password) == 0)
		{
			clrscr();
			printf("\n1.Add Students\n2.Display All student mark details\n");
			scanf("%d",&typeAdminLogin);
			if(typeAdminLogin==1)
			{
				printf("Enter the number of students :");
				scanf("%d",&nos);
				for(i=0;i<nos;i++)
				{
					printf("\nEnter Student %d Details :\n\n",i+1);
					input_details();
				}
				data_to_file();
			}
			else if (typeAdminLogin == 2)
			{
				printf("RollNo\tName\tMobileNo Address Mark1\tMark2\tMark3\tMark4\tMark5\tTotal\tRank\n");
				print_details(0);
			}
		}
	}
}
void type_login(){
	  char c;
	  int i,findrollNo,j;
	  long int findmobileNo;
	  print_details(1);              //1 to break the loop before printing
	  printf("Student Login \n\n");
	  printf("Rollno  :");
	  scanf("%d",&findrollNo);
	  printf("Mobileno  : ");
	  scanf("%ld",&findmobileNo);
	  for(i=0;i<=n;i++)
	  {
		if (findrollNo==s[i].rollNo)
		{
			if (findmobileNo==s[i].mobileNo)
			{
				printf("\nRoll No :%d\nName    :%s\nPhone No:%ld\nAddress :%s\nMark 1  :%d\nMark 2  :%d\nMark 3  :%d\nMark 4  :%d\nMark 5  :%d\nTotal   :%d\n",s[i].rollNo,s[i].name,s[i].mobileNo,s[i].address,s[i].m1,s[i].m2,s[i].m3,s[i].m4,s[i].m5,s[i].total);
				for(j=0;j<n;j++)
				{
					if (arr[j]==s[i].total)
						printf("Rank   :%d\n",j+1);
				}
				break;
			}
		 }
	  }
}
void file_to_struct()
{
	FILE *fp;
	fp = fopen("student.dat","r");
	while(fread(&s[n],sizeof(struct student),1,fp))
	n+=1;
	fclose(fp);
}
void main()
{
	int typeLogin;
	clrscr();
	file_to_struct();
	printf("1.Admin Login \n2.Student Login\n");
	scanf("%d",&typeLogin);
	if (typeLogin == 1)
		admin_login();
	else if(typeLogin == 2 )
		type_login();
	getch();
}

