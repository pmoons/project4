package me.petermooney.project4;

public class SampleComparator {

    private String mCleanAnswer;

    public SampleComparator(String rawAnswer) {
        mCleanAnswer = cleanAnswer(rawAnswer);
    }

    public int compare(String userInput) {
        String userAnswer = cleanAnswer(userInput);

        if (userAnswer == mCleanAnswer) return 100;
        if (userAnswer.isEmpty()) return 0;

        int charsCorrect = 0;
        for (int i = 0; i < mCleanAnswer.length(); i++) {
            try {
                if (mCleanAnswer.charAt(i) == userAnswer.charAt(i)) charsCorrect++;
            } catch (StringIndexOutOfBoundsException e) {
                break;
            }
        }

        double percentCorrect = ((double)charsCorrect/ mCleanAnswer.length()) * 100;
        return (int) Math.round(percentCorrect);
    }

    private String cleanAnswer(String answer) {
        if (answer.length() == 0) return "";

        return answer.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }
}
