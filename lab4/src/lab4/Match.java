package lab4;

public class Match {
    public final int start;      // inclusive
    public final int end;        // inclusive
    public final int patternId;
    public final String pattern;

    public Match(int start, int end, int patternId, String pattern) {
        this.start = start;
        this.end = end;
        this.patternId = patternId;
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        return "Match{pattern='" + pattern + "', id=" + patternId +
                ", start=" + start + ", end=" + end + "}";
    }
}
