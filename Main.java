import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = Integer.parseInt(scanner.nextLine()); // Читаем количество занятых мест
        Map<Integer, Set<Integer>> seatsMap = new HashMap<>();

        // Читаем занятые места
        for (int i = 0; i < N; i++) {
            String[] line = scanner.nextLine().split(" ");
            int row = Integer.parseInt(line[0]);
            int seat = Integer.parseInt(line[1]);

            // Добавляем место в соответствующий ряд
            seatsMap.putIfAbsent(row, new HashSet<>());
            seatsMap.get(row).add(seat);
        }

        int maxRow = -1;
        int minSeat = Integer.MAX_VALUE;

        // Обходим каждый ряд и проверяем для них соседние места
        for (Map.Entry<Integer, Set<Integer>> entry : seatsMap.entrySet()) {
            int row = entry.getKey();
            Set<Integer> seats = entry.getValue();
            List<Integer> sortedSeats = new ArrayList<>(seats);
            Collections.sort(sortedSeats);

            for (int j = 1; j < sortedSeats.size(); j++) {
                int currentSeat = sortedSeats.get(j);
                int previousSeat = sortedSeats.get(j - 1);

                // Проверяем соседние места
                if (currentSeat - previousSeat == 1) {
                    int leftOccupiedSeat = previousSeat - 1;
                    int rightOccupiedSeat = currentSeat + 1;

                    if (seats.contains(leftOccupiedSeat) && seats.contains(rightOccupiedSeat)) {
                        // Обновляем максимальный ряд и минимальное место
                        if (row > maxRow || (row == maxRow && previousSeat < minSeat)) {
                            maxRow = row;
                            minSeat = previousSeat;
                        }
                    }
                }
            }
        }

        // Выводим результат
        System.out.println(maxRow + " " + minSeat);
    }
}
