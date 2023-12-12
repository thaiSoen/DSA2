import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Message {
    private String content;

    public Message(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

class MessageQueue {
    private List<Message> messages = new ArrayList<>();
    private int front = 0;
    private int rear = -1;

    public void enqueue(Message message) {
        if (message != null) {
            messages.add(message);
            rear++;
        }
    }

    public Message dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        Message message = messages.get(front);
        messages.remove(front);
        rear--;
        return message;
    }

    public boolean isEmpty() {
        return front > rear;
    }

    public int size() {
        return rear - front + 1;
    }
}

class MessageStack {
    private List<Message> messages = new ArrayList<>();
    private int top = -1;

    public void push(Message message) {
        if (message != null) {
            messages.add(message);
            top++;
        }
    }
    public  Message get() {
        return messages.get(top);
    }
    public Message pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        Message message = messages.get(top);
        messages.remove(top);
        top--;
        return message;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }
}

class MessageSystem {
    private static void sendMessage(String userInput, MessageQueue messageQueue) {
        if (userInput == null || userInput.trim().isEmpty() || userInput.length() > 250) {
            System.out.println("Invalid message: Length should be between 1 and 250 characters.");
        } else {
            Message receivedMessage = new Message(userInput);
            messageQueue.enqueue(receivedMessage);
        }
    }

    private static void processMessages(MessageQueue messageQueue,MessageStack messageStack) {

        while (!messageQueue.isEmpty()) {
            Message dequeuedMessage = messageQueue.dequeue();
            System.out.println("Send message: " + dequeuedMessage.getContent());
            messageStack.push(dequeuedMessage);
            Message getMessage = messageStack.get();
            System.out.println("Received message: " + getMessage.getContent());
        }
    }



    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue();
        MessageStack messageStack = new MessageStack();
        Scanner scanner = new Scanner(System.in);
        for(int i = 1; i <= 3; i++){
            System.out.println("Enter a message (or type 'exit' to quit): ");
            String userInput = scanner.nextLine();
            sendMessage(userInput, messageQueue);
        }

        System.out.println("Number of messages in the queue: " + messageQueue.size());
        System.out.println("Number of messages in the stack: " + messageStack.size());
        processMessages(messageQueue, messageStack);

        scanner.close();
    }
}
