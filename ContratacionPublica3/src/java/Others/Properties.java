/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Others;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 *
 * @author Dell
 */
public class Properties {
    String archivo;
    
    public Properties() {
        archivo = "configuracion.properties";
    }
    
    public Properties(String path) {
        archivo = path;
    }
    
    public String prop(String propiedad){
        java.util.Properties prop = new java.util.Properties();
        FileInputStream is = null;
        try {
            
            is = new FileInputStream(new File(archivo));
            prop.load(new InputStreamReader(is, Charset.forName("UTF-8")));
        } catch(IOException e) {
                
        }
        return prop.getProperty(propiedad);
    }
}