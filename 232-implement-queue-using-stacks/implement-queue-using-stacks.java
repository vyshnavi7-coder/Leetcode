class MyQueue {
    private int[] input;
    private int[] output;
    private int topInput;
    private int topOutput;

    public MyQueue() {
        input = new int[100];
        output = new int[100];
        topInput = -1;
        topOutput = -1;
    }
    
    private void resize(boolean isInput) {
        if (isInput) {
            int[] newArr = new int[input.length * 2];
            System.arraycopy(input, 0, newArr, 0, input.length);
            input = newArr;
        } else {
            int[] newArr = new int[output.length * 2];
            System.arraycopy(output, 0, newArr, 0, output.length);
            output = newArr;
        }
    }

    public void push(int x) {
        if (topInput == input.length - 1) {
            resize(true);
        }
        input[++topInput] = x;
    }
    
    public int pop() {
        peek();
        return output[topOutput--];
    }
    
    public int peek() {
        if (topOutput == -1) {
            while (topInput >= 0) {
                if (topOutput == output.length - 1) {
                    resize(false);
                }
                output[++topOutput] = input[topInput--];
            }
        }
        return output[topOutput];
    }
    
    public boolean empty() {
        return topInput == -1 && topOutput == -1;
    }
}
