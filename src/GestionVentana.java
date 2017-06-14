import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class GestionVentana extends JToolBar {

    protected int indice;
    
    protected boolean sw;
    
    protected static final String ruta = "src/recursos/";
    
    protected ArrayList<String>imagenes;
    
    public GestionVentana() {
        sw = false;
        imagenes = null;
        setFloatable(false);
        setLayout(new GridLayout(3, 1));
        String img[] = {"abrir", "siguiente", "anterior"};
        for (int i = 0; i < 3; i++) {
            JButton btn = new JButton(new ImageIcon(ruta + img[i] + ".png"));
            btn.addMouseListener(new VigilaBotones());
            btn.setName(img[i]);
            add(btn);
        }
    }

    public class VigilaBotones extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            switch (e.getComponent().getName()) {
                case "abrir":
                    sw = false;
                    JFileChooser chooser = new JFileChooser();
                    chooser.setCurrentDirectory(new java.io.File("."));
                    chooser.setDialogTitle("Selecciona una Carpeta con Imágenes (*.png)");
                    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        imagenes = new ArrayList<>();
                        File lista[] = new File(chooser.getSelectedFile()
                                .getAbsolutePath()).listFiles();
                        for (File lista1 : lista) {
                            String archivo = "" + lista1;
                            if (archivo.lastIndexOf('.') != -1)
                                if (archivo.substring(archivo.lastIndexOf('.'))
                                        .equals(".png")) {
                                    sw = true;
                                    imagenes.add(archivo);
                                }
                        }
                        if (sw) {
                            cambiaImagen(indice = 0);
                        } else {
                            Ventana.areaTexto.setText(
                                "El Directorio no contiene Imágenes (*.png)");
                            Ventana.division.setBottomComponent(
                                new JScrollPane(new JLabel(new ImageIcon(""))));
                        }
                    }   
                    break;
                case "siguiente":
                    if (sw)
                        if (indice != imagenes.size()-1)
                            cambiaImagen(++indice);
                        else
                            cambiaImagen(indice = 0);
                    break;
                default:
                    if(sw)
                        if(indice != 0)
                            cambiaImagen(--indice);
                        else
                            cambiaImagen(indice = imagenes.size()-1);
                    break;
            }
        }
        
        protected void cambiaImagen(int i) {
            try {
                Ventana.division.setBottomComponent(new JScrollPane(
                            new JLabel(new ImageIcon((String)imagenes.get(i)))));
                Ventana.areaTexto.setText("Archivo:\t"+((String) imagenes.get(i))
                    .substring(((String) imagenes.get(i)).lastIndexOf('\\')+1)+
                    "\n"+"Ruta:\t"+imagenes.get(i)+"\n"+"Tamaño:\t"+
                    new DecimalFormat("#.00").format(new File((String) 
                            imagenes.get(i)).length()/1024.0)+
                    " Kb"+"\n"+"Resolución:\t"+ImageIO.read(new File((String) 
                            imagenes.get(i))).getWidth()+" x "+
                    ImageIO.read(new File((String) imagenes.get(i))).getHeight());
                Ventana.division.setDividerLocation(90);
            } catch(IOException e1){}
        }
    }
}