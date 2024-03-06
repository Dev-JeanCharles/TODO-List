import java.util.Scanner;

public class Menu {

    public static void printMenuEmpty() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        int convert;
        do {
            System.out.println("Deseja realizar operações?");
            System.out.println("Digite [1] para sair ou [2] para realizar as operações:");
            String line = scanner.nextLine();
            convert = Integer.parseInt(line);
            Thread.sleep(1000);

            switch (convert) {
                case 1:
                    System.out.println("Até mais!");
                    break;
                case 2:
                    TaskManager.Operations();
                    break;
                case 3:
                    System.out.println("Você inseriu um valor não correspondente.");
                    Thread.sleep(1000);
            }
        } while (convert != 1);
    }
}
