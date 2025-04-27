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

    public static Node insertAfter(Node first, String nip, String nama) {
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

    public static Node insertBefore(Node first, String nip, String nama) {
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

    
}

public class Prak07_24051130002 {
    
}
