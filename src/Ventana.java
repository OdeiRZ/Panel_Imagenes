import java.awt.*;
import javax.swing.*;

public class Ventana {

    protected static JTextArea areaTexto;
    
    protected static JSplitPane division;
    
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
    
    public static void main(String args[]) {
        Ventana ventana = new Ventana();
    }
}