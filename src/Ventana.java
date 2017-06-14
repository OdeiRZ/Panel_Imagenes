import java.awt.*;
import javax.swing.*;

/**
 * Clase Ventana. 
 * Se encarga de generar el contenedor frame usado para implementar la
 * interfaz gráfica nicial de nuestra aplicación.
 *
 * @author Odei
 * @version 22.09.2013
 */
public class Ventana {
    /**
     * Variable usada para mostrar mensajes durante la ejecución del programa.
     */
    protected static JTextArea areaTexto;
    
    /**
     * Variable usada para mostrar un panel con contenido partido en pantalla.
     */
    protected static JSplitPane division;
    
    /**
     * Constructor de la Interfaz Gráfica implementada para la Ventana.
     * Genera e inicializa la Interfaz y los elementos utilizados
     * para visualizar de forma interactiva la ejecución de la aplicación.
     */
    public Ventana() {
        JFrame marco = new JFrame("Directorio de Imágenes y descripción");
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setSize(380,390);
        marco.setResizable(false);
        marco.setLocationRelativeTo(null);
        marco.setIconImage(Toolkit.getDefaultToolkit().createImage(
                GestionVentana.ruta + "logo.png"));
        marco.add("East", new GestionVentana());
        areaTexto = new JTextArea("Selecciona una Carpeta de Imágenes para analizarla");
        division = new JSplitPane(JSplitPane.VERTICAL_SPLIT, 
                                 new JScrollPane(areaTexto), new JScrollPane());
        marco.add("Center", division);
        marco.setVisible(true);
    }
    
    /**
     * Método Principal de la Clase Ventana.
     * Lanza una instancia del Programa llamando al Constructor.
     * 
     * @param args String[]: argumentos de la línea de comandos
     */
    public static void main(String args[]) {
        Ventana ventana = new Ventana();
    }
}