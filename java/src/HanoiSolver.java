public class HanoiSolver {
    int ringCount;
    private final HanoiGame h;

    HanoiSolver(int ringCount) {
        this.ringCount = ringCount;
        h = new HanoiGame(ringCount);
    }

    /**
     * <pre>
     * Itereates through the big steps:
     *          1. takes the biggest possible ring on the original pin and puts it on the free pin
     *          2. builds a tower on the ring
     * </pre>
     */
    String solve() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < ringCount; i++) {
            // move the biggest ring
            int absFrom = isEven(i) ? 1 : 2;
            int absTo = isEven(i) ? 2 : 1;
            s.append(h.move(0, absTo));
            // build tower on ring
            s.append(solveRec(i, absFrom, absTo));
        }
        return s.toString();
    }


    /**
     * <pre>
     * Moves moveRingCount many rings from the "from" pin to the "to" pin.
     * Recursively builds bigger "Tower's of Hanoi":
     *          1. builds a small tower (starting with one ring)
     *          2. takes the biggest possible ring on the "from" pin and puts it on a temporary pin
     *          3. builds the small tower on top of the ring
     * </pre>
     *
     * @param moveRingCount depth of the recursion <=> amount of rings to move in this recursion <=> height of tower to build
     * @param from          where the (small) tower needs to be moved form
     * @param to            where the (small) tower needs to be moved to
     * @return A list of steps to move moveRingCount many rings from the "from" pin to the "to" pin
     */
    private String solveRec(int moveRingCount, int from, int to) {
        // if the amount of rings to move is 0 nothing happens
        if (moveRingCount == 0) return "";
        // move the small ring only (first step of small tower)
        if (moveRingCount == 1) return h.move(from, to);

        // build small tower
        String res = solveRec(moveRingCount - 1, from, other(from, to));
        // adjust next biggest ring
        res += h.move(from, to);

        // put the small tower on the ring
        int newFrom = isPowerOfTwo(moveRingCount) ? to : other(from, to);
        int newTo = isPowerOfTwo(moveRingCount) ? other(from, to) : to;
        res += solveRec(moveRingCount - 1, newFrom, newTo);
        return res;
    }

    private static boolean isEven(int i) {
        return i % 2 == 0;
    }

    private static boolean isPowerOfTwo(int i) {
        return Math.log(i) / Math.log(2) == 0;
    }

    public static int other(int von, int nach) {
        return (von * 2 + nach * 2) % 3;
    }
}
