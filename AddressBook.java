import java.io.*;
import java.util.*;

public class AddressBook {
    static final String FILE_NAME = "contacts.txt";

    static class Contact {
        String name;
        String phone;
        String email;
        String date_of_birth;

        Contact(String name, String phone, String email,String date_of_birth) {
            this.name = name;
            this.phone = phone;
            this.email = email;
            this.date_of_birth=date_of_birth;
        }

        @Override
        public String toString() {
            return "Name: " + name + "\nPhone: " + phone + "\nEmail: " + email + "\n date_of_birth :" +date_of_birth;
        }
    }

    public static void addContact() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        System.out.println("Enter Your Birth Date :");
        String date_of_birth=sc.nextLine();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(name + "," + phone + "," + email + "," + date_of_birth);
            bw.newLine();
            System.out.println("Contact added successfully!\n");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }

    public static void viewContacts() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean empty = true;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    Contact c = new Contact(parts[0], parts[1], parts[2],parts[3]);
                    System.out.println(c);
                    empty = false;
                }
            }
            if (empty) System.out.println("No contacts found.");
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    public static void searchContact() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter name to search: ");
        String searchName = sc.nextLine().toLowerCase();
        boolean found = false;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4 && parts[0].toLowerCase().contains(searchName)) {
                    Contact c = new Contact(parts[0], parts[1], parts[2],parts[3]);
                    System.out.println(c);
                    found = true;
                }
            }
            if (!found) System.out.println("Contact not found.");
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("===== Address Book Menu =====");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Search Contact");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addContact();
                    break;
                case 2:
                    viewContacts();
                    break;
                case 3:
                    searchContact();
                    break;
                case 4:
                    System.out.println("Exiting Address Book.");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }

        } while (choice != 4);
    }
}
