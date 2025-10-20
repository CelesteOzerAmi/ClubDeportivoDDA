import Controlador.Controlador;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Controlador.agregarProps();

        String opcion = "";
        while (!opcion.equals("0")){
            System.out.printf("%nBienvenido. %n%nSeleccione una opción:%n");
            System.out.println("1. Canchas");
            System.out.println("2. Socios");
            System.out.println("3. Turnos");
            System.out.println("4. Tarifas");
            System.out.println("5. Reservas");
            System.out.println("6. Pagos");
            System.out.println("0. Salir");
            String eleccion = "";
            opcion = sc.nextLine();

            switch (opcion){
                case "0":
                    System.out.println("Saliendo...");
                    break;
                case "1":
                    System.out.printf("%nCanchas%n");

                    while (!eleccion.equals("0")){
                        System.out.printf("%n1. Registrar cancha. %n2. Eliminar cancha. %n3. Ver canchas. %n4. Canchas en reparación. %n0. Volver a inicio. %n");
                        eleccion = sc.nextLine();

                        switch (eleccion){
                            case "0":
                                System.out.printf("%nVolviendo...%n");
                                break;
                            case "1":
                                System.out.printf("%nRegistrar cancha%n");
                                Controlador.registrarCancha();
                                break;
                            case "2":
                                System.out.printf("%nEliminar cancha%n");
                                Controlador.eliminarCancha();
                                break;
                            case "3":
                                System.out.printf("%nVer canchas%n");
                                Controlador.verCanchas();
                                break;
                            case "4":
                                System.out.printf("%nCanchas en reparación%n");
                                Controlador.enReparacion();
                                break;
                            default:
                                System.out.printf("%nOpción incorrecta%n");
                                break;

                        }
                    }
                    break;
                case "2":
                    System.out.printf("%nSocios%n");

                    while (!eleccion.equals("0")){
                        System.out.printf("%n1. Registrar socio. %n2. Baja socio. %n3. Ver socios. %n4. Buscar socio. %n0. Volver a inicio. %n");
                        eleccion = sc.nextLine();

                        switch (eleccion){
                            case "0":
                                System.out.println("Volviendo...");
                                break;
                            case "1":
                                System.out.println("Registrar socio");
                                Controlador.registrarSocio();
                                break;
                            case "2":
                                System.out.println("Eliminar socio");
                                Controlador.eliminarSocio();
                                break;
                            case "3":
                                System.out.println("Ver socios");
                                Controlador.verSocios();
                                break;
                            case "4":
                                System.out.println("Ver detalle de socio");
                                Controlador.verDetalleSocio();
                                break;
                            default:
                                System.out.println("Opción incorrecta");
                                break;
                        }
                    }
                    break;
                case "3":
                    System.out.printf("%nTurnos%n");

                    while (!eleccion.equals("0")){
                        System.out.printf("%n1. Registrar turno %n2. Eliminar turno %n3. Ver turnos %n0. Volver a inicio %n");
                        eleccion = sc.nextLine();

                        switch (eleccion){
                            case "0":
                                System.out.println("Volviendo....");
                                break;
                            case "1":
                                System.out.println("Registrar turno");
                                Controlador.registrarTurno();
                                break;
                            case "2":
                                System.out.println("Eliminar turno");
                                Controlador.eliminarTurno();
                                break;
                            case "3":
                                System.out.println("Ver turnos");
                                Controlador.verTurnos();
                                break;
                            default:
                                System.out.println("Opción incorrecta");
                                break;
                        }
                    }
                    break;
                case "4":
                    System.out.printf("%nTarifas%n");

                    while(!eleccion.equals("0")){
                        System.out.printf("%n1. Registrar tarifa %n2. Eliminar tarifa %n3. Ver tarifas %n0. Volver a inicio %n");
                        eleccion = sc.nextLine();

                        switch (eleccion){
                            case "0":
                                System.out.println("Volviendo....");
                                break;
                            case "1":
                                System.out.println("Registrar tarifa");
                                Controlador.registrarTarifa();
                                break;
                            case "2":
                                System.out.println("Eliminar tarifa");
                                Controlador.eliminarTarifa();
                                break;
                            case "3":
                                System.out.println("Ver tarifas");
                                Controlador.verTarifas();
                                break;
                            default:
                                System.out.println("Opción incorrecta.");
                                break;
                        }
                    }
                    break;
                case "5":
                    System.out.printf("%nReservas%n");

                    while (!eleccion.equals("0")){
                        System.out.printf("%n1. Registrar reserva %n2. Eliminar reserva %n3. Registrar ingreso de socio" +
                                "%n4. Ver reservas %n5. Consultar por fecha %n0. Volver a inicio%n");
                        eleccion = sc.nextLine();

                        switch (eleccion){
                            case "0":
                                System.out.printf("%nVolviendo...%n");
                                break;
                            case "1":
                                System.out.println("Registrar reserva");
                                Controlador.registrarReserva();
                                break;
                            case "2":
                                System.out.println("Eliminar reserva");
                                Controlador.eliminarReserva();
                                break;
                            case "3":
                                System.out.println("Registrar ingreso de socio");
                                Controlador.actualizarReserva();
                                break;
                            case "4":
                                System.out.printf("%nVer reservas%n");
                                Controlador.verReservas();
                                break;
                            case "5":
                                System.out.printf("%nConsultar por fecha%n");
                                Controlador.consultarPorFecha();
                                break;
                            default:
                                System.out.printf("%nOpción incorrecta%n");
                                break;
                        }
                    }
                    break;
                case "6":
                    System.out.printf("%nPagos%n");

                    while(!eleccion.equals("0")){
                        System.out.printf("%n1. Registrar pago %n2. Eliminar pago %n3. Ver pagos %n0. Volver a inicio%n");
                        eleccion = sc.nextLine();

                       switch (eleccion){
                           case "0":
                               System.out.printf("%nVolviendo a inicio...%n");
                               break;
                           case "1":
                               System.out.printf("%nRegistrar pago%n");
                               Controlador.registrarPago();
                               break;
                           case "2":
                               System.out.printf("%nEliminar pago%n");
                               Controlador.eliminarPago();
                               break;
                           case "3":
                               System.out.printf("%nVer pagos%n");
                               Controlador.verPagos();
                               break;
                           default:
                               System.out.printf("%nOpción incorrecta%n");
                               break;
                       }
                    }
                    break;
                default:
                    System.out.println("Opción incorrecta.");
                    break;
                }



        }

}
}