package thread.join;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlightQueryExample {
    private static List<String> company = Arrays.asList("CSA", "CEA", "HEA");

    public static void main(String[] args) {
        List<String> results = search("CSA", "HEA");
        results.forEach(System.out::println);
    }
    private static List<String> search(String origin, String destination){
        final List<String> result = new ArrayList<>();
        List<FightQueryTask> tasks = company.stream().map(f-> createSearchTask(f, origin, destination)).collect(Collectors.toList());
        tasks.forEach(Thread::start);
        tasks.forEach(f -> {
            try {
                f.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        tasks.stream().map(FightQueryTask::get).forEach(result::addAll);
        return result;
    }
    private static FightQueryTask createSearchTask(String flight, String origin, String dest){
        return  new FightQueryTask(flight, origin, dest);
    }
}
