public class ShuntingYardQuestions {
    enum Runtime {
        CONSTANT, LINEAR, QUADRATIC, CUBIC, LOGN, NLOGN, EXPONENTIAL
    }

    /**
     * What is the runtime of your infix to postfix (shunting yard) algorithm
     * based on a dynamic array implementation of a stack (as presented in
     * class)?
     * 
     * @return answer
     */
    public Runtime question1() {
        return Runtime.LINEAR;
    }

    /**
     * What is the runtime of your infix to postfix (shunting yard) algorithm
     * based on a linked list implementation of a stack?
     * 
     * @return answer
     */
    public Runtime question2() {
        return Runtime.EXPONENTIAL;
    }

    /**
     * (True/False) Given a valid input, the following stack can occur during
     * the shunting-yard algorithm. * + -
     */
    public Boolean question3() {
        return false;
    }

    /**
     * (True/False) Given a valid input, the following stack can occur during
     * the shunting-yard algorithm. ^ ^ ^
     */
    public Boolean question4() {
        return true;
    }
}
