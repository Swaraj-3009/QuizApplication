import java.util.InputMismatchException;
import java.util.Scanner;

class MainMenu{
    String username = "RAMESH";
    int password = 12345678;
    Management manage = new Management();

    //question setter
    void manager(String username, int password, Scanner sc){
        if(!(this.username.equalsIgnoreCase(username) && this.password == password)){
            System.out.println("Invalid credentials!");
            return;
        }

        while(true){ 
                System.out.println("1. Add Question");
                System.out.println("2. Show total Questions");
                System.out.println("3. Delete Questions");
                System.out.println("4. Exit (Login page)");
                
                int choice = -1;
                while (true) {
                    try {
                        System.out.print("\nEnter choice: ");
                        choice = sc.nextInt();
                        sc.nextLine();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        sc.nextLine(); 
                    }
                }

                int questionId;
                String question;
                String answer1;
                String answer2;
                String answer3;
                String answer4;
                String correctAnswer;

                switch(choice){
                    case 1 : System.out.print("Enter Question : ");
                             question = sc.nextLine();
                             System.out.print("Enter option 1 : ");
                             answer1 = sc.nextLine();
                             System.out.print("Enter option 2 : ");
                             answer2 = sc.nextLine();
                             System.out.print("Enter option 3 : ");
                             answer3 = sc.nextLine();
                             System.out.print("Enter option 4 : ");
                             answer4 = sc.nextLine();
                             System.out.print("Enter correct answer (not option!) : ");
                             correctAnswer = sc.nextLine();

                            manage.addQuestion(question, answer1, answer2, answer3, answer4, correctAnswer);

                            System.out.println("\nQuestion Added!\n");

                        break;

                    case 2 : manage.showAllQuestions();
                        break;

                    case 3 : while (true) {
                                System.out.print("Enter question ID :");

                                if (sc.hasNextInt()) {
                                    questionId = sc.nextInt();
                                    sc.nextLine();
                                    break;
                                } else {
                                    System.out.println("Please enter number only!");
                                    sc.nextLine();
                                }
                             }

                             manage.deleteQuestion(questionId);
                        break;

                    case 4 : return;

                    default : System.out.println("\nPlease enter valid details!\n");
                        
            }
        }  
    }


    //Question Attempter
    void userManager(String name, Scanner sc, int userid){
        while(true){
            System.out.println("1. Start Quiz");
            System.out.println("2. Check Score");
            System.out.println("3. View Profile");
            System.out.println("4. Exit");
            
            int choice = -1;
            while (true) {
                
                try {
                    System.out.print("\nEnter choice: ");
                    choice = sc.nextInt();
                    sc.nextLine();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    sc.nextLine(); 
                }
            }
            
            switch(choice){
                case 1 : if(manage.quest.isEmpty()){
                            System.out.println("No questions available!");
                            break;
                         }
                         
                         manage.user.add( new User(name, userid));
                    
                         System.out.println("User Id   : " + manage.user.get(manage.user.size()-1).getUserId());
                         System.out.println("User Name : " + manage.user.get(manage.user.size()-1).getName());

                         for(int i = 0; i < manage.quest.size(); i++){
                            System.out.println();
                            System.out.println((i+1) + ". " + manage.quest.get(i).question);
                            System.out.println("A. " + manage.quest.get(i).answer1);
                            System.out.println("B. " + manage.quest.get(i).answer2);
                            System.out.println("C. " + manage.quest.get(i).answer3);
                            System.out.println("D. " + manage.quest.get(i).answer4);

                            System.out.print("Write Full Answer (Not option) : ");
                            String userAnswer = sc.nextLine();

                            manage.startQuiz(name, userAnswer , i, userid);
                            
                        }
                        System.out.println("\nQuiz Ended!\n");
                    break;

                case 2 : manage.checkScore(name, userid);
                    break;

                case 3 : manage.viewProfile(name, userid);
                    break;

                case 4 : return;

                default : System.out.println("\nPlease enter valid details!\n");
            }
            System.out.println("1. Main Menu");
            System.out.println("2. Login page");
        
            int choices = -1;
            while (true) {
                try {
                    System.out.print("Enter choice: ");
                    choices = sc.nextInt();
                    sc.nextLine();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    sc.nextLine(); 
                }
            }
            if(choices == 1){
                continue;
            }
            else if(choices == 2){
                return;
            }
            else{
                System.out.println("\nPlease enter valid details!\n");
            }
        }
    }
}


//Login class
public class QuizApplication {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        MainMenu mm = new MainMenu();
        
        System.out.println("Quiz Apllication");

        while(true){
            System.out.println("1. Login as Manager");
            System.out.println("2. Login as User");
            System.out.println("3. Leaderboard");
            System.out.println("4. Exit");

            int login;
            while (true) {
            try {
                System.out.print("\nEnter choice: ");
                login = sc.nextInt();
                sc.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); 
            }
        }

            if(login == 1){
                System.out.print("Enter username : ");
                String username = sc.nextLine();
                int password;
                while (true) {
                    System.out.print("Enter 8-digit pin : ");

                    if (sc.hasNextInt()) {
                        password = sc.nextInt();
                        sc.nextLine();
                        break;
                    } else {
                        System.out.println("\nPlease enter number only!\n");
                        sc.nextLine();
                    }
                }
                mm.manager(username, password, sc);
            }
            else if(login == 2){
                System.out.print("Enter your name : ");
                String name = sc.nextLine();
                int userid;
                while (true) {
                    System.out.print("Enter User Id: ");

                    if (sc.hasNextInt()) {
                        userid = sc.nextInt();
                        sc.nextLine();
                        break;
                    } else {
                        System.out.println("Please enter number only!");
                        sc.nextLine();
                    }
                }

                mm.userManager(name, sc, userid);
            }
            else if(login == 3){
                new Leaderboard(mm.manage).leaderboardShow();       //anonymous object everytime new leaderboard create
            }
            else if(login == 4){
                sc.close();
                return;
            }
            else{
                System.out.println("\nPlese Enter valid input!\n");
            }
        }
    }
}
