

## 🔐 CryptoStegApp — A Java Encryption and Steganography Suite

A Java-based desktop application that combines **Simplified Data Encryption Standard (S-DES)**, **One-Time Pad encryption**, and **image steganography** in a graphical interface using **JavaFX**. This project demonstrates layered encryption and secure embedding of ciphered data into an image.

---

## 💡 Project Highlights

- 🔒 **S-DES Encryption** for secure binary transformation of text data.
- 🧠 **One-Time Pad** encryption layered on top of S-DES output for maximum randomness.
- 🖼️ **Steganography Module** embeds the encrypted data into a PNG image using LSB (Least Significant Bit) technique.
- 👁️ **Graphical Interface** using JavaFX to provide intuitive interaction and step-by-step visualization.
- 🔁 **Decryption Support** to retrieve and decode the original message from the image.
- ⚙️ No external libraries — the project is entirely implemented with **core Java**, showcasing deep understanding of encryption and image processing.

---

## 🧠 How It Works

### 1. **Encryption Process**
- User inputs plaintext through the GUI.
- Plaintext is first encrypted using **S-DES** with a 10-bit key.
- The binary output is encrypted again with a **One-Time Pad** (XOR with a generated pseudo-random key).
- The final ciphertext is then **hidden inside an image** using LSB steganography.

### 2. **Decryption Process**
- The system extracts the hidden bits from the modified image.
- Applies **One-Time Pad decryption** using the same key.
- Decodes the result using **S-DES decryption**.
- Displays the original message.

---

## 🛠️ Technologies Used

| Component | Technology |
|----------|------------|
| GUI      | JavaFX     |
| Core Logic | Java (OOP) |
| Image Processing | `BufferedImage`, `ImageIO` |
| File Handling | `FileChooser`, PNG Export |
| Encryption Algorithms | Custom S-DES, XOR-based One-Time Pad |

---

## 📂 Project Structure

```
CryptoStegApp/
├── Ass2Enc/
│   ├── SDEC.java        # S-DES implementation
│   ├── Main.java        # JavaFX UI, input/output handling
│   ├── Encr.java        # Image encryption and decryption (Steganography)
├── images/
│   └── comp.png         # Sample image used for testing
```

---

## 🖼️ User Interface Flow

1. User enters text in a styled `TextArea`.
2. Chooses an image file for embedding.
3. Clicks “Run” to execute:
   - S-DES encryption → One-Time Pad → Embed in image
4. “Show Steps” button walks through:
   - Encryption keys and intermediate cipher
   - Visuals of original vs. modified image
5. “Decrypt” button:
   - Extracts and decrypts hidden message
   - Displays original plaintext

---

## 🧪 Example Use Case

> Input: `"mohammad nemer"`  
> Key: `"1010000010"` (S-DES 10-bit key)  
> Output: Encrypted binary hidden inside selected image  
> Result: Same image with imperceptible change, recoverable message using the app

---

## 🧱 Core Algorithms Breakdown

- **S-DES**
  - Implements key generation (P10, LS, P8), IP/IP⁻¹, EP, S-boxes, P4, and rounds.
- **One-Time Pad**
  - XOR operation with custom key generator `(a*i + b) % 256`.
- **Steganography**
  - Uses LSB of RGB channels to embed each bit of the cipher text.
  - Uses deterministic pseudo-random access to pixel positions to obscure pattern.

---

## 💬 Final Thoughts

This project combines **modern cryptographic ideas** with **image steganography** and **intuitive visual feedback** through JavaFX. It’s an ideal demonstration of:
- Secure message encoding and decoding.
- Multi-layered encryption.
- Image-based data hiding and retrieval.
