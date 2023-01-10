import java.net.*;
import java.io.*;
import java.util.*;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; 
class Clientdata{

    void home()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Please Choose 1.Login 2.Register");
        int num=sc.nextInt();        
        if(num==2)
        {
            register(num);
        }
        else if(num==1)
        {
            login(num);
        }
        else{
            System.out.println("choose correct option");
        }

    }
      static void register(int a)
         {
            Clientdata cd=new Clientdata();
            try {
                
                Socket s=new Socket("localhost",8005);
                System.out.println("Client connected");
                String msg,name,mail;
                int id,pass;
                Scanner sc=new Scanner(System.in);
                
                while(true)
                {
                    DataInputStream dis=new DataInputStream(s.getInputStream());  
                    DataOutputStream dout=new DataOutputStream(s.getOutputStream());

                    int fun=a;        
                    dout.writeInt(fun); 

                    msg=dis.readUTF();
                    System.out.println(msg);
                   
                    name=sc.nextLine();        
                    dout.writeUTF(name); 

                    msg=dis.readUTF();
                    System.out.println(msg);
                   
                    mail=sc.nextLine();        
                    dout.writeUTF(mail); 

                    msg=dis.readUTF();
                    System.out.println(msg);
                   
                    id=sc.nextInt();        
                    dout.writeInt(id); 
                    
                    msg=dis.readUTF();
                    System.out.println(msg);
                    break;
                    
                 }
        cd.home();
                } catch (Exception e) {
                    // TODO: handle exception
                }
         }

      static void login(int num)
      {
        
        try {
           
            Socket s=new Socket("localhost",8005);
            System.out.println("Client connected");
            String name,msg,pass,output;           
            Scanner sc=new Scanner(System.in);
            
            while(true)
            {
                DataInputStream dis=new DataInputStream(s.getInputStream());  
                DataOutputStream dout=new DataOutputStream(s.getOutputStream());
   
                int fun=num;        
                dout.writeInt(fun); 

                System.out.println("enter user name");
                name=sc.nextLine();        
                dout.writeUTF(name);

                System.out.println("enter user password");
                pass=sc.nextLine();        
                dout.writeUTF(pass); 

                output=dis.readUTF();
                if(output.equals("IN"))
                {
                    System.out.println("Homepage ready...");
                    break;
                }
                else
                {
                        System.out.println("Incorrect User data Details");
                        int a=1;
                        login(a);
                        
                        
                }

                                               
             }
            } catch (Exception e) {
                // TODO: handle exception
            }
      }   
    public static void main(String[] args) {
        Clientdata cd=new Clientdata();
        cd.home();
        
        
    }
}