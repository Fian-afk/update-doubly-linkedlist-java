//KELOMPOK HARIMAU
//24051130010
//2405113002

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
            last = newNode;                 // last diarahkan ke newNode jika list kosong
        } else {
            first.previous = newNode;     // node pertama diarahkan dari newNode
        }
        newNode.next = first;           // newNode mengarah ke node pertama sebelumnya
        first = newNode;               // first diarahkan ke newNode
        System.out.print("Data berhasil ditambahkan di awal.");
    }

    public void insertLast(String nip, String nama) {
        if (cekNIP(nip)) {
            return;
        }
        Node newNode = new Node(nip, nama);
        if (isEmpty()) {
            first = newNode;    // first diarahkan ke newNode jika list koson
        } else {
            last.next = newNode;     // node terakhir sebelumnya mengarah ke newNode
            newNode.previous = last;   // newNode diarahkan dari node terakhir sebelumnya 
        }
        last = newNode; //last diarahkan ke newNode
        System.out.println("Data berhasil ditambahkan di akhir.");
    }

    public void insertAfter(String nipAcuan, String nipBaru, String namaBaru) {
        if (cekNIP(nipBaru)) return;
        Node current = first;
        while (current != null) {
            if (current.nip.equals(nipAcuan)) {
                Node newNode = new Node(nipBaru, namaBaru);
                newNode.next = current.next;    // newNode mengarah ke node setelah current
                newNode.previous = current;     // newNode diarahkan dari current
                if (current.next != null) {
                    current.next.previous = newNode;    // node setelah current diarahkan dari newNode
                } else {
                    last = newNode;     // last diarahkan ke newNode jika current adalah node terakhir
                }
                current.next = newNode;     // current mengarah ke newNode
                System.out.println("Data berhasil ditambahkan setelah NIP "+ nipAcuan);
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
                newNode.next = current;                        // newNode mengarah ke current
                newNode.previous = current.previous;           // newNode diarahkan dari node sebelum current
                if (current.previous != null) {
                    current.previous.next = newNode;           // node sebelum current mengarah ke newNode
                } else {
                    first = newNode;                           // first diarahkan ke newNode jika current adalah node pertama
                }
                current.previous = newNode;                    // current diarahkan dari newNode
                System.out.println("Data berhasil ditambahkan sebelum NIP " + nipAcuan);
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
            last = null;                                       // last dihapus jika hanya ada satu node
        } else {
            first.next.previous = null;                        // node kedua tidak lagi diarahkan dari node pertama
        }
        first = first.next;                                    // first diarahkan ke node kedua
        System.out.println("Data berhasil dihapus di awal.");
        return temp;
    }    

    public Node deleteLast() {
        if (isEmpty()) {
            System.out.println("List kosong!");
            return null;
        }
        Node temp = last;
        if (first.next == null) {
            first = null;                                      // first dihapus jika hanya ada satu node
        } else {
            last.previous.next = null;                         // node sebelum last tidak lagi mengarah ke last
        }
        last = last.previous;                                  // last diarahkan ke node sebelumnya
        System.out.println("Data berhasil dihapus di akhir.");
        return temp;
    }
    

    public Node deleteByNIP(Scanner sc, String nip) {
        if (isEmpty()) {
            System.out.println("Belum ada data.");
            return null;
        }
        
        String confirm;
        do {
            System.out.print("Yakin ingin menghapus data terakhir? (y/n): ");
            confirm = sc.next();
            if (confirm.equalsIgnoreCase("y")) {
                Node temp = last;
                if (last.previous == null) {
                    first = null;
                } else {
                    last.previous.next = null;
                }
                last = last.previous;
                System.out.println("Data berhasil dihapus.");
                return temp;
            } else if (confirm.equalsIgnoreCase("n")) {
                System.out.println("Penghapusan dibatalkan.");
                return null;
            } else {
                System.out.println("Input tidak valid. Masukkan 'y' untuk ya atau 'n' untuk tidak.");
            }
        } while (true);
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
            System.out.print("Masukkan NIP Dosen: ");
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
            System.out.print("Masukkan Nama Dosen: ");
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
            System.out.println("\n=== DATA DOSEN ===");
            System.out.println("1. Tambah data di awal");
            System.out.println("2. Tambah data di akhir");
            System.out.println("3. Tambah data setelah NIP tertentu");
            System.out.println("4. Tambah data sebelum NIP tertentu");
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
                    String nipAwal = inputNIP(sc);
                    String namaAwal = inputNama(sc);
                    list.insertFirst(nipAwal, namaAwal);
                    break;
                case 2:
                    String nipAkhir = inputNIP(sc);
                    String namaAkhir = inputNama(sc);
                    list.insertLast(nipAkhir, namaAkhir);
                    break;
                case 3:
                    System.out.print("Masukkan NIP acuan: ");
                    String acuan1 = sc.nextLine();
                    String nipBaru1 = inputNIP(sc);
                    String namaBaru1 = inputNama(sc);
                    list.insertAfter(acuan1, nipBaru1, namaBaru1);
                    break;
                case 4:
                    System.out.print("Masukkan NIP acuan: ");
                    String acuan2 = sc.nextLine();
                    String nipBaru2 = inputNIP(sc);
                    String namaBaru2 = inputNama(sc);
                    list.insertBefore(acuan2, nipBaru2, namaBaru2);
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
