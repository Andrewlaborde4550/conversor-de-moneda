import java.util.Map;

public record Tasas(String result ,
                    String base_cade ,
                    Map<String, Double> conversion_rates) {

}
