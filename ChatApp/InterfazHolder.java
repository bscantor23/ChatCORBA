package ChatApp;

/**
* ChatApp/InterfazHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from IDL.idl
* Tuesday, January 19, 2021 5:08:52 PM COT
*/

public final class InterfazHolder implements org.omg.CORBA.portable.Streamable
{
  public ChatApp.Interfaz value = null;

  public InterfazHolder ()
  {
  }

  public InterfazHolder (ChatApp.Interfaz initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ChatApp.InterfazHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ChatApp.InterfazHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ChatApp.InterfazHelper.type ();
  }

}
