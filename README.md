# Simple Java Web Server

Bu proje, Java ile yazılmış basit bir web sunucusu uygulamasıdır. Sunucu, yerel makinenizde çalışarak HTML, CSS ve diğer statik dosyaları tarayıcınızda görüntülemenizi sağlar.

## 📁 Proje İçeriği

- **WebServer.java** - Ana sunucu dosyası (multi-threaded HTTP server)
- **index.html** - Kişisel profil sayfası
- **style.css** - Sayfa stil dosyası

## 🚀 Kurulum ve Çalıştırma

> **ÖNEMLİ:** Bu proje **terminalden** çalıştırılmalıdır. IDE kullanmayın!

### Adım 1: Dosyaları Derleyin

Terminal veya komut satırını açın ve proje dizinine gidin:

```bash
cd /proje/dizini
```

Java dosyasını derleyin:

```bash
javac WebServer.java
```

Bu komut, aynı dizinde `WebServer.class` dosyası oluşturacaktır.

### Adım 2: Sunucuyu Başlatın

Derleme tamamlandıktan sonra sunucuyu başlatın:

```bash
java WebServer
```

Sunucu başarıyla başladığında şu mesajları göreceksiniz:

```
Web Server is starting...
The files are provided from this folder: ./
Open this address in your browser: http://localhost:1989
The server is listening on port 1989...
```

### Adım 3: Tarayıcıda Görüntüleyin

Tarayıcınızı açın ve şu adrese gidin:

```
http://localhost:1989
```

## ⚙️ Teknik Detaylar

- **Port:** 1989
- **Web Root:** Mevcut dizin (`./`)
- **Desteklenen Dosya Türleri:** HTML, CSS, JavaScript, JSON, görsel dosyaları (JPG, PNG, GIF)
- **Multi-threading:** Her istek ayrı bir thread'de işlenir

## 🛑 Sunucuyu Durdurma

Sunucuyu durdurmak için terminalde `Ctrl + C` tuşlarına basın.

## 📝 Notlar

- Sunucu varsayılan olarak 1989 portunda çalışır
- Tüm dosyalar (HTML, CSS vb.) sunucu dosyasıyla aynı dizinde olmalıdır
- Port değiştirmek isterseniz `WebServer.java` dosyasındaki `PORT` sabitini düzenleyip yeniden derlemeniz gerekir

## 🔧 Sorun Giderme

**"Address already in use" hatası alıyorsanız:**
- 1989 portu başka bir uygulama tarafından kullanılıyor olabilir
- Portun serbest olduğundan emin olun veya kod içinde farklı bir port numarası kullanın

**Dosyalar yüklenmiyor:**
- `index.html` ve `style.css` dosyalarının `WebServer.java` ile aynı dizinde olduğundan emin olun
- Terminal'de doğru dizinde olduğunuzu kontrol edin

## 👤 Yazar

**Mustafa**

- GitHub: [@mustfasahin](https://github.com/mustfasahin)
- Email: mstfasahn94@gmail.com

---

**Not:** Bu proje eğitim amaçlı hazırlanmış basit bir web sunucusudur. Production ortamı için kullanılması önerilmez.