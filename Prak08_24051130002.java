//KELOMPOK HARIMAU
//24051130010
//24051130002
//halo

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
// 13. Update data buku
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
        Node current = first; // Mulai dari node pertama
        while (current != null) { // Selama list tidak kosong
            // Cek apakah id sudah ada di dalam list
            if (current.id.equals(id)) {
                System.out.println("id sudah ada!");
                return true; // id sudah ada, kembalikan true
            }
            current = current.next; // Pindah ke node berikutnya
        }
        return false; // id tidak ditemukan, kembalikan false
    }

    public void insertFirst(String id, String judul) {
        if (cekid(id)) { // Cek apakah id sudah ada
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
        if (cekid(id)) { // Cek apakah id sudah ada
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
        if (cekid(idBaru)) return; // Cek apakah id sudah ada
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
        if (cekid(idBaru)) return; // Cek apakah id sudah ada
        Node current = first; // Mulai dari node pertama
        while (current != null) { // Selama list tidak kosong
            if (current.id.equals(idAcuan)) { // Jika id acuan ditemukan
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
            current = current.next; // Pindah ke node berikutnya
        }
        System.out.println("id acuan tidak ditemukan!");
    }
    
    public Node deleteFirst() {
        if (isEmpty()) { // Cek apakah list kosong
            System.out.println("Belum ada data.");
            return null; // Jika kosong, kembalikan null
        }
        Node temp = first; // Simpan node pertama untuk dikembalikan
        if (first.next == null) {
            last = null;                                       // last dihapus jika hanya ada satu node
        } else {
            first.next.previous = null;                        // node kedua tidak lagi diarahkan dari node pertama
        }
        first = first.next;                                    // first diarahkan ke node kedua
        System.out.println("Data berhasil dihapus di awal.");
        return temp;                                   // Kembalikan node yang dihapus
    }    

    public Node deleteLast() {
        if (isEmpty()) { // Cek apakah list kosong
            System.out.println("Belum ada data.");
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
        if (isEmpty()) { // Cek apakah list kosong
            System.out.println("Belum ada data.");
            return null;
        }
        
        String confirm;
        do {
            System.out.print("Yakin ingin menghapus data terakhir? (y/n): ");
            confirm = sc.next(); // Minta konfirmasi dari pengguna
            if (confirm.equalsIgnoreCase("y")) { // Jika pengguna setuju
                Node temp = last; // Simpan node terakhir untuk dikembalikan
                if (last.previous == null) { // Jika hanya ada satu node
                    first = null;               // first dihapus
                } else {
                    last.previous.next = null; // node sebelum last tidak lagi mengarah ke last
                }
                last = last.previous;         // last diarahkan ke node sebelumnya
                System.out.println("Data berhasil dihapus.");
                return temp;          // Kembalikan node yang dihapus
            } else if (confirm.equalsIgnoreCase("n")) { // Jika pengguna tidak setuju
                System.out.println("Penghapusan dibatalkan."); 
                return null; // Kembalikan null
            } else { // Jika input tidak valid
                System.out.println("Input tidak valid. Masukkan 'y' untuk ya atau 'n' untuk tidak.");
            }
        } while (true); // Ulangi sampai input valid
    }

    public void tampilMaju() {
        Node current = first; // Mulai dari node pertama
        if (isEmpty()) { // Cek apakah list kosong
            System.out.println("Belum ada data");
            return;
        }
        while (current != null) { // Selama list tidak kosong
            current.tampil(); // Tampilkan data node saat ini
            current = current.next; // menampilkan node berikutnya
        }
    }

    public void tampilMundur() {
        Node current = last; // Mulai dari node terakhir
        if (isEmpty()) { // Cek apakah list kosong
            System.out.println("Belum ada data");
            return; // Jika kosong, kembalikan null
        }
        while (current != null) { // Selama list tidak kosong
            current.tampil(); // Tampilkan data node saat ini
            current = current.previous; // menampilkan node sebelumnya
        }
    }

    public void carijudul(String judul) {
        Node current = first;
        boolean found = false;
        while (current != null) {
            if (current.judul.toLowerCase().contains(judul.toLowerCase())) {
                if (!found) { // Jika judul ditemukan untuk pertama kali
                    System.out.println("Judul ditemukan: ");
                    found = true; // Set found menjadi true
                }
                System.out.println("ID Buku: " + current.id + ", Judul Buku: " + current.judul); 
            }
            current = current.next; // Pindah ke node berikutnya

            if (!found) { // Jika judul tidak ditemukan sama sekali
                System.out.println("judul tidak ditemukan!");
            }
        }
    }

    public void exportFile(String judulfile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(judulfile))) { // Gunakan BufferedWriter untuk menulis ke file
            Node current = first; // Mulai dari node pertama
            while (current != null) { // Selama list tidak kosong
                writer.write(current.id + "," + current.judul); // Tulis id dan judul ke file
                writer.newLine(); // Pindah ke baris baru
                current = current.next; // Pindah ke node berikutnya
            }
            System.out.println("Data berhasil diekspor ke " + judulfile);
        } catch (IOException e) { // Tangani exception jika terjadi kesalahan saat menulis ke file
            System.out.println("Error saat menulis ke file: " + e.getMessage());
        }
    }
    
    public void importFile(String judulfile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(judulfile))) {
            String line;
            while ((line = reader.readLine()) != null) { // Baca setiap baris dari file
                String[] parts = line.split(","); // Pisahkan id dan judul berdasarkan koma
                if (parts.length == 2) {
                    String id = parts[0].trim(); // Ambil id dari bagian pertama
                    String judul = parts[1].trim(); // Ambil judul dari bagian kedua
                    insertLast(id, judul, false); // Tambahkan node baru ke akhir list
                }
            }
            System.out.println("Data berhasil diimpor dari " + judulfile);
        } catch (IOException e) {
            System.out.println("Error saat membaca file: " + e.getMessage()); // Tangani exception jika terjadi kesalahan saat membaca file
        }
    }

    public Node updateNode(String id, String judul) {
        Node current = first; // Mulai dari node pertama
        while (current != null) { // Selama list tidak kosong
            if (current.id.equals(id)) { // Jika id ditemukan
                current.judul = judul; // Update judul
                return current; // Kembalikan node yang diupdate
            }
            current = current.next; // Pindah ke node berikutnya
            System.out.println("Data berhasil diupdate.");
        }
        System.out.println("ID tidak ditemukan!"); // Jika id tidak ditemukan, tampilkan pesan
        // Kembalikan null jika id tidak ditemukan
        return null;
    }

}

public class Prak08_24051130002 {
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

    public static String inputjudul(Scanner input, String pesan) {
        String judul;
        while (true) {
            System.out.print(pesan); // pakai pesan custom di sini
            judul = input.nextLine();
            if (judul.matches("[a-zA-Z\\s\\-]+")) {
                break;
            } else {
                System.out.println("Judul harus berupa huruf!");
            }
        }
        return judul;
    }    

        // Versi default untuk kompatibilitas
    public static String inputjudul(Scanner input) {
        return inputjudul(input, "Masukkan judul Buku: ");
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
            System.out.println("13. Update data buku");
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
                case 13:
                    System.out.print("Masukkan id buku yang ingin diupdate: ");
                    String idUpdate = sc.nextLine();
                    String judulUpdate = inputjudul(sc, "Masukkan judul buku(update):");
                    list.updateNode(idUpdate, judulUpdate);
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
