import java.util.Scanner;

public class PengisianKRS {
        static final int MAX_DATA = 100; // Maksimal data KRS yang dapat disimpan
        static String[][] dataKRS = new String[MAX_DATA][5]; // Array 2D untuk menyimpan data KRS
        static int jumlahData = 0; // Jumlah data yang sudah diinput
        static Scanner scanner = new Scanner(System.in);
    
        public static void main(String[] args) {
            int pilihan;
            do {
                System.out.println("\n=== Sistem Pemantauan KRS Mahasiswa ===");
                System.out.println("1. Tambah Data KRS");
                System.out.println("2. Tampilkan Daftar KRS Mahasiswa");
                System.out.println("3. Analisis Data KRS");
                System.out.println("4. Keluar");
                System.out.print("Pilih menu: ");
                pilihan = scanner.nextInt();
                scanner.nextLine(); // Membersihkan buffer
    
                switch (pilihan) {
                    case 1:
                        tambahDataKRS();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        System.out.println("Keluar dari program.");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            } while (pilihan != 4);
        }
    
        static void tambahDataKRS() {
            if (jumlahData >= MAX_DATA) {
                System.out.println("Data KRS sudah penuh.");
                return;
            }
            
            System.out.println("--- Tambah Data KRS ---");

            System.out.print("Nama: ");
            String nama = scanner.nextLine();
            System.out.print("NIM: ");
            String nim = scanner.nextLine();
        
            String tambahLagi;
            do {
                if (jumlahData >= MAX_DATA) {
                    System.out.println("Data KRS sudah penuh.");
                    break;
                }
        
                System.out.print("Kode Mata Kuliah: ");
                String kodeMatkul = scanner.nextLine();
                System.out.print("Nama Mata Kuliah: ");
                String namaMatkul = scanner.nextLine();
                System.out.print("Jumlah SKS (1-3): ");
                int sks = scanner.nextInt();
        
                while (sks < 1 || sks > 3) {
                    System.out.println("Jumlah SKS harus antara 1-3.");
                    System.out.print("Masukkan kembali: ");
                    sks = scanner.nextInt();
                }
        
                scanner.nextLine(); // Membersihkan buffer
        
                int totalSKS = hitungTotalSKS(nim);
                if (totalSKS + sks > 24) {
                    System.out.println("Total SKS melebihi batas 24. Data tidak dapat ditambahkan.");
                } else {
                    dataKRS[jumlahData][0] = nama;
                    dataKRS[jumlahData][1] = nim;
                    dataKRS[jumlahData][2] = kodeMatkul;
                    dataKRS[jumlahData][3] = namaMatkul;
                    dataKRS[jumlahData][4] = Integer.toString(sks);
                    jumlahData++;
                    System.out.println("Data mata kuliah berhasil ditambahkan!");
                }
        
                System.out.print("Tambah mata kuliah lain? (y/t): ");
                tambahLagi = scanner.nextLine();
        
            } while (tambahLagi.equalsIgnoreCase("y"));
        
            System.out.println("Pengisian KRS selesai.");
        }        

    static int hitungTotalSKS(String nim) {
        int total = 0;
        for (int i = 0; i < jumlahData; i++) {
            if (dataKRS[i][1].equalsIgnoreCase(nim)) {
                total += Integer.parseInt(dataKRS[i][4]);
            }
        }
        return total;
    }

}