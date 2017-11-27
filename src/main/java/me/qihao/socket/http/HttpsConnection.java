package me.qihao.socket.http;

import org.apache.commons.io.FileUtils;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.KeyStore;

public class HttpsConnection {

    // Note: If you want to show the situation that PKIX exception happened
    // without custom keystore, you need run this program on JDK 7. JDK 8's default
    // cacerts file trusts the CA, but 7 doesn't.
    public static void main(String[] args) throws IOException, GeneralSecurityException {
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        KeyStore keyStore = buildKeyStore();
        tmf.init(keyStore);
        SSLContext ctx = SSLContext.getInstance("TLS");
        ctx.init(null, tmf.getTrustManagers(), null);

        URL url = new URL("https://district0x.io/docs/district0x-whitepaper.pdf");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        conn.setRequestMethod("GET");
        // set ssl socket factory
        conn.setSSLSocketFactory(ctx.getSocketFactory());
        int responseCode = conn.getResponseCode();
        System.out.println(responseCode);
        if (responseCode == 200) {
            try (InputStream is = conn.getInputStream()) {
                File file = Paths.get("target", "whitepaper.pdf").toFile();
                FileUtils.copyToFile(is, file);
            }
        }
    }

    private static KeyStore buildKeyStore() throws GeneralSecurityException, IOException {
        // the keystore has the DSTRoot CA imported manually.
        ClassLoader classLoader = HttpsConnection.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("cacerts");
        String password = "changeit";
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(is, password.toCharArray());
        return keyStore;
    }
}
