import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        ConsultaTasa consulta = new ConsultaTasa();
        CalcularValor calcular = new CalcularValor();
        Tasas tasas = consulta.obtenerTasa("USD");
        List<String> historial = new ArrayList<>();

        int opcion = 0;
        double valor = 0.0 ;

        while (opcion != 3){
            System.out.println("""
                **********************************************************************
                
                Bienvenido al conversor de Moneda
                
                
                1. Convetir Moneda
                2. ver historial
                3. Salir
                
                Eliga una opcion valida:
                """);
            opcion=lectura.nextInt();
            lectura.nextLine();
            switch (opcion) {

                case 1:

                    System.out.println("Ingrese el monto a convertir: ");
                    valor = lectura.nextDouble();
                    lectura.nextLine();



                    System.out.println("Ingrese tasa de origen: ");
                    String tasaOrigen = lectura.nextLine().toUpperCase();


                    System.out.println("Ingrese tasa de destino: ");
                    String tasaDestino = lectura.nextLine().toUpperCase();

                    if (tasaOrigen.equals(tasaDestino)) {
                        System.out.println(" La moneda de origen y destino no pueden ser iguales.");
                        break;
                    }

                    Map<String, Double> mapaTasas = tasas.conversion_rates();
                    Double valorOrigen = mapaTasas.get(tasaOrigen);
                    Double valorDestino = mapaTasas.get(tasaDestino);

                    if (valorOrigen == null || valorDestino == null) {
                        System.out.println("Moneda no válida.");
                    } else {
                        double resultado = calcular.calcularMonto(valor, valorOrigen, valorDestino);
                        System.out.println("Su monto desde " + tasaOrigen + " hasta " + tasaDestino + " es de: " + resultado);
                        LocalDateTime ahora = LocalDateTime.now();
                        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

                        String fecha = ahora.format(formato);
                        String registro =  valor + " " + tasaOrigen + " --> " + tasaDestino + " " + resultado + " : " + fecha;
                        historial.add(registro);
                        System.out.println();

                    }


                    break;
                case 2:
                    System.out.println("\n Historial de conversiones:");
                    if (historial.isEmpty()) {
                        System.out.println("Aún no hay conversiones registradas.");
                    } else {
                        for (String registro : historial) {
                            System.out.println("- " + registro);

                        }
                        System.out.println();
                    }
                    break;
                case 3:
                    System.out.println("Gracias por utilizar nuestro convertidor");
                    break;

                default:
                    System.out.println("Opcion no valida");
            }
        }




    }
}
