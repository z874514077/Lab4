import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
public class test { 
	    public static int linjie[][]; //邻接矩阵
	    public static String[] wordlist;//存储字母的数组
	    public static int k=1;//存储字母的共使用了k个数组内存
	    public static int fina1[] ; 
	    public static String queryBridgeWords(String word1, String word2)
		{
		    int i=0,flag=0,j=0,bridge;
		    String mystring1;
			fina1=new int[99];
			for(int b=0;b<99;b++)
				fina1[b]=-3;
		    for (i=0;i<k;i++)
		    {
		       if (word1.equals(wordlist[i]))
		    	{
		    		for (j=0;j<k;j++)
		    		{
		    			if (word2.equals(wordlist[j]))
		    			{
		    				flag=1;
		    				break;
		    			}
		    		}
		    	break;
		    	}
		    }
		    if (flag==0)
		    {
		      return "No word1 or word2 in the graph!";
		    }
		    else
		    {   
		    	bridge=0;
		    	int v1,v2;
		    	int pre[],dist[];
		    	pre=new int[99];
		    	dist=new int[99];
		        for (v1=0;v1<k;v1++)
		    		{
		    			if(linjie[i][v1]!=99)
		    			{
		    				if (v1==j)
		    				   continue;
		    			    pre[v1]=i;
		    				
		    			}
		    			else 
		    				pre[v1]=-1;
		    		}
		        for (v2=0;v2<k;v2++)
	    		{
	    			if(linjie[v2][j]!=99)
	    			{
	    				if (v2==i)
	    				   continue;
	    				dist[v2]=j;
	    				
	    			}
	    			else 
	    				dist[v2]=-2;
	    		}
		        mystring1 = "The bridge words from "+word1+" to "+word2+" is ";
		    	for(int l=0;l<k;l++)
		    	{ 
		    		for (int m=0;m<k;m++)
		    		{
		    			if((i==pre[l])&&(dist[m]==j)&&(l==m))
		    			{	
		    				mystring1=mystring1+wordlist[l]+" ";
		    			    fina1[bridge++]=l;
		    			}
		    			
		    		}
		    	}
		        if (fina1[0]==-3)
		        {
		        	return "No bridge words from "+word1+" to "+word2+"!" ;
		            
		        } 
		    }
		    return mystring1+"!";
		}
	    public static String calcShortestPath2(String word)
	    {
	    	int i,j,q;
	    	int pre[],dist[][];
	    	pre=new int[99];
	    	dist=new int[99][99];
	    	for (i=0;i<k;i++)
	    	{
	    		for (j=0;j<k;j++)
	    		{
	    			dist[i][j]=linjie[i][j];
	    			if (dist[i][j]!=99)
	    			{
	    				pre[j]=i;
	    			}
	    			else 
	    				pre[j]=-1;
	    		}
	    	}
	    	for(q=0;q<k;q++)
	    	{
	    		for (i=0;i<k;i++)
		    	{
		    		for (j=0;j<k;j++)
		    		{
		    			if((i!=j)&&(dist[i][j]>dist[i][q]+dist[q][j]))
		    			{
		    				dist[i][j] = dist[i][q] + dist[q][j];
		                    if(dist[i][j] != 99)
		                    {
		                        pre[j] = q;
		                        pre[q] = i;
		                    }
		                    else
		                        pre[j] = -1;
		    			}
		    			
		    		}
		    	}
	    		
	    	}
	    	String  mystring=""; 
	    	for (i=0;i<k;i++)
		    {
	    		if (word.equals(wordlist[i]))
	    			break;
	    	}
    		for(j=0;j<k;j++)
    		{
    			if (dist[i][j]==99)
    				continue;
    			else if (i!=j)
    			{	
    				mystring=mystring+calcShortestPath(word,wordlist[j])+"\r"+"权值为:\r"+dist[i][j]+"\r";
    				
    			}   
    		}
    		mystring=mystring+"其余顶点不可达";
    	return mystring;
	    }
	    public static String calcShortestPath(String word1, String word2)
	    {
	    	int v1,v2,i,j,q=0,min;
	    	for (v1=0;v1<k;v1++)
	    	{
	    		if (word1.equals(wordlist[v1]))
	    			break;
	    	}
	    	for (v2=0;v2<k;v2++)
	    	{
	    		if (word2.equals(wordlist[v2]))
	    			break;
	    	}
	    	int pre[],fina[],dist[],path[];
	    	pre=new int[99]; //用来记录最短路径所经过的顶点
	    	fina=new int[99];//用来标志已选和未选的标志位
	    	dist=new int[99];//路径上从顶点到下一个顶点的权重
	    	path=new int[99];
	    	for (j=0;j<k;j++)
	    	{
	    		dist[j]=linjie[v1][j];
	    		if (linjie[v1][j]==0)
	    			pre[j]=-1;
	    		else 
	    			pre[j]=v1;
	    		fina[j]=0;//初始化
	    	}
	    	for (i=0;i<k;i++)
	    	{
	    		min=99;
	    		for (j=0;j<k;j++)//找到最小值的顶点
	    		{	
	    			if ((fina[j]==0)&&(min>dist[j]))
	    			{
	    				min=dist[j];
	    				q=j;
	    			}  
	    		}
	    		if(min==99) //找不到顶点
	    			break;
	    		fina[q]=1; //标志为已选
	    		for (j=0;j<k;j++)//更新最短路径
	    		{
	    			if ((fina[j]==0)&&(dist[j]>dist[q]+linjie[q][j]))
	    			{
	    				dist[j]=dist[q]+linjie[q][j];
	    				pre[j]=q;
	    			}
	    					
	    		}
	    	}
	    	if (pre[v2]==-1)
	    	{
	    		String a="不可达";
	    		return a;		
	    	}
	    	j=v2;
	    	i=0;
	    	while(j!=v1)
	    	{
	    		path[i]=j;
	    		j=pre[j];
	    		i++;
	    		
	    	}
	    	String str4="";
	    	if (word2.equals(wordlist[path[i-1]]))
	    	{
	    		str4=word1+" -> "+word2;
	    	}
	    	else
	    	{   
	    		str4=word1;
	    		for (q=i-1;q>0;q--)
	    		{
	    			str4=str4+" -> "+wordlist[path[q]];
	    		}
	    		str4=str4+" -> "+word2;
	    	}
    		return str4;
	    }
		public static String generateNewText(String inputText)
		{
			String[] inputArray = inputText.split(" ");
			String str1="";
			for(int i=0;i<inputArray.length-1;i++)
			{
				queryBridgeWords(inputArray[i].toLowerCase(),inputArray[i+1].toLowerCase());
				 if(fina1[0]==-3)
				 {
					 str1=str1+inputArray[i]+" ";
				 }
				 else
				 {
					 str1=str1+inputArray[i]+" "+wordlist[fina1[0]]+" ";
					 
				 } 
			}
		return str1+inputArray[inputArray.length-1];	
		}		
        public static void main(String args[]) 
		{
			int times=0,i=0,j=0,qiu=0,z=0,flag=0,q=0;
			wordlist=new String[99];		
			String Filename;
			String s="";
			Filename=JOptionPane.showInputDialog("输入文件路径");
			File file = new File(Filename);
			Reader reader = null;
			try {
				System.out.println("以字符为单位读取文件内容，一次读一个字节：");				
				reader = new InputStreamReader(new FileInputStream(file));
		        int tempchar;
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		        while ((tempchar = reader.read()) != -1)  //读取文件，并将字符串分割后装入数组
		        {
		            // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
		            // 但如果这两个字符分开显示时，会换两次行。
		            // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
		            if (((char) tempchar) != '\r') 
		            {
		                if(((char) tempchar>64&&(char) tempchar<91)||((char) tempchar>96&&(char) tempchar<123))
		                {
		                	s=s+(char) tempchar;
		                }	
		                else
		                {
		                	s=s+" ";
		                }
		            }
		        }		 		        
		        String [] arr = s.split("\\s+");
		        wordlist[0]=arr[0];
		
///////////////////////////////////////////////////////////////////////////////////////////////////////		        
		        for(i=1;i<arr.length;i++)  //对英文单词进行编号
		        {
		        	for(j=i-1;j>-1;j--)
		        	{
		        		if(arr[i].equals(arr[j]))
		        		{
		        			qiu=0;
		        			break;
		        		}
		        		else
		        		{
		        			qiu=1;
		        		}
		        	}
		        	if(qiu==1)
		        	{
		        		wordlist[k]=arr[i];
		        		k=k+1;
		        	}
		        }
		        //System.out.println(wordlist[3]);
////////////////////////////////////////////////////////////////////////////////////////////////
         //矩阵的初始化
		        linjie=new int[k][k];
		        for(i=0;i<k;i++)
		        {
		        	for(j=0;j<k;j++)
		        	{
		        		linjie[i][j]=99;
		        	}
		        }
///////////////////////////////////////////////////////////////////////////////////////////
		        //相邻的置为1
		        for(i=0;i<arr.length-1;i++)
		        {    		      
		        	for(j=0;j<k;j++)
		        	{			        		
		        		if(wordlist[j].equals(arr[i])) 
			        	{
		        				for(q=0;q<k;q++)
		        				{		        					
		        					if(wordlist[q].equals(arr[i+1]))
		        					{
		        						linjie[j][q]=1;
		        						flag=1;
		        						break;
		        					}
		        				}
			        	}
		        		if(flag==1)
		        		{
		        			break;
		        		}
		        	}
		        	flag=0;
		        }
		        for(i=0;i<arr.length-3;i++)
		        {
		        	for(j=i+2;j<arr.length-1;j++)
		        	{		 
		        		if(arr[i+1].equals(arr[j]))
		        		{		        			
		        			if(arr[j+1].equals(arr[i]))
		        			{
		        				times=times+1;		        				
		        			}
		        			if(arr[j-1].equals(arr[i]))
	        				{
	        					times=times+1;
	        				}
		        		}
		        	}
		        	 while(z<k)
		        	    {
		        	    	z=z+1;
		        		     if(wordlist[z-1].equals(arr[i]))
		        		    {
		        		    	 linjie[z-1][z]=times+1;
		        		    	 break;
		        		    }
		        		     
		        	     }
		        		 times=0;
		        }
		        for(i=0;i<k;i++)
		        {
		        	for(j=0;j<k;j++)
		        	{
		        		System.out.print(linjie[i][j]);
		        		System.out.print(" ");
		        	}
		        	System.out.println("\n");  
		        }
		        reader.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
			String a=queryBridgeWords("to","my");
			System.out.println(a);
			String s1=calcShortestPath("where", "my");
			System.out.println(s1);
			String s2=calcShortestPath2("where");
			System.out.println(s2);
			String i1=queryBridgeWords("are","is");
			System.out.println(i1);
			String i2=generateNewText("dwahd aidja aijd where ad is my");
			System.out.println(i2);
		}
}
