//BINGO
/* By, Arun Joseph */

import java.lang.*;
import java.io.*;
import java.util.*;

class Bingo{
	public static int size=9;

	public static void create(int[][] a){
		Random rand=new Random();
		int i,j,r,c,x,y;
		for(r=0;r<size;r++){
			for(c=0;c<size;c++){
				y=0;
				x=rand.nextInt(size*size)+1;
				for(i=0;i<size;i++){
					for(j=0;j<size;j++){
						if(a[i][j]==x)
							y++;
					}
				}
				if(y==0)
					a[r][c]=x;
				else{
					if(c==0){
						r--;
						c=size;
					}
					else
						c--;
				}
			}
		}
	}

	public static int dispbin(int e){
		int choice=0;
		System.out.println();
		String str="98765BINGO";
		for(int i=0;i<e&&i<size;i++)
			System.out.print("*");
		if(e>=size)
			choice=1;
		else
			System.out.print(str.substring(10-size+e));
		return choice;
	}

	public static void main(String args[]){
		Scanner sc=new Scanner(System.in);
		Random rand=new Random();
		int[][] a=new int[size][size];
		int[][] b=new int[size][size];
		int[][] bin=new int[size][size];
		int[] m=new int[2*size+2];
		int[] n=new int[2*size+2];
		int r,c,i,g,num,ra=0,max,choice,choice1,choice2,e,f,toss;
		String name;
		System.out.println("BINGO");
		System.out.println();
		System.out.println("Instructions :- ");
		System.out.println(" It is a \"User vs Computer\" game in which a nxn game board is given to both the user and the computer. The user can choose to play first. Both can choose one no. at a time. The first to complete n rows, columns or diagonals wins. But BEWARE, the computer is as intelligent as you!!");
		System.out.println();
		System.out.print("Enter name :- ");
		name=sc.nextLine();
		System.out.print("Enter board size :- ");
		size=sc.nextInt();
		create(a);
		create(b);
		System.out.println();
		System.out.println("1. Computer first");
		System.out.println("2. You first");
		System.out.println("3. Toss ");
		System.out.print(" Enter choice :- ");
		choice=sc.nextInt();
		switch(choice){
			case 1:
				g=1;
				break;
			case 2:
				g=0;
				break;
			default:
				System.out.println();
				toss=rand.nextInt(2);
				if(toss==0){
					g=1;
					System.out.print("I play first!!");
				}
				else{
					g=0;
					System.out.print("You play first!!");
				}
		}
		sc.nextLine();
		sc.nextLine();
		for(;;g++){
			e=0;
			f=0;
			for(i=0;i<2*size+2;i++){
				m[i]=0;
				n[i]=0;
			}
			for(r=0;r<size;r++){
				for(c=0;c<size;c++){
				if(a[r][c]==0)
					m[r]++;
				if(b[r][c]==0)
					n[r]++;
				if(a[c][r]==0)
					m[r+size]++;
				if(b[c][r]==0)
					n[r+size]++;
				}
			}
			for(r=0;r<size;r++){
				if(a[r][r]==0)
					m[2*size]++;
				if(b[r][r]==0)
					n[2*size]++;
			}
			for(r=0;r<size;r++){
				if(a[r][size-1-r]==0)
					m[2*size+1]++;
				if(b[r][size-1-r]==0)
					n[2*size+1]++;
			}
			for(i=0;i<2*size+2;i++){
				if(m[i]==size)
					e++;
				if(n[i]==size)
					f++;
			}
			System.out.println("BINGO");
			System.out.println();
			System.out.println("Computer :-");
			System.out.println();
			for(r=0;r<size;r++){
				System.out.println();
				for(c=0;c<size;c++){
					if(a[r][c]==0)
						System.out.print(" - ");
					else
						System.out.print(" * ");
				}
			}
			choice1=dispbin(e);
			System.out.println();
			System.out.println();
			System.out.println(name+" :- ");
			for(r=0;r<size;r++){
				System.out.println();
				for(c=0;c<size;c++){
					if(b[r][c]==0)
						System.out.print(" - ");
					else{
						if(b[r][c]/100!=0)
							System.out.print(b[r][c]);
						if(b[r][c]/10!=0)
							System.out.print(" "+b[r][c]);
						else
							System.out.print(" "+b[r][c]+" ");
					}
				}
			}
			choice2=dispbin(f);
			if(choice1==1||choice2==1)
				break;
			if(g%2==0){
				System.out.println();
				System.out.print(" Enter no. :- ");
				num=sc.nextInt();
				for(r=0;r<size;r++){
					for(c=0;c<size;c++){
						if(a[r][c]==num)
							a[r][c]=0;
						if(b[r][c]==num)
							b[r][c]=0;
					}
				}
			}
			if(g%2==1){
				for(r=0;r<size;r++){
					for(c=0;c<size;c++){
						bin[r][c]=0;
						if(a[r][c]!=0){
							bin[r][c]+=Math.pow(2,m[r]);
							bin[r][c]+=Math.pow(2,m[size+c]);
							if(r==c)
								bin[r][c]+=Math.pow(2,m[2*size]);
							if((r+c)==size-1)
								bin[r][c]+=Math.pow(2,m[2*size+1]);
						}
					}
				}
				max=0;
				for(r=0;r<size;r++)
					for(c=0;c<size;c++)
						if(bin[r][c]>max){
							max=bin[r][c];
							ra=a[r][c];
						}
				System.out.println();
				System.out.println(" Computer plays :- "+ra);
				sc.nextLine();
				sc.nextLine();
				for(r=0;r<size;r++)
					for(c=0;c<size;c++){
						if(a[r][c]==ra)
							a[r][c]=0;
						if(b[r][c]==ra)
							b[r][c]=0;
					}
			}
		}
		System.out.println();
		if((e>=size)&&(f>=size))
			System.out.println(" Draw Game...");
		else if(e>=size)
			System.out.println(" You lose...");
		else
			System.out.println(" You win...");
	}
}
