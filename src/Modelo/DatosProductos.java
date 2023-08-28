package Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SENA
 */
public class DatosProductos {

    private File archivo;
    private FileReader fr;
    private FileWriter fw;
    private BufferedReader br;
    private BufferedWriter bw;
    private ArrayList<Producto> lista;
    private String mensaje;

    public DatosProductos() {
        lista = new ArrayList<>();
        archivo = new File("Productos.txt");
        try {
            fw = new FileWriter(archivo, true);
            fw.close();
        } catch (IOException ex) {
            mensaje = ex.getMessage();
        }
    }

    /**
     * agrega un producto al archivo
     *
     * @param producto: objeto con todos sus datos
     * @return true o false
     */
    public boolean agregar(Producto producto) {
        boolean agregado = false;
        mensaje = "";
        try {
            fw = new FileWriter(archivo, true);
            bw = new BufferedWriter(fw);
            bw.write(producto.getCodigo() + ","
                    + producto.getNombre() + ","
                    + producto.getPrecio() + "\n");
            bw.close();
            fw.close();
            agregado = true;
        } catch (IOException ex) {
            mensaje = ex.getMessage();
        }
        return agregado;
    }

    /**
     * verifica si existe un producto deacuerdo a su codigo
     *
     * @param codigo: codigo del producto a verificar
     * @return true o false
     */
    public boolean existeProductoCodigo(int codigo) {
        leerArchivo();
        boolean existe = false;
        for (Producto producto : lista) {
            if (producto.getCodigo() == codigo) {
                existe = true;
                break;
            }

        }
        return existe;
    }

    /**
     * Leer los productos del archivo y los guarda en una lista
     */
    public void leerArchivo() {
        lista.clear();
        try {
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            while (br.ready()) {
                String linea = br.readLine();
                String[] p = linea.split(",");
                Producto producto = new Producto(Integer.parseInt(p[0]),
                        p[1],
                        Double.parseDouble(p[2]));
                lista.add(producto);
            }
            br.close();
            fr.close();
        } catch (IOException ex) {
            mensaje = ex.getMessage();
        }
    }

    public String getMensaje() {
        return mensaje;
    }

    /**
     * Obtiene la lista de productos existennte
     *
     * @return la lista
     */
    public ArrayList<Producto> listar() {
        leerArchivo();
        return lista;
    }

    public Producto ConsultarCodigo(int Codigo) {
        leerArchivo();
        Producto p = null;
        for (Producto producto : lista) {
            if(producto.getCodigo()==Codigo){
                p= producto;
                break;
            }
        }
        return p;
    }
}
