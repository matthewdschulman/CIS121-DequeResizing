public class DequeQuestions {
    enum Runtime {
        CONSTANT, LOGN, LINEAR, NLOGN, QUADRATIC, CUBIC, EXPONENTIAL, OTHER;
    }

    /**
     * What is the runtime of DequeLL.contains()?
     */
    public Runtime question1() {
        return Runtime.LINEAR;
    }

    /**
     * What is the runtime of DequeLL.offerBack()?
     */
    public Runtime question2() {
    	return Runtime.LINEAR;
    }

    /**
     * What is the runtime of DequeLL.pollFront()?
     */
    public Runtime question3() {
    	return Runtime.CONSTANT;
    }

    /**
     * What is the runtime of DequeResizing.contains()?
     */
    public Runtime question4() {
    	return Runtime.LINEAR;
    }

    /**
     * What is the runtime of DequeResizing.offerBack()?
     */
    public Runtime question5() {
    	return Runtime.CONSTANT;
    }

    /**
     * What is the runtime of DequeResizing.pollFront()?
     */
    public Runtime question6() {
    	return Runtime.CONSTANT;
    }
}
