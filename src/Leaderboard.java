import java.util.ArrayList;

public class Leaderboard {
    ArrayList<User> leaderboard = new ArrayList<>();

    Leaderboard(Management manage) {
        leaderboard.addAll(manage.user);
    }

    void sortLeaderboard(){
        leaderboard.sort ((u1, u2) -> Integer.compare(u2.totalMarks, u1.totalMarks));
    }

    void leaderboardShow(){
        sortLeaderboard();

        System.out.println("\nLEADERBOARD\n");

        for(int i = 0; i < leaderboard.size() && i < 10; i++) {

            User u = leaderboard.get(i);

            System.out.println( "Rank " + (i+1) + " | Name : " + u.getName() + " | Marks : " + u.totalMarks );
        }
    }
}
