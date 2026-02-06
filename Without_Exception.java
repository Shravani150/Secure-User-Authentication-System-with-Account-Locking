# Code without Exception Handling
  
import java.util.Scanner;
class User{
    private String username;
    private String password;
    private int failedAttempts;
    private boolean isLocked=false;
    
    public User(String username,String password){
        this.username=username;
        this.password=password;
        this.failedAttempts=0;
        this.isLocked=false;
    }
    
    public static boolean isStrongPassword(String password){
        if(password.length()<8){
            return false;
        }
        boolean hasUpper=false;
        boolean hasLower=false;
        boolean hasDigit=false;
        boolean hasSpecial=false;
        for(int i=0;i<password.length();i++){
            char ch=password.charAt(i);
            if(Character.isUpperCase(ch)){
                hasUpper=true;
            }else if(Character.isLowerCase(ch)){
                hasLower=true;
            }else if(Character.isDigit(ch)){
                hasDigit=true;
            }else{
                hasSpecial=true;
            }
        }
        return hasUpper&&hasLower&&hasDigit&&hasSpecial;
    }
    
    public void login(String enteredPassword){
        if(isLocked){
            System.out.println("Account is Locked");
            return;
        }
        if(password.equals(enteredPassword)){
            System.out.println("Account login successful!");
            failedAttempts=0;
        }
        else{
            failedAttempts++;
            System.out.println("Entered wrong password");
        }
        if(failedAttempts>=3){
            isLocked=true;
            System.out.println("Account is locked due to 3 failed attempts");
        }
    }
}
public class Main{
    public static void main(String[]args){
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter your Username ");
        String userName=sc.nextLine();
        String pass;
        while (true) {
            System.out.print("Set a password: \n");
            pass = sc.nextLine();
            if (User.isStrongPassword(pass)) {
                break;
            } else {
                System.out.println("Weak password! Please re-enter.");
                System.out.println("Password must conatain: \n");
                System.out.println("- Password must be atleast 8 characters");
                System.out.println("- Atleast one upper case letter");
                System.out.println("- Atleast one lower case letter");
                System.out.println("- Atleast one digit");
                System.out.println("- Atleast one special character");
            }
        }
        User u1=new User(userName,pass);
        for(int i=1;i<4;i++){
            System.out.println("");
            System.out.println("Enter your password to login: ");
            String input=sc.nextLine();
            u1.login(input);
        }
        
    }
}
