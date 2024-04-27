# LP7DPBO2024C1

## Janji
Saya Azzahra Fahriza Fitriani NIM 2102296 mengerjakan soal LP7 dalam mata kuliah DPBO untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin

## Design Program 
App.java:
Ini adalah kelas utama yang berisi metode main. Ketika program dimulai, metode main akan dijalankan, dan di dalamnya, objek MainMenu akan dibuat dan ditampilkan.

Pipe.java: 
Kelas ini bertanggung jawab untuk merepresentasikan pipa dalam permainan. Pipa memiliki atribut seperti posisi, lebar, tinggi, gambar, dan lainnya yang diperlukan untuk menampilkan pipa di layar.

Player.java: 
Kelas ini mewakili pemain (burung) dalam permainan. Ini memiliki atribut seperti posisi, lebar, tinggi, gambar, dan kecepatan vertikal yang digunakan untuk mengontrol pergerakan pemain.

MainMenu.java: 
Ini adalah jendela utama program yang ditampilkan kepada pengguna saat pertama kali menjalankan aplikasi. Ini memiliki tombol "Start Game" yang, ketika diklik, akan menutup jendela menu utama dan membuka jendela permainan FlappyBird.

FlappyBird.java: 
Kelas ini mewakili panel permainan utama tempat permainan sebenarnya terjadi. Ini memiliki logika untuk menggambar elemen permainan seperti pemain dan pipa, serta mengatur interaksi pengguna melalui tombol keyboard. Ini juga mengatur pergerakan dan perilaku pemain serta pipa dalam permainan.

## Alur Program 
### Main Method
Program dimulai dari kelas App.java, yang memiliki metode main.
Metode main membuat objek MainMenu dan menampilkannya kepada pengguna.

### MainMenu
Ketika program dimulai, MainMenu ditampilkan kepada pengguna.
MainMenu memiliki tombol "Start Game" yang, ketika diklik, akan menutup jendela MainMenu dan membuka jendela permainan FlappyBird.

### FlappyBird
Jendela permainan FlappyBird dibuka.
Pada saat dibuka, semua elemen permainan (pemain, pipa, skor, label game over) diinisialisasi dan ditampilkan kepada pengguna.
Permainan dimulai, dan pemain dapat mengontrol pemain menggunakan tombol keyboard.
Pergerakan pemain dan pipa diatur oleh timer.
Jika pemain menabrak pipa atau mencapai batas atas/bawah layar, permainan berakhir.
Jika permainan berakhir, label "Game Over" dan instruksi "Press R to Restart the Game" ditampilkan.
Jika pengguna menekan tombol "R", permainan diulang dari awal.
Skor pemain diperbarui setiap kali pemain berhasil melewati dua pipa.
## Video Dokumentasi
https://github.com/azzahrafahriza/LP7DPBO2024C1/assets/101120742/1a7b39aa-b073-4ab6-9344-feb134595236

