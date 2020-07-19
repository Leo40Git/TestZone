package adudecalledleo.testzone.tests;

import adudecalledleo.testzone.TerminalInterface;

public class QuizTest extends Test {

    public static class Question {
        private final String question;
        private final int correctAnswer;
        private final String[] answers;

        public Question(String question, int correctAnswer, String... answers) {
            this.question = question;
            this.correctAnswer = correctAnswer;
            this.answers = answers;
        }

        public String getQuestion() {
            return question;
        }

        public int getCorrectAnswer() {
            return correctAnswer;
        }

        public String[] getAnswers() {
            return answers;
        }
    }

    public static final Question[] QUESTIONS = {
            new Question("Example - C is correct", 2, "A", "B", "C", "D")
    };

    @Override
    public String getName() {
        return "quiz";
    }

    @Override
    public void run(TerminalInterface ti) {
        int curQuestion = 0;
        while (curQuestion < QUESTIONS.length) {
            Question q = QUESTIONS[curQuestion];
            System.out.format("Question %d%n", curQuestion + 1);
            System.out.println(q.getQuestion());
            if (ti.promptList("Select answer:", true, 0, q.getAnswers()) == q.getCorrectAnswer()) {
                System.out.println("Correct answer!");
                curQuestion++;
            } else
                System.out.println("Incorrect answer!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
