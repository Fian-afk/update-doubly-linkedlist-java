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

    public void insertAfter(String nipAcuan, String nipBaru, String namaBaru) {
        if (cekNIP(nipBaru)) return;
        Node current = first;
        while (current != null) {
            if (current.nip.equals(nipAcuan)) {
                Node newNode = new Node(nipBaru, namaBaru);
                newNode.next = current.next;
                newNode.previous = current;
                if (current.next != null) {
                    current.next.previous = newNode;
                } else {
                    last = newNode;
                }
                current.next = newNode;
                return;
            }
            current = current.next;
        }
        System.out.println("NIP acuan tidak ditemukan!");
    }

    public void insertBefore(String nipAcuan, String nipBaru, String namaBaru) {
        if (cekNIP(nipBaru)) return;
        Node current = first;
        while (current != null) {
            if (current.nip.equals(nipAcuan)) {
                Node newNode = new Node(nipBaru, namaBaru);
                newNode.next = current;
                newNode.previous = current.previous;
                if (current.previous != null) {
                    current.previous.next = newNode;
                } else {
                    first = newNode;
                }
                current.previous = newNode;
                return;
            }
            current = current.next;
        }
        System.out.println("NIP acuan tidak ditemukan!");
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

    public Node deleteByNIP(Scanner sc, String nip) {
        String confirm;
        do {
            System.out.print("Apakah Anda yakin ingin menghapus data dengan NIP " + nip + "? (y/n): ");
            confirm = sc.nextLine().toLowerCase();
            if (confirm.equalsIgnoreCase("y")) {
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
            }else if (confirm.equalsIgnoreCase("n")) {
                System.out.println("Penghapusan dibatalkan.");
                return first;
            } else {
                System.out.println("Pilihan tidak valid! Silakan masukkan 'y' atau 'n'.");
            }
            
        }while (true);
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

public class Prak07_24051130002 {
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
        DoubleLink list = new DoubleLink();
        Scanner sc = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n=== MENU DOUBLY LINKED LIST ===");
            System.out.println("1. Tambah data di awal");
            System.out.println("2. Tambah data di akhir");
            System.out.println("3. Tambah data setelah NIP tertentu");   // <- insertAfter
            System.out.println("4. Tambah data sebelum NIP tertentu");   // <- insertBefore
            System.out.println("5. Tampilkan maju");
            System.out.println("6. Tampilkan mundur");
            System.out.println("7. Cari nama");
            System.out.println("8. Hapus data pertama");
            System.out.println("9. Hapus data terakhir");
            System.out.println("10. Hapus data berdasarkan NIP");
            System.out.println("11. Export data ke file");
            System.out.println("12. Import data dari file");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = sc.nextInt();
            sc.nextLine(); // Buang newline

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan NIP: ");
                    String nipAwal = sc.nextLine();
                    System.out.print("Masukkan Nama: ");
                    String namaAwal = sc.nextLine();
                    list.insertFirst(nipAwal, namaAwal);
                    break;
                case 2:
                    System.out.print("Masukkan NIP: ");
                    String nipAkhir = sc.nextLine();
                    System.out.print("Masukkan Nama: ");
                    String namaAkhir = sc.nextLine();
                    list.insertLast(nipAkhir, namaAkhir);
                    break;
                    case 3: // insertAfter
                    System.out.print("Masukkan NIP yang ingin disisipkan setelahnya: ");
                    String nipSetelah = sc.nextLine();
                    System.out.print("Masukkan NIP baru: ");
                    String nipBaruSetelah = sc.nextLine();
                    System.out.print("Masukkan Nama baru: ");
                    String namaBaruSetelah = sc.nextLine();
                    list.insertAfter(nipSetelah, namaBaruSetelah);
                    break;
                case 4: // insertBefore
                    System.out.print("Masukkan NIP yang ingin disisipkan sebelumnya: ");
                    String nipSebelum = sc.nextLine();
                    System.out.print("Masukkan NIP baru: ");
                    String nipBaruSebelum = sc.nextLine();
                    System.out.print("Masukkan Nama baru: ");
                    String namaBaruSebelum = sc.nextLine();
                    list.insertBefore(nipSebelum, namaBaruSebelum);
                    break;
                case 5:
                    list.tampilMaju();
                    break;
                case 6:
                    list.tampilMundur();
                    break;
                case 7:
                    System.out.print("Masukkan nama yang dicari: ");
                    String cariNama = sc.nextLine();
                    list.cariNama(cariNama);
                    break;
                case 8:
                    list.deleteFirst();
                    break;
                case 9:
                    list.deleteLast();
                    break;
                case 10:
                    System.out.print("Masukkan NIP yang ingin dihapus: ");
                    String nipHapus = sc.nextLine();
                    list.deleteByNIP(sc, nipHapus);
                    break;
                case 11:
                    System.out.print("Masukkan nama file untuk export (cth: data.txt): ");
                    String namaFileExport = sc.nextLine();
                    list.exportFile(namaFileExport);
                    break;
                case 12:
                    System.out.print("Masukkan nama file untuk import (cth: data.txt): ");
                    String namaFileImport = sc.nextLine();
                    list.importFile(namaFileImport);
                    break;
                case 0:
                    System.out.println("Terima kasih. Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Coba lagi.");
            }            
        } while (pilihan != 0);

        sc.close();
    }
}
