package Controlador;

import Modelo.DatosProductos;
import Modelo.Producto;
import Vista.FrmConsultarCodigo;
import Vista.FrmListarProductos;
import Vista.FrmRegistrarProducto;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SENA
 */
public class ControllerProducto {

    private DatosProductos dProducto = new DatosProductos();
    private String mensaje;

    public String getMensaje() {
        return mensaje;
    }

    /**
     * Recibe de la vista los datos para agregar un producto
     *
     * @param frm: Formulario con los datos del producto
     * @return true o false
     */
    public boolean registrar(FrmRegistrarProducto frm) {
        int codigo = Integer.parseInt(frm.txtCodigo.getText());
        String nombre = frm.txtNombre.getText();
        double precio = Double.parseDouble(frm.txtPrecio.getText());
        Producto p = new Producto(codigo, nombre, precio);
        boolean existe = dProducto.existeProductoCodigo(codigo);
        if (!existe) {
            boolean agregado = dProducto.agregar(p);
            mensaje = dProducto.getMensaje();
            return agregado;
        } else {
            mensaje = "Ya existe un producto con ese codigo. Verificar";
            return false;
        }
    }

    /**
     * rECIBE DE LA VISTA LA SOLICITUD PARA ISTAR LOS PRODUCTOS
     *
     * @param frm: productos donde se van a listar
     */
    public void listar(FrmListarProductos frm) {
        DefaultTableModel modelo = (DefaultTableModel) frm.tblProductos.getModel();
        String[] datos = new String[3];
        ArrayList<Producto> lista = dProducto.listar();
        modelo.setRowCount(0);
        for (Producto producto : lista) {
            datos[0] = String.valueOf(producto.getCodigo());
            datos[1] = producto.getNombre();
            datos[2] = String.valueOf(producto.getPrecio());
            modelo.addRow(datos);
        }
    }

    public boolean Consultar(FrmConsultarCodigo frm) {
        int codigo = Integer.parseInt(frm.txtCodigo.getText());
        Producto p = dProducto.ConsultarCodigo(codigo);
        frm.txtNombre.setText("");
        frm.txtPrecio.setText("");
        if(p!=null){
            frm.txtNombre.setText(p.getNombre());
            frm.txtPrecio.setText(String.valueOf(p.getPrecio()));
            return true;
        }else{
            mensaje = "No existe producto con ese codigo";
            return false;
        }
    }
}
