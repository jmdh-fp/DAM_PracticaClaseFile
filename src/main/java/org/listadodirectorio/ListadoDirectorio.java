package org.listadodirectorio;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class ListadoDirectorio {

    public static void main(String[] args) {
        final String CAD_FOR_CAB = "%-5s %-40s %14s  %-9s  %s\n";
        final String CAD_FOR_FIL = "%-5s %-40s %14d  %-9s  %s\n";
        String ruta = ".";
        if (args.length >= 1) ruta = args[0];
        File fich = new File(ruta);
        if (!fich.exists()) {
            System.out.println("No existe el fichero o directorio (" + ruta + ").");
        } else {
            if (fich.isFile()) {
                System.out.println(ruta + " es un fichero.");
                System.out.printf(CAD_FOR_CAB, "TIPO", "NOMBRE", "TAMAÑO(Bytes)", "PERMISOS", "FECHA_MODIFICACIÓN");
                System.out.printf(CAD_FOR_FIL, "[-]", fich.getName(), fich.length(), damePermisos(fich), dameFecha(fich));

            } else {
                System.out.println(ruta + " es un directorio. Contenidos: ");
                System.out.printf(CAD_FOR_CAB, "TIPO", "NOMBRE", "TAMAÑO(Bytes)", "PERMISOS", "FECHA_MODIFICACIÓN");
                File[] ficheros = fich.listFiles(); // Ojo, ficheros o directorios
                for (File f : ficheros) {
                    String textoDescr = f.isDirectory() ? "[/]" : f.isFile() ? "[_]" : "?";
                    //String fechaModif = (new Date(f.lastModified()).toString());
                   System.out.printf(CAD_FOR_FIL, textoDescr, f.getName(), f.length(), damePermisos(f), dameFecha(f));
                }
            }
        }
    }

    private static String dameFecha(File f){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date(f.lastModified()));
    }

    private static String damePermisos(File f) {
        StringBuilder permisos = new StringBuilder();

        // Tipo de permiso. Directorio: drwx Fichero: -rwx

        permisos.append(f.isDirectory() ? 'd' : '-');
        permisos.append(f.canRead() ? 'r' : '-');
        permisos.append(f.canWrite() ? 'w' : '-');
        permisos.append(f.canExecute() ? 'x' : '-');

        return permisos.toString();

    }
}
