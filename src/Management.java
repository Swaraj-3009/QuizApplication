import java.util.ArrayList;

public class Management {
    ArrayList<Question> quest = new ArrayList<>();
    ArrayList<User> user = new ArrayList<>();

    void addQuestion( String question, String answer1, String answer2, String answer3, String answer4, String correctAnswer){
        quest.add( new Question(question, answer1, answer2, answer3, answer4, correctAnswer));
        System.out.println("Question Id : " + quest.get(quest.size()-1).getQuestionId());
    }

    void showAllQuestions(){
        if(quest.size() == 0){
            System.out.println("\nQuestion Bank is Empty!\n");
            return;
        }
        for(int i = 0 ; i < quest.size(); i++){
            System.out.println(quest.get(i).getQuestionId());
            System.out.println(quest.get(i).question);
            System.out.println(" ");
        }
    }

    void deleteQuestion(int questionId){
        int index = findQuestion(questionId);

        if(index == -1){
            System.out.println("\nPlease enter valid details!\n");
        }
        else{
            quest.remove(index);
            System.out.println("\nQuestion Deleted Succesfully!\n");
        }
    }

    int findQuestion(int questionId){
        for(int i = 0; i < quest.size(); i++){
            if(quest.get(i).getQuestionId() == questionId){
                return i;
            }
        }
        return -1;
    }

    int findUser(String name, int userid){
        for(int i = user.size() - 1; i >= 0; i--){              //reverse search beacuse new user latest update got update not first object
            if(user.get(i).getName().equalsIgnoreCase(name) && user.get(i).getUserId() == userid){
                return i;
            }
        }
        return -1;
    }

    void startQuiz(String name, String userAnswer, int i, int userid){
        int index = findUser(name, userid);

        if (index == -1){
            System.out.println("\nNo user Found!\n");
            return;
        }

        if(quest.get(i).correctAnswer.equalsIgnoreCase(userAnswer)){
            user.get(index).totalCorrectAnswer++;
            user.get(index).totalMarks = user.get(index).totalMarks + 4;
        }
        else{
            user.get(index).totalMarks = user.get(index).totalMarks - 1;
        }
        user.get(index).totalQuestionsAttend++;
        boolean status = quest.get(i).correctAnswer.equalsIgnoreCase(userAnswer);
        user.get(index).history.add(new QuizRecord(quest.get(i).question,userAnswer, quest.get(i).correctAnswer,status));
        
    }

    void checkScore(String name, int userid){
        int index = findUser(name, userid);

        if(index == -1){
            System.out.println("User not found");
            return;
        }
        if(user.get(index).history.isEmpty()){
            System.out.println("No quiz attempts yet");
            return;
        }

        System.out.println("Total Question Attend :" + user.get(index).totalQuestionsAttend);
        System.out.println("Total Correct Answer  :" + user.get(index).totalCorrectAnswer);
        System.out.println("Total Marks           :" + user.get(index).totalMarks);
        System.out.println();

        for(int i = 0; i < user.get(index).history.size(); i++){

            QuizRecord r = user.get(index).history.get(i);

            System.out.println("Question      : " + r.question);
            System.out.println("Your Answer   : " + r.userAnswer);
            System.out.println("Correct Answer: " + r.correctAnswer);
            System.out.println("Status        : " + (r.isCorrect ? "Correct" : "Wrong"));
            System.out.println();
        }

    }

    void viewProfile(String name, int userid){
        int index = findUser(name, userid);

        if(index == -1){
            System.out.println("\nUser Not Found!\n");
            return;
        }

        System.out.println("Name                  :" + user.get(index).getName());
        System.out.println("ID                    :" + user.get(index).getUserId());
        System.out.println("Total Question Attend :" + user.get(index).totalQuestionsAttend);
        System.out.println("Total Correct Answer  :" + user.get(index).totalCorrectAnswer);
        System.out.println("Total Marks           :" + user.get(index).totalMarks);
    }
}
