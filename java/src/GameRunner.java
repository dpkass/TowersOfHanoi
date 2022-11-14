import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameRunner {
    static int rings;

    public static void main(String[] args) {
        rings = Integer.parseInt(args[0]);
        HanoiGame hg = new HanoiGame(rings);
        System.out.println("Game starts!!");
        hg.print();

        for (; ; ) {
            int[] i = readInput();
            if (giveUp(i)) return;
            hg.move(i[0], i[1]);
            if (win(hg)) return;
        }
    }

    private static boolean giveUp(int[] i) {
        if (i == null) {
            System.out.printf("Score: %.2f%%\n", 0f);
            System.out.println("A perfect solve would look like this:");
            HanoiSolver hs = new HanoiSolver(rings);
            hs.solve();
            return true;
        }
        return false;
    }

    private static boolean win(HanoiGame hg) {
        if (hg.testWin()) {
            System.out.println("You won!!");
            double score = hg.score();
            if (Math.abs(score - 100) < 1e-5)
                System.out.printf("Perfect Solve!! Score: %.2f%%", score);
            else {
                System.out.printf("Score: %.2f%%\n", score);
                System.out.println("A perfect solve would look like this:");
                HanoiSolver hs = new HanoiSolver(rings);
                hs.solve();
            }
            return true;
        }
        return false;
    }

    private static int[] readInput() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] s = in.readLine().split(" ");
            if (s[0].equals("exit")) throw new RuntimeException();
            int[] i = new int[2];
            i[0] = Integer.parseInt(s[0]) - 1;
            i[1] = Integer.parseInt(s[1]) - 1;
            if (i[0] < 0 || i[0] > rings || i[1] < 0 || i[1] > rings) throw new Exception();
            return i;
        } catch (RuntimeException e) {
            return null;
        } catch (Exception e) {
            System.out.println("Err: the entered values are illegal. Please try again.");
        }
        return readInput();
    }
}
