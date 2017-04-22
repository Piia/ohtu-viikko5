package ohtu;

public class TennisGame {

    private int m_score1;
    private int m_score2;
    private String player1Name;
    private String player2Name;
    private final int HIGHEST_POINTS = 4;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.m_score1 = 0;
        this.m_score2 = 0;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)) {
            m_score1 += 1;
        } else {
            m_score2 += 1;
        }
    }

    public String getScore() {
        String output;
        if (m_score1 == m_score2) {
            output = tie();
        } else if (m_score1 >= HIGHEST_POINTS || m_score2 >= HIGHEST_POINTS) {
            output = advantageOrWin();
        } else {
            output = otherScore();
        }
        return output;
    }

    private String tie() {
        String output;
        switch (m_score1) {
            case 0:
                output = "Love-All";
                break;
            case 1:
                output = "Fifteen-All";
                break;
            case 2:
                output = "Thirty-All";
                break;
            case 3:
                output = "Forty-All";
                break;
            default:
                output = "Deuce";
                break;

        }
        return output;
    }

    private String advantageOrWin() {
        String output;
        int difference = m_score1 - m_score2;

        if (difference == 1) {
            output = "Advantage player1";
        } else if (difference == -1) {
            output = "Advantage player2";
        } else if (difference >= 2) {
            output = "Win for player1";
        } else {
            output = "Win for player2";
        }
        return output;
    }

    private String otherScore() {
        String output = "";
        int temp;
        for (int i = 1; i < HIGHEST_POINTS-1; i++) {
            if (i == 1) {
                temp = m_score1;
            } else {
                output += "-";
                temp = m_score2;
            }
            switch (temp) {
                case 0:
                    output += "Love";
                    break;
                case 1:
                    output += "Fifteen";
                    break;
                case 2:
                    output += "Thirty";
                    break;
                case 3:
                    output += "Forty";
                    break;
            }
        }
        return output;
    }
}
