package messagesystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MessageSystem {

    static class Message {

        private String content;

        public Message(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }
    }

    static class MessageQueue {

        private List<Message> messages = new ArrayList<>();
        private int front = 0;
        private int rear = -1;

        public void enqueueMessage(Message message) {
            if (message != null) {
                messages.add(message);
                rear++;
            }
        }

        public Message dequeueMessage() {
            if (!isEmpty()) {
                Message message = messages.get(front);
                messages.remove(front);
                rear--;
                return message;
            } else {
                return null;
            }
        }

        public boolean isEmpty() {
            return front > rear;
        }

        public int size() {
            return rear - front + 1;
        }
    }

    static class MessageStack {

        private List<Message> messages = new ArrayList<>();
        private int top = -1;

        public void pushMessage(Message message) {
            if (message != null) {
                messages.add(message);
                top++;
            }
        }

        public Message popMessage() {
            if (!isEmpty()) {
                Message message = messages.get(top);
                messages.remove(top);
                top--;
                return message;
            } else {
                return null;
            }
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public int size() {
            return top + 1;
        }
    }

    private static void displayUserMessages(MessageStack messageStack) {
        
        if (messageStack.isEmpty()) {
            System.out.println("Khong co tin nhan nao de hien thi.");
        } else {
            while (!messageStack.isEmpty()) {
                Message message = messageStack.popMessage();
                System.out.println("Tin nhan cua nguoi dung: " + message.getContent());
            }
        }
        
    }

    private static void processUserMessage(String userInput, MessageQueue messageQueue, MessageStack messageStack) {
        if (userInput.length() <= 0) {
                        System.out.println("\nError!!!\n");
                    } else if (userInput.length() <= 250) {
            Message receivedMessage = new Message(userInput);
            messageQueue.enqueueMessage(receivedMessage);
            messageStack.pushMessage(receivedMessage);
            System.out.println("Tin nhan da nhan: " + userInput);
        } else {
            System.out.println("Invalid message: Tin nhan khong vuot qua 250 ky tu");
        }
    }

    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue();
        MessageStack messageStack = new MessageStack();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Nhap tin nhan (hoac an 'exit' de thoat): ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("exit")) {
                break;
            }
            processUserMessage(userInput, messageQueue, messageStack);
        }

        System.out.println("So tin nhan trong queue: " + messageQueue.size());
        System.out.println("So tin nhan trong stack: " + messageStack.size());
        displayUserMessages(messageStack);

        scanner.close();
    }
}
