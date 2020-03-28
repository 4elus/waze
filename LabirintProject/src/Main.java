import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        MakeRound round = new MakeRound(new FileConstructor("in.txt","out.txt"));
        MakeRound roundLong = new MakeRound(new FileConstructor("inLarge.txt","outLarge.txt"));
        round.makeLoop();
        roundLong.makeLoop();

    }
}
