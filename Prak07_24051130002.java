//KELOMPOK HARIMAU
//24051130010
//24051130002

//Menu
// 1. Tambah data di awal
// 2. Tambah data di akhir
// 3. Tambah data setelah id tertentu
// 4. Tambah data sebelum id tertentu
// 5. Tampilkan maju
// 6. Tampilkan mundur
// 7. Cari judul
// 8. Hapus data pertama
// 9. Hapus data terakhir
// 10. Hapus data berdasarkan id
// 11. Export data ke file
// 12. Import data dari file
// 0. Keluar


import java.util.Scanner;
import java.io.*;

class Node {
    public String id;
    public String judul;
    public Node next;
    public Node previous;

    public Node(String id, String judul) {
        this.id = id;
        this.judul = judul;
    }

    public void tampil() {
        System.out.println("id: " + id + ", judul: " + judul);
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

    public boolean cekid(String id) {
        Node current = first;
        while (current != null) {
            if (current.id.equals(id)) {
                System.out.println("id sudah ada!");
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void insertFirst(String id, String judul) {
        if (cekid(id)) {
            return;
        }
        Node newNode = new Node(id, judul);
        if (isEmpty()) {
            last = newNode;                 // last diarahkan ke newNode jika list kosong
        } else {
            first.previous = newNode;     // node pertama diarahkan dari newNode
        }
        newNode.next = first;           // newNode mengarah ke node pertama sebelumnya
        first = newNode;               // first diarahkan ke newNode
        System.out.print("Data berhasil ditambahkan di awal.");
    }

    public void insertLast(String id, String judul, boolean tampilPesan) {
        if (cekid(id)) {
            return;
        }
        Node newNode = new Node(id, judul);
        if (isEmpty()) {
            first = newNode;    // first diarahkan ke newNode jika list koson
        } else {
            last.next = newNode;     // node terakhir sebelumnya mengarah ke newNode
            newNode.previous = last;   // newNode diarahkan dari node terakhir sebelumnya 
        }
        last = newNode; //last diarahkan ke newNode
        if (tampilPesan) {
            System.out.println("Data berhasil ditambahkan di akhir.");
        }
    }

    public void insertAfter(String idAcuan, String idBaru, String judulBaru) {
        if (cekid(idBaru)) return;
        Node current = first;
        while (current != null) {
            if (current.id.equals(idAcuan)) {
                Node newNode = new Node(idBaru, judulBaru);
                newNode.next = current.next;    // newNode mengarah ke node setelah current
                newNode.previous = current;     // newNode diarahkan dari current
                if (current.next != null) {
                    current.next.previous = newNode;    // node setelah current diarahkan dari newNode
                } else {
                    last = newNode;     // last diarahkan ke newNode jika current adalah node terakhir
                }
                current.next = newNode;     // current mengarah ke newNode
                System.out.println("Data berhasil ditambahkan setelah id "+ idAcuan);
                return;
            }
            current = current.next;
        }
        System.out.println("id acuan tidak ditemukan!");
    }

    public void insertBefore(String idAcuan, String idBaru, String judulBaru) {
        if (cekid(idBaru)) return;
        Node current = first;
        while (current != null) {
            if (current.id.equals(idAcuan)) {
                Node newNode = new Node(idBaru, judulBaru);
                newNode.next = current;                        // newNode mengarah ke current
                newNode.previous = current.previous;           // newNode diarahkan dari node sebelum current
                if (current.previous != null) {
                    current.previous.next = newNode;           // node sebelum current mengarah ke newNode
                } else {
                    first = newNode;                           // first diarahkan ke newNode jika current adalah node pertama
                }
                current.previous = newNode;                    // current diarahkan dari newNode
                System.out.println("Data berhasil ditambahkan sebelum id " + idAcuan);
                return;
            }
            current = current.next;
        }
        System.out.println("id acuan tidak ditemukan!");
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
    

    public Node deleteByid(Scanner sc, String id) {
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

    public void carijudul(String judul) {
        Node current = first;
        boolean found = false;
        while (current != null) {
            if (current.judul.equalsIgnoreCase(judul)) {
                
                System.out.println("ID Buku: " + current.id + ", Judul Buku: " + current.judul);
                found = true;
                
            }
            current = current.next;
            
        }
        if (!found) {
            System.out.println("judul tidak ditemukan!");
        }
    }

    public void exportFile(String judulfile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(judulfile))) {
            Node current = first;
            while (current != null) {
                writer.write(current.id + "," + current.judul);
                writer.newLine();
                current = current.next;
            }
            System.out.println("Data berhasil diekspor ke " + judulfile);
        } catch (IOException e) {
            System.out.println("Error saat menulis ke file: " + e.getMessage());
        }
    }
    
    public void importFile(String judulfile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(judulfile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String id = parts[0].trim();
                    String judul = parts[1].trim();
                    insertLast(id, judul, false);
                }
            }
            System.out.println("Data berhasil diimpor dari " + judulfile);
        } catch (IOException e) {
            System.out.println("Error saat membaca file: " + e.getMessage());
        }
    }
}

public class Prak07_24051130002 {
    public static String inputid(Scanner input) {
        String id;
        while (true) {
            System.out.print("Masukkan ID Buku: ");
            id = input.nextLine();
            if (id.matches("\\d+")) {
                break;
            } else {
                System.out.println("id harus berupa angka!");
            }
        }
        return id;
    }

    public static String inputjudul(Scanner input) {
        String judul;
        while (true) {
            System.out.print("Masukkan judul Buku: ");
            judul = input.nextLine();
            if (judul.matches("[a-zA-Z\\s]+")) {
                break;
            } else {
                System.out.println("judul harus berupa huruf!");
            }
        }
        return judul;
    }

    public static void main(String[] args) {
        DoubleLink list = new DoubleLink();
        Scanner sc = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n=== DATA BUKU PERSPUSTAKAAN ===");
            System.out.println("1. Tambah data buku di awal");
            System.out.println("2. Tambah data buku di akhir");
            System.out.println("3. Tambah data buku setelah id tertentu");
            System.out.println("4. Tambah data buku sebelum id tertentu");
            System.out.println("5. Tampilkan maju");
            System.out.println("6. Tampilkan mundur");
            System.out.println("7. Cari judul");
            System.out.println("8. Hapus data pertama");
            System.out.println("9. Hapus data terakhir");
            System.out.println("10. Hapus data berdasarkan id");
            System.out.println("11. Export data ke file");
            System.out.println("12. Import data dari file");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = sc.nextInt();
            sc.nextLine(); // Buang newline

            switch (pilihan) {
                case 1:
                    String idAwal = inputid(sc);
                    String judulAwal = inputjudul(sc);
                    list.insertFirst(idAwal, judulAwal);
                    break;
                case 2:
                    String idAkhir = inputid(sc);
                    String judulAkhir = inputjudul(sc);
                    list.insertLast(idAkhir, judulAkhir, true);
                    break;
                case 3:
                    System.out.print("Masukkan id acuan: ");
                    String acuan1 = sc.nextLine();
                    String idBaru1 = inputid(sc);
                    String judulBaru1 = inputjudul(sc);
                    list.insertAfter(acuan1, idBaru1, judulBaru1);
                    break;
                case 4:
                    System.out.print("Masukkan id acuan: ");
                    String acuan2 = sc.nextLine();
                    String idBaru2 = inputid(sc);
                    String judulBaru2 = inputjudul(sc);
                    list.insertBefore(acuan2, idBaru2, judulBaru2);
                    break;
                case 5:
                    list.tampilMaju();
                    break;
                case 6:
                    list.tampilMundur();
                    break;
                case 7:
                    System.out.print("Masukkan judul yang dicari: ");
                    String carijudul = sc.nextLine();
                    list.carijudul(carijudul);
                    break;
                case 8:
                    list.deleteFirst();
                    break;
                case 9:
                    list.deleteLast();
                    break;
                case 10:
                    System.out.print("Masukkan id yang ingin dihapus: ");
                    String idHapus = sc.nextLine();
                    list.deleteByid(sc, idHapus);
                    break;
                case 11:
                    System.out.print("Masukkan judul file untuk export (cth: data.txt): ");
                    String judulFileExport = sc.nextLine();
                    list.exportFile(judulFileExport);
                    break;
                case 12:
                    System.out.print("Masukkan judul file untuk import (cth: data.txt): ");
                    String judulFileImport = sc.nextLine();
                    list.importFile(judulFileImport);
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
