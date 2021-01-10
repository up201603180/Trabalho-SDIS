import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Receiver {

    // Receive a DatagramPacket from a specific port of a multicast address
    public static void receive( int node,String multicast_address, int multicast_port ) throws IOException {

        // Join a Multicast group
        MulticastSocket multicast_socket = new MulticastSocket( multicast_port );
        InetAddress multicast_group = InetAddress.getByName( multicast_address );
        multicast_socket.joinGroup( multicast_group );

        // Receive a DatagramPacket containing a message
        byte[] buffer = new byte[ 1024 ];
        while ( true ) {

            System.out.println( "[Client "+node+"]: Waiting..." );

            DatagramPacket packet = new DatagramPacket( buffer, buffer.length );
            multicast_socket.receive(packet);

            String fullmessage = new String( packet.getData(), packet.getOffset(), packet.getLength() );
            int nodeOrig = Integer.parseInt(fullmessage.split((";"))[0]);
            String message = fullmessage.split((";"))[1];
            System.out.println("Client: "+node+" received from Server "+nodeOrig+": " + message );

            // Explorar comandos para alterar disposição dos nós etc...
            if ( "Exit".equals( message ) ) break;
        }

        // Leave Multicast group and close the socket
        multicast_socket.leaveGroup( multicast_group );
        multicast_socket.close();

    }

}