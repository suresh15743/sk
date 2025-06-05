import java.util.Scanner;
class  calculator{
       public static void  main(String args[]){
	   int num1,num2;
	   Scanner sc=new Scanner(System.in);
	   System.out.println("enter two numbers:");
	   num1=sc.nextInt();
	   num2=sc.nextInt();
	   System.out.println("enter the operator(+,-,*,/)");
	   char op=sc.next().charAt(0);
	   int k=0;
	   switch(op){
	   case '+':
	        k=num1+num2;
	        break;
	   case '-':
	        k=num1-num2;
	        break;
	   case '*':
	        k=num1*num2;
	        break;
	  case '/':
			if(num2==0){
				System.out.println("Cannot divide by 0");
			}
			else k=num1/num2;
					System.out.println("error");
		   break;
	  default:
	  System.out.println("invalid");
	   }
	   System.out.println("final result");
	   System.out.println();
	   System.out.println(num1+" "+op+" "+num2+"="+k);
	   }
	   }