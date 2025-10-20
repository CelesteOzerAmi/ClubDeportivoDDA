package Controlador;
import Dominio.*;
import Dominio.Cancha.Condicion;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public abstract class Controlador {

    // scanner para leer datos de entrada.
    static Scanner sc = new Scanner(System.in);
    // formatter para LocalDate.parse.
    static DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    //region props
    static Socio socio1 = new Socio(1, "Flavio", "Mendozo", "", "12332132", LocalDate.of(1999, 12, 31), "091232132", "URY");
    static Socio socio2 = new Socio(2, "Sandra", "Martinez", "Perez", "¨299542823", LocalDate.of(1993, 4, 12), "098343341", "URY");

    static ArrayList<String> caracteristicas1 = new ArrayList<>();
    static Cancha cancha1 = new Cancha(1, "Cancha 1", "Futbol", true, 25, Condicion.DISPONIBLE, caracteristicas1);
    static Cancha cancha3 = new Cancha(2, "Cancha 2", "Futbol", false, 25, Condicion.DISPONIBLE, caracteristicas1);
    static Cancha cancha2 = new Cancha(3, "Cancha 3", "Basketball", true, 25, Condicion.OCUPADA, caracteristicas1);
    static Cancha cancha4 = new Cancha(4, "Cancha 4", "Futbol", true, 25, Condicion.DISPONIBLE, caracteristicas1);
    static Cancha cancha5 = new Cancha(5, "Cancha 5", "Basketball", true, 16, Condicion.REPARACION, caracteristicas1);

    static Turno turno1 = new Turno(1, "Futbol", LocalTime.of(9, 30), LocalTime.of(11, 30), Duration.between(LocalTime.of(9, 30), LocalTime.of(11, 30)), true);
    static Turno turno2 = new Turno(2, "Futbol", LocalTime.of(12, 30), LocalTime.of(15, 30), Duration.between(LocalTime.of(12, 30), LocalTime.of(15, 30)), true);
    static Turno turno3 = new Turno(3, "Basketball", LocalTime.of(9, 30), LocalTime.of(11, 30), Duration.between(LocalTime.of(9, 30), LocalTime.of(11, 30)), true);
    static Turno turno4 = new Turno(4, "Basketball", LocalTime.of(12, 30), LocalTime.of(15, 30), Duration.between(LocalTime.of(12, 30), LocalTime.of(15, 30)), true);

    static Tarifa t = new Tarifa(1, turno1, cancha1, false, 1350.0, LocalDate.of(2025, 12, 31), true);
    static Tarifa t2 = new Tarifa(2, turno1, cancha1, true, 1550.0, LocalDate.of(2025, 12, 31), true);
    static Tarifa t3 = new Tarifa(3, turno2, cancha1, false, 1350.0, LocalDate.of(2025, 12, 31), true);
    static Tarifa t4 = new Tarifa(4, turno2, cancha1, true, 1550.0, LocalDate.of(2025, 12, 31), true);

    static Reserva r = new Reserva(1, socio1, cancha1, LocalDate.of(2025, 9, 29), LocalDate.of(2025, 10, 9), false, turno1, t, false, 200, false, "" );
    static Reserva r2 = new Reserva(2, socio2, cancha1, LocalDate.of(2025, 9, 29), LocalDate.of(2025, 10, 9), true, turno2, t3, true, 0, false, "" );

    public static void agregarProps(){
        canchas.add(cancha1);
        canchas.add(cancha2);
        canchas.add(cancha3);
        canchas.add(cancha4);
        canchas.add(cancha5);
        socios.add(socio1);
        socios.add(socio2);
        turnos.add(turno1);
        turnos.add(turno2);
        turnos.add(turno3);
        turnos.add(turno4);
        tarifas.add(t);
        tarifas.add(t2);
        tarifas.add(t3);
        tarifas.add(t4);
        reservas.add(r);
        reservas.add(r2);
    }

    //endregion

    //region canchas

    // se declara array para almacenar canchas registradas
    static final ArrayList<Cancha> canchas = new ArrayList<>();

    // metodo para registrar nuevas canchas
    public static void registrarCancha() {
        // nombre.
        String nombre = "";
        while(nombre.isEmpty()){
            System.out.println("Ingrese nombre");
            nombre = sc.nextLine();
            if(nombre.equals("0")){
                System.out.println("Cancelado.");
                return;
            }
            String inicial = nombre.substring(0, 1).toUpperCase();
            String resto = nombre.substring(1).toLowerCase();
            nombre = inicial.concat(resto);
        }

        // deporte.
        String deporte = "";
        while(deporte.isEmpty()){
            System.out.println("Ingrese deporte");
            deporte = sc.nextLine();
            if(deporte.equals("0")){
                System.out.println("Cancelado.");
                return;
            }
            String inicial = deporte.substring(0, 1).toUpperCase();
            String resto = deporte.substring(1).toLowerCase();
            deporte = inicial.concat(resto);
        }

        // cubierta o no.
        System.out.printf("¿Es una cancha cubierta? %n1. Sí %n2. No %n");
        boolean cubierta = (sc.nextLine().equals("1"));

        // capacidad.
        int capacidad = 0;
        try {
            System.out.println("Ingrese capacidad");
            capacidad = Integer.parseInt(sc.nextLine());
            if(capacidad <= 0){
                System.out.println("Capacidad no puede ser menor o igual a 0.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error en capacidad ingresada. Reintente");
            return;
        }

        // condición.
        System.out.printf("Seleccione condición %n1. Disponible. %n2. Reservada. %n3. Ocupada %n");
        Condicion condicion = null;
        switch (sc.nextLine()) {
            case "1" -> condicion = Condicion.DISPONIBLE;
            case "2" -> condicion = Condicion.RESERVADA;
            case "3" -> condicion = Condicion.OCUPADA;
            default -> System.out.println("Opción incorrecta");
        }

        // características.
        System.out.println("Ingrese características (opcional)");
        ArrayList<String> caracteristicas = new ArrayList<>();
        String caracteristica = sc.nextLine();
        while(!caracteristica.isEmpty()) {
            caracteristicas.add(caracteristica);
            caracteristica = sc.nextLine();
            System.out.println("Presione Enter para finalizar.");
        }

        if(condicion == null){
            System.out.println("Error en datos. Reintente");
            return;
        }

        // cancha.
        Cancha newCancha = new Cancha(canchas.size() + 1, nombre, deporte, cubierta, capacidad, condicion, caracteristicas);

        if(canchas.add(newCancha)){
            System.out.println("Registro exitoso.");
            System.out.println(newCancha);
            return;
        }
        System.out.println("Error al registrar cancha. Reintente");
    }

    // metodo para eliminar cancha registrada.
    public static void eliminarCancha() {
        // se valida que haya canchas registradas.
        if(canchas.isEmpty()){
            System.out.println("Sin canchas registradas");
            return;
        }

        System.out.println("Ingrese id de cancha a eliminar (0: Salir)");
        try{
            int id = Integer.parseInt(sc.nextLine());
            if(id == 0){
                System.out.println("Cancelado.");
                return;
            }
            for(Cancha c : canchas){
                if(c.getIdCancha() == id){
                    System.out.println(c);
                    System.out.printf("1. Eliminar %n2. Cancelar %n");
                    if(sc.nextLine().equals("1")){
                        canchas.remove(c);
                        System.out.println("Eliminada correctamente");
                        return;
                    }
                    System.out.println("Cancelado.");
                    return;
                }
            }
            System.out.println("No existe cancha con ese id");
        }
        catch (NumberFormatException e){
            System.out.println("Error en formato de id. Intente nuevamente");
        }
    }

    // metodo para ver canchas registradas.
    public static void verCanchas(){

        // se valida si hay canchas registradas.
        if(canchas.isEmpty()){
            System.out.println("No hay canchas registradas");
            return;
        }

        // elección de consulta de canchas.
        System.out.printf("1. Todas las canchas %n2. Por deporte %n3. Por nombre %n" +
                "4. Por condicion %n5. Ver estado según fecha %n0. Salir%n");

        switch (sc.nextLine()){
            case "0":
                System.out.println("Saliendo...");
                break;
            case "1":
                System.out.println("Canchas registradas");
                for(Cancha cancha : canchas){
                    System.out.println(cancha.toString());
                }
                break;
            case "2":
                System.out.println("Ingrese deporte");
                String deporteIngresado = sc.nextLine();
                if(deporteIngresado.isBlank()){
                    System.out.println("Debe ingresar deporte.");
                    return;
                }
                String inicial = deporteIngresado.substring(0, 1).toUpperCase();
                String resto = deporteIngresado.substring(1).toLowerCase();
                String deporte = inicial.concat(resto);

                if(canchas.stream().noneMatch(c -> c.getDeporte().equalsIgnoreCase(deporte)
                                                || c.getDeporte().contains(deporte))){
                    System.out.println("Sin canchas para este deporte.");
                    return;
                }
                for(Cancha c : canchas){
                    if(c.getDeporte().contains(deporte) || c.getDeporte().equalsIgnoreCase(deporte)){
                        System.out.println(c);
                    }
                }
                break;
            case "3":
                System.out.println("Ingrese nombre");
                String nombreIngresado = sc.nextLine();
                if(nombreIngresado.isBlank()){
                    System.out.println("Debe ingresar deporte.");
                    return;
                }
                String i = nombreIngresado.substring(0, 1).toUpperCase();
                String r = nombreIngresado.substring(1).toLowerCase();
                String nombre = i.concat(r);

                if(canchas.stream().noneMatch(c -> c.getNombre().equalsIgnoreCase(nombre)
                                                || c.getNombre().contains(nombre))){
                    System.out.println("Sin canchas con este nombre.");
                    return;
                }
                for(Cancha c : canchas){
                    if(c.getNombre().contains(nombre) || c.getNombre().equalsIgnoreCase(nombre)){
                        System.out.println(c);
                    }
                }
                break;
            case "4":
                System.out.println("Canchas según condición.");
                // canchas cubiertas.
                List<Cancha> canchasCubiertas;
                canchasCubiertas = canchas.stream()
                        .filter(Cancha::isCubierta)
                        .toList();
                if(!canchasCubiertas.isEmpty()){
                    System.out.println("Canchas cubiertas");
                    for(Cancha c : canchasCubiertas){
                        System.out.println(c.resumen());
                    }
                } else {
                    System.out.println("Sin canchas cubiertas");
                }
                // canchas descubiertas.
                List<Cancha> canchasDescubiertas;
                canchasDescubiertas = canchas.stream()
                        .filter(c -> !c.isCubierta())
                        .toList();
                if(!canchasDescubiertas.isEmpty()){
                    System.out.println("Canchas descubiertas");
                    for(Cancha c : canchasDescubiertas){
                        System.out.println(c.resumen());
                    }
                } else {
                    System.out.println("Sin canchas descubiertas");
                }
                break;

            case "5":
                System.out.println("Ingrese fecha para consulta (dd/mm/aaaa)");
                LocalDate fechaConsultada;
                try {
                     fechaConsultada = LocalDate.parse(sc.nextLine(), f);
                } catch (DateTimeParseException e){
                    System.out.println("Error en formato de fecha. Intente nuevamente");
                    return;
                }

                // disponibles
                ArrayList<Cancha> canchasDisponibles = new ArrayList<>();
                // reservadas.
                if(reservas.stream().noneMatch(res -> res.getFecha().equals(fechaConsultada))){
                    System.out.println("Sin canchas reservadas.");
                    canchasDisponibles = canchas;
                } else {
                    System.out.println("Canchas reservadas");
                    for(Cancha c : canchas){
                        // se muestra cada cancha que tenga una reserva para la misma fecha.
                        if(reservas.stream().anyMatch(re -> re.getCancha() == c && re.getFecha().equals(fechaConsultada))){
                            System.out.println(c.resumen());
                        } else {
                            // si no hay ninguna reserva para esa cancha, se agrega a disponibles.
                            canchasDisponibles.add(c);
                        }
                    }
                }

                // mostrar canchas disponibles.
                if(!canchasDisponibles.isEmpty()){
                    System.out.println("Canchas disponibles");
                    for(Cancha c : canchasDisponibles){
                        System.out.println(c.resumen());
                    }
                } else {
                    System.out.println("Sin canchas disponibles para la fecha");
                }
                break;


            default:
                System.out.println("Opción incorrecta");
                break;
        }

    }

    // metodo para declarar cancha en reparación/disponible.
    public static void enReparacion(){
        System.out.printf("%n1. Ver todas las canchas en reparación %n2. Modificar estado de una cancha %n0. Salir %n");
        switch (sc.nextLine()){
            case "0":
                System.out.println("Volviendo...");
                break;
            case "1":
                System.out.println("Canchas en reparación");
                if(canchas.stream().noneMatch(c -> c.getCondicion().equals(Condicion.REPARACION))){
                    System.out.println("No hay canchas en reparación.");
                    return;
                }
                for(Cancha c : canchas){
                    if(c.getCondicion().equals(Condicion.REPARACION)){
                        System.out.println(c.resumen());
                    }
                }
                break;
            case "2":
                System.out.println("Modificar estado de cancha");
                System.out.println("Ingrese id de cancha:");
                int idCancha = 0;
                try {
                    idCancha = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Error en formato de id. Intente nuevamente");
                }
                int finalIdCancha = idCancha;
                Cancha cancha = canchas.stream()
                        .filter(c -> c.getIdCancha() == finalIdCancha)
                        .findFirst()
                        .orElse(null);
                if(cancha == null){
                    System.out.println("Cancha no existe. Intente nuevamente");
                    return;
                }
                System.out.printf("%n %s Estado actual: %s %n", cancha.resumen(), cancha.getCondicion());
                if(cancha.getCondicion().equals(Condicion.REPARACION)){
                    System.out.println("1. Modificar estado a Disponible | 0. Cancelar");
                    if(sc.nextLine().equals("1")){
                        cancha.setCondicion(Condicion.DISPONIBLE);
                        System.out.println("Estado modificado correctamente");
                        System.out.printf("%n %s Estado actual: %s %n", cancha.resumen(), cancha.getCondicion());
                        return;
                    }
                    System.out.println("Cancelado.");
                    return;
                }
                if(cancha.getCondicion().equals(Condicion.DISPONIBLE)){
                    System.out.println("1. Modificar estado a En Reparación | 0. Cancelar");
                    if(sc.nextLine().equals("1")){
                        if(reservas.stream().noneMatch(r -> !r.getFecha().isBefore(LocalDate.now())
                                                        && r.getCancha().equals(cancha))){
                            cancha.setCondicion(Condicion.REPARACION);
                            System.out.println("Estado modificado correctamente");
                            System.out.printf("%n %s Estado actual: %s %n", cancha.resumen(), cancha.getCondicion());
                            return;
                        }
                        System.out.println("Hay reservas vigentes para esta cancha. Revise reservas antes de declarar" +
                                " cancha en reparación.");
                        return;
                    }
                    System.out.println("Cancelado.");
                    return;
                }
                System.out.println("Cancha ocupada o reservada para hoy. No es posible modificar estado. ");
                break;
            default:
                System.out.println("Opción incorrecta.");
                break;
        }
    }

    //endregion

    //region socios

    // se declara arraylist para almacenar socios registrados.
    public static final ArrayList<Socio> socios = new ArrayList<>();

    // metodo para registrar nuevos socios.
    public static void registrarSocio() {

        // nombre.
        String nombre = "";
        while (nombre.isEmpty()) {
            System.out.println("Ingrese nombre (0: Salir)");
            nombre = sc.nextLine();
            if (nombre.equals("0")) {
                System.out.println("Cancelado.");
                return;
            }
            String inicial = nombre.substring(0, 1).toUpperCase();
            String resto = nombre.substring(1).toLowerCase();
            nombre = inicial.concat(resto);
        }

        // apellido paterno.
        String apaterno = "";
        while (apaterno.isEmpty()) {
            System.out.println("Ingrese primer apellido (0: Salir)");
            apaterno = sc.nextLine();
            if (apaterno.equals("0")) {
                System.out.println("Cancelado.");
                return;
            }
            String inicial = apaterno.substring(0, 1).toUpperCase();
            String resto = apaterno.substring(1).toLowerCase();
            apaterno = inicial.concat(resto);
        }

        // apellido materno.
        System.out.println("Ingrese segundo apellido (opcional).");
        String amaterno = sc.nextLine();
        if(!amaterno.isBlank()){
            String inicial = amaterno.substring(0, 1).toUpperCase();
            String resto = amaterno.substring(1).toLowerCase();
            amaterno = inicial.concat(resto);
        }

        // documento.
        String num_documento = "";
        while (num_documento.isEmpty()) {
            System.out.println("Ingrese número de documento (0: Salir)");
            num_documento = sc.nextLine();
            if (num_documento.equals("0")) {
                System.out.println("Cancelado.");
                return;
            }
        }

        // fecha de nacimiento.
        System.out.println("Ingrese fecha de nacimiento (dd/mm/aaaa)");
        String dateString = sc.nextLine();
        LocalDate fecha_nacimiento;
        try {
            fecha_nacimiento = LocalDate.parse(dateString, f);
            // se valida que la fecha sea anterior a hoy.
            if(!fecha_nacimiento.isBefore(LocalDate.now())){
                System.out.println("Ingrese fecha correcta.");
                return;
            }
        } catch (DateTimeParseException e){
            System.out.println("Error en formato de fecha. Intente nuevamente.");
            return;
        }

        // teléfono.
        String telefono = "";
        while(telefono.isEmpty()){
            System.out.println("Ingrese telefono (0: Salir)");
            telefono = sc.nextLine();
            if(telefono.equals("0")){
                System.out.println("Cancelado.");
                return;
            }
        }

        // país.
        String pais = "";
        while(pais.isEmpty()){
            System.out.println("Ingrese país de nacimiento (0: Salir)");
            pais = sc.nextLine();
            if(pais.equals("0")){
                System.out.println("Cancelado.");
                return;
            }
            pais = pais.toUpperCase();
        }

        // socio.
        Socio newSocio = new Socio(socios.size() + 1, nombre, apaterno, amaterno, num_documento, fecha_nacimiento, telefono, pais);
        if(socios.add(newSocio)){
            System.out.println("Registro exitoso.");
            System.out.println(newSocio);
            return;
        }

        // error si no es posible registrar socio.
        System.out.println("Error al registrar socio. Intente nuevamente.");
    }

    // metodo para eliminar socio registrado.
    public static void eliminarSocio() {
        // se valida que haya socios registrados.
        if(socios.isEmpty()){
            System.out.println("Sin socios registrados");
            return;
        }

        System.out.println("Ingrese id de socio a eliminar (0: Salir)");
        try{
            int id = Integer.parseInt(sc.nextLine());
            if(id == 0){
                System.out.println("Cancelado.");
                return;
            }
            for(Socio s : socios){
                if(s.getIdSocio() == id){
                    System.out.println(s);
                    System.out.printf("1. Eliminar %n2. Cancelar %n");
                    if(sc.nextLine().equals("1")){
                        socios.remove(s);
                        System.out.println("Eliminado correctamente");
                        return;
                    }
                    System.out.println("Cancelado.");
                    return;
                }
            }
            System.out.println("No existe socio con ese id");
        }
        catch (NumberFormatException e){
            System.out.println("Error en formato de id. Intente nuevamente");
        }
    }

    // metodo para ver socios registrados.
    public static void verSocios(){

        if(!socios.isEmpty()){
            socios.sort(Comparator.comparing(Socio::getIdSocio));
            for(Socio s : socios){
                System.out.println(s.resumen());
            }
        } else {
            System.out.println("Sin socios registrados");
        }
    }

    // metodo para ver detalle de socio.
    public static void verDetalleSocio() {
        // se valida que haya socios registrados.
        if(socios.isEmpty()){
            System.out.println("Sin socios registrados");
            return;
        }

        System.out.println("Ingrese id de socio (0: Salir)");
        try{
            int id = Integer.parseInt(sc.nextLine());
            if(id == 0){
                System.out.println("Cancelado.");
                return;
            }
            for(Socio s : socios){
                if(s.getIdSocio() == id){
                    System.out.println("Socio encontrado");
                    System.out.println(s);
                    return;
                }
            }
            System.out.println("No existe socio con ese id");
        }
        catch (NumberFormatException e){
            System.out.println("Error en formato de id. Intente nuevamente");
        }
    }

    //endregion

    //region turnos

    // se declara arraylist para almacenar turnos registrados.
    static ArrayList<Turno> turnos = new ArrayList<>();

    // metodo para registrar nuevos turnos.
    public static void registrarTurno(){

        // deporte.
        String deporte = "";
        while(deporte.isEmpty()){
            System.out.println("Ingrese deporte (0: Salir)");
            deporte = sc.nextLine();
            if(deporte.equals("0")){
                System.out.println("Cancelado.");
                return;
            }
            String inicial = deporte.substring(0, 1).toUpperCase();
            String resto = deporte.substring(1).toLowerCase();
            deporte = inicial.concat(resto);
        }

        // hora inicio, hora fin y duración.
        LocalTime horaInicio = null;
        LocalTime horaFin = null;
        Duration duracion = null;
        try {
            DateTimeFormatter h = DateTimeFormatter.ofPattern("HH:mm");

            System.out.println("ingrese hora inicio (hh:mm, formato 24 hs)");
            horaInicio = LocalTime.parse(sc.nextLine(), h);

            System.out.println("ingrese hora fin (hh:mm, formato 24 hs)");
            horaFin = LocalTime.parse(sc.nextLine(), h);

            if(!horaFin.isAfter(horaInicio)){
                System.out.println("Hora de fin no puede ser anterior a hora de inicio.");
                return;
            }

            duracion = Duration.between(horaInicio, horaFin);
            } catch (Exception e) {
                System.out.println("Error en formato de fecha. Intente nuevamente");
                return;
            }

        // turno.
        LocalTime finalHoraInicio = horaInicio;
        LocalTime finalHoraFin = horaFin;
        String finalDeporte = deporte;

        // se verifica que no exista turno ya creado para misma hora inicio y fin y mismo deporte.
        if(turnos.stream()
                .anyMatch(t -> t.isHabilitado()
                        && t.getHoraInicio().equals(finalHoraInicio)
                        && t.getHoraFin().equals(finalHoraFin)
                        && t.getDeporte().equals(finalDeporte))){
            System.out.println("Ya existe turno igual.");
            return;
        }

        // se agrega turno a arraylist.
        Turno turno = new Turno(turnos.size() + 1, deporte, horaInicio, horaFin, duracion, true);
        if(turnos.add(turno)){
            System.out.println("Registro exitoso!!");
            System.out.println(turno);
        } else {
            System.out.println("Error al registrar turno. Intente nuevamente.");
        }
    }

    // metodo para ver turnos habilitados.
    public static void verTurnos(){

        if(!turnos.isEmpty()){
            for(Turno turno : turnos){
                if(turno.isHabilitado()){
                    System.out.println(turno);
                }
            }
            return;
        }
        System.out.println("Sin turnos registrados");
    }

    // metodo para eliminar turnos.
    public static void eliminarTurno(){
        verTurnos();
        System.out.println("Ingrese id de turno");
        try {
            int idTurno = Integer.parseInt(sc.nextLine());
            Turno turno = turnos.stream()
                    .filter(t -> t.getId() == idTurno
                            && t.isHabilitado())
                    .findFirst()
                    .orElse(null);
            if(turno == null){
                System.out.println("Id incorrecto. Intente nuevamente.");
                return;
            }
            System.out.println(turno);

            // se verifica que no haya reservas para hoy o posteriores con ese turno.
            if(reservas.stream().anyMatch(r -> r.getTurno().equals(turno)
                                         && !r.getFecha().isBefore(LocalDate.now()))){
                System.out.println("Hay reservas vigentes para este turno. Revise reservas" +
                        " antes de eliminar.");
                return;
            }

            System.out.println("1. Eliminar | 2. Cancelar");
            if(sc.nextLine().equals("1")){
                // se declara turno como inhabilitado para que no se puedan registrar nuevas reservas,
                // en lugar de eliminarlo, que podría generar conflicto con reservas anteriores a hoy.
                turno.setHabilitado(false);
                System.out.println("Turno eliminado correctamente");
                return;
            }
            System.out.println("Cancelado.");

        } catch (NumberFormatException e) {
            System.out.println("Error en formato de turno. Intente nuevamente.");
        }
    }

    //endregion

    //region tarifas
    // se declara arraylist para almacenar tarifas registradas.
    public final static ArrayList<Tarifa> tarifas = new ArrayList<>();

    // metodo para registrar tarifas.
    public static void registrarTarifa(){

        // se registra turno.
        System.out.println("Ingrese id de turno");
        verTurnos();
        Turno turno;
        try {
            int idTurno = Integer.parseInt(sc.nextLine());
            turno = turnos.stream()
                    .filter(t -> t.getId() == idTurno && t.isHabilitado())
                    .findFirst()
                    .orElse(null);
            if(turno == null){
                System.out.println("Turno no existe. Intente nuevamente. ");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error en id ingresado.");
            return;
        }

        // se registra cancha.
        System.out.println("Ingrese id de cancha");
        verCanchas();
        Cancha cancha;
        try {
            int idCancha = Integer.parseInt(sc.nextLine());
            cancha = canchas.stream()
                    .filter(c -> c.getIdCancha() == idCancha)
                    .findFirst()
                    .orElse(null);
            if(cancha == null){
                System.out.println("Cancha no existe. Intente nuevamente.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error en id ingresado.");
            return;
        }

        // tarifa especial (feriados).
        System.out.println("Indique tipo de tarifa");
        System.out.println("1. Estándar | 2. Especial (feriados)");
        boolean tarifaEspecial = (sc.nextLine().equals("2"));

        // costo.
        System.out.println("Ingrese costo de tarifa");
        double costo = 0;
        try {
            costo = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error en costo ingresado. Reintente.");
            return;
        }


        // tarifa.
        Tarifa nuevaTarifa = new Tarifa(tarifas.size() + 1,turno, cancha, tarifaEspecial, costo, LocalDate.now(), true);
        if(tarifas.add(nuevaTarifa)){
            // si se agrega nueva tarifa, se busca si existe tarifa vieja para el mismo turno, cancha y fecha especial.
            Tarifa tarifaVieja = tarifas.stream()
                            .filter(t -> t.isVigente()
                                    && t.getId() != nuevaTarifa.getId()
                                    && t.getTurno().equals(turno)
                                    && t.getCancha().equals(cancha)
                                    && t.isTarifaEspecial() == tarifaEspecial)
                            .findFirst()
                            .orElse(null);
            // tarifa vieja deja de estar vigente.
            if(tarifaVieja != null){
                tarifaVieja.setVigente(false);
            }
            System.out.println("Registro exitoso.");
            System.out.println(nuevaTarifa);
            return;
        }
        System.out.println("Error al registrar tarifa. Reintente.");
    }

    // metodo para eliminar tarifa.
    public static void eliminarTarifa(){
        verTarifas();
        System.out.println("Ingrese id de tarifa a eliminar:");
        try {
            int idTarifa = Integer.parseInt(sc.nextLine());
            Tarifa tarifa = tarifas.stream()
                    .filter(t -> t.isVigente()
                            && t.getId() == idTarifa)
                    .findFirst()
                    .orElse(null);
            if(tarifa == null){
                System.out.println("No existe tarifa con ese id.");
                return;
            }
            System.out.println(tarifa);

            // se verifica que no haya reservas vigentes con esa tarifa.
            if(reservas.stream().anyMatch(r -> r.getTarifa().equals(tarifa)
                                            && !r.getFecha().isBefore(LocalDate.now()))){
                System.out.println("Hay reservas vigentes con esta tarifa. Revise reservas antes" +
                        " de cancelar.");
                return;
            }

            System.out.println("1. Eliminar | 0. Cancelar");
            if(sc.nextLine().equals("1")){
                // se setea tarifa a no vigente para no generar conflicto con reservas pasadas.
                tarifa.setVigente(false);
                System.out.println("Tarifa eliminada correctamente.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error en id ingresado. Intente nuevamente.");
        }
    }

    // metodo para ver tarifas.
    public static void verTarifas(){

        if(!tarifas.isEmpty()){
            for(Tarifa tarifa : tarifas){
                System.out.println(tarifa.toString());
            }
            return;
        }
        System.out.println("Sin tarifas registradas");

    }
    //endregion

    //region reservas

    // se inicializa arraylist para almacenar reservas registradas.
    public static ArrayList<Reserva> reservas = new ArrayList<>();

    // función para ver reservas registradas.
    public static void verReservas(){

        // se verifica si existen reservas.
        if(!reservas.isEmpty()){
            System.out.println("1. Reservas vigentes | 2. Reservas pasadas | 0. Volver");
            // se muestran reservas vigentes.
            String opcionReserva = sc.nextLine();
            if(opcionReserva.equals("1")){
                ArrayList<Reserva> reservasVigentes = new ArrayList<>();
                for(Reserva r : reservas){
                    if(!r.getFecha().isBefore(LocalDate.now())){
                        reservasVigentes.add(r);
                        System.out.println(r);
                    }
                }
                if(reservasVigentes.isEmpty()){
                    System.out.println("No hay reservas vigentes");
                }
            }
            // se muestran reservas pasadas.
            else if(opcionReserva.equals("2")){
                ArrayList<Reserva> reservasNoVigentes = new ArrayList<>();
                for(Reserva r : reservas){
                    if(r.getFecha().isBefore(LocalDate.now())){
                        reservasNoVigentes.add(r);
                        System.out.println(r);
                    }
                }
                if(reservasNoVigentes.isEmpty()){
                    System.out.println("No hay reservas no vigentes");
                }
            }
            else {
                System.out.println("Volviendo...");
                return;
            }
        } else {
            // mensaje error si no hay reservas registradas.
            System.out.println("No hay reservas registradas.");
        }

    }

    // metodo para registrar nueva reserva.
    public static void registrarReserva(){

        // se verifica que haya suficientes datos registrados para reservar.
        if(socios.isEmpty()){
            System.out.println("Sin socios registrados. Registre socio antes de continuar.");
            return;
        }
        if(canchas.isEmpty()){
            System.out.println("Sin canchas registradas. Registre cancha antes de continuar.");
            return;
        }
        if(turnos.isEmpty()){
            System.out.println("Sin turnos registrados. Registre turno antes de continuar");
            return;
        }
        if(tarifas.isEmpty()){
            System.out.println("Sin tarifas registradas. Registre tarifa antes de continuar");
            return;
        }

        // socio.
        Socio socio = null;
        System.out.println("Ingrese id de socio");
        verSocios();
        while(socio == null){
            try {
                int idSocio = Integer.parseInt(sc.nextLine());
                socio = socios.stream()
                        .filter(s -> s.getIdSocio() == idSocio)
                        .findFirst()
                        .orElse(null);
                if(socio == null){
                    System.out.println("Socio no encontrado. Reintente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingrese id correcto");
            }
        }
        System.out.println(socio.resumen());

        // fecha de reserva.
        LocalDate fechaReserva = LocalDate.now();

        // fecha reservada.
        System.out.println("Ingrese fecha para la reserva (dd/mm/aaaa)");
        LocalDate fecha = null;
        while(fecha == null){
            try {
                fecha = LocalDate.parse(sc.nextLine(), f);
                if(fecha.isBefore(LocalDate.now())){
                    System.out.println("Fecha no puede ser anterior a hoy.");
                    fecha = null;
                }
            } catch (DateTimeParseException e){
                System.out.println("Error en el formato de la fecha. Intente nuevamente.");
            }
        }

        // tarifa especial.
        System.out.printf("%n¿Aplica tarifa especial? (feriados, etc) %n1. Sí %n2. No %n");
        boolean fechaEspecial = (Integer.parseInt(sc.nextLine()) == 1);

        // deporte.
        String deporte = "";
        while(deporte.isEmpty()){
            System.out.println("Ingrese deporte (0: Salir)");
            deporte = sc.nextLine();
            if(deporte.equals("0")){
                System.out.println("Cancelado.");
                return;
            }
        }

        final String finalDeporte = deporte;
        // list para almacenar los turnos para ese deporte que están activos,
        // ordenados por hora de inicio.
        List<Turno> turnosParaDeporte = turnos.stream()
                .filter(t -> t.getDeporte().equalsIgnoreCase(finalDeporte)
                        && t.isHabilitado())
                .sorted(Comparator.comparing(Turno::getHoraInicio))
                .toList();

        // list para almacenar canchas para ese deporte.
        List<Cancha> canchasParaDeporte = canchas.stream()
                .filter(c -> c.getDeporte().equalsIgnoreCase(finalDeporte))
                .toList();



        // map para almacenar para cada turno las canchas disponibles.
        Map<Turno, List<Cancha>> turnosConCanchasDisponibles = new LinkedHashMap<>();

        // metodo para filtrar canchas disponibles para ese turno y deporte.
        for (Turno turno : turnosParaDeporte) {

            // list para almacenar canchas para ese deporte y sin reservas para cada turno.
            List<Cancha> canchasLibres = new ArrayList<>();

            for (Cancha cancha : canchasParaDeporte) {
                LocalDate finalFecha = fecha;
                // para cada cancha, se evalúa si tiene reserva para esa fecha y ese turno.
                boolean estaReservada = reservas.stream()
                        .anyMatch(r -> r.getFecha().equals(finalFecha)
                                && r.getTurno().equals(turno)
                                && r.getCancha().equals(cancha));
                boolean enReparacion = (cancha.getCondicion().equals(Condicion.REPARACION));
                if (!estaReservada && !enReparacion) {
                    // si la cancha no tiene reserva para esa fecha y turno, y no está en reparación
                    // se agrega a canchasLibres.
                    canchasLibres.add(cancha);
                }
            }
            if (!canchasLibres.isEmpty()) {
                // si hay al menos una cancha disponible, se agrega(n) al map
                // indicando el turno para el cual están disponibles.
                turnosConCanchasDisponibles.put(turno, canchasLibres);
            }
        }

        // se muestran turnos disponibles.
        System.out.println("Turnos disponibles");
        // por cada registro (entry) del map turnosConCanchasDisponibles,
        // se toma el key (turno) y el value (lista de canchas).
        for (Map.Entry<Turno, List<Cancha>> entry : turnosConCanchasDisponibles.entrySet()) {
            Turno turno = entry.getKey();
            List<Cancha> disponibles = entry.getValue();
            // se muestran el turno con cada cancha.
            System.out.printf("Id turno %d | %s | Canchas disponibles: %s%n",
                    turno.getId(),
                    turno.getHoraInicio(),
                    disponibles.stream().map(Cancha::getNombre).collect(Collectors.joining(", "))
            );
        }

        // mensaje si no hay turnos para ninguna cancha de ese deporte.
        if (turnosConCanchasDisponibles.isEmpty()) {
            System.out.println("No hay turnos disponibles para ese deporte en esa fecha.");
            return;
        }

        // se elige turno.
        System.out.println("Ingrese id del turno que desea reservar");
        int idTurnoElegido = 0;
        try {
            idTurnoElegido = Integer.parseInt(sc.nextLine());

        } catch (NumberFormatException e) {
            System.out.println("Error en id ingresado. Intente nuevamente.");
            return;
        }

        // se almacena turno con id ingresado.
        int finalIdTurnoElegido = idTurnoElegido;
        Turno turnoSeleccionado = turnosConCanchasDisponibles.keySet().stream()
                .filter(t -> t.getId() == finalIdTurnoElegido)
                .findFirst()
                .orElse(null);

        if (turnoSeleccionado == null) {
            System.out.println("Turno no válido.");
            return;
        }

        // se muestran canchas disponibles en el map para ese turno.
        List<Cancha> canchasDisponibles = turnosConCanchasDisponibles.get(turnoSeleccionado);
        System.out.println("Canchas disponibles para ese turno:");
        for (Cancha cancha : canchasDisponibles) {
            System.out.printf("Id %d: %s %n", cancha.getIdCancha(), cancha.getNombre());
        }

        // elegir cancha.
        System.out.println("Ingrese id de la cancha que desea reservar:");
        int idCanchaElegida = 0;
        try{
            idCanchaElegida = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error en id ingresado. Intente nuevamente.");
            return;
        }

        // se almacena la cancha con el id ingresado.
        int finalIdCanchaElegida = idCanchaElegida;
        Cancha canchaSeleccionada = canchasDisponibles.stream()
                .filter(c -> c.getIdCancha() == finalIdCanchaElegida)
                .findFirst()
                .orElse(null);

        // mensaje si no existe cancha con ese id.
        if (canchaSeleccionada == null) {
            System.out.println("Cancha no válida.");
            return;
        }

        // se asigna tarifa.
        Tarifa tarifa = tarifas.stream()
                .filter(t -> t.isVigente() && t.getCancha() == canchaSeleccionada && t.getTurno() == turnoSeleccionado && t.isTarifaEspecial() == fechaEspecial)
                .findFirst()
                .orElse(null);

        // mensaje si no hay tarifa aplicable.
        if(tarifa == null){
            System.out.println("No se encontró tarifa vigente para esta cancha y este turno. Revise tarifas registradas");
            return;
        }

        // se muestra tarifa.
        System.out.println("Tarifa aplicable: " + tarifa.getCosto());

        // elección de reserva prepaga.
        System.out.printf("%n¿Abona total ahora?%n1. Sí %n2. No%n");
        boolean prepago = (Integer.parseInt(sc.nextLine()) == 1);
        double senia = 0;

        // si no es prepaga, se debe ingresar seña.
        if(!prepago){
            System.out.println("Ingrese seña");
            while(senia == 0){
                try {
                    senia = Double.parseDouble(sc.nextLine());
                    if(senia >= tarifa.getCosto()){
                        System.out.println("Seña no puede ser mayor al costo de la tarifa.");
                        senia = 0;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error en seña ingresada. Reintente");
                }
            }
        }

        // observaciones.
        System.out.printf("%n¿Desea registrar observaciones adicionales? %n1. Registrar %n2. Cancelar%n");
        String observaciones = "";
        if(sc.nextLine().equals("1")){
            System.out.println("Observaciones:");
            observaciones = sc.nextLine();
        }

        // reserva.
        Reserva r = new Reserva(reservas.size() + 1, socio, canchaSeleccionada, fechaReserva, fecha,
                fechaEspecial, turnoSeleccionado, tarifa, prepago, senia, false, observaciones);
        if(reservas.add(r)){
            System.out.println("Reserva registrada correctamente");
            System.out.println(r.resumen());
            return;
        }
        System.out.println("Error al registrar reserva. Intente nuevamente");
    }

    // metodo para eliminar reserva según id ingresado.
    public static void eliminarReserva(){

        ArrayList<Reserva> reservasVigentes = new ArrayList<>();
        for(Reserva r : reservas){
            if(!r.getFecha().isBefore(LocalDate.now())){
                reservasVigentes.add(r);
                System.out.println(r.resumen());
            }
        }
        if(reservasVigentes.isEmpty()){
            System.out.println("No hay reservas vigentes.");
            return;
        }

        System.out.println("Ingrese id de reserva");

        try {
            int id = Integer.parseInt(sc.nextLine());
            // se busca reserva por id.
            Reserva res = reservasVigentes.stream()
                    .filter(r -> r.getIdReserva() == id)
                    .findFirst()
                    .orElse(null);
            // si existe reserva, se puede eliminar.
            if(res != null){
                System.out.println(res);
                System.out.printf("%n1. Eliminar reserva %n2. Cancelar %n");
                if(sc.nextLine().equals("1")){
                    reservas.remove(res);
                    System.out.println("Reserva eliminada correctamente");
                    return;
                }
                System.out.println("Operación cancelada");
            } else {
                // mensaje error al no encontrar reserva con ese id.
                System.out.println("No se encontró reserva con ese id");
            }
        } catch (NumberFormatException e) {
            // mensaje error al no ingresar id integer.
            System.out.println("Error en id de reserva. Reintente.");
        }
    }

    // metodo para actualizar reserva cuando ingresa el socio.
    public static void actualizarReserva(){
        System.out.println("Ingrese id de reserva");
        int id = 0;
        try {
            id = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error en formato de id. Intente nuevamente.");
            return;
        }
        int finalId = id;
        // se almacena reserva con ese id.
        Reserva res = reservas.stream()
                .filter(r -> r.getIdReserva() == finalId)
                .findFirst()
                .orElse(null);

        // se verifica que la reserva sea para fecha hoy o antes.
        if(res != null && !res.getFecha().isAfter(LocalDate.now())){
            System.out.println(res.resumen());
            System.out.println("1. Actualizar ingreso de socio | 0. Cancelar");
            // se actualiza reserva a enCurso.
            if(sc.nextLine().equals("1")){
                res.setEnCurso(true);
                System.out.println("Actualizada correctamente.");
                return;
            }
            System.out.println("Cancelado.");
        }

    }

    // metodo para consultar reservas por fecha ingresada.
    public static void consultarPorFecha(){
        System.out.println("Ingrese fecha de inicio (dd-mm-aaaa)");
        LocalDate fechaInicio;
        try {
            fechaInicio = LocalDate.parse(sc.nextLine(), f);
        } catch (DateTimeParseException e){
            System.out.println("Error en formato de fecha. Intente nuevamente.");
            return;
        }

        System.out.println("Ingrese fecha de fin (dd-mm-aaaa)");
        LocalDate fechaFin;
        try {
            fechaFin = LocalDate.parse(sc.nextLine(), f);
        } catch (DateTimeParseException e){
            System.out.println("Error en formato de fecha. Intente nuevamente.");
            return;
        }

        List<Reserva> reservasEnPeriodo = reservas.stream()
                .filter(r -> !r.getFecha().isBefore(fechaInicio) && !r.getFecha().isAfter(fechaFin))
                .sorted(Comparator.comparing(Reserva::getFecha))
                .toList();
        if(!reservasEnPeriodo.isEmpty()){
            System.out.println("Reservas en período indicado");
            for(Reserva r : reservasEnPeriodo){
                System.out.println(r.resumen());
            }
        } else {
            System.out.println("Sin reservas en período indicado");
        }
    }
    //endregion

    //region pagos

    // inicialización de arraylist con pagos registrados.
    public static ArrayList<Pago> pagos = new ArrayList<>();

    // metodo para ver pagos registrados.
    public static void verPagos(){

        if(!pagos.isEmpty()){
            for(Pago p : pagos){
                System.out.println(p);
            }
        } else {
            System.out.println("Sin pagos registrados.");
        }
    }

    // metodo para registrar nuevo pago.
    public static void registrarPago(){

        Reserva reserva = null;
        // se busca reserva por id ingresado.
        System.out.println("Ingrese id de reserva");
        verReservas();
        while(reserva == null){
            try {
                int idReserva = Integer.parseInt(sc.nextLine());
                reserva = reservas.stream()
                        .filter(s -> s.getIdReserva() == idReserva)
                        .findFirst()
                        .orElse(null);
                if(reserva == null){
                    System.out.println("Reserva no encontrada. Intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Formato de id incorrecto.");
                return;
            }
        }
        System.out.println(reserva.resumen());

        // se verifica que haya tarifas aplicables si no es reserva prepaga.
        if(!reserva.isPrepago() && tarifas.isEmpty()){
            System.out.println("Sin tarifas vigentes. Registre tarifa antes de continuar");
            return;
        }

        // se registran extras.
        ArrayList<Extras> extras = new ArrayList<>();

        String registrarExtras = "1";
        while(registrarExtras.equals("1")){
            try {
                System.out.printf("%n¿Agregar extras? %n1. Sí | 2. No %n");
                registrarExtras = sc.nextLine();

                if(!registrarExtras.equals("1")){
                    continue;
                }

                System.out.println("Concepto: ");
                String nombre = sc.nextLine();

                System.out.println("Valor: ");
                double costo = Double.parseDouble(sc.nextLine());

                // se instancia extra.
                Extras e = new Extras(nombre, costo);
                System.out.println(e);

                // confirmación de extra.
                System.out.printf("%n" + e + "%n¿Agregar? %n1. Sí | 2. No %n");
                if(sc.nextLine().equals("1")){
                    extras.add(e);
                    System.out.println("Agregado correctamente");
                } else {
                    System.out.println("Cancelado.");
                }
            } catch (NumberFormatException e){
                System.out.println("Reintente");
                registrarExtras = "1";
            }
        }

        // cálculo de costo por conceptos extra.
        double costoExtras = 0;
        if(!extras.isEmpty()){
            System.out.printf("%nExtras: %n");
            for(Extras e : extras){
                // se suma el costo de cada extra.
                System.out.println(e);
                costoExtras += e.getCosto();
            }
            System.out.printf("%nTotal extras: $" + costoExtras + "%n");
        }

        // cálculo de costo por uso de cancha.
        System.out.printf("%nRecibo final%n");
        double costoFinal = 0;

        Tarifa tarifa = null;
        // si reserva es prepaga, se mantiene costo abonado por tarifa vigente en momento de reserva.
        if(reserva.isPrepago()){
            System.out.println("Reserva prepaga: $" + reserva.getSenia());
            tarifa = reserva.getTarifa();
        } else {
            // si reserva no es prepaga, se busca tarifa vigente al momento del pago.
            Reserva finalReserva = reserva;
            // se almacena tarifa que esté vigente, aplique para la cancha de la reserva y cumpla condición de fecha especial.
            tarifa = tarifas.stream()
                    .filter(t -> t.isVigente()
                            && t.getCancha() == finalReserva.getCancha()
                            && t.isTarifaEspecial() == finalReserva.isFechaEspecial())
                    .findFirst()
                    .orElse(null);

            if(tarifa != null){
                // se calcula costo de uso de cancha restando la seña abonada en reserva.
                System.out.println("Costo por tarifa vigente: $" + tarifa.getCosto());
                System.out.println("Seña: $" + reserva.getSenia());
                costoFinal += tarifa.getCosto() - reserva.getSenia();
                System.out.println("Total por uso de cancha: " + costoFinal);
            } else {
                // si no se encuentra tarifa, se permite registrar costo único para este pago.
                System.out.println("No se encuentra tarifa aplicable");
                System.out.println("1. Cancelar y registrar nueva tarifa | 2. Ingresar costo");
                if(sc.nextLine().equals("2")){
                    while(costoFinal == 0){
                        System.out.println("Ingrese costo por uso de cancha. Se restará seña.");
                        try {
                            // se ingresa costo de uso de cancha.
                            costoFinal += Double.parseDouble(sc.nextLine());
                            System.out.println("Costo por uso de cancha: $" + costoFinal);
                            // se resta seña.
                            costoFinal -= finalReserva.getSenia();
                            System.out.println("Seña: $" + finalReserva.getSenia());
                            // total final por uso de cancha.
                            System.out.println("Total por uso de cancha: $" + costoFinal);
                        } catch (NumberFormatException e) {
                            System.out.println("Monto incorrecto. Intente nuevamente.");
                        }
                    }
                } else {
                    System.out.println("Cancelado. Registre nueva tarifa y reintente.");
                    return;
                }
            }
        }

        // total a abonar.
        System.out.println("Total extras: $" + costoExtras);
        costoFinal += costoExtras;
        System.out.println("Total a abonar: $" + costoFinal);

        // registro de fecha de pago.
        System.out.printf("%n¿Abona ahora? %n1. Sí | 2. No %n");
        LocalDate fecha = null;
        boolean pagoRealizado = (sc.nextLine().equals("1"));

        if(pagoRealizado){
            fecha = LocalDate.now();
        }

        // instancia de pago registrado.
        Pago newPago =  new Pago(pagos.size() + 1, reserva, fecha, tarifa, extras, costoFinal, pagoRealizado);
        if(pagos.add(newPago)){
            System.out.println("Pago registrado correctamente");
            System.out.println(newPago);
            return;
        }

        // mensaje de error al registrar pago.
        System.out.println("Error en registro de pago. Reintente.");
    }

    // metodo para eliminar pagos.
    public static void eliminarPago(){
        System.out.println("Ingrese id de pago:");
        try {
            int idPago = Integer.parseInt(sc.nextLine());
            Pago pago = pagos.stream().filter(p -> p.getId() == idPago
                                                && p.isPagoRealizado())
                    .findFirst()
                    .orElse(null);

            if(pago == null){
                System.out.println("Pago no se encontró.");
                return;
            }

            System.out.println(pago);
            System.out.println("1. Eliminar | 0. Cancelar");
            if(sc.nextLine().equals("1")){
                pagos.remove(pago);
                System.out.println("Pago eliminado correctamente");
                return;
            }
            System.out.println("Cancelado.");

        } catch (NumberFormatException e) {
            System.out.println("Error en id ingresado. Intente nuevamente.");
        }
    }


    //endregion
}
