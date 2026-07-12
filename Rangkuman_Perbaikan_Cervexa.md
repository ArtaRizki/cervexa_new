# Laporan Penyelesaian Bug Aplikasi Cervexa (KmedHealthIndonesia)
**Tanggal:** 8 Juli 2026
**Platform:** Android (Decompiled Project)
**Tujuan Perbaikan:** Memulihkan fungsionalitas aplikasi hasil decompile, membasmi crash (force close) utama pada Camera & Gallery, serta mengamankan perizinan storage pada OS Android modern.

---

## 1. Sinkronisasi Resource ID (R.java)
- **Masalah:** Aplikasi hasil _decompile_ menggunakan angka ID statis yang sudah tertulis mati di berbagai file CXXXXR.java atau R.java bawaan *library*. Ketika di-*build* ulang menggunakan Gradle, sistem Android memberikan ID baru yang tidak cocok dengan angka lama, menyebabkan UI tidak dapat dimuat dan memicu IllegalArgumentException maupun NullPointerException.
- **Solusi yang Diterapkan:**
  1. Membuat *script* sinkronisasi (update_r_ids.py) yang membaca daftar ID aktif dari R.txt hasil _build_ Gradle.
  2. *Script* secara otomatis memindai seluruh *file* Java di proyek dan memperbarui semua deklarasi ID lama (R.id.xxx = 0x7f0....) dengan referensi dinamis yang benar (mengarah ke ID *layout*, *id*, *string*, *drawable*, dll yang relevan).
- **Status:** **Tuntas.** Aplikasi dapat masuk ke Activity dan merender layout *View* dengan normal tanpa *crash* di tahap *inflation*.

## 2. Penanganan Scoped Storage (Izin Penyimpanan Android 10+)
- **Masalah:** Ketika kamera USB (Mikroskop MS2) dinyalakan dan tombol _Capture_ ditekan, aplikasi langsung *Force Close*. Log menunjukkan error java.io.IOException: Permission denied karena aplikasi *library* pihak ketiga (com.serenegiant.usb) mencoba membuat *file* secara langsung (cara lama / _legacy storage_) yang diblokir oleh sistem keamanan Android 10+ (Scoped Storage).
- **Solusi yang Diterapkan:**
  1. Melakukan validasi tambahan di UVCUSBCameraActivity untuk memastikan izin _Read/Write External Storage_ dan _Camera_ diminta secara legal melalui sistem sebelum proses penyimpanan dilakukan.
  2. Menerapkan pengamanan dengan metode MediaStore / ContentResolver dan mengubah jalur penyimpanan standar *library* ke direktori aman yang tidak dikunci oleh Scoped Storage.
- **Status:** **Tuntas.** Fitur _capture_ kamera dapat berjalan normal dan aman dijalankan pada *Smartphone/Tablet* dengan Android versi terbaru.

## 3. Restorasi DataBinding Mapper (Gallery Crash Fix)
- **Masalah:** Saat fitur _Gallery_ dibuka untuk melihat hasil foto, aplikasi mengalami _crash_ berupa NullPointerException. Akar masalahnya adalah pada aplikasi hasil decompile, *file* XML telah kehilangan tag <layout> asli yang dibutuhkan oleh DataBinding. Akibatnya, DataBinderMapperImpl milik Gradle dibuat kosong, sehingga DataBindingUtil.inflate mengembalikan nilai _null_ saat memuat elemen item foto (gallery_photo_item).
- **Solusi yang Diterapkan:**
  1. Memanfaatkan kelas DataBinderMapperImpl bawaan JADX dari kode awal yang masih mereferensikan seluruh UI aplikasi lama.
  2. Menyuntikkan (*inject*) *mapper* JADX tersebut secara paksa dan global menggunakan _Reflection_ ke sistem inti DataBindingUtil.sMapper Android di CameraApplication.java ketika aplikasi pertama kali dibuka (saat onCreate()).
- **Status:** **Tuntas.** Seluruh *crash* DataBinding teratasi secara instan, dan aplikasi bisa merender kumpulan foto yang ditangkap di fitur _Gallery_ tanpa ada error _force-close_.

---

## 4. Analisis Mode Fallback Kamera
- Telah dikonfirmasi juga bahwa di dalam UVCUSBCameraActivity, aplikasi telah mengimplementasikan mode *fallback*. 
- Jika Kamera USB Mikroskop tidak terdeteksi dalam ~5 detik, aplikasi akan secara otomatis memunculkan tampilan layar **Kamera HP**. Fitur foto tetap berjalan memakai Kamera HP, meskipun fitur rekam video secara *default* diblokir dengan *Toast Warning* khusus saat mode Kamera HP ini berjalan.

**Kesimpulan:**
Performa dasar dari aplikasi terkait fungsionalitas UI, *Camera UVC streaming*, proses _taking pictures_, dan pemuatan _Gallery_ semuanya kini sudah kembali normal dan siap diuji coba secara komprehensif.
