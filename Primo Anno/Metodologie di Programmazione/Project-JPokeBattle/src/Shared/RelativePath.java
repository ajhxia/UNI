package Shared;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/*
 * Questa classe permette di ottenere il percorso di un file all'interno del progetto
 * Il percorso relativo è utile per accedere a file all'interno del progetto senza dover specificare il percorso assoluto
 */

public class RelativePath { 
    // Restituisce il percorso assoluto di un file all'interno del progetto
    // in ingresso vuole il percorso relativo del file
    public static String getAbsolutePath(String relativePath) {
        String classPath = RelativePath.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String decodedClassPath = null;
        try {
            decodedClassPath = URLDecoder.decode(classPath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        File classFile = new File(decodedClassPath);
        String classDirectory = classFile.getParent();
        
        // Rimuovi la barra iniziale se presente
        if (relativePath.startsWith("/")) {
            relativePath = relativePath.substring(1);
        }
        
        // Costruisci il percorso completo
        String filePath = classDirectory + File.separator + relativePath;
        return filePath;
    }
    
}