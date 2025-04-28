import java.util.Scanner;
import java.io.*;

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

    public Node deleteByNIP(String nip) {
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
        if (current == first) {
            deleteFirst();
        } else if (current == last) {
            deleteLast();
        } else {
            current.previous.next = current.next;
            current.next.previous = current.previous;
        }
        return first;
    }

    public void tampilMaju() {
        Node current = first;
        if (isEmpty()) {
            System.out.println("List kosong!");
            return;
        }
        while (current != null) {
            current.tampil();
            current = current.next;
        }
    }

    public void tampilMundur() {
        Node current = last;
        if (isEmpty()) {
            System.out.println("List kosong!");
            return;
        }
        while (current != null) {
            current.tampil();
            current = current.previous;
        }
    }

    public void cariNama(String nama) {
        Node current = first;
        boolean found = false;
        while (current != null) {
            if (current.nama.equalsIgnoreCase(nama)) {
                System.out.println("NIP: " + current.nip + ", Nama: " + current.nama);
                found = true;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("Nama tidak ditemukan!");
        }
    }

    public void exportFile(String namafile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(namafile))) {
            Node current = first;
            while (current != null) {
                writer.write(current.nip + "," + current.nama);
                writer.newLine();
                current = current.next;
            }
            System.out.println("Data berhasil diekspor ke " + namafile);
        } catch (IOException e) {
            System.out.println("Error saat menulis ke file: " + e.getMessage());
        }
    }
    
    public void importFile(String namafile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(namafile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String nip = parts[0].trim();
                    String nama = parts[1].trim();
                    insertLast(nip, nama);
                }
            }
            System.out.println("Data berhasil diimpor dari " + namafile);
        } catch (IOException e) {
            System.out.println("Error saat membaca file: " + e.getMessage());
        }
    }
}

public class prak07_24051130002 {
    public static String inputNIP(Scanner input) {
        String nip;
        while (true) {
            System.out.print("Masukkan NIP (angka saja): ");
            nip = input.nextLine();
            if (nip.matches("\\d+")) {
                break;
            } else {
                System.out.println("NIP harus berupa angka!");
            }
        }
        return nip;
    }

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
        Scanner input = new Scanner(System.in);
        DoubleLink dll = new DoubleLink();
        int choice;
        String nip, nama, searchNIP;

        do {
            System.out.println("\n=== Menu Doubly Linked List ===");
            System.out.println("1. Masukkan data di awal List");
            System.out.println("2. Masukkan data di akhir List");
            System.out.println("3. Masukkan data setelah NIP tertentu");
            System.out.println("4. Masukkan data sebelum NIP tertentu");
            System.out.println("5. Hapus data pertama");
            System.out.println("6. Hapus data terakhir");
            System.out.println("7. Tampilkan data dari awal");
            System.out.println("8. Tampilkan data dari akhir");
            System.out.println("9. Keluar");
            System.out.print("Pilihan Anda (1-9): ");
            while (!input.hasNextInt()) {
                System.out.println("Input harus angka!");
                System.out.print("Pilihan Anda (1-9): ");
                input.next();
            }
            choice = input.nextInt();
            input.nextLine(); // buang enter

            switch (choice) {
                case 1:
                    nip = inputNIP(input);
                    nama = inputNama(input);
                    dll.insertFirst(nip, nama);
                    break;
                case 2:
                    nip = inputNIP(input);
                    nama = inputNama(input);
                    dll.insertLast(nip, nama);
                    break;
                case 3:
                    System.out.print("Masukkan NIP yang dicari (insert setelah): ");
                    searchNIP = input.nextLine();
                    nip = inputNIP(input);
                    nama = inputNama(input);
                    dll.insertAfter(searchNIP, nama);
                    break;
                case 4:
                    System.out.print("Masukkan NIP yang dicari (insert sebelum): ");
                    searchNIP = input.nextLine();
                    nip = inputNIP(input);
                    nama = inputNama(input);
                    dll.insertBefore(searchNIP, nama);
                    break;
                case 5:
                    dll.deleteFirst();
                    break;
                case 6:
                    dll.deleteLast();
                    break;
                case 7:
                    System.out.println("Data dari awal:");
                    Node current = dll.getFirst();
                    while (current != null) {
                        current.tampil();
                        current = current.next;
                    }
                    break;
                case 8:
                    System.out.println("Data dari akhir:");
                    Node cur = dll.getLast();
                    while (cur != null) {
                        cur.tampil();
                        cur = cur.previous;
                    }
                    break;
                case 9:
                    System.out.println("Terima kasih telah menggunakan program ini.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }

        } while (choice != 9);

        input.close();
    }
}
