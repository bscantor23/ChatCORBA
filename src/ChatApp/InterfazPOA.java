package ChatApp;


/**
* ChatApp/InterfazPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from IDL.idl
* Tuesday, January 19, 2021 5:08:52 PM COT
*/

public abstract class InterfazPOA extends org.omg.PortableServer.Servant
 implements ChatApp.InterfazOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("connection", new java.lang.Integer (0));
    _methods.put ("newMessages", new java.lang.Integer (1));
    _methods.put ("getMessages", new java.lang.Integer (2));
    _methods.put ("listUsers", new java.lang.Integer (3));
    _methods.put ("disconnect", new java.lang.Integer (4));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // ChatApp/Interfaz/connection
       {
         String userName = in.read_string ();
         String $result = null;
         $result = this.connection (userName);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 1:  // ChatApp/Interfaz/newMessages
       {
         String roomName = in.read_string ();
         String message = in.read_string ();
         this.newMessages (roomName, message);
         out = $rh.createReply();
         break;
       }

       case 2:  // ChatApp/Interfaz/getMessages
       {
         String roomName = in.read_string ();
         String $result = null;
         $result = this.getMessages (roomName);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 3:  // ChatApp/Interfaz/listUsers
       {
         String roomName = in.read_string ();
         String $result = null;
         $result = this.listUsers (roomName);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 4:  // ChatApp/Interfaz/disconnect
       {
         String userName = in.read_string ();
         String roomName = in.read_string ();
         this.disconnect (userName, roomName);
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:ChatApp/Interfaz:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Interfaz _this() 
  {
    return InterfazHelper.narrow(
    super._this_object());
  }

  public Interfaz _this(org.omg.CORBA.ORB orb) 
  {
    return InterfazHelper.narrow(
    super._this_object(orb));
  }


} // class InterfazPOA
