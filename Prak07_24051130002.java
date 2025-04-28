import java.util.Scanner;
import java.io.PrintStream;

class Node {
    public String nip;
    public String nama;
    public Node next;
    public Node previous;

    public Node(String nip, String nama) {
        this.nip = nip;
        this.nama = nama;
    }

    public void tampil() {
        System.out.println("NIP: " + nip + ", Nama: " + nama);
    }
}

class DoubleLink {
    private Node first;
    private Node last;

    public DoubleLink() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public boolean cekNIP(String nip) {
        Node current = first;
        while (current != null) {
            if (current.nip.equals(nip)) {
                System.out.println("NIP sudah ada!");
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void insertFirst(String nip, String nama) {
        if (cekNIP(nip)) {
            // System.out.println("NIP sudah ada, tidak bisa ditambahkan!");
            return;
        }
        Node newNode = new Node(nip, nama);
        if (isEmpty()) {
            last = newNode;
        } else {
            first.previous = newNode;
        }
        newNode.next = first;
        first = newNode;
    }

    public void insertLast(String nip, String nama) {
        if (cekNIP(nip)) {
            // System.out.println("NIP sudah ada, tidak bisa ditambahkan!");
            return;
        }
        Node newNode = new Node(nip, nama);
        if (isEmpty()) {
            first = newNode;
        } else {
            last.next = newNode;
            newNode.previous = last;
        }
        last = newNode;
    }

    public Node insertAfter(String nip, String nama) {
        Node current = first;
        while (current != null) {
            if (current.nip.equals(nip)) {
                break;
            }
            current = current.next;
        }
        if (current == null) {
            System.out.println("NIP tidak ditemukan!");
            return first;
        }
        Node newNode = new Node(nip, nama);
        newNode.previous = current;
        newNode.next = current.next;
        current.next = newNode;
        if (newNode.next != null) {
            newNode.next.previous = newNode;
        } 
        current.next = newNode;
        return first;
    }

    public Node insertBefore(String nip, String nama) {
        Node current = first;
        while (current != null) {
            if (current.nip.equals(nip)) {
                break;
            }
            current = current.next;
        }
        if (current == null) {
            System.out.println("NIP tidak ditemukan!");
            return first;
        }
        Node newNode = new Node(nip, nama);
        newNode.previous = current.previous;
        newNode.next = current;
        if (current.previous != null) {
            current.previous.next = newNode;
        } else {
            first = newNode;
        }
        current.previous = newNode;
        return first;
    }

    public Node deleteFirst() {
        if (isEmpty()) {
            System.out.println("List kosong!");
            return null;
        }
        Node temp = first;
        if (first.next == null) {
            last = null;
        } else {
            first.next.previous = null;
        }
        first = first.next;
        return temp;
    }

    public Node deleteLast() {
        if (isEmpty()) {
            System.out.println("List kosong!");
            return null;
        }
        Node temp = last;
        if (first.next == null) {
            first = null;
        } else {
            last.previous.next = null;
        }
        last = last.previous;
        return temp;
    }
}

public class prak07_24051130002 {

    // Validasi agar input NIM hanya angka
    public static String inputNIM(Scanner input) {
        String nim;
        while (true) {
            System.out.print("Masukkan NIM (angka saja): ");
            nim = input.nextLine();
            if (nim.matches("\\d+")) {
                break;
            } else {
                System.out.println("NIM harus berupa angka!");
            }
        }
        return nim;
    }

    // Validasi agar input Nama hanya huruf
    public static String inputNama(Scanner input) {
        String nama;
        while (true) {
            System.out.print("Masukkan Nama (huruf saja): ");
            nama = input.nextLine();
            if (nama.matches("[a-zA-Z\\s]+")) {
                break;
            } else {
                System.out.println("Nama harus berupa huruf!");
            }
        }
        return nama;
    }

    public static void main(String[] args) {
        DoublyLinkedList dll = new DoublyLinkedList();
        Scanner input = new Scanner(System.in);
        int choice;
        String nim, nama, searchNIM;

        do {
            System.out.println("\n=== Menu Doubly Linked List ===");
            System.out.println("1. Memasukkan data di awal List");
            System.out.println("2. Memasukkan data di akhir List");
            System.out.println("3. Memasukkan data setelah data tertentu");
            System.out.println("4. Menghapus data di awal List");
            System.out.println("5. Menghapus data di akhir List");
            System.out.println("6. Menghapus suatu data tertentu dari List");
            System.out.println("7. Menampilkan data dari awal List");
            System.out.println("8. Menampilkan data dari akhir List");
            System.out.println("9. Mencari data berdasarkan Nama");
            System.out.println("10. Ekspor data ke file");
            System.out.println("11. Impor data dari file");
            System.out.println("12. Keluar");
            System.out.print("Pilih menu (1-12): ");
            while (!input.hasNextInt()) {
                System.out.println("Input harus angka!");
                System.out.print("Pilih menu (1-12): ");
                input.next();
            }
            choice = input.nextInt();
            input.nextLine(); // Buang newline

            switch (choice) {
                case 1:
                    nim = inputNIM(input);
                    nama = inputNama(input);
                    dll.insertAtBeginning(nim, nama);
                    break;
                case 2:
                    nim = inputNIM(input);
                    nama = inputNama(input);
                    dll.insertAtEnd(nim, nama);
                    break;
                case 3:
                    System.out.print("Masukkan NIM setelah data mana? ");
                    searchNIM = input.nextLine();
                    nim = inputNIM(input);
                    nama = inputNama(input);
                    dll.insertAfter(searchNIM, nim, nama);
                    break;
                case 4:
                    dll.deleteAtBeginning();
                    break;
                case 5:
                    dll.deleteAtEnd();
                    break;
                case 6:
                    System.out.print("Masukkan NIM yang ingin dihapus: ");
                    nim = input.nextLine();
                    dll.deleteByNIM(nim);
                    break;
                case 7:
                    dll.displayFromHead();
                    break;
                case 8:
                    dll.displayFromTail();
                    break;
                case 9:
                    System.out.print("Masukkan Nama yang ingin dicari: ");
                    nama = input.nextLine();
                    dll.searchByName(nama);
                    break;
                case 10:
                    System.out.print("Masukkan nama file untuk ekspor (contoh: data.txt): ");
                    String exportFile = input.nextLine();
                    dll.exportToFile(exportFile);
                    break;
                case 11:
                    System.out.print("Masukkan nama file untuk impor (contoh: data.txt): ");
                    String importFile = input.nextLine();
                    dll.importFromFile(importFile);
                    break;
                case 12:
                    System.out.println("Terima kasih telah menggunakan program ini!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (choice != 12);

        input.close();
    }
}
