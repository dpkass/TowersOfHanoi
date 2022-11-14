import java.util.*;
import java.util.stream.IntStream;

public class HanoiGame {
    int rings;
    int score;
    List<Stack<Integer>> playfield = new ArrayList<>(3);

    HanoiGame(int rings) {
        this.rings = rings;
        IntStream.rangeClosed(0, 2).forEach(i -> playfield.add(new Stack<Integer>()));
        playfield.get(0)
                 .addAll(Arrays.asList(IntStream.rangeClosed(1, rings)
                                                .map(i -> -i)
                                                .sorted()
                                                .map(i -> -i)
                                                .boxed()
                                                .toArray(Integer[]::new)));
    }

    String move(int from, int to) {
        Stack<Integer> start = playfield.get(from);
        Stack<Integer> goal = playfield.get(to);

        if (goal.size() != 0 && start.peek() >= goal.peek()) {
            System.out.println("Illegal move");
            return null;
        }

        score++;
        goal.push(start.pop());
        print();
        return "(%s -> %s) ".formatted(from, to);
    }

    public double score() {
        double perfectScore = Math.pow(2, rings) - 1;
        return perfectScore / score * 100;
    }

    boolean testWin() {
        return playfield.get(1).size() == rings || playfield.get(2).size() == rings;
    }

    void print() {
        System.out.println(score + ":");
        for (int i = rings; i >= 0; i--) {
            for (int j = 0; j < 3; j++)
                System.out.print((playfield.get(j).size() > i ? playfield.get(j).get(i) : "|") + "   ");
            System.out.println();
        }
    }
}
