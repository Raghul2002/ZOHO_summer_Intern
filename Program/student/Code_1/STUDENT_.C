#include<stdio.h>
#include<conio.h>
#include<string.h>
int main()
{
	char c,name[20],address[40];
	long int mobileNo,findmobileNo;
	int option2,rollNo,findrollNo,temp,m1,j,m2,m3,m4,m5,total=0,count=0,arr[10];
	char* username;
	char* password;
	char* option;
	char ch,strcount[15];
	FILE *fp;
	clrscr();
	printf("1.Admin Login (Admin)\n2.Student Login(Student)\n");
	gets(option);
	clrscr();
	if (strcmp(option,"Admin")==0){
	  printf("Admin Login \n\n");
	  printf("Admin username: ");
	  gets(username);
	  printf("Admin password: ");
	  gets(password);
	  if (strcmp(username, "user") == 0) {
		if (strcmp(password, "password") == 0) {
			printf("\nUser successfully logged in...");
			clrscr();
			printf("\n1.Add Students\n2.Display All student mark details\n");
			scanf("%d",&option2);
			if (option2==1){
				printf("Enter Roll No : ");
				scanf("%d",&rollNo);
				printf("Enter Name :");
				fflush(stdin);
				gets(name);
				printf("Mobile No:");
				scanf("%ld",&mobileNo);
				printf("Address:");
				scanf("	%[^\n]%*c",&address);
				printf("Mark 1:");
				scanf("%d",&m1);
				printf("Mark 2:");
				scanf("%d",&m2);
				printf("Mark 3:");
				scanf("%d",&m3);
				printf("Mark 4:");
				scanf("%d",&m4);
				printf("Mark 5:");
				scanf("%d",&m5);
				total =m1+m2+m3+m4+m5;
				fp = fopen("student.txt", "a");
				fprintf(fp,"%d %s %ld %s %d %d %d %d %d %d \n",rollNo,name,mobileNo,address,m1,m2,m3,m4,m5,total);
				fclose(fp);
				fp=fopen("count.txt","a");
				fprintf(fp,"1");
				fclose(fp);
			}
			else if (option2 ==2){
				int count =0;
				char c;
				int i;
				fp=fopen("count.txt","r");
				fscanf(fp,"%s",strcount);
				fclose(fp);
				count=strlen(strcount);
				printf("RollNo Name  MobileNo Address Mark1 Mark2  Mark3  Mark4  Mark5 Total  Rank");
				fp = fopen("student.txt","r");
				for(i=0;i<count;i++){
					fscanf(fp,"%d %s %ld %s %d %d %d %d %d %d ",&rollNo,name,&mobileNo,address,&m1,&m2,&m3,&m4,&m5,&total);
					fscanf(fp,"\n");
					arr[i]=total;
				}
				for (i=0;i<count;++i)
				{
					for (j=i+1;j<count;++j)
					{
						if (arr[i] < arr[j])
						{
						temp =  arr[i];
						arr[i] = arr[j];
						arr[j] = temp;
						}
					}
				}
				fclose(fp);
				fp = fopen("student.txt","r");
				for(i=0;i<count;i++){
					int j=0;
					fscanf(fp,"%d %s %ld %s %d %d %d %d %d %d \n",&rollNo,name,&mobileNo,address,&m1,&m2,&m3,&m4,&m5,&total);
					while(total!=arr[j]){
					       j+=1;
					}
					printf("\n%d      %s   %ld     %s    %d    %d     %d     %d     %d    %d    %d",rollNo,name,mobileNo,address,m1,m2,m3,m4,m5,total,j+1);
				}
				fclose(fp);
			}
		}
		else{
		     printf("\nPassword entered is invalid");
		}
	  }
	  else {
		printf("\nUsername entered is invalid");
	  }
	  //create the file
	  fp = fopen("details.txt", "w");
	  fprintf(fp," %s  %s",username,password);
	  fclose(fp);
	  }
	  else if (strcmp(option,"Student")==0){
	  char c;
	  int i;
	  printf("Student Login \n\n");
	  printf("Rollno  :");
	  scanf("%d",&findrollNo);
	  printf("Mobileno  : ");
	  scanf("%ld",&findmobileNo);
	  fp=fopen("count.txt","r");
	  fscanf(fp,"%s",strcount);
	  fclose(fp);
	  count=strlen(strcount);
	  fp = fopen("student.txt","r");
	  for(i=0;i<count;i++){
		j=0;
		fscanf(fp,"%d %s %ld %s %d %d %d %d %d %d \n",&rollNo,name,&mobileNo,address,&m1,&m2,&m3,&m4,&m5,&total);
		while(total!=arr[j]){
		       j+=1;
		}
		if (rollNo==findrollNo) {
			if (mobileNo == findmobileNo)
			printf("\nRoll No :%d\nName    :%s\nPhone No:%ld\nAddress :%s\nMark 1  :%d\nMark 2  :%d\nMark 3  :%d\nMark 4  :%d\nMark 5  :%d\nTotal   :%d\nRank    :%d ",rollNo,name,mobileNo,address,m1,m2,m3,m4,m5,total,j+1);
			else
			printf("Invalid roll number and phone no. ");
		}
	   }
	   fclose(fp);
      }
      getch();
      return 0;
}