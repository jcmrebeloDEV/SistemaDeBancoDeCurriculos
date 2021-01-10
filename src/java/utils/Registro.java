package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Registro {

private Properties p = null;

public Registro(String nomeArquivo) {


ClassLoader sysClassLoader = getClass().getClassLoader();

URL[] urls = ((URLClassLoader) sysClassLoader).getURLs();
URL diretorioDeClasses = null;


for (URL url : urls) {
            try {
                if (URLDecoder.decode(url.getPath(), "UTF-8").contains("/WEB-INF/classes/")) {
                    diretorioDeClasses = url;
                }
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
            }
}

try {

    p = new Properties();
    FileInputStream in = null;

    if (diretorioDeClasses == null) throw new IOException("Diretorio de classes nao encontrado");

    try {
        in = new FileInputStream(new File(URLDecoder.decode(diretorioDeClasses.getPath(), "UTF-8") + System.getProperty("file.separator") + nomeArquivo));
        p.load(in);
    } catch (Exception ex) {
        Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try {
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

} catch (Exception ex) {
Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
}


}

public String lerValor(String chave) {
return p.getProperty(chave);
}

public static void main(String[] args) {
Registro reg = new Registro("MecanismoDeBusca.properties");
System.out.println(reg.lerValor("indiceCurriculo"));
}
}
