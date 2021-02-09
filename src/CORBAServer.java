import ChatApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import java.text.*;
import java.util.*;
import java.util.regex.Pattern;

class ServerInterfaceImpl extends InterfazPOA {

	// Lista de mensajes
	static List<String> messageLogs = new ArrayList<>();

	// Lista de usuarios
	private static List<String> names = new ArrayList<>();

	// Objeto ORB
	private ORB orb;

	// Inicializacion
	public void setORB(ORB orb_val) {
		orb = orb_val;
	}

	public String connection(String userName) {

		// StringBuilder sb contiene mensajes
		StringBuilder sb = new StringBuilder();

        //Si el nombre ya esta registrado, retorna fallo sino,
        //Agrega el nombre a la lista
        //Agrega el nombre a la sala de usuarios
        //Crea un mensaje de coneccion
        //Puede enviar mensajes previos y los retorna
		if (names.contains(userName.toLowerCase())) {
			sb.append("failure");
		} else {
			//Obtine la hora actual
			DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            Date TimeStamp = new Date();
            String connectedTime = "se ha conectado. \n" + dateFormat.format(TimeStamp);
			//Obtiene la fecha actual
            dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            TimeStamp = new Date();
            connectedTime += " del " + dateFormat.format(TimeStamp) + "\n";
			//Agrega el nombre a la lista
			names.add(userName);
			//Agrega el mensaje de entrada
			messageLogs.add("general @" + userName + " " + connectedTime);

			sb.append("joined");

			for (int i = 0; i < messageLogs.size(); i++) {
				if (messageLogs.get(i).startsWith("general")) {
					sb.append("|" + messageLogs.get(i));
				}
			}
		}

		return sb.toString();
	}

	// Agrega el mensaje a la lista de mensajes
	public void newMessages(String roomName, String message) {
		messageLogs.add(roomName + " " + message);
	}

	// Retorna el ultimo mensaje del chat
	public String getMessages(String roomName) {
		String valueToReturn = "";
		// Obtiene el ultimo mensaje
		String message = messageLogs.get(messageLogs.size() - 1);

		// Retorna el ultimo mensaje de la sala
		if (message.startsWith(roomName)) {
			valueToReturn = message.substring(message.indexOf(" ") + 1);
		}

		// Retorna el mensaje o null si la sala no tiene mensajes
		return valueToReturn;
	}

	// Retorna la lista de usuarios
	public String listUsers(String roomName) {

		StringBuilder sb = new StringBuilder();

		// Retorna la lista y la almacena en un StringBuilder (sb)
		for (String s : names) {
			sb.append(s);
			sb.append(".");
		}

		// Retorna los usuarios
		return sb.toString();
	}

	// Desconecta al usuario del chat
	public void disconnect(String userName, String roomName) {
		// Quita al usuario de la lista
		names.remove(userName);
		// Envia el mensaje de desconeccion al chas
		messageLogs.add(roomName + " @" + userName + " ha salido de la sala.");
	}

}

public class CORBAServer {

	public static void main(String args[]) {

		try {
			// Crea el objeto ORB
			ORB orb = ORB.init(args, null);

			// Obtiene la referencia del POA y activa el POAManager
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			// Crea el servant y asigna el orb
			ServerInterfaceImpl serverInterfaceImpl = new ServerInterfaceImpl();
			serverInterfaceImpl.setORB(orb);

			// Obtiene la referencia del servant
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(serverInterfaceImpl);
			Interfaz href = InterfazHelper.narrow(ref);

			// Nombre del servicio y asignacion de referencias
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			String name = "ServerInterface";
			NameComponent path[] = ncRef.to_name(name);
			ncRef.rebind(path, href);

			System.out.println("Server running, accepting clients connection...");

			// Espera la llegada de clientes
			orb.run();
		}catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}
	}
}