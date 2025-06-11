/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package manejoempleado2;

import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;



public class ManejoEmpleado2 {

    
    private final String archivo;
    
    public ManejoEmpleado2(String archivo) {
        this.archivo = archivo;
    }

   
    
     public void guardarEmpleado(Empleado empleado) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(archivo, "rw")) {
            long posicion = raf.length(); // Mover al final del archivo
            raf.seek(posicion);
            
            // Escribir campos con formato fijo
            raf.writeUTF(empleado.getCedula());
            raf.writeUTF(empleado.getNombre());
            raf.writeUTF(empleado.getCargo());
            raf.writeDouble(empleado.getSueldo());
            raf.writeInt(empleado.getEdad());
            raf.writeUTF(empleado.getDireccion());
            raf.writeUTF(empleado.getTelefono());
        }
    }

    public List<Empleado> obtenerEmpleados() throws IOException {
        List<Empleado> empleados = new ArrayList<>();
        try (RandomAccessFile raf = new RandomAccessFile(archivo, "r")) {
            while (raf.getFilePointer() < raf.length()) {
                Empleado empleado = new Empleado(
                    raf.readUTF(),
                    raf.readUTF(),
                    raf.readUTF(),
                    raf.readDouble(),
                    raf.readInt(),
                    raf.readUTF(),
                    raf.readUTF()
                );
                empleados.add(empleado);
            }
        }
        return empleados;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
