import ChatApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.io.*;
import java.util.regex.Pattern;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.*;
import javax.swing.JOptionPane;

public class chatframe{

    static Interfaz serverInterfaceImpl;

    public static String lastMessage = "";
    public static String userName = "";
    public static String connectedRoom = "";
    public static String strResponse = "";

    public static void main(String args[]) {

        //Declaracion de variables graficas
        JFrame frame = new JFrame("Chat Frame");       
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        JTextField Nombre = new javax.swing.JTextField();
        JButton Salir = new javax.swing.JButton();
        JButton Entrar = new javax.swing.JButton();
        JLabel label = new javax.swing.JLabel();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTextArea Textos = new javax.swing.JTextArea();
        JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        JTextArea Lista = new javax.swing.JTextArea();
        JTextField Texto = new javax.swing.JTextField();
        JButton Envia = new javax.swing.JButton();
        JLabel vip = new javax.swing.JLabel();
        
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setBackground(new java.awt.Color(102, 102, 102));
        frame.setForeground(new java.awt.Color(51, 0, 255));
        frame.setMinimumSize(new java.awt.Dimension(900, 500));
        frame.setMaximumSize(new java.awt.Dimension(901, 501));
        
        Salir.setBackground(new java.awt.Color(0, 51, 153));
        Salir.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        Salir.setForeground(new java.awt.Color(255, 255, 255));
        Salir.setText("SALIR");

        Entrar.setBackground(new java.awt.Color(0, 0, 102));
        Entrar.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        Entrar.setForeground(new java.awt.Color(255, 255, 255));
        Entrar.setText("INICIAR SESION");

        label.setBackground(new java.awt.Color(0, 51, 255));
        label.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        label.setText("Nombre de Usuario");

        Textos.setBackground(new java.awt.Color(0, 0, 0));
        Textos.setColumns(20);
        Textos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Textos.setForeground(new java.awt.Color(255, 255, 255));
        Textos.setRows(5);
        jScrollPane1.setViewportView(Textos);

        Lista.setBackground(new java.awt.Color(0, 51, 51));
        Lista.setColumns(20);
        Lista.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Lista.setForeground(new java.awt.Color(255, 255, 255));
        Lista.setRows(5);
        Lista.setCaretColor(new java.awt.Color(0, 0, 255));
        jScrollPane2.setViewportView(Lista);

        Texto.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        Envia.setBackground(new java.awt.Color(204, 204, 204));
        Envia.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Envia.setForeground(new java.awt.Color(51, 51, 51));
        Envia.setText("ENVIAR");

        vip.setFont(new java.awt.Font("Stencil", 2, 24)); // NOI18N
        vip.setText("LISTA VIP");
        //Acomodacion de objetos en un layout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Entrar, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label)
                        .addGap(32, 32, 32)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Texto, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Envia))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(vip, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(vip)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Entrar)
                        .addGap(26, 26, 26)
                        .addComponent(Salir)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Texto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Envia, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(97, Short.MAX_VALUE))
        );


        // Creando el panel en la parte inferior y agregando componentes       
        JPanel panel = new JPanel(); // el panel no está visible en la salida      
   
        panel.add(Nombre); // Componentes agregados usando Flow Layout      
        panel.add(Salir);       
        panel.add(Entrar);       
        panel.add(label);
        panel.add(jScrollPane1);        
        panel.add(jScrollPane2);
        panel.add(Texto);
        panel.add(Envia);
     
        //Hace visible el frame
        frame.setVisible(true);   

        try {
            // Crea e inicializa el orb
            ORB orb = ORB.init(args, null);

            // Nombre de servicio y asignacion de referencia
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Nombra la referencia
            String name = "ServerInterface";
            serverInterfaceImpl = InterfazHelper.narrow(ncRef.resolve_str(name));

        } catch (Exception e) {
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, "CORBA no inicializado");
            System.exit(0);
        }

        // Envia textos al chat
        Envia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try{
                    //Envios de textos
                    String input = Texto.getText();
                    Textos.append("@" + userName + ": " + input+"\n");
                    serverInterfaceImpl.newMessages(connectedRoom, "@" + userName + ": " + input);
                    Texto.setText("");
                    //Si no ha entrado al chat
                    if(Entrar.isEnabled()==true){
                        Textos.append("Atención, no está conectado en chat\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                    JOptionPane.showMessageDialog(null, "Server no inicializado, intente nuevamente");
                    System.exit(0);
                }
            }
        });

        // Se desconecta del chat
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serverInterfaceImpl.disconnect(userName, connectedRoom);
                System.exit(0);
            }
        });

        // Se conecta al chat
        Entrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {

                    if(Nombre.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Por favor, digite el nombre del usuario");
                    } else {
                        
                        //Deshabilita el campo de nombre
                        Nombre.setEditable(false);
                        Entrar.setEnabled(false);
                        String connectionResponse = serverInterfaceImpl.connection(Nombre.getText());
                        System.out.println("Nombre registrado: "+Nombre.getText());
                        //Si esta registrado el nombre, salta aviso de error
                        //Sino se conecta al chat
                        if (connectionResponse.equals("failure")) {
                            Nombre.setEditable(true);
                            Entrar.setEnabled(true);
                            Textos.append("Nombre fallido, ingrese otro\n");
                        } else {
                            //Selecciona la sala
                            connectedRoom = "general";
                            strResponse = connectionResponse;
                            userName = Nombre.getText();
                            //Asignacion de fecha y hora actual
                            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                            Date TimeStamp = new Date();
                            Textos.setText("");
                            Textos.append("Bienvenido a ChatCorba\n\n");
                            String connectedTime = "Te has conectado a las " + dateFormat.format(TimeStamp);
            
                            dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                            TimeStamp = new Date();
                            connectedTime += " del dia " + dateFormat.format(TimeStamp);
                            //Mensaje para el usuario que entra
                            Textos.append(connectedTime);
                            Textos.append("\n\n");
            
                            // Hilo encargado de recivir mensajes
                            Thread receivingMessages = new Thread(new Runnable() {
                                public void run() {
                                    while (true) {
                                        String serverResponse = serverInterfaceImpl.getMessages(connectedRoom);
                                        if (!(serverResponse.equals(lastMessage)) && !(serverResponse.equals("")) && !(serverResponse.startsWith("@" + userName))) {
                                            lastMessage = serverResponse;
                                            Textos.append(serverResponse + "\n");
                                        }
            
                                        try {
                                            Thread.sleep(500);
                                        } catch (Exception e) {
                                            System.out.println(e);
                                        }
                                    }
                                }
                            });

                            //Hilo encargado de actualizar la lista
                            Thread ActListas = new Thread(new Runnable() {
                                public void run() {
                                    while (true) {
                                        Lista.setText("");
                                        String returnValue = serverInterfaceImpl.listUsers(connectedRoom);
                                        String[] returnValueParts = returnValue.split(Pattern.quote("."));
                                        
                                        for (int i = 0; i < returnValueParts.length; i++) {
                                            Lista.append((i + 1) + ". " + returnValueParts[i] + "\n");
                                        }
                                        try {
                                            Thread.sleep(500);
                                        } catch (Exception e) {
                                            System.out.println(e);
                                        }
                                    }
                                }
                            });
            
                            // Inicia los hilos
                            receivingMessages.start();
                            ActListas.start();
                        } 
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                    JOptionPane.showMessageDialog(null, "Server no inicializado");
                    System.exit(0);
                }
                
            }
        });
    }
}
