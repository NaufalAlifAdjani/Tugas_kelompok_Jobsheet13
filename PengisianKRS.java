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
                        tampilkanKRS();
                        break;
                    case 3:
                        // analisisSKS();
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

    static void tampilkanKRS() {
        System.out.print("Masukkan NIM: ");
        String nim = scanner.nextLine();
        int totalSKS = 0;
        boolean ditemukan = false;

        System.out.println("\nKRS Mahasiswa dengan NIM: " + nim);
        for (int i = 0; i < jumlahData; i++) {
            if (dataKRS[i][1].equalsIgnoreCase(nim)) {
                System.out.println("Kode Matkul: " + dataKRS[i][2] + ", Nama Matkul: " + dataKRS[i][3] +
                        ", SKS: " + dataKRS[i][4]);
                totalSKS += Integer.parseInt(dataKRS[i][4]);
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Data KRS tidak ditemukan.");
        } else {
            System.out.println("Total SKS: " + totalSKS);
        }
    }

    static void analisisSKS() {
        if (jumlahData == 0) {
            System.out.println("Belum ada data KRS yang ditambahkan.");
            return;
        }
    
        System.out.println("\n--- Analisis Data KRS ---");
        String[] mahasiswaSKS = new String[MAX_DATA];
        int jumlahMahasiswa = 0;
    
        for (int i = 0; i < jumlahData; i++) {
            String nim = dataKRS[i][1];
            if (hitungTotalSKS(nim) < 20 && !sudahTercatat(mahasiswaSKS, nim, jumlahMahasiswa)) {
                mahasiswaSKS[jumlahMahasiswa] = nim;
                jumlahMahasiswa++;
            }
        }
    
        if (jumlahMahasiswa == 0) {
            System.out.println("Tidak ada mahasiswa dengan total SKS kurang dari 20.");
        } else {
            System.out.println("Jumlah mahasiswa dengan SKS kurang dari 20: " + jumlahMahasiswa);
            System.out.println("\nData Mahasiswa dengan SKS Kurang dari 20:");
            System.out.printf("%-15s %-15s %-15s %-30s %-5s\n", "Nama", "NIM", "Kode Matkul", "Nama Matkul", "SKS");
            for (int i = 0; i < jumlahMahasiswa; i++) {
                String nim = mahasiswaSKS[i];
                for (int j = 0; j < jumlahData; j++) {
                    if (dataKRS[j][1].equalsIgnoreCase(nim)) {
                        System.out.printf("%-15s %-15s %-15s %-30s %-5s\n",
                                dataKRS[j][0], // Nama
                                dataKRS[j][1], // NIM
                                dataKRS[j][2], // Kode Matkul
                                dataKRS[j][3], // Nama Matkul
                                dataKRS[j][4]  // SKS
                        );
                    }
                }
            }
        }
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

        static boolean sudahTercatat(String[] mahasiswaSKS, String nim, int jumlahMahasiswa) {
        for (int i = 0; i < jumlahMahasiswa; i++) {
            if (mahasiswaSKS[i].equalsIgnoreCase(nim)) {
                return true;
            }
        }
        return false;
    }

}
